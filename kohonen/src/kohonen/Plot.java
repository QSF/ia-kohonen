package kohonen;

import javax.swing.JFrame;
import org.math.plot.Plot3DPanel;

/**
 *
 * @author vinicius
 */
public class Plot {
    public static void plot(String title, double[] x, double[] y, double[] z) {
        Plot3DPanel plot = new Plot3DPanel();
        plot.addLinePlot(title, x, y, z);
        
        JFrame frame = new JFrame(title);
        frame.add(plot);
        frame.setSize(700,700);
        frame.setVisible(true);
    }
}
