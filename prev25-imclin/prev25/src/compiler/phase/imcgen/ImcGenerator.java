package compiler.phase.imcgen;

import java.util.Vector;

import compiler.common.report.Report;
import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.*;
import compiler.phase.memory.*;
import compiler.phase.seman.SemAn;
import compiler.phase.seman.TYP;

/**
 * Intermediate code generator.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class ImcGenerator implements AST.FullVisitor<Object, ImcTracker> {
    public static SizeEvaluator sizeEval = new SizeEvaluator();

    // ----- Trees -----

    @Override
    public IMC.Expr visit(Nodes<? extends Node> nodes, ImcTracker arg) {
        if (arg == null) {
            arg = new ImcTracker();
        }
        for (final Node node : nodes)
            if ((node != null) || (!compiler.Compiler.devMode()))
                node.accept(this, arg);
        return null;
    }

    // // ----- Definitions -----
    @Override
    public IMC.Expr visit(DefFunDefn defFunDefn, ImcTracker arg) {
        if (arg.depth > 1) {

            ImcGen.entryLabel.put(defFunDefn, new MEM.Label(defFunDefn.name));
            ImcGen.exitLabel.put(defFunDefn, new MEM.Label());
        } else {
            ImcGen.entryLabel.put(defFunDefn, new MEM.Label());
            ImcGen.exitLabel.put(defFunDefn, new MEM.Label());
        }
        arg.depth++;
        var newArg = new ImcTracker(defFunDefn);
        defFunDefn.stmts.accept(this, newArg);
        return null;
    }

    // @Override
    // public IMC.Expr visit(ParDefn parDefn, ImcTracker arg) {
    // if ((parDefn.type != null) || (!compiler.Compiler.devMode()))
    // parDefn.type.accept(this, arg);
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(CompDefn compDefn, ImcTracker arg) {
    // if ((compDefn.type != null) || (!compiler.Compiler.devMode()))
    // compDefn.type.accept(this, arg);
    // return null;
    // }

    // // ----- Statements -----

    // @Override
    // public IMC.Expr visit(LetStmt letStmt, ImcTracker arg) {
    // if ((letStmt.defns != null) || (!compiler.Compiler.devMode()))
    // letStmt.defns.accept(this, arg);
    // if ((letStmt.stmts != null) || (!compiler.Compiler.devMode()))
    // letStmt.stmts.accept(this, arg);
    // return null;
    // }

    @Override
    public IMC.Stmt visit(AssignStmt assignStmt, ImcTracker arg) {
        return ImcGen.stmt.put(assignStmt, new IMC.MOVE((IMC.Expr) assignStmt.dstExpr.accept(this, arg),
                (IMC.Expr) assignStmt.srcExpr.accept(this, arg)));
    }

    @Override
    public IMC.Stmt visit(AST.ExprStmt exprStmt, ImcTracker arg) {
        return ImcGen.stmt.put(exprStmt, new IMC.ESTMT((IMC.Expr)exprStmt.expr.accept(this, arg)));
    }

    // @Override
    // public IMC.Stmt visit(IfThenStmt ifThenStmt, ImcTracker arg) {
    //     ifThenStmt.condExpr.accept(this, arg);
    //     ifThenStmt.thenStmt.accept(this, arg);
    //     IMC.Stmt ifThenElseStmt = new IMC.STMTS(new Vector<IMC.Stmt>());
    //     ifThenElseStmt.add(new IMC.JUMP(new IMC.NAME(ifThenStmt.thenStmt.label)));
    //     return ImcGen.stmt.put(ifThenStmt, new IMC.CJUMP((IMC.Expr)
    //     ifThenStmt.condExpr.accept(this, arg), new IMC.LABEL(new MEM.Label()), new
    //     IMC.LABEL(new MEM.Label())));
    // }

    @Override
    public IMC.Stmt visit(IfThenElseStmt ifThenElseStmt, ImcTracker arg) {
        var lblIf = new MEM.Label();
        var lblElse = new MEM.Label();
        var lblEnd = new MEM.Label();
        var ifThenElseIMC = new Vector<IMC.Stmt>();
        var ifIMC = new Vector<IMC.Stmt>();
        var elseIMC = new Vector<IMC.Stmt>();
        Report.info("1");
        // IMC.Expr condExpr = (IMC.Expr) ifThenElseStmt.condExpr.accept(this, arg);
        
        ifThenElseIMC.add(new IMC.CJUMP((IMC.Expr) ifThenElseStmt.condExpr.accept(this, arg), new IMC.NAME(lblIf), new IMC.NAME(lblElse)));
        ifThenElseIMC.add(new IMC.LABEL(lblIf));
        Report.info("2");

        // ifThenElseStmt.thenStmt.forEach(stmt -> {
        //     ifIMC.add((IMC.Stmt) stmt.accept(this, arg));
        // });

        for (var n : ifThenElseStmt.thenStmt){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            ifIMC.addLast(t);
        }
        ifThenElseIMC.add(new IMC.STMTS(elseIMC));
        Report.info("3");


        ifThenElseIMC.add(new IMC.JUMP(new IMC.NAME(lblEnd)));
        ifThenElseIMC.add(new IMC.LABEL(lblElse));
        
        // ifThenElseStmt.elseStmt.forEach(stmt -> {
        //     elseIMC.add((IMC.Stmt) stmt.accept(this, arg));
        // });
        for (var n : ifThenElseStmt.elseStmt){
            IMC.Stmt t =(IMC.Stmt) n.accept(this, arg);
            elseIMC.addLast(t);
        }
        Report.info("5");
        ifThenElseIMC.add(new IMC.ESTMT((IMC.Expr) ifThenElseStmt.elseStmt.accept(this, arg)));
        ifThenElseIMC.add(new IMC.JUMP(new IMC.NAME(lblEnd)));

        // var a = new IMC.STMTS(ifThenElseIMC);

        Report.info("6");
        if (ifThenElseIMC == null) {
            Report.info("ifThenElseIMC");
        }
        if (ifIMC == null) {
            Report.info("ifIMC");
        }
        if (elseIMC == null) {
            Report.info("elseIMC");
        }
        ifThenElseIMC.forEach(a -> {
            if (a == null) {
                Report.info("WADAHEL");
            }
        } );
        return ImcGen.stmt.put(ifThenElseStmt, new IMC.STMTS(ifThenElseIMC));
    }

    @Override
    public IMC.Stmt visit(ReturnStmt returnStmt, ImcTracker arg) {
        var frame = Memory.frames.get(arg.funDefn);
        var epilog = ImcGen.exitLabel.get(arg.funDefn);
        var stmts = new Vector<IMC.Stmt>();
        stmts.add(new IMC.MOVE(new IMC.TEMP(frame.RV), (IMC.Expr) returnStmt.retExpr.accept(this, arg)));
        stmts.add(new IMC.JUMP(new IMC.NAME(epilog)));

        return ImcGen.stmt.put(returnStmt, new IMC.STMTS(stmts));
    }

    // @Override
    // public IMC.Expr visit(WhileStmt whileStmt, ImcTracker arg) {
    //     whileStmt.condExpr.accept(this, arg);

    //     return null;
    // }

    // // ----- Types -----

    // @Override
    // public IMC.Expr visit(AtomType atomType, ImcTracker arg) {
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(ArrType arrType, ImcTracker arg) {
    // if ((arrType.elemType != null) || (!compiler.Compiler.devMode()))
    // arrType.elemType.accept(this, arg);
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(PtrType ptrType, ImcTracker arg) {
    // if ((ptrType.baseType != null) || (!compiler.Compiler.devMode()))
    // ptrType.baseType.accept(this, arg);
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(StrType strType, ImcTracker arg) {
    // if ((strType.comps != null) || (!compiler.Compiler.devMode()))
    // strType.comps.accept(this, arg);
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(UniType uniType, ImcTracker arg) {
    // if ((uniType.comps != null) || (!compiler.Compiler.devMode()))
    // uniType.comps.accept(this, arg);
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(FunType funType, ImcTracker arg) {
    // if ((funType.parTypes != null) || (!compiler.Compiler.devMode()))
    // funType.parTypes.accept(this, arg);
    // if ((funType.resType != null) || (!compiler.Compiler.devMode()))
    // funType.resType.accept(this, arg);
    // return null;
    // }

    // @Override
    // public IMC.Expr visit(NameType nameType, ImcTracker arg) {
    // return null;
    // }

    // ----- Expressions -----

    @Override
    public IMC.Expr visit(ArrExpr arrExpr, ImcTracker arg) {
        var arrSize = SemAn.ofType.get(arrExpr).accept(sizeEval, null);
        IMC.Expr arrExprIMC = (IMC.Expr) arrExpr.arrExpr.accept(this, arg);
        IMC.Expr arrAddr = null;
        // TO JE NAROBE IN NE UPOSTEVA STRUCTOV
        long idSize = 0;
        if (arrExprIMC instanceof IMC.MEM8 a) {
            arrAddr = a.addr;
            idSize = 8;
        } else if (arrExprIMC instanceof IMC.MEM1 a) {
            arrAddr = a.addr;
            idSize = 1;
        }
        return ImcGen.expr.put(arrExpr, new IMC.MEM8(
                new IMC.BINOP(IMC.BINOP.Oper.ADD, arrAddr,
                        new IMC.BINOP(IMC.BINOP.Oper.MUL, (IMC.Expr) arrExpr.idx.accept(this, arg),
                                new IMC.CONST(idSize)))));
    }

    @Override
    public IMC.Expr visit(AtomExpr atomExpr, ImcTracker arg) {
        return ImcGen.expr.put(atomExpr, new IMC.CONST(Long.valueOf(atomExpr.value)));
    }

    @Override
    public IMC.Expr visit(BinExpr binExpr, ImcTracker arg) {
        IMC.BINOP.Oper op = IMC.BINOP.Oper.OR;
        switch (binExpr.oper) {
            case OR:
                op = IMC.BINOP.Oper.OR;
                break;
            case AND:
                op = IMC.BINOP.Oper.AND;
                break;
            case EQU:
                op = IMC.BINOP.Oper.EQU;
                break;
            case NEQ:
                op = IMC.BINOP.Oper.NEQ;
                break;
            case LTH:
                op = IMC.BINOP.Oper.LTH;
                break;
            case GTH:
                op = IMC.BINOP.Oper.GTH;
                break;
            case LEQ:
                op = IMC.BINOP.Oper.LEQ;
                break;
            case GEQ:
                op = IMC.BINOP.Oper.GEQ;
                break;
            case MUL:
                op = IMC.BINOP.Oper.MUL;
                break;
            case DIV:
                op = IMC.BINOP.Oper.DIV;
                break;
            case MOD:
                op = IMC.BINOP.Oper.MOD;
                break;
            case ADD:
                op = IMC.BINOP.Oper.ADD;
                break;
            case SUB:
                op = IMC.BINOP.Oper.SUB;
                break;
            default:
                break;
        }
        return ImcGen.expr.put(binExpr, new IMC.BINOP(op, (IMC.Expr) binExpr.fstExpr.accept(this, arg),
                (IMC.Expr) binExpr.sndExpr.accept(this, arg)));
    }

    // @Override
    // public IMC.Expr visit(CallExpr callExpr, ImcTracker arg) {
         
    //     return ImcGen.expr.put(callExpr, new IMC.CALL(
    //         callExpr.funExpr.accept(this, arg),
                
    //     ));
    // }

    @Override
    public IMC.Expr visit(CastExpr castExpr, ImcTracker arg) {
        var castType = SemAn.ofType.get(castExpr);
        if (castType instanceof TYP.BoolType) {
            return ImcGen.expr.put(castExpr,
                    new IMC.BINOP(IMC.BINOP.Oper.MOD, (IMC.Expr) castExpr.expr.accept(this, arg), new IMC.CONST(2)));
        } else if (castType instanceof TYP.CharType) {
            return ImcGen.expr.put(castExpr,
                    new IMC.BINOP(IMC.BINOP.Oper.MOD, (IMC.Expr) castExpr.expr.accept(this, arg), new IMC.CONST(256)));
        } else {
            return ImcGen.expr.put(castExpr, (IMC.Expr) castExpr.expr.accept(this, arg));
        }
    }

    @Override
    public IMC.Expr visit(CompExpr compExpr, ImcTracker arg) {
        var arrSize = SemAn.ofType.get(compExpr).accept(sizeEval, null);
        IMC.Expr compExprIMC = (IMC.Expr) compExpr.recExpr.accept(this, arg);
        IMC.Expr arrAddr = null;
        // TODO: treba narest da dobi velikost offseta
        long idSize = 0; 
        if (compExprIMC instanceof IMC.MEM8 a) {
            arrAddr = a.addr;
        } else if (compExprIMC instanceof IMC.MEM1 a) {
            arrAddr = a.addr;
        }

        return ImcGen.expr.put(compExpr, new IMC.MEM8(
            new IMC.BINOP(IMC.BINOP.Oper.ADD, arrAddr,
                    new IMC.CONST(idSize))
        ));
    }

    @Override
    public IMC.Expr visit(NameExpr nameExpr, ImcTracker arg) {
        var nameType = SemAn.ofType.get(nameExpr);
        if (nameType instanceof TYP.BoolType || nameType instanceof TYP.CharType)
            return ImcGen.expr.put(nameExpr, new IMC.MEM1(new IMC.NAME(new MEM.Label(nameExpr.name))));
        return ImcGen.expr.put(nameExpr, new IMC.MEM8(new IMC.NAME(new MEM.Label(nameExpr.name))));
    }

    @Override
    public IMC.Expr visit(PfxExpr pfxExpr, ImcTracker arg) {
        IMC.UNOP.Oper op = IMC.UNOP.Oper.NEG;
        switch (pfxExpr.oper) {
            case NOT:
                op = IMC.UNOP.Oper.NOT;
                break;
            case SUB:
                op = IMC.UNOP.Oper.NEG;
                break;
            default:
                break;
        }
        return ImcGen.expr.put(pfxExpr, new IMC.UNOP(op, (IMC.Expr) pfxExpr.subExpr.accept(this, arg)));
    }

    @Override
    public IMC.Expr visit(SfxExpr sfxExpr, ImcTracker arg) {
        return ImcGen.expr.put(sfxExpr, new IMC.MEM8((IMC.Expr) sfxExpr.subExpr.accept(this, arg)));
    }

    @Override
    public IMC.Expr visit(SizeExpr sizeExpr, ImcTracker arg) {
        var sizeType = sizeExpr.type.accept(this, arg);
        if (sizeType instanceof TYP.BoolType || sizeType instanceof TYP.CharType)
            return ImcGen.expr.put(sizeExpr, new IMC.CONST(1));
        return ImcGen.expr.put(sizeExpr, new IMC.CONST(8));
    }

}
