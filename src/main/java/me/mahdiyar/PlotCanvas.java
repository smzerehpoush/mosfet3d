package me.mahdiyar;

import java.awt.*;

public class PlotCanvas extends Canvas {

    private final Axis axis;
    private final DataWrapper data;
    private final Conversion conv;
    private final boolean showFullData;
    private int width;
    private int height;
    private Image imgBkgd;
    private boolean dataChanged;

    public PlotCanvas() {
        conv = new Conversion();
        axis = new Axis(this);
        data = new DataWrapper(this);
        showFullData = true;
        dataChanged = false;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        axis.init();
    }

    public void addData(Data data1) {
        data.add(data1);
        dataChanged = true;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getSize();
        if (dimension.width != width || dimension.height != height) {
            width = dimension.width;
            height = dimension.height;
            imgBkgd = createImage(width, height);
            repaintImg();
        } else if (dataChanged)
            repaintImg();
        g.drawImage(imgBkgd, 0, 0, null);
        data.drawCurrent(g, Color.magenta);
        data.drawSpot(g, Color.red);
    }

    public DataWrapper getData() {
        return data;
    }

    public Axis getAxis() {
        return axis;
    }

    public Conversion getConversion() {
        return conv;
    }

    public void setChanged(boolean flag) {
        dataChanged = flag;
    }

    private void repaintImg() {
        Graphics g = imgBkgd.getGraphics();
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);
        axis.draw(g);
        if (showFullData)
            data.drawFull(g);
        g.dispose();
        dataChanged = false;
    }
}
