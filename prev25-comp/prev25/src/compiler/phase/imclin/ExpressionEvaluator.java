package compiler.phase.imclin;

import compiler.common.report.*;
import compiler.phase.imcgen.*;
import compiler.phase.memory.*;
import java.util.*;

public class ExpressionEvaluator
    implements IMC.Visitor<IMC.Expr, Vector<IMC.Stmt>> {

    public IMC.Expr visit(IMC.BINOP binOp, Vector<IMC.Stmt> stmts) {
        IMC.Expr fstExpr = binOp.fstExpr.accept(this, stmts);
        MEM.Temp temp1 = new MEM.Temp();
        stmts.add(new IMC.MOVE(new IMC.TEMP(temp1), fstExpr));
        IMC.Expr sndExpr = binOp.sndExpr.accept(this, stmts);
        MEM.Temp temp2 = new MEM.Temp();
        stmts.add(new IMC.MOVE(new IMC.TEMP(temp2), sndExpr));
        MEM.Temp resultTemp = new MEM.Temp();
        stmts.add(new IMC.MOVE(new IMC.TEMP(resultTemp), new IMC.BINOP(binOp.oper, new IMC.TEMP(temp1), new IMC.TEMP(temp2))));
        return new IMC.TEMP(resultTemp);
    }

    public IMC.Expr visit(IMC.CALL call, Vector<IMC.Stmt> stmts) {
        Vector<IMC.Expr> canonArgs = new Vector<IMC.Expr>();
        for (IMC.Expr arg : call.args) {
            IMC.Expr canonArg = arg.accept(this, stmts);
            MEM.Temp temp = new MEM.Temp();
            stmts.add(new IMC.MOVE(new IMC.TEMP(temp), canonArg));
            canonArgs.add(new IMC.TEMP(temp));
        }
        MEM.Temp temp = new MEM.Temp();
        stmts.add(new IMC.MOVE(new IMC.TEMP(temp), new IMC.CALL(call.addr, call.offs, canonArgs)));
        return new IMC.TEMP(temp);
    }

    public IMC.Expr visit(IMC.CJUMP cjump, Vector<IMC.Stmt> visArg) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.CONST constant, Vector<IMC.Stmt> stmts) {
        return new IMC.CONST(constant.value);
    }

    public IMC.Expr visit(IMC.ESTMT eStmt, Vector<IMC.Stmt> visArg) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.JUMP jump, Vector<IMC.Stmt> visArg) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.LABEL label, Vector<IMC.Stmt> visArg) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.MEM1 mem, Vector<IMC.Stmt> stmts) {
        IMC.Expr addrExpr = mem.addr.accept(this, stmts);
        MEM.Temp tmp = new MEM.Temp();
        stmts.add(new IMC.MOVE(new IMC.TEMP(tmp), new IMC.MEM1(addrExpr)));
        return new IMC.TEMP(tmp);
    }

    public IMC.Expr visit(IMC.MEM8 mem, Vector<IMC.Stmt> stmts) {
        IMC.Expr addrExpr = mem.addr.accept(this, stmts);
        MEM.Temp addrTemporary = new MEM.Temp();
        stmts.add(new IMC.MOVE(new IMC.TEMP(addrTemporary), new IMC.MEM8(addrExpr)));
        return new IMC.TEMP(addrTemporary);
    }

    public IMC.Expr visit(IMC.MOVE move, Vector<IMC.Stmt> visArg) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.NAME name, Vector<IMC.Stmt> stmts) {
        return new IMC.NAME(name.label);
    }

    public IMC.Expr visit(IMC.SEXPR sExpr, Vector<IMC.Stmt> stmts) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.STMTS stmts, Vector<IMC.Stmt> visArg) {
        throw new Report.InternalError();
    }

    public IMC.Expr visit(IMC.TEMP temp, Vector<IMC.Stmt> stmts) {
        return new IMC.TEMP(temp.temp);
    }

    public IMC.Expr visit(IMC.UNOP unOp, Vector<IMC.Stmt> stmts) {
        return new IMC.UNOP(unOp.oper, unOp.subExpr.accept(this, stmts));
    }
}