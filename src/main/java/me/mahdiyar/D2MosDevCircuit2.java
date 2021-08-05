package me.mahdiyar;

import java.awt.*;


public class D2MosDevCircuit2 extends D2MosDevCircuit {

    protected int getVgsY() {
        return getMosY() / 2;
    }

    protected int getVdY() {
        return getMosY() / 4;
    }

    protected String getVdName() {
        super.Vd.reverse();
        return "Vds";
    }

    protected void drawChannel(Graphics g) {
        drawChannel(g, super.vgs - super.vd);
    }
}
