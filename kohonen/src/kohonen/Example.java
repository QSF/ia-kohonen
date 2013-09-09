/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohonen;

/**
 *
 * @author sean
 */
public class Example {
    
    private double buying;
    private double maint;
    private double safety;

    public Example(double buying, double maint, double safety) {
        this.buying = buying;
        this.maint = maint;
        this.safety = safety;
    }

    public double getBuying() {
        return buying;
    }

    public void setBuying(double buying) {
        this.buying = buying;
    }

    public double getMaint() {
        return maint;
    }

    public void setMaint(double maint) {
        this.maint = maint;
    }

    public double getSafety() {
        return safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }
}
