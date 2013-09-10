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
    private Plot plot;
    private String widthType;
    
    private boolean isRealTime;
    private String title;
    
    private double learningRate;
    private int radius;
    private List<Example> trainingSet = new ArrayList<Example>();
    private double[][] weightMatrix;
    private int neuronsLine;
    private int neuronsColumn;
    
    public Kohonen(boolean isRealTime,Configuration configuration, String widthType) {        
        this.isRealTime = isRealTime;
        
        learningRate = configuration.getLearningRate();
        radius = configuration.getRadius();
        trainingSet = configuration.getTrainingSet();
        weightMatrix = configuration.getMatrix();
        neuronsLine = configuration.getNeuronsLine();
        neuronsColumn = configuration.getNeuronsColumn();
        
        this.widthType = widthType;
        
        this.title = "";
        title += "taxa aprend.:" + this.learningRate;
        title += "\tcamada de saída:" + this.neuronsLine + "x" + this.neuronsColumn;
        title += "\traio:" + this.radius;
        title += "\t" + this.widthType;
        if (this.isRealTime)
            this.plot = new Plot(title);
        
    }
    
    public void execute() {
        System.out.println("Começando o treinamento ...");
        for (Example ex : this.trainingSet) {
            if (this.isRealTime)
                this.plotChart();
            int winner = defineWinner(ex);
            updateWeight(winner, ex);
        }
        if (!this.isRealTime)
            this.plot = new Plot(title);
        this.plotChart();
    }
    
    public int defineWinner(Example ex) {
        System.out.println("***Caso de teste: " + ex.toString());
        
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
        System.out.println("Neurônio Vencedor: " + line + "," + column);
        System.out.println("Atualizando: ");
        //atualiza os pesos da vizinhança
        for (int i = line - this.radius; i <= line + this.radius && i 
                                                    < this.neuronsLine ; i++) {
            //linha não pode ser negativa
            if (i < 0) 
                i = 0;
            
            for (int j = column - this.radius; j <= column + this.radius
                    && j < this.neuronsColumn; j++) {
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
        
        System.out.print(line + "," + column);
        System.out.print(" => ");
        System.out.print(this.weightMatrix[0][index] + "\t");
        System.out.print(this.weightMatrix[1][index] + "\t");
        System.out.println(this.weightMatrix[2][index]);
    }
    
    public void plotChart() {
        int neuronsQuantity = this.neuronsLine*this.neuronsColumn;
        
        double[] x = new double[neuronsQuantity];
        double[] y = new double[neuronsQuantity];
        double[] z = new double[neuronsQuantity];
        
        for (int i = 0; i < neuronsQuantity; i++) {
            x[i] = this.weightMatrix[0][i];
            y[i] = this.weightMatrix[1][i];
            z[i] = this.weightMatrix[2][i];
        }
        this.plot.plot(x, y, z);
    }
}
