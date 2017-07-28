/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow;

import lightshow.operation.BeatSynchronizer;
import lightshow.operation.ColorChangeOperation;
import lightshow.operation.modulator.ConstantModulator;
import lightshow.operation.modulator.SawtoothWaveModulator;
import lightshow.operation.modulator.SquareWaveModulator;

/**
 *
 * @author rferretti
 */
public class BasicLightShow extends LightShow {
    public BasicLightShow(int schedulerFrequency, /*Iterable<Channel> channels, */int tempo) {
        super(schedulerFrequency, /*channels, */tempo);
        
        BeatSynchronizer half = getNewSynchronizer(2);
        BeatSynchronizer quarter = getNewSynchronizer(4);
        BeatSynchronizer sixteenth = getNewSynchronizer(16);
        
        //addOperation(new ColorChangeOperation(s1, new SawtoothWaveModulator(1, true), 2));
        //addOperation(new ColorChangeOperation(s2, new SquareWaveModulator(1, true), 4));
        //addOperation(new ColorChangeOperation(s3, new ConstantModulator(), 16));
        
        addOperation(new ColorChangeOperation(quarter, new SawtoothWaveModulator(4, true), 4));
        addOperation(new ColorChangeOperation(quarter, new SquareWaveModulator(2, true), 4));
        
    }
}
