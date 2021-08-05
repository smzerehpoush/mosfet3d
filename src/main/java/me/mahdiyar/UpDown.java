package me.mahdiyar;

import java.awt.*;


public class UpDown extends Canvas {

    private final Color arrowColor;
    private final Color bkgdColor;
    protected int width;
    protected int height;
    protected int dxArrow;
    protected int dyArrow;
    protected boolean topPressed;
    protected boolean bottomPressed;
    protected CustomAWT listener;
    int[] xpoints;
    int[] ypoints;
    private Polygon upArrow;
    private Polygon downArrow;
    private Image offscreen;
    private Graphics gOffscreen;

    public UpDown(int i, int j, Color color, Color color1) {
        topPressed = false;
        bottomPressed = false;
        arrowColor = color;
        bkgdColor = color1;
        xpoints = new int[3];
        ypoints = new int[3];
        initSizes(i, j);
    }

    public boolean downArrowPressed() {
        return bottomPressed;
    }

    public boolean upArrowPressed() {
        return topPressed;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getSize();
        if (width != dimension.width || height != dimension.height)
            initSizes(dimension.width, dimension.height);
        if (offscreen == null || width != dimension.width || height != dimension.height) {
            offscreen = createImage(width, height);
            gOffscreen = offscreen.getGraphics();
        }
        paintScrollbar(gOffscreen);
        g.drawImage(offscreen, 0, 0, null);
    }

    public void addListener(CustomAWT customawt) {
        listener = customawt;
    }

    private void initSizes(int i, int j) {
        width = i;
        dyArrow = j / 2;
        height = 2 * dyArrow;
        dxArrow = i + 1;
        setSize(i, height);
        xpoints[0] = i / 2;
        ypoints[0] = Math.max(2, dyArrow / 5);
        xpoints[1] = (i * 3) / 4;
        ypoints[1] = (dyArrow * 4) / 5;
        xpoints[2] = i / 4;
        ypoints[2] = ypoints[1];
        upArrow = new Polygon(xpoints, ypoints, 3);
        ypoints[0] = j - ypoints[0];
        ypoints[1] = j - ypoints[1];
        ypoints[2] = ypoints[1];
        downArrow = new Polygon(xpoints, ypoints, 3);
    }

    private void paintScrollbar(Graphics g) {
        g.setColor(bkgdColor);
        g.fill3DRect(0, 0, dxArrow, dyArrow, !topPressed);
        g.fill3DRect(0, dyArrow, dxArrow, dyArrow, !bottomPressed);
        g.setColor(arrowColor);
        g.fillPolygon(upArrow);
        g.fillPolygon(downArrow);
    }
}
