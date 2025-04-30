package compiler.phase.memory;

import java.nio.ReadOnlyBufferException;
import java.util.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.LetStmt;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.abstr.AST.ParDefn;
import compiler.phase.abstr.AST.VarDefn;
import compiler.phase.seman.*;
import compiler.phase.seman.NameResolver.*;
import compiler.phase.memory.*;
import compiler.phase.memory.MEM.RelAccess;
import compiler.common.report.*;

/**
 * Computing memory layout: stack frames and variable accesses.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class MemEvaluator implements AST.FullVisitor<Object, Tracker> {
    public static SizeEvaluator sizeEval = new SizeEvaluator();

    @Override
    public TYP.Type visit(Nodes<? extends AST.Node> nodes, Tracker tracker) {
        if (tracker == null) {
            tracker = new Tracker();
        }
        for (final AST.Node node : nodes) {
            node.accept(this, tracker);
        }

        return null;
    }

    @Override
    public Object visit(AST.VarDefn varDefn, Tracker tracker) {
        varDefn.type.accept(this, tracker);

        long size = SemAn.ofType.get(varDefn).accept(sizeEval, null);
        long correctedOffset = size;

        if (correctedOffset % 8 != 0 && !tracker.isInFunctionParam) {
            correctedOffset = ((correctedOffset / 8) * 8) + 8;
        }

        long prefix = tracker.isInFunctionParam ? 1 : -1;

        if (tracker.depth < 0) {
            return Memory.accesses.put(
                    varDefn,
                    new MEM.AbsAccess(size, new MEM.Label(varDefn.name)));
        }

        tracker.offset += correctedOffset * prefix;

        return Memory.accesses.put(
                varDefn,
                new MEM.RelAccess(size, tracker.offset, tracker.depth));
    }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Tracker tracker) {
        MEM.Label label;
        boolean isNewFunction = false;
    
        var oldDepth = tracker.depth;
        var oldOffset = tracker.offset;
        var oldSize = tracker.size;
    
        if (tracker.depth < 0) {
            tracker.depth = 0;
        }
    
        if (tracker.isInFunction) {
            label = new MEM.Label();
        } else {
            tracker.isInFunction = true;
            isNewFunction = true;
            label = new MEM.Label(defFunDefn.name);
        }
    
        var sizeBeforeParams = tracker.size;
        tracker.offset = 0;
        tracker.isInFunctionParam = true;
        tracker.size = 0;
    
        // Process parameters
        defFunDefn.pars.accept(this, tracker);
    
        tracker.isInFunctionParam = false;
    
        if (tracker.size % 8 != 0) {
            tracker.size = ((tracker.size / 8) * 8) + 8;
        }
    
        tracker.offset = 0;
        tracker.depth++;
    
        // Process function body statements
        defFunDefn.stmts.accept(this, tracker);
    
        long localVarsSize = sizeBeforeParams - tracker.offset;
        long argsSize = tracker.size;
        long totalFunctionSize = localVarsSize + argsSize + 2 * 8;
    
        MEM.Frame frame = new MEM.Frame(
            label,
            tracker.depth - 1,
            localVarsSize,
            argsSize,
            totalFunctionSize
        );
    
        if (isNewFunction) {
            tracker.isInFunction = false;
        }
    
        tracker.offset = oldOffset;
        tracker.depth = oldDepth;
        tracker.size = oldSize;
    
        return Memory.frames.put(defFunDefn, frame);
    }

    @Override
    public Object visit(AST.ExtFunDefn extFunDefn, Tracker tracker) {
        var label = new MEM.Label(extFunDefn.name);
        var oldDepth = tracker.depth;
        tracker.depth += 2;
        var oldSize = tracker.size;
        var curOffset = tracker.offset;
        tracker.isInFunctionParam = true;
        tracker.offset = 0;
        extFunDefn.pars.accept(this, tracker);
        tracker.depth = oldDepth;
        tracker.isInFunctionParam = false;
        tracker.offset = curOffset;
        tracker.size = oldSize;
        return label;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Tracker tracker) {
        var parType = SemAn.ofType.get(parDefn);
        var size = parType.accept(sizeEval, null);
        var correctedOffset = size;
        if (correctedOffset % 8 != 0)
            correctedOffset = (correctedOffset / 8) * 8 + 8;

        long prefix = -1;
        if (tracker.isInFunctionParam)
            prefix = 1;

        if (parType instanceof TYP.RecType) {
            Tracker t = new Tracker(tracker.depth, size, tracker.offset + correctedOffset * prefix);
            parDefn.type.accept(this, t);
            tracker.offset = t.size;
        } else {
            var t = new Tracker(tracker);
            t.size = 0;
            t.offset += correctedOffset;
            parDefn.type.accept(this, t);
            tracker.offset = t.offset;
        }

        tracker.size += 1;
        return Memory.accesses.put(parDefn, new MEM.RelAccess(size, tracker.offset, tracker.depth));
    }

    @Override
    public TYP.Type visit(AST.StrType strType, Tracker tracker) {
        var t = tracker.offset;
        strType.comps.accept(this, null);
        tracker.offset = t;
        return null;
    }

    @Override
    public TYP.Type visit(AST.UniType uniType, Tracker tracker) {
        var t = tracker.offset;
        tracker.offset = 0;
        for (AST.CompDefn comp : uniType.comps) {
            comp.accept(this, tracker);
            tracker.offset = 0;
        }
        tracker.offset = t;
        return null;
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Tracker tracker) {
        TYP.Type compType = SemAn.ofType.get(compDefn);
        long size = compType.accept(sizeEval, null);
        long correctedOffset = size;
        if (correctedOffset % 8 != 0)
            correctedOffset = (correctedOffset / 8) * 8 + 8;

        if (compType instanceof TYP.RecType) {
            Tracker t = new Tracker(tracker.depth, size, 0);
            compDefn.type.accept(this, t);
        } else {
            compDefn.type.accept(this, tracker);
        }
        var access = new MEM.RelAccess(size, tracker.offset, -1);
        tracker.offset += correctedOffset;

        return Memory.accesses.put(compDefn, access);
    }

    @Override
    public Object visit(AST.TypDefn typDefn, Tracker tracker) {
        var typType = SemAn.isType.get(typDefn);

        long prefix = -1;
        if (tracker.isInFunctionParam) {
            prefix = 1;
        }

        if (typType instanceof TYP.RecType) {
            var tempOffset = tracker.offset;
            tracker.offset = 0;

            typDefn.type.accept(this, tracker);

            tracker.offset = tempOffset;
            var t = new Tracker();
            t.depth = tracker.depth;

            typDefn.accept(this, t);

            var size = SemAn.ofType.get(typDefn).accept(sizeEval, null);
            if (t.depth >= 0) {
                // var access = new MEM.RelAccess(size, t.size, t.depth);
                return new MEM.RelAccess(size, t.size, t.depth);
                //return Memory.accesses.put(typDefn, access);
                // return null;
            }
            return null;
            //return Memory.accesses.put(typDefn, new MEM.AbsAccess(size, new MEM.Label(typDefn.name)));
        }

        typDefn.type.accept(this, tracker);

        if (tracker.depth >= 0) {
            var size = typType.accept(sizeEval, null);
            // var access = new MEM.RelAccess(-1, tracker.offset+size*prefix, tracker.depth);
            return new MEM.RelAccess(-1, tracker.offset + size * prefix, tracker.depth);
            //Memory.accesses.put(typDefn, access);
            // return null;
        }
        //var size = SemAn.ofType.get(typDefn).accept(sizeEval, null);
        // var access = new MEM.AbsAccess(-1, new MEM.Label(typDefn.name));
        return new MEM.AbsAccess(-1, new MEM.Label(typDefn.name));
        //Memory.accesses.put(typDefn, access);
        // return null;
    }

    @Override
    public Object visit(LetStmt letStmt, Tracker tracker) {
        // Report.info("depth" + tracker.depth);
        // tracker.depth += 1;
        // Report.info("depth" + tracker.depth);

        if ((letStmt.defns != null) || (!compiler.Compiler.devMode()))
            letStmt.defns.accept(this, tracker);
        if ((letStmt.stmts != null) || (!compiler.Compiler.devMode()))
            letStmt.stmts.accept(this, tracker);
        return null;
    }

    public static String fixString(String s) {
        String newString = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\') {
                i++;
                if (s.charAt(i) == '"') {
                    newString += '"';
                } else if (s.charAt(i) == '0' && s.charAt(i + 1) == 'x') {
                    var subs = s.substring(i + 2, i + 4);
                    // Report.info(subs);
                    newString += (char) Integer.parseInt(subs, 16);
                    i += 4;
                }
            } else {
                newString += s.charAt(i);
            }
        }
        // Report.info("new string: '" + newString + "'");
        return newString;
    }

    @Override
    public Object visit(AST.AtomExpr atomExpr, Tracker tracker) {
        switch (atomExpr.type) {
            case STR:
                // Report.info("String size: " + size);
                String str = atomExpr.value.substring(1, atomExpr.value.length() - 1);
                // str = '"' + fixString(str) + '\0'  + '"';
                // Report.info("String value: " + str);
                str = fixString(str);
                var lbl = new MEM.Label();
                var access = new MEM.AbsAccess(str.length()+1, lbl, str);
                Memory.strings.put(atomExpr, access);
                break;
            default:
                break;
        }
        return null;
    }
}