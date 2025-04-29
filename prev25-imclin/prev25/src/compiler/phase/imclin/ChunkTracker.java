package compiler.phase.imclin;

import java.util.Vector;

import compiler.phase.abstr.*;
import compiler.phase.imcgen.*;
import compiler.phase.memory.MEM;

public class ChunkTracker {
    public Vector<IMC.Stmt> statements = new Vector<IMC.Stmt>();
    public IMC.TEMP SL;
    public IMC.TEMP RV;
    public MEM.Label funExit;
    public MEM.Label funEntry;
    public ChunkTracker(){

    }
    public void add(IMC.Stmt n){
        statements.addLast(n);
    }
    public void addFirst(IMC.Stmt n){
        statements.add(0,n);
    }
    public void addAll(Vector<IMC.Stmt> n){
        statements.addAll(n);
    }

    public Vector<IMC.Stmt> getVec(){
        return statements;
    }
}