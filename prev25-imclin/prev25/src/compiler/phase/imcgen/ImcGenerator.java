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
    // @Override
    // public IMC.Expr visit(DefFunDefn defFunDefn, ImcTracker arg) {
    //     if (arg.depth > 1) {

    //         ImcGen.entryLabel.put(defFunDefn, new MEM.Label(defFunDefn.name));
    //         ImcGen.exitLabel.put(defFunDefn, new MEM.Label());
    //     } else {
    //         ImcGen.entryLabel.put(defFunDefn, new MEM.Label());
    //         ImcGen.exitLabel.put(defFunDefn, new MEM.Label());
    //     }
    //     arg.depth++;
    //     var newArg = new ImcTracker(defFunDefn);
    //     defFunDefn.stmts.accept(this, newArg);
    //     return null;
    // }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, ImcTracker arg) {
        var frame = Memory.frames.get(defFunDefn);
        var staticLink = new IMC.TEMP(frame.FP);
    
        var entryLabel = new MEM.Label();
        var exitLabel = new MEM.Label();
    
        ImcGen.entryLabel.put(defFunDefn, entryLabel);
        ImcGen.exitLabel.put(defFunDefn, exitLabel);
    
        var context = new ImcTracker(defFunDefn);
        context.l1 = entryLabel;
        context.l2 = exitLabel;
        context.staticLink = staticLink;
    
        defFunDefn.stmts.accept(this, context);
    
        return null;
    }

    // // ----- Statements -----

    @Override
    public IMC.Stmt visit(LetStmt letStmt, ImcTracker arg) {
        for (var defn : letStmt.defns) {
            defn.accept(this, arg);
        }
        Vector<IMC.Stmt> letIMC = new Vector<IMC.Stmt>();
        for (var stmt : letStmt.stmts) {
            letIMC.addLast((IMC.Stmt) stmt.accept(this, arg));
        }
        return ImcGen.stmt.put(letStmt, new IMC.STMTS(letIMC));
    }

    @Override
    public IMC.Stmt visit(AssignStmt assignStmt, ImcTracker arg) {
        return ImcGen.stmt.put(assignStmt,
                new IMC.MOVE(
                        (IMC.Expr) assignStmt.dstExpr.accept(this, arg),
                        (IMC.Expr) assignStmt.srcExpr.accept(this, arg)));
    }

    @Override
    public IMC.Stmt visit(AST.ExprStmt exprStmt, ImcTracker arg) {
        return ImcGen.stmt.put(exprStmt, new IMC.ESTMT((IMC.Expr) exprStmt.expr.accept(this, arg)));
    }

    @Override
    public IMC.Stmt visit(IfThenStmt ifThenStmt, ImcTracker arg) {
        var lblIf = new MEM.Label();
        var lblEnd = new MEM.Label();
        var ifThenIMC = new Vector<IMC.Stmt>();
        var ifIMC = new Vector<IMC.Stmt>();
        ifThenIMC.add(new IMC.CJUMP((IMC.Expr) ifThenStmt.condExpr.accept(this, arg), new IMC.NAME(lblIf),
                new IMC.NAME(lblEnd)));
        ifThenIMC.add(new IMC.LABEL(lblIf));
        for (var ifStmt : ifThenStmt.thenStmt) {
            IMC.Stmt t = (IMC.Stmt) ifStmt.accept(this, arg);
            ifIMC.addLast(t);
        }
        ifThenIMC.add(new IMC.STMTS(ifIMC));
        ifThenIMC.add(new IMC.JUMP(new IMC.NAME(lblEnd)));
        ifThenIMC.add(new IMC.LABEL(lblEnd));

        return ImcGen.stmt.put(ifThenStmt, new IMC.STMTS(ifThenIMC));
    }

    @Override
    public IMC.Stmt visit(IfThenElseStmt ifThenElseStmt, ImcTracker arg) {
        var lblIf = new MEM.Label();
        var lblElse = new MEM.Label();
        var lblEnd = new MEM.Label();
        var ifThenElseIMC = new Vector<IMC.Stmt>();
        var ifIMC = new Vector<IMC.Stmt>();
        var elseIMC = new Vector<IMC.Stmt>();
        ifThenElseIMC.add(new IMC.CJUMP((IMC.Expr) ifThenElseStmt.condExpr.accept(this, arg), new IMC.NAME(lblIf),
                new IMC.NAME(lblElse)));
        ifThenElseIMC.add(new IMC.LABEL(lblIf));
        for (var ifStmt : ifThenElseStmt.thenStmt) {
            IMC.Stmt t = (IMC.Stmt) ifStmt.accept(this, arg);
            ifIMC.addLast(t);
        }
        ifThenElseIMC.add(new IMC.STMTS(ifIMC));
        ifThenElseIMC.add(new IMC.JUMP(new IMC.NAME(lblEnd)));
        ifThenElseIMC.add(new IMC.LABEL(lblElse));
        for (var n : ifThenElseStmt.elseStmt) {
            IMC.Stmt t = (IMC.Stmt) n.accept(this, arg);
            elseIMC.addLast(t);
        }
        ifThenElseIMC.add(new IMC.STMTS(elseIMC));
        ifThenElseIMC.add(new IMC.JUMP(new IMC.NAME(lblEnd)));
        ifThenElseIMC.add(new IMC.LABEL(lblEnd));

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

    @Override
    public IMC.Stmt visit(WhileStmt whileStmt, ImcTracker arg) {
        var conditionLbl = new MEM.Label();
        var whileLbl = new MEM.Label();
        var endLbl = new MEM.Label();
        var whileIMC = new Vector<IMC.Stmt>();
        var loopIMC = new Vector<IMC.Stmt>();
        whileIMC.add(new IMC.LABEL(conditionLbl));
        whileIMC.add(new IMC.CJUMP(
                (IMC.Expr) whileStmt.condExpr.accept(this, arg),
                new IMC.NAME(whileLbl),
                new IMC.NAME(endLbl)));
        whileIMC.add(new IMC.LABEL(whileLbl));
        for (var stmt : whileStmt.stmts) {
            loopIMC.addLast((IMC.Stmt) stmt.accept(this, arg));
        }
        whileIMC.add(new IMC.STMTS(loopIMC));
        whileIMC.add(new IMC.JUMP(new IMC.NAME(conditionLbl)));
        whileIMC.add(new IMC.LABEL(endLbl));
        return ImcGen.stmt.put(whileStmt, new IMC.STMTS(whileIMC));
    }

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

    public static int stringToChar(String s) {
        s = s.substring(1, s.length() - 1);
        if (s.length() == 1) {
            return s.charAt(0);
        }
        if (s.startsWith("\\'")) {
            return '\'';
        }
        if (s.startsWith("\\\\")) {
            return '\\';
        }
        return Integer.parseInt(s.replace("\\0x", ""), 16);
    }
    
    @Override
    public IMC.Expr visit(AST.AtomExpr atomExpr, ImcTracker arg) {
        IMC.CONST value = null;
        switch (atomExpr.type) {
            case INT:
                value = new IMC.CONST(Long.valueOf(atomExpr.value));
                break;
            case BOOL:
                if (atomExpr.value.equals("true"))
                    value = new IMC.CONST(1);
                else
                    value = new IMC.CONST(0);
                break;
            case CHAR:
                value = new IMC.CONST(stringToChar(atomExpr.value));
                break;
            case PTR:
                value = new IMC.CONST(0);
                break;
            case STR:
                MEM.AbsAccess tmp = Memory.strings.get(atomExpr);
                return ImcGen.expr.put(atomExpr, new IMC.NAME(tmp.label));
            default:
                throw new Report.Error("Unsupported type");
        }
        arg.lastExpr = value;

        return ImcGen.expr.put(atomExpr, value);
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

    public static long exprSize(AST.Expr expr) {
        var type = SemAn.ofType.get(expr);
        return type.accept(MemEvaluator.sizeEval, null);
    }

    @Override
    public Object visit(AST.CallExpr callExpr, ImcTracker arg) {
        var argsExprs = new Vector<IMC.Expr>();
        var argsSizes = new Vector<Long>();

        var functionExpr = (IMC.Expr) callExpr.funExpr.accept(this, arg);
        var context = (ImcTracker) arg;

        // Add static link as the first argument
        argsExprs.add(new IMC.MEM8(context.staticLink));
        argsSizes.add(8L);

        // Process all argument expressions
        for (var expr : callExpr.argExprs) {
            argsExprs.add((IMC.Expr) expr.accept(this, arg));
            argsSizes.add(exprSize(expr));
        }

        var call = new IMC.CALL(functionExpr, argsSizes, argsExprs);
        context.lastExpr = call;

        // Report.info(argsExprs.toString() + " <- arguments");
        // Report.info(call.toString());

        return ImcGen.expr.put(callExpr, call);
    }
    
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

    public static long compOffset(AST.CompExpr compExpr, ImcTracker arg) {
        TYP.RecType b = (TYP.RecType)SemAn.ofType.get(compExpr.recExpr).actualType();
        //var x = Memory.accesses.get(compExpr);
        /*for (AST.CompDefn x : b.compTypes.name){}*/
        int i = 0;
        for(; i<b.names.size(); i++){
            if(b.names.get(i).equals(compExpr.name)){
                break;
            }
        }
        if(i>=b.names.size()){
            throw new Report.Error(compExpr,"Could not get node of component");
        }
        var nodeOfComponent = (AST.CompDefn)b.componentNodes.get(i);
        long FUCK = ((MEM.RelAccess)Memory.accesses.get(nodeOfComponent)).offset;
        
        return FUCK;
    }

    @Override
    public IMC.Expr visit(CompExpr compExpr, ImcTracker arg) {
        IMC.Expr compExprIMC = (IMC.Expr) compExpr.recExpr.accept(this, arg);
        IMC.Expr arrAddr = null;
        // TODO: treba narest da dobi velikost offseta
        long idSize = compOffset(compExpr, arg);
        if (compExprIMC instanceof IMC.MEM8 a) {
            arrAddr = a.addr;
        } else if (compExprIMC instanceof IMC.MEM1 a) {
            arrAddr = a.addr;
        }

        return ImcGen.expr.put(compExpr, new IMC.MEM8(
                new IMC.BINOP(IMC.BINOP.Oper.ADD, arrAddr,
                        new IMC.CONST(idSize))));
    }

    // @Override
    // public IMC.Expr visit(NameExpr nameExpr, ImcTracker arg) {
    //     var nameType = SemAn.ofType.get(nameExpr);
    //     if (nameType instanceof TYP.BoolType || nameType instanceof TYP.CharType)
    //         return ImcGen.expr.put(nameExpr, new IMC.MEM1(new IMC.NAME(new MEM.Label(nameExpr.name))));
    //     return ImcGen.expr.put(nameExpr, new IMC.MEM8(new IMC.NAME(new MEM.Label(nameExpr.name))));
    // }

    public IMC.Expr visit(AST.NameExpr nameExpr, ImcTracker arg) {
        var tmp = SemAn.defAt.get(nameExpr);
        
        if (tmp instanceof AST.DefFunDefn) {
            var frame = Memory.frames.get(tmp);
            var name = new IMC.NAME(frame.label);
            ((ImcTracker) arg).lastExpr = name;
            return ImcGen.expr.put(nameExpr, name);
        } 
        
        if (tmp instanceof AST.ExtFunDefn) {
            var name = new IMC.NAME(new MEM.Label(tmp.name));
            ((ImcTracker) arg).lastExpr = name;
            return ImcGen.expr.put(nameExpr, name);
        }
    
        TYP.Type type = SemAn.ofType.get(nameExpr);
        if (type instanceof TYP.NameType) {
            type = type.actualType();
        }
    
        if (!(tmp instanceof AST.Node)) {
            throw new Report.Error("Unexpected semantic definition for NameExpr.");
        }
    
        var nekiNovega = (ImcTracker) arg;
        var access = Memory.accesses.get(tmp);
        IMC.Expr expr;
    
        if (access instanceof MEM.AbsAccess absAccess) {
            expr = new IMC.NAME(absAccess.label);
        } 
        else if (access instanceof MEM.RelAccess relAccess) {
            var frame = Memory.frames.get(nekiNovega.funDefn);
            var fpTemp = new IMC.TEMP(frame.FP);
            expr = new IMC.BINOP(
                IMC.BINOP.Oper.ADD,
                fpTemp,
                new IMC.CONST(relAccess.offset)
            );
        } 
        else {
            throw new Report.Error("Unsupported memory access type.");
        }
    
        if (type instanceof TYP.BoolType || type instanceof TYP.CharType) {
            var mem1 = new IMC.MEM1(expr);
            nekiNovega.lastExpr = mem1;
            return ImcGen.expr.put(nameExpr, mem1);
        }
    
        var mem8 = new IMC.MEM8(expr);
        nekiNovega.lastExpr = mem8;
        return ImcGen.expr.put(nameExpr, mem8);
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
            case PTR:
                op = null;
                break;
            default:
                break;
        }

        if (op == null) {
            IMC.Expr subExprIMC = (IMC.Expr) pfxExpr.subExpr.accept(this, arg);
            if (subExprIMC instanceof IMC.MEM8 mem8) {
                subExprIMC = mem8.addr;
            }
            else if (subExprIMC instanceof IMC.MEM1 mem1) {
                subExprIMC = mem1.addr;
            }
            return ImcGen.expr.put(pfxExpr, subExprIMC);
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
