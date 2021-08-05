package me.mahdiyar;

public class Conversion {

    private final double xFillFactor;
    private final double yFillFactor;
    private double xToScr;
    private double yToScr;
    private double xToPhys;
    private double yToPhys;

    public Conversion() {
        xFillFactor = 0.94999999999999996D;
        yFillFactor = 0.94999999999999996D;
        setPhysToScr(1.0D, 1.0D);
    }

    public int toScrX(double d) {
        return (int) (xToScr * d + 0.5D);
    }

    public int[] toScrX(double[] ad) {
        int[] ai = new int[ad.length];
        for (int i = 0; i < ad.length; i++)
            ai[i] = (int) (xToScr * ad[i] + 0.5D);

        return ai;
    }

    public int toScrX(int i) {
        return (int) (xToScr * (double) i + 0.5D);
    }

    public int toScrY(double d) {
        return (int) (yToScr * d + 0.5D);
    }

    public int toScrY(int i) {
        return (int) (yToScr * (double) i + 0.5D);
    }

    public int[][] toScrY(double[][] ad) {
        int[][] ai = new int[ad.length][];
        for (int i = 0; i < ad.length; i++)
            ai[i] = toScrY(ad[i]);

        return ai;
    }

    public int[] toScrY(double[] ad) {
        int[] ai = new int[ad.length];
        for (int i = 0; i < ad.length; i++)
            ai[i] = (int) (yToScr * ad[i]);

        return ai;
    }

    public double toPhysX(int i) {
        return xToPhys * (double) i;
    }

    public double toPhysY(int i) {
        return yToPhys * (double) i;
    }

    public void setPhysToScr(double d, double d1) {
        xToScr = d;
        xToPhys = 1.0D / d;
        yToScr = d1;
        yToPhys = 1.0D / d1;
    }

    public void setX(int i, double d) {
        xToScr = (xFillFactor * (double) i) / d;
        xToPhys = 1.0D / xToScr;
    }

    public void setY(int i, double d) {
        yToScr = (yFillFactor * (double) i) / d;
        yToPhys = 1.0D / yToScr;
    }


}
