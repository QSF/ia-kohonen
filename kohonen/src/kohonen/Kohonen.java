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
    private double[][] weightMatrix;
    
    public Kohonen() {
        Configuration configuration = new Configuration();
        
        learningRate = configuration.getLearningRate();
        radius = configuration.getRadius();
        trainingSet = configuration.getTrainingSet();
        weightMatrix = configuration.getMatrix();
    }
    
    public void execute() {
        for (Example ex : this.trainingSet) {
            int winner = defineWinner(ex);
            updateWeight(winner, ex);
        }
            
        plot();
    }
    
    public int defineWinner(Example ex) {
        return 0;
    }
    
    public void updateWeight(int neuronWinner, Example ex) {
        
    }
    
    public void plot() {
        
    }
}
