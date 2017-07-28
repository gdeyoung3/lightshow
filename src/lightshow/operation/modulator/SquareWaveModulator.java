/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow.operation.modulator;

/**
 *
 * @author rferretti
 */
public class SquareWaveModulator extends Modulator {
    public SquareWaveModulator(double subdivisionPeriod, boolean isPhaseInverted) {
        super(subdivisionPeriod, isPhaseInverted);
    }
    
    public SquareWaveModulator(double subdivisionPeriod) {
        super(subdivisionPeriod);
    }
    
    public SquareWaveModulator() {
        super();
    }
    
    @Override
    public double getOutput(double input) {
        double adjustedCount = input / getSubdivisionPeriod();
        double baseWave = Math.round(adjustedCount % 1);
        
        if (getIsPhaseInverted()) {
            return 1.0 - baseWave;
        } else {
            return baseWave;
        }
    }
}
