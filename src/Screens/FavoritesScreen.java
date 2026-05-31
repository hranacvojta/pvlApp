package Screens;

import Structure.Station;
import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the screen that displays the user's favorite river stations.
 * It reads the saved favorites from a local text file and cross-references them
 * with the main JSON data to display a filtered table.
 */
public class FavoritesScreen {

    private JFrame frame;

    /**
     * Constructs the FavoritesScreen and initializes the main JFrame.
     */
    public FavoritesScreen(){
        frame = new JFrame("PvlAppFavoritesScreen");
    }

    /**
     * Initializes the graphical user interface components, sets up the window layout,
     * creates the data table for displaying favorites, and makes the window visible.
     */
    public void init() {
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(" Favorite ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        String[] column = {"river", "station", "height [cm]", "flow [m3s]", "water temperature [c]", "flood activity rate"};
        DefaultTableModel model = new DefaultTableModel(null, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        loadFavoritesFromJson(model);

        frame.setVisible(true);
    }

    /**
     * Loads the favorite station names from a text file and populates the given table model
     * with matching station data retrieved from the main JSON statistics file.
     *
     * @param model The DefaultTableModel of the table to be populated.
     */
    private void loadFavoritesFromJson(DefaultTableModel model) {
        File favFile = new File("res/favorites.txt");

        if (!favFile.exists()) {
            return;
        }

        try {
            List<String> favoriteNames = Files.readAllLines(favFile.toPath());

            try (FileReader reader = new FileReader("res/PvlStatistics.json")) {
                Gson gson = new Gson();
                Structure.StructureCase structureCase = gson.fromJson(reader, Structure.StructureCase.class);

                if (structureCase != null && structureCase.stations() != null) {
                    for (Station stanice : structureCase.stations()) {

                        if (favoriteNames.contains(stanice.place())) {

                            // Pokud ano, přidáme ji do tabulky
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
                }
            }

        } catch (Exception e) {
            System.err.println("Couldnt load favorites data: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Something went wrong while loading favorites.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}