package compiler.phase.regall;

import compiler.common.report.*;
import compiler.phase.*;
import compiler.phase.asmgen.*;
import compiler.phase.asmgen.ASM.*;
import compiler.phase.imcgen.IMC;

import java.util.*;

import compiler.phase.livean.*;

public class RegAll extends Phase {

    public final HashMap<ASM.TempOperand, Integer> tempToReg = new HashMap<ASM.TempOperand, Integer>();
    int numberOfRegisters = 16; // Number of registers available

    public RegAll(int x) {
        super("regall");
        if (x < 7 || x > 32) {
            throw new Report.Error("Invalid number of registers: " + x + ". Must be between 7 and 32.");
        }
    }

    private Graph build(ASM.AsmChunk chunk) {
        Graph graph = new Graph();
        LivenessAnalyzer.analyze(chunk);

        for (ASM.Line line : chunk.lines) {
            if (!(line instanceof ASM.Instr instr))
                continue;

            for (ASM.TempOperand temp : instr.def) {
                graph.addNode(temp);
            }
            for (ASM.TempOperand temp : instr.out) {
                graph.addNode(temp);
            }
        }

        for (ASM.Line line : chunk.lines) {
            if (!(line instanceof ASM.Instr instr))
                continue;

            for (ASM.TempOperand d : instr.def) {
                for (ASM.TempOperand o : instr.out) {
                    graph.addEdge(o, d);
                }
            }
        }

        return graph;
    }

    private boolean simplify(Graph graph, Stack<Node> stack) {
        for (Node node : graph.nodes()) {
            if (node.degree() < numberOfRegisters) {
                // Remove the node from the graph
                graph.removeNode(node.temporary);
                stack.push(node);
                return true; // A node was found and removed from the graph
            }
        }
        return false; // No nodes were found with degree less than number of registers
    }

    private Vector<ASM.TempOperand> select(Graph reconstructedGraph, HashMap<Node, HashSet<Node>> edges,
            Stack<Node> stack) {
        Vector<ASM.TempOperand> spills = new Vector<>();

        boolean coloringFound = true;

        while (!stack.empty()) {
            Node currentNode = stack.pop();
            HashSet<Node> neighbours = reconstructedGraph.nodes();
            neighbours.retainAll(edges.get(currentNode));

            boolean isPossibleColour[] = new boolean[numberOfRegisters];
            for (int i = 4; i < isPossibleColour.length; i++) {
                isPossibleColour[i] = true;
            }

            reconstructedGraph.addNode(currentNode);
            for (Node neighbour : neighbours) {
                reconstructedGraph.addEdge(neighbour, currentNode);
                if (neighbour.color >= 0) {
                    isPossibleColour[neighbour.color] = false;
                }
            }

            for (int i = 4; i < isPossibleColour.length; i++) {
                if (isPossibleColour[i]) {
                    currentNode.color = i;
                    break;
                }
            }

            if (currentNode.color < 0) {
                currentNode.actualSpill = true;
                coloringFound = false;
                spills.add(currentNode.temporary);
            }

        }
        return spills;
    }

    public void allocate() {
        for (ASM.AsmChunk chunk : AsmGen.asmChunks()) {
            boolean coloringFound = false;
            Graph reconstructedGraph = null;

            do {
                Graph interferenceGraph = build(chunk);
                var edges = interferenceGraph.edges();

                Stack<Node> stack = new Stack<>();
                do {
                    boolean wasSimplified;
                    do {
                        wasSimplified = this.simplify(interferenceGraph, stack);
                    } while (wasSimplified);

                    /* TODO */
                    // Node removedNode = spill(interferenceGraph, stack);
                } while (!interferenceGraph.isEmpty());

                reconstructedGraph = new Graph();
                /* TODO */
                Vector<ASM.TempOperand> spills = this.select(reconstructedGraph, edges, stack);
                coloringFound = spills.size() == 0;

                if (coloringFound) {
                    break;
                }

                // modifyCode();
                throw new Report.Error("Couldn't assign registers to temporary variables");

            } while (!coloringFound);

            for (Node node : reconstructedGraph.nodes()) {
                this.tempToReg.put(node.temporary, node.color);
            }
            convert(chunk);
        }
    }

    private void convert(ASM.AsmChunk chunk) {
        for (ASM.Line line : chunk.lines) {
            if (!(line instanceof ASM.Instr))
                continue;
            if (line instanceof ASM.R r) {
                if (r.rs1 instanceof TempOperand t)
                    r.rs1 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (r.rs2 instanceof TempOperand t)
                    r.rs2 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (r.rd instanceof TempOperand t)
                    r.rd = new ASM.RegOperand("x" + tempToReg.get(t));

            }
            if (line instanceof ASM.I i) {

                if (i.rs1 instanceof TempOperand t)
                    i.rs1 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (i.imm instanceof TempOperand t)
                    i.imm = new ASM.RegOperand("x" + tempToReg.get(t));
                if (i.rd instanceof TempOperand t)
                    i.rd = new ASM.RegOperand("x" + tempToReg.get(t));

            }
            if (line instanceof ASM.S s) {
                if (s.rs1 instanceof TempOperand t)
                    s.rs1 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (s.rs2 instanceof TempOperand t)
                    s.rs2 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (s.offset instanceof TempOperand t)
                    s.offset = new ASM.RegOperand("x" + tempToReg.get(t));

            }
            if (line instanceof ASM.B b) {
                if (b.rs1 instanceof TempOperand t)
                    b.rs1 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (b.rs2 instanceof TempOperand t)
                    b.rs2 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (b.offset instanceof TempOperand t)
                    b.offset = new ASM.RegOperand("x" + tempToReg.get(t));

            }
            if (line instanceof ASM.U u) {
                if (u.imm instanceof TempOperand t)
                    u.imm = new ASM.RegOperand("x" + tempToReg.get(t));
                if (u.rd instanceof TempOperand t)
                    u.rd = new ASM.RegOperand("x" + tempToReg.get(t));
            }
            if (line instanceof ASM.J j) {
                if (j.rs1 instanceof TempOperand t)
                    j.rs1 = new ASM.RegOperand("x" + tempToReg.get(t));
                if (j.offset instanceof TempOperand t)
                    j.offset = new ASM.RegOperand("x" + tempToReg.get(t));
                if (j.rd instanceof TempOperand t)
                    j.rd = new ASM.RegOperand("x" + tempToReg.get(t));
            }

        }
    }

        @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ASM.AsmChunk chunk : AsmGen.asmChunks()) {
          for(ASM.Line line : chunk.lines) {
              if (line instanceof ASM.Instr instr) {
                sb.append(line.toString()).append("\n");
            }
          }
        }
        return sb.toString();
    }
}