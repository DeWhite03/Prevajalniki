package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.memory.*;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.Nodes;
import compiler.phase.imclin.LIN.*;
import compiler.phase.imcgen.*;
import compiler.phase.seman.*;

import java.util.*;

public class ChunkGenerator implements AST.FullVisitor<IMC.Expr, ChunkTracker> {

    public static HashMap<String, IMC.TEMP> nameTempMap = new HashMap<>();

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
        var imc = sfxExpr.subExpr.accept(this, arg);
        // var imc = ImcGen.expr.get(sfxExpr);
        arg.add(new IMC.MOVE(l, imc));
        return l;
    }

    @Override
    public IMC.Expr visit(AST.PfxExpr pfxExpr, ChunkTracker arg) {
        var l = new IMC.TEMP(new MEM.Temp());
        var imc = pfxExpr.subExpr.accept(this, arg);
        arg.add(new IMC.MOVE(l, imc));
        return l;
    }

    // public boolean isCallExpr(AST.Node node) {
    //     if (node instanceof AST.CallExpr) return true;
    //     if (node instanceof AST.BinExpr expr) return isCallExpr(expr.fstExpr) || isCallExpr(expr.sndExpr);
    //     if (node instanceof AST.CastExpr expr) return isCallExpr(expr.expr);
    //     if (node instanceof AST.SfxExpr expr) return isCallExpr(expr.subExpr);
    //     if (node instanceof AST.PfxExpr expr) return isCallExpr(expr.subExpr);
    //     return false;
    // }

    // public boolean isCallExprOneLevel(AST.Node node) {
    //     return node instanceof AST.CallExpr;
    // }

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

    // public Vector<IMC.Expr> checkAndFixArgumentCall(AST.Nodes<? extends AST.Node> nodes, ChunkTracker arg, int depth) {
    //     Vector<IMC.Expr> args = new Vector<>();

    //     args.add(new IMC.MEM8(arg.SL));

    //     for (AST.Node node : nodes) {
    //         IMC.Expr expr = ImcGen.expr.get(node);
    //         if (expr instanceof IMC.CALL call) {
    //             Vector<IMC.Expr> fixedArgs = checkAndFixArgumentCallRec(call.args, arg, depth + 1);
    //             IMC.Expr temp = new IMC.TEMP(new MEM.Temp());
    //             arg.add(new IMC.MOVE(temp, new IMC.CALL(call.addr, call.offs, fixedArgs)));
    //             args.add(temp);
    //         } else if (expr instanceof IMC.CONST || expr instanceof IMC.TEMP || expr instanceof IMC.MEM8) {
    //             args.add(expr);
    //         } else {
    //             IMC.Expr temp = new IMC.TEMP(new MEM.Temp());
    //             arg.add(new IMC.MOVE(temp, expr));
    //             args.add(temp);
    //         }
    //     }

    //     // Report.info("Depth of call: " + depth);
    //     return args;
    // }

    // public Vector<IMC.Expr> checkAndFixArgumentCallRec(Vector<IMC.Expr> args, ChunkTracker arg, int depth) {
    //     Vector<IMC.Expr> fixedArgs = new Vector<>();

    //     for (IMC.Expr expr : args) {
    //         if (expr instanceof IMC.CALL call) {
    //             Vector<IMC.Expr> nested = checkAndFixArgumentCallRec(call.args, arg, depth + 1);
    //             IMC.Expr temp = new IMC.TEMP(new MEM.Temp());
    //             arg.add(new IMC.MOVE(temp, new IMC.CALL(call.addr, call.offs, nested)));
    //             fixedArgs.add(temp);
    //         } else if (expr instanceof IMC.CONST || expr instanceof IMC.TEMP) {
    //             fixedArgs.add(expr);
    //         } else {
    //             IMC.Expr temp = new IMC.TEMP(new MEM.Temp());
    //             arg.add(new IMC.MOVE(temp, expr));
    //             fixedArgs.add(temp);
    //         }
    //     }

    //     // Report.info("Depth of call: " + depth);
    //     return fixedArgs;
    // }

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
        // Report.info("l: " +  l.toString());
        // Report.info("r: " +  r.toString());
        arg.add(new IMC.MOVE(l, r));
        return l;
    }

    public IMC.Expr visit(AST.NameExpr nameExpr, ChunkTracker arg) {
        if (nameTempMap.containsKey(nameExpr.name)) {
            return nameTempMap.get(nameExpr.name);
        }
        
        var imc = ImcGen.expr.get(nameExpr);
        var type = SemAn.ofType.get(nameExpr);
        if (type instanceof TYP.PtrType) {
            imc = new IMC.MEM8(imc);
        }
        
        var l = new IMC.TEMP(new MEM.Temp());
        arg.add(new IMC.MOVE(l, imc));
        nameTempMap.put(nameExpr.name, l);
        return l;
    }

    // @Override
    // public Object visit(AST.LetStmt letStmt, ChunkTracker arg) {
    //     ChunkTracker innerContext = new ChunkTracker();
    //     innerContext.SL = arg.SL;
    //     letStmt.defns.accept(this, innerContext);
    //     letStmt.stmts.accept(this, innerContext);

    //     arg.addAll(innerContext.getVec());
    //     return null;
    // }

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

    // @Override
    // public Object visit(AST.ExprStmt exprStmt, ChunkTracker arg) {
    //     IMC.ESTMT estmt = (IMC.ESTMT) ImcGen.stmt.get(exprStmt);

    //     if (estmt.expr instanceof IMC.CALL call) {
    //         AST.CallExpr callExpr = (AST.CallExpr) exprStmt.expr;
    //         // Report.info(arg.SL.toString());
    //         Vector<IMC.Expr> args = checkAndFixArgumentCall(callExpr.argExprs, arg, 0);

    //         estmt = new IMC.ESTMT(new IMC.CALL(call.addr, call.offs, args));
    //     }

    //     // arg.add(estmt);
    //     return null;
    // }

    // public Vector<IMC.Stmt> removeStmts(IMC.STMTS stmts) {
    //     Vector<IMC.Stmt> flat = new Vector<>();
    //     for (IMC.Stmt stmt : stmts.stmts) {
    //         if (stmt instanceof IMC.STMTS nested) {
    //             flat.addAll(removeStmts(nested));
    //         } else {
    //             flat.add(stmt);
    //         }
    //     }
    //     return flat;
    // }

    // @Override
    // public Object visit(Object o, Object o2) {
    //     throw new Report.Error("Not yet implemented for " + o.toString());
    // }
}
