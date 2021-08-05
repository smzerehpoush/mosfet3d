package me.mahdiyar;


public class FloatRange {

    public double min;
    public double max;

    public FloatRange(double d, double d1) {
        min = d;
        max = d1;
    }

    public FloatRange(FloatRange floatrange) {
        min = floatrange.min;
        max = floatrange.max;
    }


    public void union(FloatRange floatrange) {
        min = Math.min(floatrange.min, min);
        max = Math.max(floatrange.max, max);
    }
}
