package compiler.phase.imcgen;

import compiler.phase.abstr.*;
import compiler.phase.abstr.AST.DefFunDefn;
import compiler.phase.memory.*;

/**
 * Intermediate code generator.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class ImcGenerator implements AST.FullVisitor<Object, Object> {
    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        return null;
    }
}
