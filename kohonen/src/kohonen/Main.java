/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohonen;

import java.util.Random;

/**
 *
 * @author sean
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String configFile = "config.properties";
        if (args.length == 1) {
            configFile = args[0];
        }
        Configuration configuration = new Configuration(configFile);
        new Kohonen(configuration).execute();
    }
}
