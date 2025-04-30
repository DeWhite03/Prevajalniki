package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.abstr.AST.PtrType;
import compiler.phase.imclin.LIN.*;
import compiler.phase.imcgen.*;
import compiler.phase.seman.*;

import java.util.*;

public class ChunkGenerator implements AST.FullVisitor<IMC.Expr, ChunkTracker> {


    @Override
    public IMC.Expr visit(AST.Nodes<? extends AST.Node> nodes, ChunkTracker arg) {
        for (final AST.Node node : nodes) {
            if (node instanceof AST.DefFunDefn) {
                arg = new ChunkTracker();
            }
            node.accept(this, arg);
        }
        return null;
    }

    @Override
    public IMC.Expr visit(AST.SfxExpr sfxExpr, ChunkTracker arg) {
        var l = new IMC.TEMP(new MEM.Temp());
        var type = SemAn.ofType.get(sfxExpr);
        // Report.info(type.toString());
        var imc = sfxExpr.subExpr.accept(this, arg);
        if (type instanceof TYP.CharType || type instanceof TYP.BoolType) {
            imc = new IMC.MEM1(imc);
        } else {
            imc = new IMC.MEM8(imc);
        }
        arg.add(new IMC.MOVE(l, imc));
        return l;
    }

    @Override
    public IMC.Expr visit(AST.PfxExpr pfxExpr, ChunkTracker arg) {
        var l = new IMC.TEMP(new MEM.Temp());
        var imc = pfxExpr.subExpr.accept(this, arg);
        if (pfxExpr.oper == AST.PfxExpr.Oper.PTR) {
            imc = new IMC.MEM8(imc);
        }
        // if (SemAn.ofType.get(pfxExpr) instanceof TYP.PtrType) {
        //     imc = new IMC.MEM8(imc);
        // }
        arg.add(new IMC.MOVE(l, imc));
        return l;
    }

    @Override
    public IMC.Expr visit(AST.BinExpr binExpr, ChunkTracker arg) {
        var t = (IMC.BINOP) ImcGen.expr.get(binExpr);
        var lbl1 = (IMC.TEMP) binExpr.fstExpr.accept(this, arg);
        var lbl2 = (IMC.TEMP) binExpr.sndExpr.accept(this, arg);
        var l = new IMC.TEMP(new MEM.Temp());
        arg.add(new IMC.MOVE(l, new IMC.BINOP(t.oper, lbl1, lbl2)));
        if (lbl1 == null) {
            Report.info(binExpr, "lbl1 is null");
        }
        return l;
    }

    @Override
    public IMC.Expr visit(AST.DefFunDefn defFunDefn, ChunkTracker arg) {
        MEM.Frame frame = Memory.frames.get(defFunDefn);
        MEM.Label entry = ImcGen.entryLabel.get(defFunDefn);
        MEM.Label exit = ImcGen.exitLabel.get(defFunDefn);
        arg.funEntry = entry;
        arg.funExit = exit;



        
        var functionContext = new ChunkTracker();
        functionContext.add(new IMC.LABEL(entry));
        functionContext.SL = new IMC.TEMP(frame.FP);
        functionContext.RV = new IMC.TEMP(frame.RV);
        functionContext.funExit = exit;
        // functionContext.add(new IMC.MOVE(functionContext.RV, new IMC.TEMP(frame.RV)));
        
        defFunDefn.stmts.accept(this, functionContext);

        functionContext.add(new IMC.JUMP(new IMC.NAME(exit)));
        if (frame.label.name.charAt(0) == 'L') {
            arg.addAll(functionContext.getVec());
        } else {
            LIN.CodeChunk codeChunk = new CodeChunk(frame, functionContext.getVec(), entry, exit);
            ImcLin.addCodeChunk(codeChunk);
        }

        return null;
    }

    @Override
    public IMC.Expr visit(AST.ReturnStmt returnStmt, ChunkTracker arg) {
        var t = returnStmt.retExpr.accept(this, arg);
        arg.add(new IMC.MOVE(arg.RV, t));
        arg.add(new IMC.JUMP(new IMC.NAME(arg.funExit)));
        return arg.RV;
    }

    @Override
    public IMC.Expr visit(AST.AtomExpr atomExpr, ChunkTracker arg) {
        var imc = ImcGen.expr.get(atomExpr);
        var newTemp = new IMC.TEMP(new MEM.Temp());
        if (SemAn.ofType.get(atomExpr) instanceof TYP.PtrType ptr) {
            if (!(ptr.baseType instanceof TYP.CharType)) {
                return newTemp;
            }
            var mem = Memory.strings.get(atomExpr);
            LIN.DataChunk dc = new DataChunk(mem);
            ImcLin.addDataChunk(dc);
        }
        
        arg.add(new IMC.MOVE(newTemp, imc));
        return newTemp;
    }

    @Override
    public IMC.Expr visit(AST.CallExpr callExpr, ChunkTracker arg) {
        var expr = (IMC.CALL) ImcGen.expr.get(callExpr);

        
        var l = new IMC.TEMP(new MEM.Temp());
        Vector<IMC.Expr> args = new Vector<>();
        Vector<Long> offsets = new Vector<>();


        args.add(new IMC.MEM8(arg.SL));
        offsets.add(8L);
        for (AST.Expr argExpr : callExpr.argExprs) {
            args.add(argExpr.accept(this, arg));
            offsets.add(8L);
        }

        var t = new IMC.MOVE(l, new IMC.CALL(expr.addr, offsets, args));
        arg.add(t);
        return l;
    }

    @Override
    public IMC.Expr visit(AST.AssignStmt assignStmt, ChunkTracker arg) {
        var l = assignStmt.dstExpr.accept(this, arg);
        var r = assignStmt.srcExpr.accept(this, arg);

        Report.info("l: " + l.toString());
        Report.info("r: " + assignStmt.dstExpr.toString());
        if (assignStmt.dstExpr instanceof AST.PfxExpr pfxExpr) {
            Report.info(pfxExpr.toString());
            if (pfxExpr.oper == AST.PfxExpr.Oper.PTR) {
                var type = SemAn.ofType.get(pfxExpr);
                if (type instanceof TYP.PtrType ptr) {
                        Report.info(ptr.toString());
                    if (ptr.baseType instanceof TYP.CharType) {
                        r = new IMC.MEM1(r);
                    } else {
                        r = new IMC.MEM8(r);
                    }
                }
            }
        }
        // Report.info("l: " +  l.toString());
        // Report.info("r: " +  r.toString());
        arg.add(new IMC.MOVE(l, r));
        return l;
    }

    public IMC.Expr visit(AST.NameExpr nameExpr, ChunkTracker arg) {
        if (arg.nameTempMap.containsKey(nameExpr.name)) {
            return arg.nameTempMap.get(nameExpr.name);
        }
        
        var imc = ImcGen.expr.get(nameExpr);
        if (imc instanceof IMC.MEM8 mem) {
            imc = mem.addr;
            if (!(imc instanceof IMC.NAME)) {
                imc = new IMC.MEM8(imc);
            }
        }
        // Report.info("name: " + nameExpr.name);
        var l = new IMC.TEMP(new MEM.Temp());
        arg.add(new IMC.MOVE(l, imc));
        arg.nameTempMap.put(nameExpr.name, l);
        return l;
    }


    @Override
    public IMC.Expr visit(AST.IfThenStmt ifThenStmt, ChunkTracker arg) {
        var condition = ifThenStmt.condExpr.accept(this, arg);
        var trueLabel = new MEM.Label();
        var falseLabel = new MEM.Label();
        var endLabel = new MEM.Label();
        arg.add(new IMC.CJUMP(condition, new IMC.NAME(trueLabel), new IMC.NAME(falseLabel)));
        arg.add(new IMC.LABEL(falseLabel));
        arg.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        arg.add(new IMC.LABEL(trueLabel));
        
        ifThenStmt.thenStmt.accept(this, arg);

        arg.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        arg.add(new IMC.LABEL(endLabel));
        return null;
    }

    @Override
    public IMC.Expr visit(AST.WhileStmt whileStmt, ChunkTracker arg) {
        var conditionLabel = new MEM.Label();
        var trueLabel = new MEM.Label();
        var endLabel = new MEM.Label();
        arg.add(new IMC.LABEL(conditionLabel));
        var condition = whileStmt.condExpr.accept(this, arg);
        arg.add(new IMC.CJUMP(condition, new IMC.NAME(trueLabel), new IMC.NAME(endLabel)));
        arg.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        arg.add(new IMC.LABEL(trueLabel));
        whileStmt.stmts.accept(this, arg);
        arg.add(new IMC.JUMP(new IMC.NAME(conditionLabel)));
        arg.add(new IMC.LABEL(endLabel));
        return null;
    }

    @Override
    public IMC.Expr visit(AST.IfThenElseStmt ifThenElseStmt, ChunkTracker arg) {
        var condition = ifThenElseStmt.condExpr.accept(this, arg);
        var trueLabel = new MEM.Label();
        var falseLabel = new MEM.Label();
        var endLabel = new MEM.Label();
        arg.add(new IMC.CJUMP(condition, new IMC.NAME(trueLabel), new IMC.NAME(falseLabel)));
        arg.add(new IMC.LABEL(falseLabel));

        ifThenElseStmt.elseStmt.accept(this, arg);
        arg.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        arg.add(new IMC.LABEL(trueLabel));

        ifThenElseStmt.thenStmt.accept(this, arg);

        arg.add(new IMC.JUMP(new IMC.NAME(endLabel)));
        arg.add(new IMC.LABEL(endLabel));
        return null;
    }
    
    @Override
    public IMC.Expr visit(AST.CastExpr castExpr, ChunkTracker arg) {
        var imc = castExpr.expr.accept(this, arg);
        var l = new IMC.TEMP(new MEM.Temp());
        arg.add(new IMC.MOVE(l, imc));
        return l;
    }

    @Override
    public IMC.Expr visit(AST.SizeExpr sizeExpr, ChunkTracker arg) {
        var imc = ImcGen.expr.get(sizeExpr);
        var l = new IMC.TEMP(new MEM.Temp());
        arg.add(new IMC.MOVE(l, imc));
        return l;
    }
}
