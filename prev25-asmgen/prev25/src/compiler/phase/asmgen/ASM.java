package compiler.phase.asmgen;

import compiler.phase.imcgen.*;
import java.util.Vector;

public class ASM {

    public static class AsmChunk {
        public Vector<Line> lines = new Vector<>();

        public void add(Line line) {
            lines.add(line);
        }

        public void addAll(Vector<Line> lines) {
            this.lines.addAll(lines);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Line line : lines) {
                sb.append(line.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public static abstract class Line {
        public Line() {
        }
    }

    public static class Label extends Line {
        public IMC.LABEL lbl;

        public Label(IMC.LABEL lbl) {
            this.lbl = lbl;
        }

        public String toString() {
            return lbl.label.toString();
        }
    }

    public static abstract class Instr extends Line {
        public String opcode;
        public Vector<IMC.TEMP> use = new Vector<>();
        public Vector<IMC.TEMP> def = new Vector<>();
    }

    public static class R extends Instr {
        public IMC.TEMP rd;
        public IMC.TEMP rs1;
        public IMC.TEMP rs2;

        public R(String opcode, IMC.TEMP rd, IMC.TEMP rs1, IMC.TEMP rs2) {
            this.opcode = opcode;
            this.rd = rd;
            this.rs1 = rs1;
            this.rs2 = rs2;
        }

        @Override
        public String toString() {
            return opcode + " " + rd + ", " + rs1 + ", " + rs2;
        }
    }

    public static class I extends Instr {
        public IMC.TEMP rd;
        public IMC.TEMP rs1;
        public long imm;

        public I(String opcode, IMC.TEMP rd, IMC.TEMP rs1, long imm) {
            this.opcode = opcode;
            this.rd = rd;
            this.rs1 = rs1;
            this.imm = imm;
        }

        @Override
        public String toString() {
            return opcode + " " + rd + ", " + rs1 + ", " + imm;
        }
    }

    public static class S extends Instr {
        public IMC.TEMP rs1;
        public IMC.TEMP rs2;
        public long offset;

        public S(String opcode, IMC.TEMP rs1, IMC.TEMP rs2, long offset) {
            this.opcode = opcode;
            this.rs1 = rs1;
            this.rs2 = rs2;
            this.offset = offset;
        }

        @Override
        public String toString() {
            return opcode + " " + rs2 + ", " + offset + "(" + rs1 + ")";
        }
    }

    public static class B extends Instr {
        public IMC.TEMP rs1;
        public IMC.TEMP rs2;
        public long offset;

        public B(String opcode, IMC.TEMP rs1, IMC.TEMP rs2, long offset) {
            this.opcode = opcode;
            this.rs1 = rs1;
            this.rs2 = rs2;
            this.offset = offset;
        }

        @Override
        public String toString() {
            return opcode + " " + rs1 + ", " + rs2 + ", " + offset;
        }
    }

    public static class U extends Instr {
        public IMC.TEMP rd;
        public long imm;

        public U(String opcode, IMC.TEMP rd, long imm) {
            this.opcode = opcode;
            this.rd = rd;
            this.imm = imm;
        }

        @Override
        public String toString() {
            return opcode + " " + rd + ", " + imm;
        }
    }

    public static class J extends Instr {
        public IMC.TEMP rd;
        public IMC.TEMP rs1;
        public long offset;

        public J(String opcode, IMC.TEMP rd, long offset) {
            this.opcode = opcode;
            this.rd = rd;
            this.offset = offset;
        }

        public J(String opcode, IMC.TEMP rd, IMC.TEMP rs1, long offset) {
            this.opcode = opcode;
            this.rd = rd;
            this.rs1 = rs1;
            this.offset = offset;
        }

        @Override
        public String toString() {
            if (rs1 != null)
                return opcode + " " + rd + ", " + offset + "(" + rs1 + ")";
            else
                return opcode + " " + rd + ", " + offset;
        }
    }
}
