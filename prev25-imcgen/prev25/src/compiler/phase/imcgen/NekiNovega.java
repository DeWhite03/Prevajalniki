package compiler.phase.imcgen;

import compiler.phase.abstr.AST.FunDefn;

public class NekiNovega {
    public FunDefn funDefn;

    public NekiNovega(FunDefn funDefn) {
        this.funDefn = funDefn;
    }
    public NekiNovega() {
        this.funDefn = null;
    }
}