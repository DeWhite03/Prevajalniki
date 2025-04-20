package compiler.phase.imcgen;

import compiler.phase.abstr.AST;

public class ImcTracker {
    long depth = 0;
    AST.FunDefn funDefn = null;

    public ImcTracker() {
        this.funDefn = null;
    }

    public ImcTracker(AST.FunDefn funDefn) {
        this.funDefn = funDefn;
    }
}
