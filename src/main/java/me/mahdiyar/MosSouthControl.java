package me.mahdiyar;

import java.awt.*;
import java.util.StringTokenizer;

public abstract class MosSouthControl extends Panel {

    private final MosOperation mos;
    private Label Vgs;
    private Label Vd;
    private UpDown10 vgs;
    private UpDown10 vd;
    private Choice choice;
    private Choice nVt;
    private Choice pVt;
    private Choice channelType;
    private Checkbox checkbox;
    private boolean nChannel;


    protected MosSouthControl(MosOperation mosoperation) {
        mos = mosoperation;
        initComps();
        add(Vgs);
        add(vgs);
        mosoperation.setVgsControl(vgs);
        vgs.addListener(mosoperation);
        add(Vd);
        add(vd);
        mosoperation.setVdControl(vd);
        vd.addListener(mosoperation);
        add(new Label("   "));
        add(choice);
        choice.addItemListener(e -> {
            String s1 = choice.getSelectedItem();
            try {
                double d1 = getVt(s1);
                mos.setVt(d1);
                mos.repaint();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        });
        add(channelType);
        channelType.addItemListener(e -> {
            String s = channelType.getSelectedItem();
            nChannel = mos.isNChannel();
            if (s.equals("N-channel"))
                nChannel = true;
            else if (s.equals("P-channel"))
                nChannel = false;
            if (nChannel != mos.isNChannel()) {
                setChoice();
                mos.setNChannel(nChannel);
                double d2 = 0.0D;
                double d3 = 0.0D;
                double d = nChannel ? 1.0D : -1D;
                mos.setVgs(d2);
                mos.setVd(d3);
                mos.setVt(d);
                mos.repaint();
            }
        });
        checkbox.addItemListener(e -> {
            mos.setLabelsVisibility(checkbox.getState());
            mos.repaint();
            mos.mosCkt.repaint();
        });
        add(checkbox);
        nChannel = true;
    }

    protected abstract String getVdLabel();

    private double getVt(String s)
            throws NumberFormatException {
        double d = 0.0D;
        StringTokenizer stringtokenizer = new StringTokenizer(s);
        int i;
        for (i = 0; stringtokenizer.hasMoreElements() && i < 3; i++) {
            String s1 = stringtokenizer.nextToken();
            if (i == 2)
                d = new Double(s1);
        }

        if (i < 2)
            throw new NumberFormatException();
        else
            return d;
    }

    private void initComps() {
        Vgs = new Label("Vgs");
        Vgs.setAlignment(Label.RIGHT);
        Vd = new Label(getVdLabel());
        Vd.setAlignment(Label.RIGHT);
        vgs = new UpDown10(12, 25, Color.black, Color.lightGray);
        vd = new UpDown10(12, 25, Color.black, Color.lightGray);
        nVt = new Choice();
        nVt.addItem("Vt = 0.5 V");
        nVt.addItem("Vt = 1.0 V");
        nVt.addItem("Vt = 1.5 V");
        nVt.addItem("Vt = 2.0 V");
        nVt.addItem("Vt = 3.0 V");
        nVt.addItem("Vt = 4.0 V");
        pVt = new Choice();
        pVt.addItem("Vt = -0.5 V");
        pVt.addItem("Vt = -1.0 V");
        pVt.addItem("Vt = -1.5 V");
        pVt.addItem("Vt = -2.0 V");
        pVt.addItem("Vt = -3.0 V");
        pVt.addItem("Vt = -4.0 V");
        choice = nVt;
        choice.select(1);
        channelType = new Choice();
        channelType.addItem("N-channel");
        channelType.addItem("P-channel");
        channelType.select("N-channel");
        checkbox = new Checkbox("show labels");
        checkbox.setState(true);
    }

    private void setChoice() {
        remove(choice);
        choice = nChannel ? nVt : pVt;
        add(choice, 5);
        validate();
        choice.select(1);
    }
}
