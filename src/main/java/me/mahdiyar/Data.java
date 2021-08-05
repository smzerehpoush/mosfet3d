package me.mahdiyar;

import java.awt.*;

public abstract class Data {

    private static final Format FORMAT = new Format(2, 1.0D);
    private FloatRange xRange;
    private FloatRange yRange;

    protected Data() {
        init();
        setRange();
    }

    public abstract double[] getX();

    public abstract double[][] getY();

    public double[] getParam() {
        return null;
    }

    public FloatRange getRangeX() {
        return xRange;
    }

    public FloatRange getRangeY() {
        return yRange;
    }

    public String getNameX() {
        return "x-axis";
    }

    public String getNameY() {
        return "y-axis";
    }

    public String getNameP() {
        return "param";
    }

    public Format getFormatX() {
        return FORMAT;
    }

    public Format getFormatY() {
        return FORMAT;
    }

    public Format getFormatP() {
        return FORMAT;
    }

    public Color getColor() {
        return Color.blue;
    }

    protected void init() {
    }

    protected void setRange() {
        double[] ad = getX();
        double d = ad[0];
        double d1 = ad[0];
        for (double v : ad) {
            if (v < d)
                d = v;
            if (v > d1)
                d1 = v;
        }

        xRange = new FloatRange(d, d1);
        double[][] ad1 = getY();
        d = ad1[0][0];
        d1 = d;
        for (double[] doubles : ad1) {
            for (int k = 0; k < ad1[0].length; k++) {
                if (doubles[k] < d)
                    d = doubles[k];
                if (doubles[k] > d1)
                    d1 = doubles[k];
            }

        }

        yRange = new FloatRange(d, d1);
    }

}
