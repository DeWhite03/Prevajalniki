package compiler.phase.livean;

import java.util.Vector;

import compiler.phase.*;
import compiler.phase.asmgen.ASM;
import compiler.phase.asmgen.AsmGen;

public class LiveAn extends Phase {

    public LiveAn() {
        super("livean");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ASM.AsmChunk chunk : AsmGen.asmChunks()) {
          for(ASM.Line line : chunk.lines) {
              if (line instanceof ASM.Instr instr) {
                sb.append(line.toString()).append("\n");
                sb.append("in: " + instr.in.toString()).append("\n");
                sb.append("out: " + instr.out.toString()).append("\n");
            }
          }
        }
        return sb.toString();
    }
}
