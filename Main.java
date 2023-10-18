import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    private final NodosSalon nodosSalon = new NodosSalon();
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final ClassroomManager classroomManager = ClassroomManager.getInstance();
    private final ClassesManager classesManager = ClassesManager.getInstance();

    NodosClase creador = new NodosClase();

    public Main() {
        JFrame frame = new JFrame("Menú con CardLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel menuPanel = new JPanel();
        JButton agregarClaseButton = new JButton("Agregar clase y asignar salón");
        JButton agregarSalonButton = new JButton("Agregar salón");
        JButton cambiarSalonButton = new JButton("Cambiar salón de una clase");

        creador.crear();

        JButton viewAvailableClassroomsButton = getAvailableClassroomsButton(frame);

        menuPanel.add(agregarClaseButton);
        menuPanel.add(agregarSalonButton);
        menuPanel.add(cambiarSalonButton);
        menuPanel.add(viewAvailableClassroomsButton);

        cardPanel.add(menuPanel, "Menú");

        agregarClaseButton.addActionListener(e -> {
            new AgregarClaseGUI();
            cardLayout.show(cardPanel, "Asignar salón");
        });

        agregarSalonButton.addActionListener(e -> {
            new AgregarSalonGUI(nodosSalon);
            cardLayout.show(cardPanel, "Agregar salón");
        });

        cambiarSalonButton.addActionListener(e -> {
            new CambiarSalonGUI();
            cardLayout.show(cardPanel, "Cambiar salón de una clase");
        });

        frame.add(cardPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    private JButton getAvailableClassroomsButton(JFrame frame) {
        JButton viewAvailableClassroomsButton = new JButton("View Available Classrooms and Classes");
        viewAvailableClassroomsButton.addActionListener(e -> {
            List<NodoSalon> availableClassrooms = classroomManager.getAvailableClassrooms();
            List<NodoClase> availableClasses = classesManager.getAvailableClasses();
            AvailableClassroomsAndClassesDialog dialog = new AvailableClassroomsAndClassesDialog(frame, availableClassrooms, availableClasses);
            dialog.setVisible(true);
        });
        return viewAvailableClassroomsButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
