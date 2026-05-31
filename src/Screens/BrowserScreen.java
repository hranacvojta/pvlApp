package Screens;
import Structure.Station;
import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Represents the browser screen of the application.
 * Displays a searchable table of river stations loaded from a JSON file
 * and allows users to add specific stations to their favorites.
 */
public class BrowserScreen {

    private JFrame frame;

    /**
     * Constructs the BrowserScreen and initializes the main JFrame.
     */
    public BrowserScreen() {
        this.frame = new JFrame("PvlAppBrowserScreen");
    }

    /**
     * Initializes and configures all GUI components, including the layout,
     * search bar, dynamic table with a sorter/filter, and the favorite button.
     * Finally, makes the frame visible.
     */
    public void innit() {
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

        String[] column = {"river", "place", "height [cm]", "flow [m3s]", "water temperature [c]", "flood activity rate"};
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
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

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

        button.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a station from the table first.", "Notice", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int modelRow = table.convertRowIndexToModel(selectedRow);

            String stationName = (String) model.getValueAt(modelRow, 1);

            saveFavorite(stationName);
        });




        this.frame.setVisible(true);

    }

    /**
     * Loads station data from the local JSON file and populates the table model.
     *
     * @param model The DefaultTableModel of the table to be populated.
     */
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

    /**
     * Saves the selected station's name to the favorites text file.
     * Prevents duplicate entries from being added.
     *
     * @param place The name of the station (place) to add to favorites.
     */
    private void saveFavorite(String place) {
        File file = new File("res/favorites.txt");

        try {
            List<String> favorites = Files.readAllLines(file.toPath());

            if (favorites.contains(place)) {
                JOptionPane.showMessageDialog(null, "Station '" + place + "' is already in your favorites!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Přidáme název stanice a zalomení řádku nakonec souboru
            String lineToAppend = place + System.lineSeparator();
            Files.write(file.toPath(), lineToAppend.getBytes(), StandardOpenOption.APPEND);

            // Úspěšná hláška pro uživatele
            JOptionPane.showMessageDialog(null, "Station '" + place + "' was successfully added to favorites!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            System.err.println("Chyba při ukládání oblíbené stanice: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to save favorite station.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
}