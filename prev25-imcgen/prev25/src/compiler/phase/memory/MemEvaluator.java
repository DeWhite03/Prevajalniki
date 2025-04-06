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
        // Report.info("aaaaaaaaa");
        tracker = new Tracker();
        for (final AST.Node node : nodes) {
            node.accept(this, tracker);
        }

        return null;
    }

    @Override
    public Object visit(VarDefn varDefn, Tracker tracker) {
        Report.info(varDefn, "visiting: " + varDefn.name);
        var varType = SemAn.ofType.get(varDefn);
        Long size = (Long) varType.accept(sizeEval, tracker);
        if (tracker.depth > 0) {
            Memory.accesses.put(varDefn, new MEM.RelAccess(size, tracker.offset, tracker.depth));
        } else {
            Memory.accesses.put(varDefn, new MEM.AbsAccess(size, new MEM.Label(varDefn.name)));
        }
        varDefn.type.accept(this, tracker);
        return null;
    }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Tracker tracker) {
        var label = new MEM.Label(defFunDefn.name);
        // var size = Memory.accesses.get(defFunDefn).size;
        var curDepth = tracker.depth + 1;
        var curSize = tracker.size;
        var curOffset = tracker.offset;
        tracker.depth += 1;
        // defFunDefn.pars.accept(this, tracker);
        long argSize = 0;
        for (AST.ParDefn par : defFunDefn.pars) {
            var parType = SemAn.ofType.get(par);
            argSize += parType.accept(sizeEval, null);
        }

        MEM.Frame frame = new MEM.Frame(label, curDepth, 0, 0, 0);
        Memory.frames.put(defFunDefn, frame);
        defFunDefn.stmts.accept(this, tracker);
        defFunDefn.pars.accept(this, tracker);
        return frame;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Tracker tracker) {
        var parType = SemAn.ofType.get(parDefn);
        long parSize = parType.accept(sizeEval, null) + 1l;

        if (parType instanceof TYP.RecType) {
            // Tracker n = new Tracker(tracker.depth, parSize, 0);
            tracker.setVals(tracker.depth, parSize, 0);
            parDefn.type.accept(this, tracker);
            tracker.offset = tracker.size;
        } else {
            tracker.offset += parSize;
            // naprej
            parDefn.type.accept(this, tracker);
        }

        parDefn.type.accept(this, tracker);

        var t = new MEM.RelAccess(parSize, tracker.offset, tracker.depth);
        return Memory.accesses.put(parDefn, t);
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Tracker tracker) {
        TYP.Type b = SemAn.ofType.get(compDefn);
        long size = b.accept(sizeEval, null);

        var access = new MEM.RelAccess(size, tracker.offset, -1);
        if (b instanceof TYP.RecType) {
            // Tracker n = new Tracker(tracker.depth, size, 0);
            tracker.setVals(tracker.depth, size, 0);
            compDefn.type.accept(this, tracker);
            tracker.offset = tracker.size;
        } else {
            tracker.offset += size;
            //  
            compDefn.type.accept(this, tracker);
        }

        return Memory.accesses.put(compDefn, access);
    }

    @Override
    public Object visit(LetStmt letStmt, Tracker tracker) {
        Report.info("depth" + tracker.depth);
        tracker.depth += 1;
        Report.info("depth" + tracker.depth);

        if ((letStmt.defns != null) || (!compiler.Compiler.devMode()))
            letStmt.defns.accept(this, tracker);
        if ((letStmt.stmts != null) || (!compiler.Compiler.devMode()))
            letStmt.stmts.accept(this, tracker);
        return null;
    }

    @Override
    public Object visit(AST.AtomExpr atomExpr, Tracker tracker) {
        switch (atomExpr.type) {
            case STR:
                break;
            default:
                break;
        }
        return null;
    }
}