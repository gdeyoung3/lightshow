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
public class ColorChangeOperation extends Operation {
    private static final int MAX_OUTPUT_LENGTH = 50;
    
    public ColorChangeOperation(BeatSynchronizer synchronizer, Modulator modulator, int beatLifetime) {
        super(synchronizer, modulator, beatLifetime);
        
        //System.out.println();
        pushCursorPos();
    }
    
    @Override
    void operate(boolean isNewBeat) {
        popCursorPos();
        
        if (isNewBeat) {
            //System.out.println();
            
            System.out.print(beatAge + 1);
        }
        
        moveCursorPos(5);
        System.out.print(getModulatorOutput());
        
        // TODO: user modulator to modulate between two different colors
        // TODO: ability to use color from previous operation
    }
    
    private void printOnSameLine(String toPrint) {
        int outputCharRemaining = MAX_OUTPUT_LENGTH - toPrint.length();
        String clearRemaining = new String(new char[outputCharRemaining])
            .replace('\0', ' ');
        
        popCursorPos();
        System.out.print(toPrint);
        System.out.print(clearRemaining);
    }
    
    private void terminalCommand(String command) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%s", escCode, command));
    }
    
    private void pushCursorPos() {
        terminalCommand("s");
    }
    
    private void popCursorPos() {
        terminalCommand("u");
    }
    
    private void moveCursorPos(int forwardCount) {
        terminalCommand(String.format("%dC", forwardCount));
    }
}
