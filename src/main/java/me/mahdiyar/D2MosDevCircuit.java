package me.mahdiyar;

import java.awt.*;

public abstract class D2MosDevCircuit extends Canvas {

    protected BatterySymbol Vd;
    protected double vgs;
    protected double vd;
    private Image imgMosCkt;
    private Image imgOff;
    private Graphics gOff;
    private int width;
    private int height;
    private final D2Mosfet2D fet;
    private final GroundSymbol bGrd;
    private final GroundSymbol sGrd;
    private final BatterySymbol Vgs;
    private int yMos;
    private int yLowWire;
    private FontMetrics fm;
    private int xChannelMaxSat;
    private double yToV;
    private double xToV;
    private final int[] xSat;
    private final int[] ySat;
    private final int[] xLin;
    private final int[] yLin;
    private final Format format;
    private boolean typeChanged;
    private boolean nChannel;
    private double vT;
    private final double vgsMax;
    private int counter;

    protected D2MosDevCircuit() {
        bGrd = new GroundSymbol();
        sGrd = new GroundSymbol();
        Vgs = new BatterySymbol("horizontal", "right");
        Vd = new BatterySymbol("horizontal", "left");
        format = new Format(2, 1.0D);
        nChannel = true;
        typeChanged = false;
        xSat = new int[3];
        ySat = new int[3];
        xLin = new int[4];
        yLin = new int[4];
        vT = 1.0D;
        vgsMax = 4D;
        vgs = 0.0D;
        vd = 0.0D;
        setBackground(Color.lightGray);
        fet = new D2Mosfet2D();
        fet.setNChannel(nChannel);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        initImg();
        drawOffscreen(gOff);
        g.drawImage(imgOff, 0, 0, null);
        if (counter++ < 7)
            drawDescription(g);
    }

    private void drawOffscreen(Graphics g) {
        g.drawImage(imgMosCkt, 0, 0, null);
        if (typeChanged) {
            Vgs.reverse();
            Vd.reverse();
            typeChanged = false;
        }
        g.setColor(Color.black);
        Vgs.draw(g);
        Vd.draw(g);
        drawValues(g);
        drawChannel(g);
    }

    public void setNChannel(boolean flag) {
        if (nChannel != flag) {
            typeChanged = true;
            nChannel = flag;
            fet.setNChannel(flag);
            Graphics g = imgMosCkt.getGraphics();
            fet.draw(g);
            g.dispose();
            counter = 0;
            repaint();
        }
    }

    public void setVgs(double d) {
        vgs = Math.abs(d);
        repaint();
    }

    private void refreshChannel() {
        initForChannelB();
        repaint();
    }

    public void setVd(double d) {
        vd = Math.abs(d);
        repaint();
    }

    public void setVt(double d) {
        vT = Math.abs(d);
        refreshChannel();
    }

    public double getVgsMax() {
        return vT + vgsMax;
    }

    protected abstract int getVgsY();

    protected abstract int getVdY();

    protected abstract String getVdName();

    protected abstract void drawChannel(Graphics g);

    protected int getMosY() {
        return fet.getY();
    }

    protected void drawChannel(Graphics g, double d) {
        if (vgs <= vT)
            return;
        if (d > vgs)
            d = vgs;
        if (nChannel)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        if (d > vT) {
            yLin[1] = yLin[0] + (int) (yToV * (vgs - vT));
            yLin[2] = yLin[0] + (int) (yToV * (d - vT));
            g.fillPolygon(xLin, yLin, 4);
        } else {
            ySat[1] = ySat[0] + (int) (yToV * (vgs - vT));
            xSat[2] = xChannelMaxSat + (int) (xToV * d);
            g.fillPolygon(xSat, ySat, 3);
        }
    }

    private void drawDescription(Graphics g) {
        FontMetrics fontmetrics = g.getFontMetrics();
        int i = fontmetrics.getHeight();
        int j = fet.getChannelY() + i;
        g.setColor(Color.lightGray);
        String s = nChannel ? "n" : "p";
        String s1 = nChannel ? "p-type Si" : "n-type Si";
        g.drawString(s, fet.getSourceX(), j);
        g.drawString(s, fet.getDrainX(), j);
        int k = fontmetrics.stringWidth(s1) / 2;
        g.drawString(s1, fet.getGateX() - k, fet.getBulkY() - 2 * i);
        g.setColor(Color.gray);
        g.drawString("oxide", fet.getX(), fet.getY());
    }

    private void initImg() {
        Dimension dimension = getSize();
        if (dimension.width == width && dimension.height == height)
            return;
        width = dimension.width;
        height = dimension.height;
        if (width > 130 && width < 300) {
            Vgs.setScale(3);
            Vd.setScale(3);
        } else if (width >= 300) {
            Vgs.setScale(4);
            Vd.setScale(4);
        }
        initImgMosCkt();
    }

    private void initImgMosCkt() {
        imgMosCkt = createImage(width, height);
        Graphics g = imgMosCkt.getGraphics();
        yMos = height / 2;
        yLowWire = (yMos * 3) / 4;
        fet.setRect(width / 10, yMos, (width * 8) / 10, (height * 4) / 10);
        initForChannelA();
        initForChannelB();
        drawCircuit(g);
        g.dispose();
        imgOff = createImage(width, height);
        gOff = imgOff.getGraphics();
    }

    private void initForChannelA() {
        xSat[0] = fet.getChannelX();
        ySat[0] = fet.getChannelY();
        xSat[1] = xSat[0];
        ySat[2] = ySat[0];
        xLin[0] = xSat[0];
        yLin[0] = ySat[0];
        xLin[1] = xLin[0];
        xLin[2] = xLin[0] + fet.getChannelLength();
        xLin[3] = xLin[2];
        yLin[3] = yLin[0];
    }

    private void initForChannelB() {
        int i = (fet.getChannelHeight() * 2) / 3;
        xChannelMaxSat = (fet.getGateX() + xLin[2]) / 2;
        yToV = (double) i / vgsMax;
        xToV = (double) (xLin[2] - xChannelMaxSat) / vT;
    }

    private void drawCircuit(Graphics g) {
        fet.draw(g);
        int i = fet.getBulkX();
        int j = fet.getBulkY();
        byte byte0 = 7;
        bGrd.setLocation(i, j + byte0);
        g.setColor(Color.black);
        bGrd.draw(g);
        g.drawLine(i, j, i, j + byte0);
        fm = g.getFontMetrics();
        int k = fm.stringWidth("S");
        int l = fm.getDescent();
        j = fet.getContactY() - l;
        g.drawString("S", fet.getSourceX() + k, j);
        g.drawString("G", fet.getGateX() + k, j);
        g.drawString("D", fet.getDrainX() + k, j);
        int i1 = fet.getX();
        int j1 = i1 + fet.getWidth();
        int k1 = (i1 + fet.getGateX()) / 2;
        int l1 = (fet.getGateX() + j1) / 2;
        Vgs.setLocation(k1, getVgsY());
        Vd.setLocation(l1, getVdY());
        j = fet.getContactY();
        i = fet.getSourceX();
        int i2 = Vd.getLocation().y;
        int j2 = Vgs.getLocation().y;
        int k2 = fet.getGateX();
        g.drawLine(i, j, i, yLowWire);
        g.drawLine(i, yLowWire, i1, yLowWire);
        g.drawLine(i1, yLowWire, i1, i2);
        g.drawLine(i1, j2, Vgs.getLeftX(), j2);
        g.drawLine(Vgs.getRightX(), j2, k2, j2);
        g.drawLine(k2, j2, k2, j);
        if (i2 != j2)
            g.drawLine(i1, i2, k2, i2);
        i = fet.getDrainX();
        int l2 = i;
        g.drawLine(i, j, i, yLowWire);
        g.drawLine(i, yLowWire, j1, yLowWire);
        g.drawLine(j1, yLowWire, j1, i2);
        g.drawLine(j1, i2, Vd.getRightX(), i2);
        g.drawLine(k2, i2, Vd.getLeftX(), i2);
        j = yLowWire + (fm.getAscent() * 2) / 3;
        i = (l2 * 2) / 3 + j1 / 3;
        int i3 = i + 12;
        g.drawLine(i, j, i3, j);
        Plot.drawArrowLeft(i, j, 3, g);
        g.drawString("Id", i3 + 2, yLowWire + fm.getAscent());
        l = fm.getAscent() + fm.getLeading();
        g.drawString("Vgs", Vgs.getLeftX(), Vgs.getLowY() + l);
        g.drawString(getVdName(), Vd.getLeftX(), Vd.getLowY() + l);
        j = (yLowWire + j2) / 2;
        i = i1 / 2;
        l = i;
        g.drawLine(i, j, i1, j);
        sGrd.setLocation(i, j + l);
        g.drawLine(i, j, i, sGrd.getLocation().y);
        sGrd.draw(g);
    }

    private void drawValues(Graphics g) {
        g.setColor(Color.magenta);
        int i = Vgs.getLeftX();
        int j = Vgs.getHiY() - fm.getDescent();
        String s = nChannel ? "+" : "-";
        g.drawString(s + format.formE(vgs) + " V", i, j);
        i = Vd.getLeftX();
        j = Vd.getHiY() - fm.getDescent();
        g.drawString(s + format.formE(vd) + " V", i, j);
    }
}
