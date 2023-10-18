import javax.swing.*;
import java.awt.*;

public class AgregarSalonGUI extends JFrame {
    private final JTextField noDeSalonTextField;
    private final JTextField aforoTextField;
    JCheckBox proyectorCheckbox;
    boolean tieneProyector;

    public AgregarSalonGUI(NodosSalon nodosSalon) {
        super("Agregar salón");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel codigoLabel = new JLabel("No. de salón:");
        noDeSalonTextField = new JTextField(20);

        JLabel facultadLabel = new JLabel("Facultad:");
        String[] facultades = {"Ingenierías", "Ciencias", "Humanidades", "Ciencias sociales", "Salud"};
        JComboBox<String> facultadesMenu = new JComboBox<>(facultades);
        facultadesMenu.setSelectedIndex(0);

        JLabel aforoLabel = new JLabel("Aforo:");
        aforoTextField = new JTextField(20);
        JLabel proyectorLabel = new JLabel("¿Tiene proyector?");
        proyectorCheckbox = new JCheckBox();

        JButton agregarButton = new JButton("Agregar Salón");

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(codigoLabel);
        panel.add(noDeSalonTextField);
        panel.add(facultadLabel);
        panel.add(facultadesMenu);
        panel.add(aforoLabel);
        panel.add(aforoTextField);
        panel.add(proyectorLabel);
        panel.add(proyectorCheckbox);
        panel.add(agregarButton);

        agregarButton.addActionListener(e -> {

            String selectedFaculty = (String) facultadesMenu.getSelectedItem();
            int noDeSalon = Integer.parseInt(noDeSalonTextField.getText());
            assert selectedFaculty != null;
            String locacion = getString(selectedFaculty, noDeSalon);
            int aforo = Integer.parseInt(aforoTextField.getText());
            tieneProyector = proyectorCheckbox.isSelected();

            NodoSalon nuevoSalon = new NodoSalon(locacion, selectedFaculty, aforo, tieneProyector, null);
            // Assuming you have a reference to the NodosSalon instance, call insertSalon
            if (!nodosSalon.insertSalon(nuevoSalon)) {
                JOptionPane.showMessageDialog(this, "No se pudo agregar el salón porque ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Se agregó el salón", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

            dispose();
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static String getString(String selectedFaculty, int noDeSalon) {
        assert selectedFaculty != null;

        // change the facultyAbbreviation depending on the faculty. If ingenierias = IA, if ciencias = CN, etc.
        String facultyAbbreviation;

        switch (selectedFaculty) {
            case "Ingenierías" -> facultyAbbreviation = "IA";
            case "Ciencias" -> facultyAbbreviation = "CN";
            case "Humanidades" -> facultyAbbreviation = "HU";
            case "Ciencias sociales" -> facultyAbbreviation = "CS";
            case "Salud" -> facultyAbbreviation = "SL";
            default -> throw new IllegalStateException("Unexpected value: " + selectedFaculty);
        }

        return "Edificio " + facultyAbbreviation + " - Aula " + noDeSalon;
    }

    public AgregarSalonGUI() {
        this(new NodosSalon());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgregarSalonGUI::new);
    }
}