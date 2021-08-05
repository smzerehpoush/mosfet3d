package me.mahdiyar;

import javafx.scene.control.Alert;
import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MosMenuBar extends MenuBar {

    public MosMenuBar(Frame parentComponent) throws HeadlessException {
        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        Dialog dialog = new Dialog(parentComponent, "About Mosfet 3D", true);
        dialog.setSize(450, 450);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        TextArea textField = new TextArea("The NSF awarded ubuffalo.edu Java applets service for a set of\n" +
                "Educational Java Applets with semiconductors in mind known as \n" +
                "\"The semiconductor Applet Service\" (1996-2021) was closed recently.\n" +
                "The 2D MOS applet was my professor's favorite tool during the years\n" +
                "in his \"Analog Integrated Circuits\", \"VLSI\", and \"Electronics I\"\n" +
                "courses.\n" +
                "\n" +
                "The ubuffalo's 2D version is now fully refurbished and \n" +
                "migrated from old days AWT and Java 1.1 to J2EE\n" +
                "and elevated from 2D to 3D by me.\n" +
                "\n" +
                "I think this will help to teach MOS operation for a\n" +
                "broad range of courses by respected instructors/professors.\n" +
                "\n" +
                "Contact me at mahdiyar.zerehpoush@gmail.com");
        textField.setEditable(false);
        dialog.add(textField, BorderLayout.CENTER);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(false);
            }
        });
        aboutMenuItem.addActionListener(e -> dialog.setVisible(true));
        helpMenu.add(aboutMenuItem);
        this.add(helpMenu);
    }
}
