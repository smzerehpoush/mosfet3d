package me.mahdiyar;

import java.awt.*;

public class BatterySymbol
        implements VisualElement {

    private final Point plus;
    private final Point minus;
    private int scale;
    private int plusToMinus;
    private int plusHalfWidth;
    private int minusHalfWidth;
    private String align;
    private String plusSide;

    public BatterySymbol(String s, String s1) {
        scale = 2;
        plusToMinus = 4;
        plusHalfWidth = 4;
        plus = new Point(0, 0);
        minus = new Point(0, 0);
        align = "horizontal";
        plusSide = "right";
        setAlign(s, s1);
        setScale();
    }

    public void setLocation(int i, int j) {
        plus.x = i;
        plus.y = j;
        setMinus();
    }

    public void draw(Graphics g) {
        if (align.equals("vertical")) {
            drawVertical(g);
        } else {
            drawHorizontal(g);
        }
    }

    public void setAlign(String s, String s1) {
        if (s.equals("horizontal") || s.equals("vertical"))
            align = s;
        else
            return;
        if (align.equals("horizontal")) {
            if (s1.equals("right") || s1.equals("left")) {
                plusSide = s1;
            }
            return;
        }
        if (s1.equals("up") || s1.equals("down")) {
            plusSide = s1;
        }
    }

    public void setScale(int i) {
        if (i > 4 || i < 0)
            scale = 2;
        else
            scale = i;
        setScale();
    }

    public Point getLocation() {
        return plus;
    }

    public void reverse() {
        if (plus.y == minus.y) {
            int i = plus.x;
            plus.x = minus.x;
            minus.x = i;
            if (plus.x > minus.x) {
                plusSide = "right";
            } else {
                plusSide = "left";
            }
            return;
        }
        if (plus.x == minus.x) {
            int j = plus.y;
            plus.y = minus.y;
            minus.y = j;
            if (plus.y < minus.y) {
                plusSide = "up";
                return;
            }
            plusSide = "down";
        }
    }

    public int getLeftX() {
        if (align.equals("vertical"))
            return plus.x - plusHalfWidth;
        return Math.min(plus.x, minus.x);
    }

    public int getRightX() {
        if (align.equals("vertical"))
            return plus.x + plusHalfWidth;
        return Math.max(plus.x, minus.x);
    }

    public int getHiY() {
        if (align.equals("vertical")) {
            return Math.min(plus.y, minus.y);
        } else {
            return plus.y - plusHalfWidth;
        }
    }

    public int getLowY() {
        if (align.equals("vertical")) {
            return Math.max(plus.y, minus.y);
        } else {
            return plus.y + plusHalfWidth;
        }
    }

    private void setScale() {
        switch (scale) {
            case 0: // '\0'
                plusToMinus = 2;
                plusHalfWidth = 2;
                return;

            case 1: // '\001'
                plusToMinus = 3;
                plusHalfWidth = 3;
                return;

            case 2: // '\002'
                plusToMinus = 4;
                plusHalfWidth = 4;
                return;

            case 3: // '\003'
                plusToMinus = 5;
                plusHalfWidth = 6;
                return;

            case 4: // '\004'
                plusToMinus = 6;
                plusHalfWidth = 8;
                return;
            default:
        }
    }

    private void drawVertical(Graphics g) {
        g.drawLine(plus.x - plusHalfWidth, plus.y, plus.x + plusHalfWidth, plus.y);
        g.drawLine(minus.x - minusHalfWidth, minus.y, minus.x + minusHalfWidth, minus.y);
        if (scale >= 2 && scale <= 4) {
            g.drawLine(plus.x - plusHalfWidth, plus.y - 1, plus.x + plusHalfWidth, plus.y - 1);
            g.drawLine(minus.x - minusHalfWidth, minus.y - 1, minus.x + minusHalfWidth, minus.y - 1);
        }
    }

    private void drawHorizontal(Graphics g) {
        g.drawLine(plus.x, plus.y + plusHalfWidth, plus.x, plus.y - plusHalfWidth);
        g.drawLine(minus.x, minus.y + minusHalfWidth, minus.x, minus.y - minusHalfWidth);
        if (scale >= 2 && scale <= 4) {
            g.drawLine(plus.x + 1, plus.y + plusHalfWidth, plus.x + 1, plus.y - plusHalfWidth);
            g.drawLine(minus.x + 1, minus.y + minusHalfWidth, minus.x + 1, minus.y - minusHalfWidth);
        }
    }

    private void setMinus() {
        minusHalfWidth = plusHalfWidth / 2;
        if (align.equals("vertical")) {
            setVerticalMinus();
        } else {
            setHorizontalMinus();
        }
    }

    private void setVerticalMinus() {
        minus.x = plus.x;
        if (plusSide.equals("down")) {
            minus.y = plus.y - plusToMinus;
        } else {
            minus.y = plus.y + plusToMinus;
        }
    }

    private void setHorizontalMinus() {
        minus.y = plus.y;
        if (plusSide.equals("left")) {
            minus.x = plus.x + plusToMinus;
        } else {
            minus.x = plus.x - plusToMinus;
        }
    }
}
