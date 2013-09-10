package kohonen;

import javax.swing.JFrame;
import org.math.plot.*;

/**
 *
 * @author vinicius
 */
public class Plot {
    private JFrame frame;
    private Plot3DPanel plot = null;
    
    public Plot(String title) {
        this.frame = new JFrame(title);
        this.frame.setSize(700,700);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
    
    public void plot(double[] x, double[] y, double[] z) {
        
        //limpa o anterior
        if (this.plot != null)
            this.plot.removeAllPlots();
        else {
            this.plot = new Plot3DPanel();
            this.frame.add(plot);
        }
        //exibe o novo
//        this.plot = new Plot3DPanel();
        this.plot.addScatterPlot("", x, y, z);
//        this.frame.add(plot);
        this.frame.repaint();
        this.frame.validate();
        
    }
}
