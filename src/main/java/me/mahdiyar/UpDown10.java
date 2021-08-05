package me.mahdiyar;

import java.awt.*;

public class UpDown10 extends UpDown {

    public UpDown10(int i, int j, Color color, Color color1) {
        super(i, j, color, color1);
    }

    @Override
    public boolean mouseDown(Event event, int i, int j) {
        if (i >= 0 && i <= super.width && j >= 0 && j <= super.dyArrow) {
            if (super.listener != null)
                super.listener.start();
            super.topPressed = true;
            repaint();
            return true;
        }
        if (i >= 0 && i <= super.width && super.dyArrow < j && j <= super.height) {
            if (super.listener != null)
                super.listener.start();
            super.bottomPressed = true;
            repaint();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mouseDrag(Event event, int i, int j) {
        if (super.topPressed && (i < 0 || i > super.width || j < 0 || j > super.dyArrow)) {
            if (super.listener != null)
                super.listener.stop();
            super.topPressed = false;
            repaint();
            return true;
        }
        if (super.bottomPressed && (i < 0 || i > super.width || j < super.dyArrow || j > super.height)) {
            if (super.listener != null)
                super.listener.stop();
            super.bottomPressed = false;
            repaint();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mouseUp(Event event, int i, int j) {
        if (super.listener != null)
            super.listener.stop();
        if (super.topPressed) {
            super.topPressed = false;
            repaint();
            return true;
        }
        if (super.bottomPressed) {
            super.bottomPressed = false;
            repaint();
            return true;
        } else {
            return false;
        }
    }
}
