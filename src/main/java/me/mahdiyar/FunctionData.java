package me.mahdiyar;

public abstract class FunctionData extends Data {

    protected abstract double f(double d, double d1);

    public abstract boolean isCurrentDrawable();

    public double[] getCurrentY(double d) {
        double[] ad = getX();
        double[] ad1 = new double[ad.length];
        new Format(2, 1.0D);
        for (int i = 0; i < ad1.length; i++)
            ad1[i] = f(ad[i], d);

        return ad1;
    }

    public void setDoubleParam(double d) {
    }

    public void setBooleanParam(boolean flag) {
    }

    public double getY(double d, double d1) {
        return f(d, d1);
    }

    public double[][] getY() {
        double[] ad = getParam();
        double[][] ad1;
        if (ad != null) {
            ad1 = new double[ad.length][];
        } else {
            ad1 = new double[1][];
            ad = new double[1];
        }
        for (int i = 0; i < ad1.length; i++)
            ad1[i] = getCurrentY(ad[i]);

        return ad1;
    }
}
