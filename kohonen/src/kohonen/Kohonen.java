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
        //verificar a posição na matriz de vetores
        int line = neuronWinner/this.neuronsLine;
        int column = neuronWinner%this.neuronsColumn;
        
        //atualiza os pesos da vizinhança
        for (int i = line - this.radius; i < line + this.radius ; i++) {
            //linha não pode ser negativa
            if (i < 0) 
                i = 0;
            
            for (int j = column - this.radius; j < column + this.radius; j++) {
                //coluna não pode ser negativa
                if (j < 0) 
                    j = 0;
                //atualiza o peso
                this.updateNeuron(i, j, ex);
                
                //coluna não pode extrapolar máximo de colunas
                if (j == this.neuronsColumn - 1) {
                    //condição sai do for
                    j = column + this.radius;
                }
            }
            //linha não pode extrapolar a quantidade máxima de linhas
            if (i == this.neuronsLine - 1) {
                //condição de sai do for
                i = line + this.radius;
            }
        }
    }
    
    private void updateNeuron(int line, int column, Example ex) {
        //ve o index da matriz de peso
        int index = line*this.neuronsColumn + column;
        double x0 = ex.getBuying();
        double x1 = ex.getMaint();
        double x2 = ex.getSafety();
        //atualiza os pesos
        this.weightMatrix[0][index] += this.learningRate * (x0 - this.weightMatrix[0][index]);
        this.weightMatrix[1][index] += this.learningRate * (x1 - this.weightMatrix[1][index]);
        this.weightMatrix[2][index] += this.learningRate * (x2 - this.weightMatrix[2][index]);
    }
    
    public void plot() {
        
    }
}
