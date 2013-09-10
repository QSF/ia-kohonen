/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohonen;

/**
 *
 * @author sean
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean isRealTime = false;
        //real time ou não
        if (args.length >= 1) {
            if (args[0].equals("-rt=true"))
                isRealTime = true;
        } else {
            System.out.println("Faltando argumento -rt=");
            System.exit(-1);
        }
        
        String configFile = "config.properties";
        if (args.length >= 2) {
            configFile = args[1];
        }
        String widthType = "";
        if (args.length >= 3) {
            widthType = args[2];
        }
        
        Configuration configuration = new Configuration(configFile);
        new Kohonen(isRealTime, configuration, widthType).execute();
    }
}
