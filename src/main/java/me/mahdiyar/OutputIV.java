package me.mahdiyar;


public class OutputIV extends FunctionData {

    private double Vt;
    private double K;
    private double lmbd;
    private boolean isNChannel;

    @Override
    protected void init() {
        Vt = 1.0D;
        K = 0.00050000000000000001D;
        lmbd = 0.01D;
        isNChannel = true;
    }

    @Override
    public boolean isCurrentDrawable() {
        return true;
    }

    @Override
    public void setBooleanParam(boolean flag) {
        if (isNChannel != flag) {
            isNChannel = flag;
            K = -K;
            lmbd = -lmbd;
            if (isNChannel)
                Vt = 1.0D;
            else
                Vt = -1D;
            setRange();
        }
    }

    @Override
    public void setDoubleParam(double d) {
        if (Vt != d) {
            if (Vt * d < 0.0D)
                return;
            Vt = d;
            setRange();
        }
    }

    @Override
    protected double f(double d, double d1) {
        double d2 = d1 - Vt;
        double d3;
        if (isNChannel) {
            if (d2 <= 0.0D)
                d3 = 0.0D;
            else if (d <= d2)
                d3 = K * (d2 * d - 0.5D * d * d);
            else
                d3 = 0.5D * K * d2 * d2 * (1.0D + lmbd * (d - d2));
        } else if (d2 >= 0.0D)
            d3 = 0.0D;
        else if (d >= d2)
            d3 = K * (d2 * d - 0.5D * d * d);
        else
            d3 = 0.5D * K * d2 * d2 * (1.0D + lmbd * (d - d2));
        return d3;
    }

    @Override
    public double[] getX() {
        double[] ad = new double[80];
        if (isNChannel)
            makeArray(ad, 0.0D, Vt + 5D);
        else
            makeArray(ad, 0.0D, Vt - 5D);
        return ad;
    }

    @Override
    public double[] getParam() {
        double[] ad = new double[5];
        if (isNChannel)
            makeArray(ad, Vt, Vt + 5D);
        else
            makeArray(ad, Vt, Vt - 5D);
        return ad;
    }

    private void makeArray(double[] ad, double d, double d1) {
        double d2 = (d1 - d) / (double) ad.length;
        ad[0] = d;
        for (int i = 1; i < ad.length; i++)
            ad[i] = ad[i - 1] + d2;

    }

    @Override
    public String getNameX() {
        return "Vds";
    }

    @Override
    public String getNameY() {
        return "Id ";
    }

    @Override
    public String getNameP() {
        return "Vgs";
    }

    @Override
    public Format getFormatY() {
        return new Format(2, 1000D);
    }

    @Override
    public Format getFormatP() {
        return new Format(2, 1.0D);
    }
}
