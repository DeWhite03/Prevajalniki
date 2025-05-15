package compiler.phase.asmgen;

import java.util.Vector;

import compiler.phase.*;

public class AsmGen extends Phase {

    private final static Vector<ASM.AsmChunk> asmChunks = new Vector<ASM.AsmChunk>();

	/**
	 * Constructs a new phase for the linearization of intermediate code.
	 */
    public AsmGen() {
        super("asmgen");
    }
    
	/**
	 * Adds a data chunk to a collection of all data chunks of the program.
	 * 
	 * @param asmChunk An asm chunk.
	 */
    public static void addAsmChunk(ASM.AsmChunk asmChunk) {
        asmChunks.add(asmChunk);
    }

	/**
	 * Returns a collection of all code chunks of the program.
	 * 
	 * @return A collection of all code chunks of the program.
	 */
	public static Vector<ASM.AsmChunk> asmChunks() {
		return new Vector<ASM.AsmChunk>(asmChunks);
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ASM.AsmChunk chunk : asmChunks) {
            sb.append(chunk.toString()).append("\n");
        }
        return sb.toString();
    }

}
