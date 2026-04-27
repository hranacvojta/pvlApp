import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PvlApp {

    private JFrame frame;

    public PvlApp() {
        this.frame = new JFrame("PvlApp");
    }


    public void init(){
        this.frame.setSize(600, 600);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);


        // nastavi automaticky windowed fullscreen
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        DefaultListModel<String> model = new DefaultListModel<>();
        //arraylist 2.0
        JList<String> todoList = new JList<>(model);

        //na scrollovani jinak by to neslo
        this.frame.add(new JScrollPane(todoList), BorderLayout.CENTER);


        //abysme mohli dat tlacitko a textfield vedle sebe
        JPanel panel = new JPanel(new BorderLayout());

        //editovatelne okenko pro vstup uzivatele
        JTextField field = new JFormattedTextField();
        panel.add(field, BorderLayout.CENTER);

        //
        JButton button = new JButton("Add");
//        CustomButton.changeStyle2(button);
        panel.add(button, BorderLayout.EAST);

        this.frame.add(panel, BorderLayout.SOUTH);

        // da textu hodnotu ktera je aktualne ve psacim poli
        button.addActionListener(e->{
            String text = field.getText();

            // ulozime do tabuky(modelu) a vypraznime aby uzivatel mohl zase v klidu psat
            if (!text.isEmpty()){
                model.addElement(text);
                field.setText("");

            }
        });



//        field.addKeyListener();






        todoList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    int index = todoList.locationToIndex(e.getPoint());

                    if (index != -1) {
                        model.remove(index);
                    }
                }
            }
        });













        this.frame.setVisible(true);
    }
}