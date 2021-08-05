package me.mahdiyar;

import java.awt.*;

public class MosBottomControl extends Panel {

    MosOperation mos;
    Button id;
    Button ids;
    Button info;

    public MosBottomControl(MosOperation mosoperation) {
        mos = mosoperation;
        id = new Button("Id vs. Vds");
        add(id);
        id.addActionListener(e -> mos.outVisible());
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
