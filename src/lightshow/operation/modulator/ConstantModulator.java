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
public class ConstantModulator extends Modulator {
    public ConstantModulator() {
        super();
    }
    
    @Override
    public double getOutput(double input) {
        return 1.0;
    }
}
