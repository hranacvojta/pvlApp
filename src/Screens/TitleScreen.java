package Screens;

import API.ApiThread;

import javax.swing.*;
import java.awt.*;

public class TitleScreen {



    private JFrame frame;

    // konstruktor ve kterem vytvarime Jframe
    public TitleScreen(){
        frame = new JFrame("PvlAppTitleScreen");
    }


    public void init() {
        this.frame.setSize(600, 600);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // LAYOUT je jich hodne muzem si vybrat
//        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // kdyz je null, tak se okno automaticky spusti uprostred nasi obrazovky
        this.frame.setLocationRelativeTo(null);

        // je to obycenjy text - proste vypis
        JLabel label = new JLabel("Welcome", JLabel.CENTER);


        //pridame label do stredu layoutu
//        this.frame.add(label, BorderLayout.CENTER);

        // vytvoreni tlacitka s popiskem "Prejit na mapu"
        JButton buttonContinueToBrowser = new JButton("CONTINUE TO BROWSER");
        buttonContinueToBrowser.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContinueToBrowser.setMaximumSize(new Dimension(350, 50));
        panel.add(Box.createVerticalStrut(170));
        panel.add(buttonContinueToBrowser);


        // vytvoreni tlacitka s popiskem "nastaveni"
        JButton buttonSettings = new JButton("SETTINGS");
        buttonSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSettings.setMaximumSize(new Dimension(350, 50));
        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonSettings);


        // buton with tag "close"
        JButton buttonClose = new JButton("CLOSE");
        buttonClose.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonClose.setMaximumSize(new Dimension(350, 50));
        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonClose);


        JButton buttonFavorites = new  JButton("FAVORITES");
        buttonFavorites.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFavorites.setMaximumSize(new Dimension(350, 50));
        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonFavorites);


        frame.add(panel);





        //na tlacitko pridame reaktant - close onkno jenom schova, ale dispose ho ukonci/vypne
        buttonContinueToBrowser.addActionListener(e->{
            if (ApiThread.isAipThreadDone() ==  true) {
                this.frame.dispose();
                new BrowserScreen().innit();
            }else{
                System.out.println("API hadnt done its job yet...");
            }

        });

        //na tlacitko pridame reaktant - close onkno jenom schova, ale dispose ho ukonci/vypne
        buttonSettings.addActionListener(e->{
            this.frame.dispose();
            new SettingsScreen().init();
        });

        buttonFavorites.addActionListener(e->{
            this.frame.dispose();
            new FavoritesScreen().init();
        });


        this.frame.setVisible(true);
    }



}