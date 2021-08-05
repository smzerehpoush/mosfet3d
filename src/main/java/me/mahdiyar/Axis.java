package me.mahdiyar;

import java.awt.*;

public class Axis {

    private final PlotCanvas canvas;
    private final Conversion conv;
    private final Point origin;
    private final boolean logX;
    private final boolean logY;
    private DataWrapper data;
    private boolean labelXUp;
    private boolean labelYLeft;
    private boolean arrowUp;
    private boolean arrowLeft;
    private IntRange xLogRange;
    private IntRange yLogRange;

    public Axis(PlotCanvas plotcanvas) {
        canvas = plotcanvas;
        origin = new Point(0, 0);
        labelXUp = false;
        labelYLeft = true;
        arrowUp = true;
        arrowLeft = false;
        xLogRange = null;
        yLogRange = null;
        logX = false;
        logY = false;
        conv = plotcanvas.getConversion();
    }

    public void init() {
        data = canvas.getData();
    }

    public void draw(Graphics g) {
        FontMetrics fontmetrics = g.getFontMetrics();
        Format format = data.getFormatX();
        Format format1 = data.getFormatY();
        initX(fontmetrics, format1);
        initY(fontmetrics);
        drawX(g, format);
        drawY(g, format1);
    }

    public Point getOrigin() {
        return origin;
    }

    private void drawX(Graphics g, Format format) {
        int i = canvas.getSize().width;
        g.drawLine(0, origin.y, i, origin.y);
        FontMetrics fontmetrics = g.getFontMetrics();
        byte byte0;
        int j;
        if (labelXUp) {
            j = -fontmetrics.getDescent();
            byte0 = 4;
        } else {
            j = fontmetrics.getLeading() + fontmetrics.getAscent();
            byte0 = -4;
        }
        String s = data.getNameX();
        if (arrowLeft) {
            Plot.drawArrowLeft(0, origin.y, 4, g);
            g.drawString(s, 1, origin.y + j);
        } else {
            Plot.drawArrowRight(i, origin.y, 4, g);
            int i1 = fontmetrics.stringWidth(s) + 1;
            g.drawString(s, i - i1, origin.y + j);
        }
        if (logX) {
            tickLogX(g, byte0, j);
        } else {
            tickLinX(g, format, byte0, j, i);
        }
    }

    private void tickLinX(Graphics g, Format format, int i, int j, int k) {
        FontMetrics fontmetrics = g.getFontMetrics();
        FloatRange floatrange = data.getRangeX();
        double d = data.getOrigin().getX();
        double d1 = conv.toPhysX(30);
        int j1 = origin.x;
        int l1 = origin.y;
        while (d >= floatrange.min) {
            String s = format.formE(d);
            int l = fontmetrics.stringWidth(s) / 2;
            g.drawLine(j1, l1, j1, l1 + i);
            if (arrowLeft && j1 > 30)
                g.drawString(s, j1 - l, l1 + j);
            d -= d1;
            j1 -= 30;
        }
        d = data.getOrigin().getX() + d1;
        for (int k1 = origin.x + 30; d <= floatrange.max; k1 += 30) {
            String s1 = format.formE(d);
            int i1 = fontmetrics.stringWidth(s1) / 2;
            g.drawLine(k1, l1, k1, l1 + i);
            if (!arrowLeft && k1 < k - 30)
                g.drawString(s1, k1 - i1, l1 + j);
            d += d1;
        }

    }

    private void tickLogX(Graphics g, int i, int j) {
        FontMetrics fontmetrics = g.getFontMetrics();
        FloatRange floatrange = data.getRangeX();
        byte byte0 = ((byte) (xLogRange.max - xLogRange.min <= 6 ? 1 : 2));
        int k1 = conv.toScrX(byte0);
        int j2 = origin.y;
        if (floatrange.min > 0.0D) {
            int k = xLogRange.min;
            int l1 = origin.x;
            for (; k <= xLogRange.max; k += byte0) {
                String s = "1E" + k;
                int i1 = fontmetrics.stringWidth(s) / 2;
                g.drawLine(l1, j2, l1, j2 + i);
                g.drawString(s, l1 - i1, j2 + j);
                l1 += k1;
            }

            return;
        }
        if (floatrange.max < 0.0D) {
            int l = xLogRange.max;
            int i2 = origin.x;
            while (l <= xLogRange.max) {
                String s1 = "-1E" + l;
                int j1 = fontmetrics.stringWidth(s1) / 2;
                g.drawLine(i2, j2, i2, j2 + i);
                g.drawString(s1, i2 - j1, j2 + j);
                i2 -= k1;
                l -= byte0;
            }

        }
    }

    private void drawY(Graphics g, Format format) {
        int i = canvas.getSize().height;
        g.drawLine(origin.x, 0, origin.x, i);
        FontMetrics fontmetrics = g.getFontMetrics();
        int j = fontmetrics.getHeight();
        byte byte0;
        byte byte1;
        if (labelYLeft) {
            byte1 = -1;
            byte0 = 4;
        } else {
            byte1 = 0;
            byte0 = -4;
        }
        String s = data.getNameY();
        int k = byte1 * fontmetrics.stringWidth(s);
        if (k == 0)
            k = 2;
        if (arrowUp) {
            Plot.drawArrowUp(origin.x, 0, 4, g);
            g.drawString(s, origin.x + k, j);
        } else {
            Plot.drawArrowDown(origin.x, i, 4, g);
            g.drawString(s, origin.x + k, i - j);
        }
        if (logY) {
            tickLogY(g, byte0, byte1);
        } else {
            tickLinY(g, format, byte0, byte1, i);
        }
    }

    private void tickLinY(Graphics g, Format format, int i, int j, int k) {
        FontMetrics fontmetrics = g.getFontMetrics();
        FloatRange floatrange = data.getRangeY();
        double d = data.getOrigin().getY();
        double d1 = conv.toPhysY(30);
        int j1 = origin.x;
        for (int k1 = origin.y; d >= floatrange.min; k1 += 30) {
            g.drawLine(j1, k1, j1 + i, k1);
            String s = format.formE(d);
            int l = j * fontmetrics.stringWidth(s);
            if (l == 0)
                l = 2;
            if (!arrowUp && k1 < k - 30)
                g.drawString(s, j1 + l, k1);
            d -= d1;
        }

        d = data.getOrigin().getY() + d1;
        for (int l1 = origin.y - 30; d <= floatrange.max; l1 -= 30) {
            g.drawLine(j1, l1, j1 + i, l1);
            String s1 = format.formE(d);
            int i1 = j * fontmetrics.stringWidth(s1);
            if (i1 == 0)
                i1 = 2;
            if (arrowUp && l1 > 30)
                g.drawString(s1, j1 + i1, l1);
            d += d1;
        }

    }

    private void tickLogY(Graphics g, int i, int j) {
        FontMetrics fontmetrics = g.getFontMetrics();
        FloatRange floatrange = data.getRangeY();
        byte byte0 = ((byte) (yLogRange.max - yLogRange.min <= 6 ? 1 : 2));
        int k1 = conv.toScrY(byte0);
        int l1 = origin.x;
        int i2 = origin.y;
        if (floatrange.min > 0.0D) {
            for (int k = yLogRange.min; k <= yLogRange.max; k += byte0) {
                String s = "1E" + k;
                int i1 = j * fontmetrics.stringWidth(s);
                g.drawLine(l1, i2, l1 + i, i2);
                g.drawString(s, l1 + i1, i2);
                i2 += k1;
            }

            return;
        }
        if (floatrange.max < 0.0D) {
            int l = yLogRange.max;
            while (l <= yLogRange.max) {
                String s1 = "-1E" + l;
                int j1 = j * fontmetrics.stringWidth(s1);
                g.drawLine(l1, i2, l1 + i, i2);
                g.drawString(s1, l1 + j1, i2);
                i2 -= k1;
                l -= byte0;
            }

        }
    }

    private void initX(FontMetrics fontmetrics, Format format) {
        FloatRange floatrange = data.getRangeY();
        String s = data.getNameY();
        int i = fontmetrics.stringWidth(s);
        String s1 = format.formE(floatrange.min);
        int j = fontmetrics.stringWidth(s1);
        if (j > i)
            i = j;
        s1 = format.formE(floatrange.max);
        j = fontmetrics.stringWidth(s1);
        if (j > i)
            i = j;
        i += 2;
        arrowLeft = false;
        if (logX) {
            initLogX(i);
        } else {
            initLinX(i);
        }
    }

    private void initLinX(int i) {
        int j = canvas.getSize().width;
        FloatRange floatrange = data.getRangeX();
        FloatPoint floatpoint = data.getOrigin();
        int k;
        int l;
        if (floatrange.min >= 0.0D) {
            k = 0;
            l = j;
            floatpoint.setX(floatrange.min);
        } else if (floatrange.max <= 0.0D) {
            k = j;
            l = 0;
            floatpoint.setX(floatrange.max);
            arrowLeft = true;
        } else {
            l = (int) ((floatrange.max / (floatrange.max - floatrange.min)) * (double) j);
            k = j - l;
            floatpoint.setX(0.0D);
        }
        int k1;
        if (k <= l) {
            int i1 = i - k;
            labelYLeft = true;
            if (i1 >= 0) {
                origin.x = i;
                k1 = j - i1;
            } else {
                origin.x = k;
                k1 = j;
            }
        } else {
            int j1 = i - l;
            labelYLeft = false;
            if (j1 >= 0) {
                origin.x = j - i;
                k1 = j - j1;
            } else {
                origin.x = j - l;
                k1 = j;
            }
        }
        conv.setX(k1, floatrange.max - floatrange.min);
    }

    private void initLogX(int i) {
        if (xLogRange == null)
            xLogRange = new IntRange();
        FloatRange floatrange = data.getRangeX();
        int j = canvas.getSize().width;
        double d = Math.log(10D);
        int k;
        if (floatrange.min > 0.0D) {
            origin.x = i;
            k = j - i;
            labelYLeft = true;
            xLogRange.min = (int) (Math.log(floatrange.min) / d) - 1;
            xLogRange.max = (int) (Math.log(floatrange.max) / d) + 1;
        } else if (floatrange.max < 0.0D) {
            origin.x = j - i;
            k = origin.x;
            labelYLeft = false;
            arrowLeft = true;
            xLogRange.max = (int) Math.log(-floatrange.min) + 1;
            xLogRange.min = (int) Math.log(-floatrange.max) - 1;
        } else {
            return;
        }
        conv.setX(k, (double) xLogRange.max - xLogRange.min);
    }

    private void initY(FontMetrics fontmetrics) {
        int j = fontmetrics.getHeight();
        int i = canvas.getSize().height;
        arrowUp = true;
        if (logY) {
            initLogY(i, j);
        } else {
            initLinY(i, j);
        }
    }

    private void initLinY(int i, int j) {
        FloatRange floatrange = data.getRangeY();
        int k;
        int l;
        if (floatrange.min >= 0.0D) {
            k = 0;
            l = i;
        } else if (floatrange.max <= 0.0D) {
            k = i;
            l = 0;
            arrowUp = false;
        } else {
            l = (int) ((floatrange.max / (floatrange.max - floatrange.min)) * (double) i);
            k = i - l;
        }
        int i1;
        if (k <= l) {
            labelXUp = false;
            if (k < j) {
                origin.y = i - j;
                i1 = origin.y;
            } else {
                origin.y = i - k;
                i1 = i;
            }
        } else {
            labelXUp = true;
            if (l <= j) {
                origin.y = j;
                i1 = i - j;
            } else {
                origin.y = l;
                i1 = i;
            }
        }
        conv.setY(i1, floatrange.max - floatrange.min);
    }

    private void initLogY(int i, int j) {
        double d = Math.log(10D);
        FloatRange floatrange = data.getRangeY();
        if (yLogRange == null)
            yLogRange = new IntRange();
        int k;
        if (floatrange.min > 0.0D) {
            origin.y = i - j;
            k = origin.y;
            labelXUp = false;
            yLogRange.min = (int) (Math.log(floatrange.min) / d) - 1;
            yLogRange.max = (int) (Math.log(floatrange.max) / d) + 1;
        } else if (floatrange.max < 0.0D) {
            labelXUp = true;
            arrowUp = false;
            origin.y = j;
            k = i - j;
            yLogRange.max = (int) (Math.log(-floatrange.min) / d) + 1;
            yLogRange.min = (int) (Math.log(-floatrange.max) / d) - 1;
        } else {
            return;
        }
        conv.setY(k, (double) yLogRange.max - yLogRange.min);
    }
}
