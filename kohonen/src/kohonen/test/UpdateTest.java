package kohonen.test;

import java.util.Random;

/**
 *
 * @author vinicius
 */
public class UpdateTest {
    public static void main(String[] args) {
        int winner = 9;
        int line = winner/4;
        System.out.println("line: " + line);
        int column = winner%4;
        System.out.println("column: " + column);
        
        int index = line*4 + column;
        System.out.println("index: " + index);
        
        Random random = new Random();
       
        for (int i = 0; i < 60; i++) {
           double valor = random.nextDouble();
           System.out.print(valor + ",");            
        }
    }
}
