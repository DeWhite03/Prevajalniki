package compiler.phase.regall;

public class RegAll extends Phase {
    public RegAll(int numOfRegs) {
        if(x < 7 && x > 32) {
            throw new Report.Error("The allowed number of registers is [7, 32]")
        }
        numOfRegs -= 5 // reserving registers

    }

    public Graph build(ASM.AsmChunk code) {
        Graph graph = new Graph();
        
    }

}
