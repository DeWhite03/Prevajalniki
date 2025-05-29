package compiler.phase.asmgen;

import compiler.phase.imcgen.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class ASM {
    public static abstract class Operand {
        @Override
        public abstract String toString();
    }

    public static class NameOperand extends Operand {
        public final IMC.NAME name;

        public NameOperand(IMC.NAME name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name.label.name;
        }
    }
    
    public static class TempOperand extends Operand {
        public final IMC.TEMP temp;
    
        public TempOperand(IMC.TEMP temp) {
            this.temp = temp;
        }
    
        @Override
        public String toString() {
            return temp.temp.toString();
        }
    }
    
    public static class RegOperand extends Operand {
        public final String regName; // e.g., "x0", "sp", "ra"

        public RegOperand(String regName) {
            this.regName = regName;
        }

        @Override
        public String toString() {
            return regName;
        }
    }
    
    public static class ImmOperand extends Operand {
        public final long imm;

        public ImmOperand(long imm) {
            this.imm = imm;
        }

        @Override
        public String toString() {
            return Long.toString(imm);
        }
    }

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
                sb.append(line).append("\n");
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
            return lbl.label.name + ":";
        }
    }

    public static abstract class Instr extends Line {
        public String opcode;
        public HashSet<TempOperand> use = new HashSet<TempOperand>();
        public HashSet<TempOperand> def = new HashSet<TempOperand>();

        public HashSet<TempOperand> in = new HashSet<TempOperand>();
        public HashSet<TempOperand> out = new HashSet<TempOperand>();
    }

    public static class R extends Instr {
        public Operand rd;
        public Operand rs1;
        public Operand rs2;

        public R(String opcode, Operand rd, Operand rs1, Operand rs2) {
            this.opcode = opcode;
            this.rd = rd;
            this.rs1 = rs1;
            this.rs2 = rs2;
            if(rs1 instanceof TempOperand t) {
                use.add(t);
            }
            if(rs2 instanceof TempOperand t) {
                use.add(t);
            }
            if(rd instanceof TempOperand t) {
                def.add(t);
            }
        }

        @Override
        public String toString() {
            return opcode + " " + rd + ", " + rs1 + ", " + rs2 + " | use: " + use + " | def: " + def;
        }
    }

    public static class I extends Instr {
        public Operand rd;
        public Operand rs1;
        public Operand imm;

        public I(String opcode, Operand rd, Operand rs1, Operand imm) {
            this.opcode = opcode;
            this.rd = rd;
            this.rs1 = rs1;
            this.imm = imm;
            if(rs1 instanceof TempOperand t) {
                use.add(t);
            }
            if(imm instanceof TempOperand t) {
                use.add(t);
            }
            if(rd instanceof TempOperand t) {
                def.add(t);
            }
        }

        @Override
        public String toString() {
            return opcode + " " + rd + ", " + rs1 + ", " + imm + " | use: " + use + " | def: " + def;
        }
    }

    public static class S extends Instr {
        public Operand rs1;
        public Operand rs2;
        public Operand offset;

        public S(String opcode, Operand rs1, Operand rs2, Operand offset) {
            this.opcode = opcode;
            this.rs1 = rs1;
            this.rs2 = rs2;
            this.offset = offset;
            if(rs1 instanceof TempOperand t) {
                use.add(t);
            }
            if(rs2 instanceof TempOperand t) {
                use.add(t);
            }
            if(offset instanceof TempOperand t) {
                use.add(t);
            }
        }

        @Override
        public String toString() {
            return opcode + " " + rs2 + ", " + offset + "(" + rs1 + ")" + " | use: " + use + " | def: " + def;
        }
    }

    public static class B extends Instr {
        public Operand rs1;
        public Operand rs2;
        public Operand offset;

        public B(String opcode, Operand rs1, Operand rs2, Operand offset) {
            this.opcode = opcode;
            this.rs1 = rs1;
            this.rs2 = rs2;
            this.offset = offset;

            
            if(rs1 instanceof TempOperand t) {
                use.add(t);
            }
            if(rs2 instanceof TempOperand t) {
                use.add(t);
            }
            if(offset instanceof TempOperand t) {
                def.add(t);
            }
        }

        @Override
        public String toString() {
            return opcode + " " + rs1 + ", " + rs2 + ", " + offset + " | use: " + use + " | def: " + def;
        }
    }

    public static class U extends Instr {
        public Operand rd;
        public Operand imm;

        public U(String opcode, Operand rd, Operand imm) {
            this.opcode = opcode;
            this.rd = rd;
            this.imm = imm;

            
            if(imm instanceof TempOperand t) {
                use.add(t);
            }
            if(rd instanceof TempOperand t) {
                def.add(t);
            }
        }

        

        @Override
        public String toString() {
            return opcode + " " + rd + ", " + imm + " | use: " + use + " | def: " + def;
        }
    }

    public static class J extends Instr {
        public Operand rd;
        public Operand rs1;
        public Operand offset;

        public J(String opcode, Operand rd, Operand offset) {
            this.opcode = opcode;
            this.rd = rd;
            this.offset = offset;

            
            if(offset instanceof TempOperand t) {
                use.add(t);
            }
            if(rd instanceof TempOperand t) {
                def.add(t);
            }
        }

        public J(String opcode, Operand rd, Operand rs1, Operand offset) {
            this.opcode = opcode;
            this.rd = rd;
            this.rs1 = rs1;
            this.offset = offset;

            
            if(rs1 instanceof TempOperand t) {
                use.add(t);
            }
            if(offset instanceof TempOperand t) {
                use.add(t);
            }
            if(rd instanceof TempOperand t) {
                def.add(t);
            }
        }

        @Override
        public String toString() {
            if (rs1 != null)
                return opcode + " " + rd + ", " + offset + "(" + rs1 + ")" + " | use: " + use + " | def: " + def;
            else
                return opcode + " " + rd + ", " + offset + " | use: " + use + " | def: " + def;
        }
    }
}
