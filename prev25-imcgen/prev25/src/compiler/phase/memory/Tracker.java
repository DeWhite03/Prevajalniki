package compiler.phase.memory;

public class Tracker{
    public long depth;
    public long size;
    public long offset;
    public boolean isInFunctionParam;
    public boolean isInFunction;

    public Tracker(long depth, long size, long offset) {
        this.depth = depth;
        this.size = size;
        this.offset = offset;
        this.isInFunctionParam = false;
        this.isInFunction = false;
    }
    public Tracker(Tracker t){
        this.depth = t.depth;
        this.size = t.size;
        this.offset = t.offset;
        this.isInFunctionParam = t.isInFunctionParam;
    }
    public Tracker() {
        this.depth = -1;
        this.size = 0;
        this.offset = 0;
        this.isInFunctionParam = false;
    }

    void changeState() {
        isInFunctionParam = !isInFunctionParam;
    }
    
    void changeIsInFunctionState(){
        isInFunction = !isInFunction;
    }
    
    public void setVals(long depth, long size, long offset) {
        this.depth = depth;
        this.size = size;
        this.offset = offset;
    }
    @Override
    public String toString(){
        return "depth: " + depth + ", size: " + size + ", offset: " + offset +", isInFunctionParam: " + isInFunctionParam + ", isInFunction:" + isInFunction;
    }
}