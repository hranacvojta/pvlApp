import javax.swing.*;
import java.awt.*;

public class SettingsScreen {
    private JFrame frame;
    public SettingsScreen(){
        frame = new JFrame("PvlAppSettingsScreen");
    }


    public void init() {
        this.frame.setSize(600, 600);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("PvlApp Settings");
        this.frame.add(label);

        this.frame.setVisible(true);
    }



}
