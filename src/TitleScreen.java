import javax.swing.*;
import java.awt.*;

public class TitleScreen {



    private JFrame frame;

    // konstruktor ve kterem vytvarime
    public TitleScreen(){
        frame = new JFrame("TItleOfTitleScreen");
    }


    public void init(){
        this.frame.setSize(600, 600);

        // LAYOUT je jich hodne muzem si vybrat
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // kdyz je null, tak se okno automaticky spusti uprostred nasi obrazovky
        this.frame.setLocationRelativeTo(null);

        // je to obycenjy text - proste vypis
        JLabel label = new JLabel("Welcome", JLabel.CENTER);

        //pridame label do stredu layoutu
        this.frame.add(label, BorderLayout.CENTER);

        // vytvoreni tlacitka s popiskem "Start"
        JButton button = new JButton("Start");

//        CustomButton.changeStyle(button);



        this.frame.add(button, BorderLayout.SOUTH);

        //na tlacitko pridame reaktant - close onkno jenom schova, ale dispose ho ukonci/vypne
        button.addActionListener(e->{
            this.frame.dispose();
            new PvlApp().init();
        });


        this.frame.setVisible(true);
    }



}