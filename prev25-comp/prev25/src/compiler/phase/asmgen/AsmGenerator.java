package compiler.phase.asmgen;

import java.util.*;
import compiler.phase.asmgen.ASM.Operand;
import compiler.phase.asmgen.ASM.RegOperand;
import compiler.phase.asmgen.ASM.TempOperand;
import compiler.phase.asmgen.ASM.NameOperand;
import compiler.phase.imcgen.*;
import compiler.phase.imcgen.IMC.BINOP;
import compiler.phase.imclin.*;
import compiler.phase.memory.MEM;
import compiler.common.report.Report;

public class AsmGenerator {

    private static Operand op(String reg) {
        return new RegOperand(reg);
    }

    private static Operand op(IMC.TEMP temp) {
        return new TempOperand(temp);
    }
    
    private static Operand op(IMC.NAME name) {
        return new NameOperand(name);
    }

    private static Operand op(long value) {
        return new ASM.ImmOperand(value);
    }

    // private static boolean checkImmediate(long vlaue) {
    //     if( )
    // }

    public AsmGenerator() {}

    public static void generateAsmChunks() {
        for (LIN.CodeChunk codeChunk : ImcLin.codeChunks()) {
            generateAsmChunk(codeChunk);
        }
    }

    private static void generateAsmChunk(LIN.CodeChunk codeChunk) {
        ASM.AsmChunk asmChunk = new ASM.AsmChunk();
        for (IMC.Stmt stmt : codeChunk.stmts()) {
            asmChunk.addAll(generateLines(stmt));
        }
        AsmGen.addAsmChunk(asmChunk);
    }

    private static Vector<ASM.Line> generateLines(IMC.Stmt stmt) {
        // Report.info(stmt.toString());
        Vector<ASM.Line> lines = new Vector<>();

        if (stmt instanceof IMC.LABEL lbl) {
            lines.add(new ASM.Label(lbl));
            return lines;
        }

        // Handle unconditional jump (IMC.JUMP)
        if (stmt instanceof IMC.JUMP jump) {
            Operand target = op((IMC.NAME) jump.addr);  // Address to jump to
            lines.add(new ASM.J("JAL", target, op(0))); // JAL instruction to jump
            return lines;
        }

        // Handle conditional jump (IMC.CJUMP)
        if (stmt instanceof IMC.CJUMP cjump) {
            // Conditional jump (evaluates the condition)
            // Operand cond = op((IMC.TEMP) cjump.cond);   // Condition to check (e.g., a register or value)
            IMC.TEMP cond = (IMC.TEMP) cjump.cond;
            // IMC.TEMP l = (IMC.TEMP) cond.fstExpr;
            // IMC.TEMP r = (IMC.TEMP) cond.sndExpr;
            Operand posAddr = op((IMC.NAME) cjump.posAddr); // Positive address (if true)
            Operand negAddr = op((IMC.NAME) cjump.negAddr); // Negative address (if false)

            lines.add(new ASM.B("BNE", op(cond), op("x0"), posAddr));
            // if(cond.oper == IMC.BINOP.Oper.EQU) {
            //     lines.add(new ASM.B("BEQ", op(l), op(r), posAddr));
            // } else if (cond.oper == IMC.BINOP.Oper.NEQ) {
            //     lines.add(new ASM.B("BNE", op(l), op(r), negAddr));
            // } else if (cond.oper == IMC.BINOP.Oper.LTH) {
            //     lines.add(new ASM.B("BLT", op(l), op(r), posAddr));
            // } else if (cond.oper == IMC.BINOP.Oper.GTH) {
            //     lines.add(new ASM.B("BGT", op(l), op(r), negAddr));
            // } else if (cond.oper == IMC.BINOP.Oper.LEQ) {
            //     lines.add(new ASM.B("BLE", op(l), op(r), posAddr));
            // } else if (cond.oper == IMC.BINOP.Oper.GEQ) {
            //     lines.add(new ASM.B("BGE", op(l), op(r), negAddr));
            // }
            return lines;
            // lines.add()
            // lines.add(new ASM.I("BEQ", cond, posAddr, 0)); // BEQ instruction for conditional jump
    }

        if (stmt instanceof IMC.MOVE move) {
            if (move.dst instanceof IMC.TEMP dst) {
                if (move.src instanceof IMC.CALL src) {
                    // Step 1: Pass arguments
                    List<IMC.Expr> args = src.args;
                    int argCount = 0;
                    for (int i = 0; i < args.size(); i++) {
                        IMC.Expr arg = args.get(i);
            
                        // Evaluate each argument (assume it's TEMP or MEM for now)
                        Operand argOp;
                        if (arg instanceof IMC.TEMP tempArg) {
                            argOp = op(tempArg);
                        } else if (arg instanceof IMC.MEM8 memArg && memArg.addr instanceof IMC.TEMP tempAddr) {
                            argOp = new TempOperand(new IMC.TEMP(new MEM.Temp())); // allocate a temp
                            lines.add(new ASM.I("LD", argOp, op(tempAddr), op(0)));
                        } else {
                            continue; // Skip unsupported cases for now
                        }
            
                        if (i < 8) {
                            lines.add(new ASM.I("ADDI", op("a" + i), argOp, op(0))); // Move into a0–a7
                        } else {
                            int offset = (i - 8) * 8; // Stack offset for extra args
                            lines.add(new ASM.S("SD", argOp, op("sp"), op(offset)));
                        }
            
                        argCount++;
                    }
            
                    // Step 2: Call function
                    IMC.NAME func = (IMC.NAME) src.addr;
                    lines.add(new ASM.U("LA", op("t0"), op(func))); // Load address of function
                    lines.add(new ASM.I("JALR", op("ra"), op("t0"), op(0))); // Call via t0
            
                    // Step 3: Move return value from a0 to dst
                    lines.add(new ASM.I("ADDI", op(dst), op("a0"), op(0)));
            
                    return lines;
                }

                if (move.src instanceof IMC.NAME src) {
                    lines.add(new ASM.U("LA", op(dst), op(src)));
                    return lines;
                }
                if (move.src instanceof IMC.TEMP src) {
                    lines.add(new ASM.I("ADDI", op(dst), op(src), op(0)));
                    return lines;
                }
                if (move.src instanceof IMC.CONST src) {
                    lines.add(new ASM.I("ADDI", op(dst), op("x0"), op(src.value)));
                    return lines;
                }
                if (move.src instanceof IMC.MEM8 src) {
                    if (src.addr instanceof IMC.TEMP tmp) {
                        lines.add(new ASM.I("LD", op(dst), op(tmp), op(0)));
                        return lines;
                    }
                    if (src.addr instanceof IMC.CONST cst) {
                        lines.add(new ASM.I("LD", op(dst), op("x0"), op(cst.value)));
                        return lines;
                    }
                    if (src.addr instanceof IMC.BINOP binop && binop.oper == IMC.BINOP.Oper.ADD) {
                        lines.add(new ASM.I("LD", op(dst), op((IMC.TEMP) binop.fstExpr),
                                op(((IMC.CONST) binop.sndExpr).value)));
                        return lines;
                    }
                }

                if (move.src instanceof IMC.MEM1 src) {
                    if (src.addr instanceof IMC.TEMP tmp) {
                        lines.add(new ASM.S("LB", op(dst), op(tmp), op(0)));
                        return lines;
                    }
                    if (src.addr instanceof IMC.CONST cst) {
                        lines.add(new ASM.S("LB", op(dst), op("x0"), op(cst.value)));
                        return lines;
                    }
                    if (src.addr instanceof IMC.BINOP binop && binop.oper == IMC.BINOP.Oper.ADD) {
                        lines.add(new ASM.S("LB", op(dst), op((IMC.TEMP) binop.fstExpr),
                                op(((IMC.CONST) binop.sndExpr).value)));
                        return lines;
                    }
                }

                if (move.src instanceof IMC.BINOP binop) {
                    IMC.Expr fst = binop.fstExpr;
                    IMC.Expr snd = binop.sndExpr;

                    Operand rd = op(dst);
                    Operand rs1 = op((IMC.TEMP) fst);
                    Operand rs2 = (snd instanceof IMC.TEMP) ? op((IMC.TEMP) snd) : null;
                    long imm = (snd instanceof IMC.CONST) ? ((IMC.CONST) snd).value : 0L;

                    switch (binop.oper) {
                        case ADD:
                            lines.add((snd instanceof IMC.CONST)
                                    ? new ASM.I("ADDI", rd, rs1, op(imm))
                                    : new ASM.R("ADD", rd, rs1, rs2));
                            break;
                        case SUB:
                            lines.add(new ASM.R("SUB", rd, rs1, rs2));
                            break;
                        case MUL:
                            lines.add(new ASM.R("MUL", rd, rs1, rs2));
                            break;
                        case DIV:
                            lines.add(new ASM.R("DIV", rd, rs1, rs2));
                            break;
                        case MOD:
                            lines.add(new ASM.R("REM", rd, rs1, rs2));
                            break;
                        case AND:
                            lines.add((snd instanceof IMC.CONST)
                                    ? new ASM.I("ANDI", rd, rs1, op(imm))
                                    : new ASM.R("AND", rd, rs1, rs2));
                            break;
                        case OR:
                            lines.add((snd instanceof IMC.CONST)
                                    ? new ASM.I("ORI", rd, rs1, op(imm))
                                    : new ASM.R("OR", rd, rs1, rs2));
                            break;
                        case EQU:
                            lines.add(new ASM.R("SUB", rd, rs1, rs2));
                            lines.add(new ASM.I("SEQZ", rd, rd, op(0)));  // pseudo-instruction
                            break;
                        case NEQ:
                            lines.add(new ASM.R("SUB", rd, rs1, rs2));
                            lines.add(new ASM.I("SNEZ", rd, rd, op(0)));  // pseudo-instruction
                            break;
                        case LTH:
                            lines.add((snd instanceof IMC.CONST)
                                    ? new ASM.I("SLTI", rd, rs1, op(imm))
                                    : new ASM.R("SLT", rd, rs1, rs2));
                            break;
                        case GTH:
                            lines.add((snd instanceof IMC.CONST)
                                    ? new ASM.I("SLTI", rd, rs2, op(imm))
                                    : new ASM.R("SLT", rd, rs2, rs1));
                            break;
                        case LEQ:
                            lines.add(new ASM.R("SLT", rd, rs2, rs1));
                            lines.add(new ASM.I("XORI", rd, rd, op(1)));
                            break;
                        case GEQ:
                            lines.add(new ASM.R("SLT", rd, rs1, rs2));
                            lines.add(new ASM.I("XORI", rd, rd, op(1)));
                            break;
                    }
                    return lines;
                }
            }

            if (move.src instanceof IMC.TEMP src && move.dst instanceof IMC.MEM8 dst) {
                if (dst.addr instanceof IMC.TEMP tmp) {
                    lines.add(new ASM.S("SD", op(src), op(tmp), op(0)));
                    return lines;
                }
                if (dst.addr instanceof IMC.CONST cst) {
                    lines.add(new ASM.S("SD", op(src), op("x0"), op(cst.value)));
                    return lines;
                }
                if (dst.addr instanceof IMC.BINOP binop && binop.oper == IMC.BINOP.Oper.ADD) {
                    lines.add(new ASM.S("SD", op(src), op((IMC.TEMP) binop.fstExpr),
                            op(((IMC.CONST) binop.sndExpr).value)));
                    return lines;
                }
            }
        }

        lines.add(new ASM.Label(new IMC.LABEL(new MEM.Label("Not implemented for statement: " + stmt))));
        return lines;
    }
}
