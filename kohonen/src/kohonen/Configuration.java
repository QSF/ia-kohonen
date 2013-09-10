/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohonen;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author sean
 */
public class Configuration {
    
    private Properties properties = new Properties();
   
    // Parâmetro: taxa de aprendizado
    private double learningRate;
    
    // Parâmetro: pesos das arestas
    private String weight;
    
    // Parâmetro: quantidade de linhas de neurônios
    private int neuronsLine;
    
    // Parâmetro: quantidade de colunas neurônios
    private int neuronsColumn;
    
    // Parâmetro: raio de vizinhança
    private int radius;
    
    // Vetor com os exemplos de treinamento
    private List<Example> trainingSet = new ArrayList<Example>();
    
    // Representação da matriz de pesos
    private List<List<Double>> weightMatrix;
    
    private double[][] matrix;
    
    public Configuration(String configFile) {
        read(configFile);
    }
    
    public void read(String configFile) {
        try {
            properties.load(new FileInputStream(configFile));
            
            learningRate = Double.parseDouble(properties.getProperty("learning-rate"));
            neuronsLine = Integer.parseInt(properties.getProperty("neurons-line"));
            neuronsColumn = Integer.parseInt(properties.getProperty("neurons-column"));
            weight = properties.getProperty("weight");
            radius = Integer.parseInt(properties.getProperty("radius"));
            
            // Lê conjunto de treinamento
            readFromFile(new BufferedReader(new FileReader("training-set.txt")));
            
            // Monta a matriz de pesos
            createWeightMatrix();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void readFromFile(BufferedReader buffer) {
        String currentLine = null;
        try {
            while ((currentLine = buffer.readLine()) != null) {
                String[] examples = currentLine.split(",");
                
                Example ex = new Example(
                        parseBuying(examples[0]),
                        parseMaint(examples[1]), 
                        parseSafety(examples[2]));
                trainingSet.add(ex);
            }
          
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (buffer != null) { 
                    buffer.close(); 
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void createWeightMatrix() {
        int neuronsQuantity = this.neuronsLine * this.neuronsColumn;
        weightMatrix = new ArrayList<List<Double>>();
        
        for (int i = 0; i < neuronsQuantity; i++) {
            List<Double> list = new ArrayList<Double>();
            weightMatrix.add(list);
        }

        String[] weightParts = weight.split(",");

        int i = 0;
        
        for (List<Double> list : weightMatrix) {
            list.add(Double.parseDouble(weightParts[i]));
            list.add(Double.parseDouble(weightParts[i+1]));
            list.add(Double.parseDouble(weightParts[i+2]));
            i += 3;
        }
        listToMatrix();
    }
    
    public double parseBuying(String example) {
        
        double buying = -1;
        
        switch (example) {
            case "vhigh":
                buying = 0;
                break;
            case "high":
                buying = 1;       
                break;
            case "med":
                buying = 2;
                break;
            case "low":
                buying = 3;
                break;
            default:
                break;
        }
        return buying;
    }
    
    public double parseMaint(String example) {
        
        double maint = -1;
        
        switch (example) {
            case "vhigh":
                maint = 0;
                break;
            case "high":
                maint = 1;       
                break;
            case "med":
                maint = 2;
                break;
            case "low":
                maint = 3;
                break;
            default:
                break;
        }
        return maint;
    }
    
    public double parseSafety(String example) {
        
        double safety = -1;
        
        switch (example) {
            case "low":
                safety = 3;
                break;
            case "med":
                safety = 2;       
                break;
            case "high":
                safety = 1;
                break;
            default:
                break;
        }
        return safety;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public int getRadius() {
        return radius;
    }

    public List<Example> getTrainingSet() {
        return trainingSet;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getNeuronsLine() {
        return neuronsLine;
    }

    public int getNeuronsColumn() {
        return neuronsColumn;
    }
    
    public void print() {
        System.out.println("Taxa de aprendizado: " + learningRate);
        System.out.println("Quantidade de Linhas Neurônios: " + neuronsLine);
        System.out.println("Quantidade de Colunas Neurônios: " + neuronsColumn);
        System.out.println("Pesos das arestas: " + weight);
        System.out.println("Raio de vizinhança:  " + radius);
        
        System.out.println("\n*** Conjunto de treinamento ***");
        for (Example ex : trainingSet) {
            System.out.println(ex.getBuying() + "," + ex.getMaint() + "," + ex.getSafety());
        } 
        
        System.out.println("\n*** Matriz de pesos ***");
        for (List<Double> list : weightMatrix) {
            System.out.println(list.get(0) + "," + list.get(1) + "," + list.get(2));
        }
    }

    private void listToMatrix() {
        this.matrix = new double[3][this.neuronsLine * this.neuronsColumn];
        int i = 0;
        for (List<Double> list : this.weightMatrix) {
            matrix[0][i] = list.get(0);
            matrix[1][i] = list.get(1);
            matrix[2][i] = list.get(2);
            i++;
        }
    }
}
