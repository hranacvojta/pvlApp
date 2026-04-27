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
        JButton buttonContinueToMap = new JButton("CONTINUE TO MAP");
        buttonContinueToMap.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContinueToMap.setMaximumSize(new Dimension(350, 50));
        panel.add(Box.createVerticalStrut(200));
        panel.add(buttonContinueToMap);


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

        frame.add(panel);





        //na tlacitko pridame reaktant - close onkno jenom schova, ale dispose ho ukonci/vypne
        buttonContinueToMap.addActionListener(e->{
            this.frame.dispose();
            new PvlApp().init();
        });

        //na tlacitko pridame reaktant - close onkno jenom schova, ale dispose ho ukonci/vypne
        buttonSettings.addActionListener(e->{
            this.frame.dispose();
            new SettingsScreen().init();
        });


        this.frame.setVisible(true);
    }



}