package compiler.phase.imcgen;

import compiler.phase.abstr.AST;
import compiler.phase.memory.MEM.*;


public class ImcTracker {
    long depth = 0;
    AST.FunDefn funDefn = null;
    public Label l1;
    public Label l2;
    public IMC.Expr lastExpr;
    public IMC.TEMP staticLink;

    public ImcTracker() {
        this.funDefn = null;
    }

    public ImcTracker(AST.FunDefn funDefn) {
        this.funDefn = funDefn;
        this.staticLink = null;
    }
}
