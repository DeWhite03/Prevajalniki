package compiler.phase.asmgen;

import java.util.*;

import compiler.phase.imcgen.*;
import compiler.phase.imclin.*;

public class AsmGenerator {
    public static void generateAsmChunks() {
        for (LIN.CodeChunk codeChunk : ImcLin.codeChunks()) {
            generateAsmChunk(codeChunk);
        }
    }

    private static void generateAsmChunk(LIN.CodeChunk codeChunk) {
        ASM.AsmChunk asmChunk = new ASM.AsmChunk();
        for (IMC.Stmt stmt : codeChunk.stmts()) {
            asmChunk.add(generateLine(stmt));
        }
        AsmGen.addAsmChunk(asmChunk);
    }

    private static ASM.Line generateLine(IMC.Stmt stmt) {
        
        return null;
    }
}
