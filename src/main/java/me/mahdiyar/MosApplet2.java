package me.mahdiyar;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class MosApplet2 extends Applet implements Printable, ActionListener {
    private final Mos3DPanel mos3DPanel;
    private final D2Mos2DPanel mos2DPanel;

    public MosApplet2() throws HeadlessException {
        CardLayout cardLayout = new CardLayout();
        Panel panelContent = new Panel();
        mos3DPanel = new Mos3DPanel();
        mos2DPanel = new D2Mos2DPanel();

        panelContent.setLayout(cardLayout);

        mos3DPanel.setSize(800, 600);
        mos2DPanel.setSize(800, 600);

        panelContent.add(mos3DPanel, "3D");
        panelContent.add(mos2DPanel, "2D");

        cardLayout.show(panelContent, "3D");
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        add("Center", panelContent);
        setSize(800, 600);
        setVisible(true);
        Button printButton = new Button("Print This Window");
        printButton.addActionListener(this);
        add("South", printButton);
        Choice dimension = new Choice();
        dimension.addItem("3D");
        dimension.addItem("2D");
        dimension.select("3D");
        add("North", dimension);
        dimension.addItemListener(e -> {
            mos3DPanel.setVisible(!mos3DPanel.isVisible());
            mos2DPanel.setVisible(!mos2DPanel.isVisible());
        });
    }

    @Override
    public void init() {
        new MosApplet2();
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        mos3DPanel.printAll(g);
        return PAGE_EXISTS;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }
    }
}
