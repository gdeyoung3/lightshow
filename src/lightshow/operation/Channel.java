/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow.operation;

import java.awt.Color;

/**
 *
 * @author rferretti
 */
public abstract class Channel {
    private final int id;
    
    Channel(int id) {
        this.id = id;
    }
    
    public int GetId() {
        return id;
    }
    
    public abstract void SetColor(Color newColor);
}
