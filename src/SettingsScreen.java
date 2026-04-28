import javax.swing.*;
import java.awt.*;

public class SettingsScreen {
    private JFrame frame;
    public SettingsScreen(){
        frame = new JFrame("PvlAppSettingsScreen");
    }


    public void init() {
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("PvlApp Settings");
        frame.add(label);

        frame.setVisible(true);
    }



}
