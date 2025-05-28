package compiler.phase.imclin;

import java.util.HashMap;
import java.util.Vector;

import compiler.phase.abstr.*;
import compiler.phase.imcgen.*;
import compiler.phase.memory.MEM;

public class ChunkTracker {
    
    public HashMap<String, IMC.TEMP> nameTempMap = new HashMap<>();
    public Vector<IMC.Stmt> statements = new Vector<IMC.Stmt>();
    public IMC.TEMP SL;
    public IMC.TEMP RV;
    public MEM.Label funExit;
    public MEM.Label funEntry;
    public boolean condStmt = false;
    public ChunkTracker(){

    }

    public void add(IMC.Stmt n) {
        statements.addLast(n);
    }
    
    public IMC.Stmt pop() {
        if (statements.size() == 0) {
            return null;
        }
        return statements.remove(statements.size() - 1);
    }

    public IMC.Stmt getLast() {
        if (statements.size() == 0) {
            return null;
        }
        return statements.get(statements.size() - 1);
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