/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow.operation;

import lightshow.operation.modulator.Modulator;

/**
 *
 * @author rferretti
 */
public abstract class Operation {
    private final BeatSynchronizer synchronizer;
    private final Modulator modulator;
    private boolean isRepeated;
    final int beatLifetime;
    int lastBeatCount, beatAge;
    
    public Operation(BeatSynchronizer synchronizer, Modulator modulator, int beatLifetime) {
        this.synchronizer = synchronizer;
        this.modulator = modulator;
        this.modulator.setSubdivisionPeriod((double)beatLifetime);
        this.beatLifetime = beatLifetime;
        
        init();
    }
    
    public final void init() {
        lastBeatCount = -1; // trigger a new beat
        beatAge = -1; // will be immediately incremented and used
    }
    
    public final boolean operate() {
        boolean isNewBeat = false;
        int synchronizerCount = synchronizer.getCount();
        
        // NOTE: != instead of > because the synchronizer count may reset
        if (synchronizerCount != lastBeatCount) {
            lastBeatCount = synchronizerCount;
            beatAge++;
            isNewBeat = true;
        }
        
        boolean isExpired = (beatAge == beatLifetime);
        
        if (!isExpired) {
            operate(isNewBeat);
        }
        
        return !isExpired;
    }
    
    final double getModulatorOutput() {
        double input = (double)beatAge + synchronizer.getSubdivisionPercentage();
        
        return modulator.getOutput(input);
    }
    
    abstract void operate(boolean isNewBeat);
}
