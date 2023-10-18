import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CambiarSalonGUI extends JFrame {
    JLabel nombreBusquedaLabel;
    JTextField nombreBusquedaTexfield;
    JButton buscarYeliminarButton;
    public CambiarSalonGUI(){
        super("Cambiar salón");
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        container.setLayout(new FlowLayout());

        nombreBusquedaLabel = new JLabel("Seleccione la clase a cambiar:");
        container.add(nombreBusquedaLabel);

        // Display the available classes in a JComboBox
        List<NodoClase> availableClasses = ClassesManager.getInstance().getAvailableClasses();
        String[] availableClassesArray = new String[availableClasses.size()];
        for (int i = 0; i < availableClasses.size(); i++) {
            NodoClase clase = availableClasses.get(i);
            availableClassesArray[i] = clase.nombreClase;
        }
        JComboBox<String> availableClassesMenu = new JComboBox<>(availableClassesArray);
        container.add(availableClassesMenu);

        buscarYeliminarButton = new JButton("Reasignar salón");
        container.add(buscarYeliminarButton);
        buscarYeliminarButton.addActionListener(e -> {

            // Show only the classrooms that match the capacity and projector requirements
            NodoClase clase = availableClasses.get(availableClassesMenu.getSelectedIndex());
            List<NodoSalon> availableClassroomsForClass = ClassroomManager.getInstance().getAvailableClassroomsForClass(clase);

            // Show the available classrooms with their respective attributes
            AvailableClassroomsDialog dialog = new AvailableClassroomsDialog(this, availableClassroomsForClass, clase);
            dialog.setVisible(true);
        });

        setSize(300, 150);
        setVisible(true);
    }
}
