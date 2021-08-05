package me.mahdiyar;

import java.awt.*;

public class D2Mos2DPanel extends Panel {

    public D2Mos2DPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        OutputIV outputiv = new OutputIV();
        D2MosOperation2D mosOperation2D = new D2MosOperation2D(outputiv);
        add("Center", mosOperation2D);
        add("North", new D2MosSouthControl2D(mosOperation2D));
        add("South", new D2MosNorthControl(mosOperation2D));
    }
}
