import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AvailableClassroomsAndClassesDialog extends JDialog {

    public AvailableClassroomsAndClassesDialog(JFrame parent, List<NodoSalon> availableClassrooms, List<NodoClase> availableClasses) {
        super(parent, "Available Classrooms and Classes", true);

        Object[][] classroomData = new Object[availableClassrooms.size()][4];
        for (int i = 0; i < availableClassrooms.size(); i++) {
            NodoSalon salon = availableClassrooms.get(i);
            classroomData[i][0] = salon.getUbicacion();
            classroomData[i][1] = salon.getEscuela();
            classroomData[i][2] = salon.getCapacidad();
            classroomData[i][3] = salon.isProyector() ? "Yes" : "No";
        }

        Object[][] classData = new Object[availableClasses.size()][5];
        for (int i = 0; i < availableClasses.size(); i++) {
            NodoClase clase = availableClasses.get(i);
            classData[i][0] = clase.facultad;
            classData[i][1] = clase.nombreClase;
            classData[i][2] = clase.aforo;
            classData[i][3] = clase.proyector ? "Yes" : "No";
            classData[i][4] = clase.salonAsignado;
        }

        String[] classrooomColumnNames = {"Location", "Faculty", "Capacity", "Projector"};
        JTable table = new JTable(classroomData, classrooomColumnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        String[] classColumnNames = {"Faculty", "Class Name", "Capacity", "Projector", "Assigned Classroom"};
        JTable table2 = new JTable(classData, classColumnNames);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.WEST);
        add(scrollPane2, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(parent);
    }
}