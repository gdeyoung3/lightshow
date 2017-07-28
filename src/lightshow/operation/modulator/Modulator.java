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
public abstract class Modulator {
    private final boolean isPhaseInverted;
    private double subdivisionPeriod;
    
    public Modulator(double subdivisionPeriod, boolean isPhaseInverted) {
        this.subdivisionPeriod = subdivisionPeriod;
        this.isPhaseInverted = isPhaseInverted;
    }
    
    public Modulator(double subdivisionPeriod) {
        this(subdivisionPeriod, false);
    }
    
    public Modulator() {
        this(0.0);
    }
    
    public final void setSubdivisionPeriod(double subdivisionPeriod) {
        if (this.subdivisionPeriod == 0.0) {
            this.subdivisionPeriod = subdivisionPeriod;
        }
    }
    
    final double getSubdivisionPeriod() {
        return subdivisionPeriod;
    }
    
    final boolean getIsPhaseInverted() {
        return isPhaseInverted;
    }
    
    public abstract double getOutput(double input);
}
