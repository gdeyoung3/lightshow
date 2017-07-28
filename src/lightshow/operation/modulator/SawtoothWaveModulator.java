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
public class SawtoothWaveModulator extends Modulator {
    public SawtoothWaveModulator(double subdivisionPeriod, boolean isPhaseInverted) {
        super(subdivisionPeriod, isPhaseInverted);
    }
    
    public SawtoothWaveModulator(double subdivisionPeriod) {
        super(subdivisionPeriod);
    }
    
    public SawtoothWaveModulator() {
        super();
    }
    
    @Override
    public double getOutput(double input) {
        double adjustedCount = input / getSubdivisionPeriod();
        double baseWave = adjustedCount % 1;
        
        if (getIsPhaseInverted()) {
            return 1.0 - baseWave;
        } else {
            return baseWave;
        }
    }
}
