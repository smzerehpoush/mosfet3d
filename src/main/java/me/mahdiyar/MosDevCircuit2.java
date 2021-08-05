package me.mahdiyar;

import java.awt.*;

class MosDevCircuit2 extends MosDevCircuit {

    MosDevCircuit2() {
    }

    @Override
    protected int getVgsY() {
        return getMosY() / 2;
    }

    @Override
    protected int getVdY() {
        return getMosY() / 4;
    }

    @Override
    protected String getVdName() {
        return "Vds";
    }

    protected void drawChannel(Graphics g) {
        drawChannel(g, super.vgs - super.vd);
    }
}
