package kohonen.test;

import kohonen.Plot;

/**
 *
 * @author vinicius
 */
public class PlotTest {
    public static void main(String[] args) {
        
        double[] x = {1,2,3,4,5,6,7,8,9} ,
                y = {1,2,3,4,5,6,7,8,9} ,
                z = {1,2,3,4,5,6,7,8,9};
        Plot.plot("", x, y, z);
    }
}
