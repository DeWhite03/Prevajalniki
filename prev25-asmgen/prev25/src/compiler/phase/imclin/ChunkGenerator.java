package compiler.phase.imclin;

import compiler.phase.*;
import compiler.phase.abstr.*;
import compiler.phase.imcgen.*;
import compiler.phase.imclin.*;
import compiler.phase.memory.*;
import compiler.phase.seman.*;
import java.util.*;
import java.util.function.*;


public class ChunkGenerator implements AST.FullVisitor<Object, Object> {

    public Object visit(AST.AtomExpr atomExpr, Object arg) {
        if (atomExpr.type == AST.AtomExpr.Type.STR) {
            MEM.AbsAccess absAccess = Memory.strings.get(atomExpr);
            ImcLin.addDataChunk(new LIN.DataChunk(absAccess));
        }
        return null;
    }

    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        defFunDefn.stmts.accept(this, null);

        MEM.Frame frame             = Memory.frames.get(defFunDefn);
        MEM.Label entryLabel        = ImcGen.entryLabel.get(defFunDefn);
        MEM.Label exitLabel         = ImcGen.exitLabel.get(defFunDefn);
        Vector<IMC.Stmt> statements = new Vector<IMC.Stmt>();

        statements.add(new IMC.LABEL(entryLabel));

        defFunDefn.stmts.forEach(
            stmt
            -> statements.addAll(
                ImcGen.stmt.get(stmt).accept(new StatementEvaluator(), null)
            )
        );

        statements.add(new IMC.JUMP(new IMC.NAME(exitLabel)));

        Vector<IMC.Stmt> linearized = new Vector<IMC.Stmt>();
        for (IMC.Stmt statement : statements) {
            switch (statement) {
            case IMC.CJUMP conditionalJumpStatement -> {
                IMC.LABEL newNegativeLabel = new IMC.LABEL(new MEM.Label());

                linearized.add(new IMC.CJUMP(
                    conditionalJumpStatement.cond,
                    conditionalJumpStatement.posAddr,
                    new IMC.NAME(newNegativeLabel.label)
                ));
                linearized.add(newNegativeLabel);
                linearized.add(new IMC.JUMP(conditionalJumpStatement.negAddr));
                break;
            }
            default -> linearized.add(statement);
            }
        }

        ImcLin.addCodeChunk(
            new LIN.CodeChunk(frame, linearized, entryLabel, exitLabel)
        );

        return null;
    }

    public Object visit(AST.VarDefn varDefn, Object arg) {
        MEM.Access access = Memory.accesses.get(varDefn);
        if (access instanceof MEM.AbsAccess absAccess) {
            ImcLin.addDataChunk(new LIN.DataChunk(absAccess));
        }
        return null;
    }

    // public Object visit(AST.LetStmt letStmt, Object arg) {
    //     letStmt.defns.accept(this, arg);
    //     letStmt.stmts.accept(this, null);
    //     return null;
    // }
}