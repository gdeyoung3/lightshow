/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightshow.operation;

/**
 *
 * @author rferretti
 */
public class BeatSynchronizer {
    private final double maxDuration;
    private final double durationPerSubdivision;
    private double accumulatedDuration;
    
    public BeatSynchronizer(double tempo, int subdivisions, int maxSubdivisions) {
        // the inverse of the tempo in beats per minute, subdivided, converted to milliseconds
        durationPerSubdivision = (60.0 * 1000.0) / (tempo / 4.0 * (double)subdivisions);
        maxDuration = (double)maxSubdivisions * durationPerSubdivision;
        accumulatedDuration = 0.0;
    }
    
    public void tick(Integer duration) {
        accumulatedDuration += (double)duration;
        
        if (accumulatedDuration >= maxDuration) {
            accumulatedDuration -= maxDuration;
        }
    }
    
    public int getCount() {
        return (int)(accumulatedDuration / durationPerSubdivision);
    }
    
    public long getDurationToNext() {
        return (long)(durationPerSubdivision - (accumulatedDuration % durationPerSubdivision));
    }
    
    public double getSubdivisionPercentage() {
        double countAndPercentage = accumulatedDuration / durationPerSubdivision;
        return countAndPercentage - Math.floor(countAndPercentage);
    }
}
