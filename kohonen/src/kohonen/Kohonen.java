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
    private int neuronsLine;
    private int neuronsColumn;
    
    public Kohonen() {
        Configuration configuration = new Configuration();
        
        learningRate = configuration.getLearningRate();
        radius = configuration.getRadius();
        trainingSet = configuration.getTrainingSet();
        weightMatrix = configuration.getMatrix();
        neuronsLine = configuration.getNeuronsLine();
        neuronsColumn = configuration.getNeuronsColumn();
    }
    
    public void execute() {
        for (Example ex : this.trainingSet) {
            int winner = defineWinner(ex);
            //updateWeight(winner, ex);
        }
            
      //  plot(); 
    }
    
    public int defineWinner(Example ex) {
        
        int position = 0;
        double euclideanDistance = 0;
        
        double sum = Math.pow((weightMatrix[0][0] - ex.getBuying()), 2);
        sum += Math.pow((weightMatrix[1][0] - ex.getMaint()), 2);
        sum += Math.pow((weightMatrix[2][0] - ex.getSafety()), 2);
        
        euclideanDistance = Math.sqrt(sum);
        
        int neuronsQuantity = neuronsLine * neuronsColumn; 
   
        for (int i = 1; i < neuronsQuantity; i++) {
            sum = Math.pow((weightMatrix[0][i] - ex.getBuying()), 2);
            sum += Math.pow((weightMatrix[1][i] - ex.getMaint()), 2);
            sum += Math.pow((weightMatrix[2][i] - ex.getSafety()), 2);
            
            if (Math.sqrt(sum) < euclideanDistance) {
                euclideanDistance = Math.sqrt(sum);
                position = i;
            }
        }
        return position;
    }
    
    public void updateWeight(int neuronWinner, Example ex) {
        
    }
    
    public void plot() {
        
    }
}
