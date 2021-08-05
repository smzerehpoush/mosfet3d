package me.mahdiyar;

import java.awt.*;

public class Plot {
    private Plot() {
    }

    public static void drawArrowRight(int i, int j, int k, Graphics g) {
        g.drawLine(i, j, i - k, j - k);
        g.drawLine(i, j, i - k, j + k);
    }

    public static void drawArrowLeft(int i, int j, int k, Graphics g) {
        g.drawLine(i, j, i + k, j - k);
        g.drawLine(i, j, i + k, j + k);
    }

    public static void drawArrowUp(int i, int j, int k, Graphics g) {

        g.drawLine(i, j, i - k, j + k);
        g.drawLine(i, j, i + k, j + k);

    }

    public static void drawArrowDown(int i, int j, int k, Graphics g) {
        g.drawLine(i, j, i - k, j - k);
        g.drawLine(i, j, i + k, j - k);
    }
}
