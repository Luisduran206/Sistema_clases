import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AgregarClaseGUI extends JFrame implements ActionListener{
    //Swing components and declarations
    JLabel plazasLabel, facultadLabel, proyectorLabel, nombreLabel;
    JTextField plazasTextField, nombreTextfield;
    JButton asignar;
    JCheckBox proyectorCheckbox;
    Boolean proyectorBool;
    JComboBox<String> facultadesMenu;
    NodosClase creador = new NodosClase();

    String[] facultades = {"Ingenierías", "Ciencias", "Humanidades", "Ciencias sociales", "Salud"};

    public AgregarClaseGUI() {
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        nombreLabel = new JLabel("¿Cuál es el código de la clase?");
        container.add(nombreLabel);
        nombreTextfield = new JTextField(15);
        container.add(nombreTextfield);

        plazasLabel = new JLabel("¿Cuántos alumnos hay en la clase?");
        container.add(plazasLabel);
        plazasTextField = new JTextField(20);
        container.add(plazasTextField);

        facultadLabel = new JLabel("Escoge la facultad a la que pertenece la clase");
        container.add(facultadLabel);
        facultadesMenu = new JComboBox<>(facultades);
        container.add(facultadesMenu);

        // Create checkbox
        proyectorLabel = new JLabel("¿Necesita proyector?");
        container.add(proyectorLabel);
        proyectorCheckbox = new JCheckBox("Proyector");
        container.add(proyectorCheckbox);
        proyectorCheckbox.addActionListener(this);

        asignar = new JButton("Guardar datos y asignar aula");
        container.add(asignar);

        asignar.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(450, 157);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        // if the user closes the window go back to the main menu

        if (event.getSource() == asignar){
            //get school
            int facultadIndex = facultadesMenu.getSelectedIndex();
            String facultadString = switch (facultadIndex) {
                case 0 -> "Ingenierías";
                case 1 -> "Ciencias";
                case 2 -> "Humanidades";
                case 3 -> "Ciencias sociales";
                case 4 -> "Salud";
                default -> null;
            };

            // get class code
            String nombreClase = nombreTextfield.getText();

            //get the number of students
            String text = plazasTextField.getText(); // Get the text from the text field
            int aforo = Integer.parseInt(text);

            //get if the class needs proyector
            proyectorBool = proyectorCheckbox.isSelected();

            if(facultadString != null && nombreClase != null && aforo > 0) {
                // use assignClassroom method from ClassroomManager

                String confirmacion = creador.insertClase(facultadString, nombreClase, aforo, proyectorBool);
                JOptionPane.showMessageDialog(this, confirmacion);
            }

            dispose();
        }
    }

    public static void main(String[] args){
    }
}