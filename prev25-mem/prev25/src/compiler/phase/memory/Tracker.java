package compiler.phase.memory;

public class Tracker {
    long depth;
    long size;
    long offset;

    public Tracker(long depth, long size, long offset) {
        this.depth = depth;
        this.size = size;
        this.offset = offset;
    }

    public Tracker() {

        this.depth = 0;
        this.size = 0;
        this.offset = 0;
    }
    
    public void setVals(long depth, long size, long offset) {
        this.depth = depth;
        this.size = size;
        this.offset = offset;
    }
}