package compiler.phase.livean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import compiler.phase.asmgen.ASM;
import compiler.phase.asmgen.AsmGen;

public class LivenessAnalyzer {

    public static void analyzeChunks() {
        for (ASM.AsmChunk chunk : AsmGen.asmChunks()) {
            analyze(chunk);
        }
    }

    public static void analyze(ASM.AsmChunk chunk) {
        Vector<ASM.Line> lines = chunk.lines;
        Map<ASM.Line, Set<ASM.Instr>> succ = new HashMap<>();
        Map<String, ASM.Instr> labelToInstr = new HashMap<>();

        // Step 1: Map labels to instructions
        for (int i = 0; i < lines.size(); i++) {
            ASM.Line line = lines.get(i);
            if (line instanceof ASM.Label lbl) {
                // Find the next instruction after the label
                for (int j = i + 1; j < lines.size(); j++) {
                    if (lines.get(j) instanceof ASM.Instr instr) {
                        labelToInstr.put(lbl.lbl.label.name, instr);
                        break;
                    }
                }
            }
        }

        // System.out.println("LabelToinstruction: " + labelToInstr.toString());

        // Step 2: Build succ map
        for (int i = 0; i < lines.size(); i++) {
            ASM.Line line = lines.get(i);
            if (!(line instanceof ASM.Instr instr))
                continue;

            Set<ASM.Instr> successors = new HashSet<>();

            if (instr instanceof ASM.B || instr instanceof ASM.J) {
                // Branch or jump - handle offset operand (label)
                ASM.Operand offset = null;
                if (instr instanceof ASM.B bInstr)
                    offset = bInstr.offset;
                if (instr instanceof ASM.J jInstr)
                    offset = jInstr.offset;

                if (offset instanceof ASM.NameOperand target) {

                    ASM.Instr targetInstr = labelToInstr.get(target.toString());
                    if (targetInstr != null)
                        successors.add(targetInstr);
                }

                // For conditional branches, also add fall-through
                if (instr instanceof ASM.B && i + 1 < lines.size()
                        && lines.get(i + 1) instanceof ASM.Instr fallThrough) {
                    successors.add((ASM.Instr) fallThrough);
                }
            } else {
                for (int j = i + 1; j < lines.size(); j++) {
                    ASM.Line nextLine = lines.get(j);
                    if (nextLine instanceof ASM.Instr nextInstr) {
                        successors.add(nextInstr);
                        break; // Only add the next instruction as a successor
                    }
                }
            }
            
            // else if (i + 1 < lines.size() && lines.get(i + 1) instanceof ASM.Instr nextInstr) {
            //     successors.add(nextInstr);
            // } else if (i + 1 < lines.size() && lines.get(i + 1) instanceof ASM.Instr nextInstr) {
            //     successors.add(nextInstr);
            // }
            // System.out.println("Successors of " + instr.toString() + ": " + successors.toString());
            succ.put(instr, successors);
        }

        // Step 3: Apply data-flow equations
        int loopCount = 0;
        boolean changed;
        do {
            changed = false;

            for (int i = lines.size() - 1; i >= 0; i--) {
                ASM.Line line = lines.get(i);
                if (!(line instanceof ASM.Instr instr)) continue;

                Set<ASM.TempOperand> inOld = new HashSet<>(instr.in);
                Set<ASM.TempOperand> outOld = new HashSet<>(instr.out);

                // out[n] = union of in[s] for s in succ[n]
                Set<ASM.TempOperand> out = new HashSet<>();
                for (ASM.Instr succInstr : succ.getOrDefault(instr, Set.of())) {
                    out.addAll(succInstr.in);
                }
                instr.out = new HashSet<>(out);

                // in[n] = use[n] ∪ (out[n] - def[n])
                Set<ASM.TempOperand> in = new HashSet<>(instr.use);
                Set<ASM.TempOperand> outMinusDef = new HashSet<>(out);
                outMinusDef.removeAll(instr.def);
                in.addAll(outMinusDef);
                instr.in = new HashSet<>(in);

                if (!in.equals(new HashSet<>(inOld)) || !out.equals(new HashSet<>(outOld))) {
                    changed = true;
                }
            }
            // StringBuilder sb = new StringBuilder();
            // for (ASM.Line line : chunk.lines) {
            //     if (line instanceof ASM.Instr instr) {
            //         sb.append(line.toString()).append("\n");
            //         sb.append("in: " + instr.in.toString()).append("\n");
            //         sb.append("out: " + instr.out.toString()).append("\n");
            //     }
            // }
            // System.out.println("Loop " + loopCount + ":\n" + sb.toString());
            // loopCount++;
        } while (changed);
    }
}
