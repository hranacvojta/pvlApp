package Screens;

import javax.swing.*;

public class BrowserScreen {

    private JFrame frame;


    public BrowserScreen() {
        this.frame = new  JFrame("PvlAppBrowserScreen");
    }

    public void innit(){
        this.frame.setSize(600, 600);
        JPanel panel = new JPanel();

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);























        this.frame.setVisible(true);

    }










}
