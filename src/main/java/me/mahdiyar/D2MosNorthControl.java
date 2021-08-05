package me.mahdiyar;

import java.awt.*;

public class D2MosNorthControl extends Panel {

    D2MosOperation mos;
    Button id;
    Button ids;
    Button info;

    public D2MosNorthControl(D2MosOperation mosOperation) {
        mos = mosOperation;
        id = new Button("Id vs. Vds");
        id.addActionListener(e -> mos.outVisible());
        add(id);
        add(new Label("   "));
        ids = new Button("Ids vs. Vgs");
        ids.addActionListener(e -> mos.transVisible());
        add(ids);
        add(new Label("   "));
        info = new Button("Discussion");
        info.addActionListener(e -> mos.infoVisible());
        add(info);
        add(new Label("   "));
    }
}
