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
    
    // Parâmetro: quantidade de neurônios
    private int neuronsQuantity;
    
    // Vetor com os exemplos de treinamento
    private List<Example> trainingSet = new ArrayList<Example>();
    
    // Representação da matriz de pesos
    private List<List<Double>> weightMatrix;
    
    public Configuration() {
        read();
    }
    
    public void read() {
        try {
            properties.load(new FileInputStream("config.properties"));
            
            learningRate = Double.parseDouble(properties.getProperty("learning-rate"));
            neuronsQuantity = Integer.parseInt(properties.getProperty("neurons-quantity"));
            weight = properties.getProperty("weight");
            
            // Lê conjunto de treinamento
            // readFromFile(new BufferedReader(new FileReader("")));
            
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
                        Double.parseDouble(examples[0]),
                        Double.parseDouble(examples[1]), 
                        Double.parseDouble(examples[2]));
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
        
        weightMatrix = new ArrayList<List<Double>>(neuronsQuantity);
        
        for (int i = 0; i < neuronsQuantity; i++) {
            List<Double> list = new ArrayList<Double>(3);
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
    }
    
    public void print() {
        System.out.println("Taxa de aprendizado: " + learningRate);
        System.out.println("Quantidade de Neurônios: " + neuronsQuantity);
        System.out.println("Pesos das arestas: " + weight);
        
        /* System.out.println("\n*** Conjunto de treinamento ***");
        for (Example ex : trainingSet) {
            System.out.println(ex.getX1() + "," + ex.getX2() + "," + ex.getX3());
        } */
        
        System.out.println("\n*** Matriz de pesos ***");
        System.out.println(weightMatrix.size());
        for (List<Double> list : weightMatrix) {
            System.out.println(list.size());
            System.out.println(list.get(0) + "," + list.get(1) + "," + list.get(2));
        }
    }
}
