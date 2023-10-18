import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AvailableClassroomsDialog extends JDialog {

    public AvailableClassroomsDialog(JFrame parent, List<NodoSalon> availableClassrooms, NodoClase clase) {
        super(parent, "Available Classrooms and Classes", true);

        Object[][] data = new Object[availableClassrooms.size()][4];
        for (int i = 0; i < availableClassrooms.size(); i++) {
            NodoSalon salon = availableClassrooms.get(i);
            data[i][0] = salon.getUbicacion();
            data[i][1] = salon.getEscuela();
            data[i][2] = salon.getCapacidad();
            data[i][3] = salon.isProyector() ? "Yes" : "No";
        }

        String[] columnNames = {"Location", "Faculty", "Capacity", "Projector"};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);

        // Add a button to assign the selected classroom to the selected class
        JButton assignClassroomButton = new JButton("Assign Classroom");

        // Create a menu that displays the classrooms for the user to select but only if there are classrooms available
        List<NodoSalon> availableClassroooms = ClassroomManager.getInstance().getAvailableClassroomsForClass(clase);
        String[] availableClassesArray = new String[availableClassroooms.size()];
        for (int i = 0; i < availableClassroooms.size(); i++) {
            NodoSalon salon = availableClassroooms.get(i);
            availableClassesArray[i] = salon.getUbicacion();
        }
        JComboBox<String> availableClassesMenu = new JComboBox<>(availableClassesArray);
        add(availableClassesMenu, BorderLayout.NORTH);

        // Add the button to the menu
        add(assignClassroomButton, BorderLayout.SOUTH);

        // Add an action listener to the button
        assignClassroomButton.addActionListener(e -> {
            List<NodoSalon> assignedClassrooms = ClassroomManager.getInstance().getAssignedClassrooms();

            // add the previous classroom to the available list
            NodoSalon previousSalon = ClassroomManager.getInstance().getAssignedClassroom(clase, assignedClassrooms);
            ClassroomManager.getInstance().addClassroom(previousSalon);
            System.out.println("Previous classroom added to the available list.");

            // assign the new classroom to the class
            NodoSalon newSalon = availableClassroooms.get(availableClassesMenu.getSelectedIndex());

            // update the class with the new information

            clase.facultad = newSalon.getEscuela();
            clase.salonAsignado = newSalon.getUbicacion();
            clase.aforo = newSalon.getCapacidad();
            clase.proyector = newSalon.isProyector();

            ClassroomManager.getInstance().assignClassroom(newSalon);
            System.out.println("New classroom assigned to the class.");

            // dispose of the dialog and the parent
            System.out.println("Dialog and parent disposed.");
            dispose();
            parent.dispose();
        });

    }
}
