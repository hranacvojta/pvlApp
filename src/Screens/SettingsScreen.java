package Screens;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the settings screen of the application.
 * Provides a graphical interface for configuring application preferences.
 */
public class SettingsScreen {
    private JFrame frame;

    /**
     * Constructs the SettingsScreen and initializes the main JFrame.
     */
    public SettingsScreen(){
        frame = new JFrame("PvlAppSettingsScreen");
    }

    /**
     * Initializes the graphical user interface components, sets up the window layout,
     * adds a placeholder label, and makes the window visible.
     */
    public void init() {
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Screens.PvlApp Settings");
        frame.add(label);

        frame.setVisible(true);
    }



}