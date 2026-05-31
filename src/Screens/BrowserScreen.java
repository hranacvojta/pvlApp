package Screens;
import Structure.Station;
import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.FileReader;

public class BrowserScreen {

    private JFrame frame;


    public BrowserScreen() {
        this.frame = new  JFrame("PvlAppBrowserScreen");
    }

    public void innit(){
        this.frame.setSize(600, 600);
        JPanel panel = new JPanel();


        JButton button = new JButton("Add as favorite");
        button.setBackground(Color.GREEN);


        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.frame.setLayout(new BorderLayout());

        JTextField searchField = new JTextField();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel(" search station: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(button, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);

        String[] column = {"river", "station", "height [cm]", "flow [m3s]", "water temperature [c]", "flood activity rate"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(null, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filter(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filter(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });



        loadFromJson(model);
















        this.frame.setVisible(true);

    }




    private static void loadFromJson(DefaultTableModel model) {
        try (FileReader reader = new FileReader("res/PvlStatistics.json")) {

            Gson gson = new Gson();
            Structure.StructureCase structureCase = gson.fromJson(reader, Structure.StructureCase.class);

            if (structureCase != null && structureCase.stations() != null) {
                for (Station stanice : structureCase.stations()) {

                    model.addRow(new Object[]{
                            stanice.river(),
                            stanice.place(),
                            stanice.height_cm(),
                            stanice.flow_m3s(),
                            stanice.waterTemperature_c(),
                            stanice.floodActivityRate()
                    });
                }
            }

        } catch (Exception e) {
            System.err.println("Couldnt load file...: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Something went wrong", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }



















}
