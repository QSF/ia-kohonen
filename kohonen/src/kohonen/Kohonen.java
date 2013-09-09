/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohonen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sean
 */
public class Kohonen {
    
    private double learningRate;
    private int radius;
    private List<Example> trainingSet = new ArrayList<Example>();
    private List<List<Double>> weightMatrix;
    
    public Kohonen() {
        Configuration configuration = new Configuration();
        
        learningRate = configuration.getLearningRate();
        radius = configuration.getRadius();
        trainingSet = configuration.getTrainingSet();
        weightMatrix = configuration.getWeightMatrix();
    }
    
    public void execute() {
        updateWeight(defineWinner());
        plot();
    }
    
    public int defineWinner() {
        return 0;
    }
    
    public void updateWeight(int neuronWinner) {
        
    }
    
    public void plot() {
        
    }
}
