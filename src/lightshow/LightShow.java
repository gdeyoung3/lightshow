/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow;

import lightshow.operation.BeatSynchronizer;
import lightshow.operation.Operation;
import lightshow.scheduler.ClockScheduler;
import java.util.ArrayList;
import java.util.List;
import lightshow.scheduler.SubscribableSchedulerSubscriber;

/**
 *
 * @author rferretti
 */
public class LightShow implements Runnable, SubscribableSchedulerSubscriber { // TODO: stoppable?
    private final int schedulerFrequency;
    //private final Iterable<Channel> channels;
    private final double tempo;
    private final ClockScheduler scheduler;
    private final List<Operation> operations;
    private final List<BeatSynchronizer> synchronizers;
    private int currentOperationIndex;
    
    public LightShow(int schedulerFrequency, /*Iterable<Channel> channels, */double tempo) {
        this.schedulerFrequency = schedulerFrequency;
        //this.channels = channels;
        this.tempo = tempo;
        scheduler = new ClockScheduler(schedulerFrequency);
        operations = new ArrayList<>(); // ArrayList because we want accesses to be quick
        synchronizers = new ArrayList<>(); // no particular reason for ArrayList
    }
    
    @Override
    public void run() {
        if (operations.size() > 0) {
            currentOperationIndex = 0;
            scheduler.subscribe(this);
            scheduler.start();
        }
    }
    
    @Override
    public void scheduledEventHandler() {
        if (!operations.get(currentOperationIndex).operate()) {
            operateNext();
        }
        
        synchronizers.forEach(synchronizer -> {
            synchronizer.tick(schedulerFrequency);
        });
    }
    
    public void stop() {
        // TODO: reset channels?
        // TODO: reinit operations (inactivate all)
        
        scheduler.stop();
        scheduler.unsubscribe(this);
    }
    
    public void Import(LightShow toImport) {
        // TODO: grab all the operations from toImport
    }
    
    final BeatSynchronizer getNewSynchronizer(int subdivisions, int maxSubdivisions) {
        BeatSynchronizer sync = new BeatSynchronizer(tempo, subdivisions, maxSubdivisions);
        synchronizers.add(sync);
        
        return sync;
    }
    
    final BeatSynchronizer getNewSynchronizer(int subdivisions) {
        // 2 max subdivisions is all you need for a bitwise beat clock
        return getNewSynchronizer(subdivisions, 2);
    }
    
    final void addOperation(Operation toAdd) {
        operations.add(toAdd);
    }
    
    private void operateNext() {
        currentOperationIndex++;
        
        if (currentOperationIndex > operations.size() - 1) {
            currentOperationIndex = 0;
        }
        
        Operation nextOperation = operations.get(currentOperationIndex);
        nextOperation.init();
        nextOperation.operate();
    }
}

// TODO:
// bpm set... time between any two method calls
// beat sync... measure/phrase starts at method call