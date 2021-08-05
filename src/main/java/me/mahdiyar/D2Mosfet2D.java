package me.mahdiyar;

import java.awt.*;

public class D2Mosfet2D {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean nChannel;
    private int yContactTop;
    private int ySemiTop;
    private int yNeutralTop;
    private int ySemiBottom;
    private int yBulkElectrode;
    private int hContact;
    private int hOxide;
    private int hChannel;
    private int hNeutral;
    private int hBulkContact;
    private int hNeutralCenter;
    private int hNeutralSide;
    private int xS;
    private int xD;
    private int xG;
    private int xBulkElectrode;
    private int xSEnd;
    private int xDEnd;
    private int xNeutralCenter;
    private int wContactTop;
    private int wContactBottom;
    private int wChannel;
    private int wSource;
    private int wNeutralCenter;
    private int wNeutralSide;

    public D2Mosfet2D() {
        nChannel = true;
    }

    public void draw(Graphics g) {
        clear(g);
        g.setColor(Color.black);
        g.drawRect(x, y, width - 1, height - 1);
        drawFirstLayer(g);
        drawSecondLayer(g);
    }

    public void clear(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(x, y, width, height);
    }

    public boolean isNChannel() {
        return nChannel;
    }

    public void setNChannel(boolean flag) {
        nChannel = flag;
    }

    public void setRect(int i, int j, int k, int l) {
        x = i;
        y = j;
        width = k;
        height = l;
        setParameters();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSourceX() {
        return xS;
    }

    public int getContactY() {
        return yContactTop;
    }

    public int getGateX() {
        return xG;
    }

    public int getDrainX() {
        return xD;
    }

    public int getChannelX() {
        return xSEnd;
    }

    public int getChannelY() {
        return ySemiTop;
    }

    public int getChannelLength() {
        return wChannel;
    }

    public int getChannelHeight() {
        return hChannel;
    }

    public int getBulkX() {
        return xBulkElectrode;
    }

    public int getBulkY() {
        return yBulkElectrode;
    }

    private void drawFirstLayer(Graphics g) {
        drawOxide(g);
        drawBulkContact(g);
    }

    private void drawSecondLayer(Graphics g) {
        drawSDRegions(g);
        drawContacts(g);
        drawBulkNeutral(g);
    }

    private void drawContacts(Graphics g) {
        g.setColor(Color.black);
        int i = yContactTop;
        int j = wContactTop / 2;
        int k = wContactBottom / 2;
        g.fillRect(xS - j, i, wContactTop, hContact);
        g.fillRect(xS - k, y, wContactBottom, hOxide);
        g.fillRect(xG - wChannel / 2, i, wChannel, hContact + hOxide / 3);
        g.fillRect(xD - j, i, wContactTop, hContact);
        g.fillRect(xD - k, y, wContactBottom, hOxide);
    }

    private void drawBulkContact(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(x, ySemiBottom, width, hBulkContact);
    }

    private void drawSDRegions(Graphics g) {
        g.setColor(nChannel ? Color.blue : Color.red);
        int i = wSource / 2;
        g.fillRect(xS - i, ySemiTop, wSource, hChannel);
        g.fillRect(xD - i, ySemiTop, wSource, hChannel);
    }

    private void drawOxide(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(x, y, width, hOxide);
    }

    private void drawBulkNeutral(Graphics g) {
        g.setColor(nChannel ? Color.red : Color.blue);
        g.fillRect(x, ySemiTop, wNeutralSide, hNeutralSide);
        g.fillRect(x, yNeutralTop, width, hNeutral);
        g.fillRect((x + width) - wNeutralSide, ySemiTop, wNeutralSide, hNeutralSide);
        drawBulkNeutral2(g);
    }

    private void drawBulkNeutral2(Graphics g) {
        g.setColor(nChannel ? Color.red : Color.blue);
        g.fillRect(xNeutralCenter, yNeutralTop - hNeutralCenter, wNeutralCenter, hNeutralCenter);
    }

    private void setParameters() {
        hOxide = height / 10;
        hContact = height / 15;
        hBulkContact = hContact;
        int i = height - hOxide - hBulkContact;
        hChannel = i / 3;
        int j = i / 5;
        hNeutralSide = hChannel + j;
        hNeutral = i - hNeutralSide;
        hNeutralCenter = j;
        yContactTop = y - hContact;
        ySemiTop = y + hOxide;
        ySemiBottom = ySemiTop + i;
        yNeutralTop = ySemiTop + hNeutralSide;
        yBulkElectrode = ySemiBottom + hBulkContact;
        wChannel = width / 3;
        wSource = width / 8;
        wContactBottom = wSource / 3;
        wContactTop = 2 * wContactBottom;
        wNeutralCenter = (wChannel * 3) / 4;
        xG = x + width / 2;
        xSEnd = xG - wChannel / 2;
        xDEnd = xSEnd + wChannel;
        xS = xSEnd - wSource / 2;
        xD = xDEnd + wSource / 2;
        xBulkElectrode = x + width / 5;
        xNeutralCenter = xG - wNeutralCenter / 2;
        int k = hNeutralSide - hChannel;
        wNeutralSide = xS - wSource / 2 - k - x;
    }
}
