package kohonen;

import javax.swing.JFrame;
import org.math.plot.*;

/**
 *
 * @author vinicius
 */
public class Plot {
    public static void plot(String title, double[] x, double[] y, double[] z) {
        
        Plot3DPanel plot = new Plot3DPanel();
        plot.addScatterPlot(title, x, y, z);
        
        JFrame frame = new JFrame(title);
        frame.add(plot);
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
