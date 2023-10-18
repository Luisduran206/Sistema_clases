import java.util.ArrayList;
import java.util.List;

public class ClassroomManager {
    private static final ClassroomManager instance = new ClassroomManager();
    private static final List<NodoSalon> availableClassrooms = new ArrayList<>();
    private static final List<NodoSalon> assignedClassrooms = new ArrayList<>();

    private ClassroomManager() {
        // Private constructor to enforce singleton pattern
    }

    public static ClassroomManager getInstance() {
        return instance;
    }

    public List<NodoSalon> getAvailableClassrooms() {
        return availableClassrooms;
    }

    public List<NodoSalon> getAssignedClassrooms() {
        return assignedClassrooms;
    }

    // Correct way to add a classroom to ClassroomManager
    public void addClassroom(NodoSalon salon) {
        availableClassrooms.add(salon);
    }

    public void assignClassroom(NodoSalon salon) {
        availableClassrooms.remove(salon);
    }

    public List<NodoSalon> getAvailableClassroomsForClass(NodoClase clase) {
        // Get the available classrooms depending on the class requirements (capacity and projector)
        List<NodoSalon> availableClassroomsForClass = new ArrayList<>();
        for (NodoSalon salon : availableClassrooms) {
            if (salon.getCapacidad() >= clase.aforo && salon.isProyector() == clase.proyector) {
                availableClassroomsForClass.add(salon);
            }
        }
        return availableClassroomsForClass;
    }

    public NodoSalon getAssignedClassroom(NodoClase clase, List<NodoSalon> assignedClassrooms) {
        // Get the assigned classroom for the class
        for (NodoSalon salon : assignedClassrooms) {
            // check if the class name is the same as the one in the list
            System.out.println("Comparing " + salon.getClase() + " with " + clase.nombreClase);
            if (salon.getClase() == null) {
                continue;
            }
            if (salon.getClase().equalsIgnoreCase(clase.nombreClase)) {
                return salon;
            }
        }
        System.out.println("No assigned classroom found for " + clase.nombreClase);
        return null;
    }

    public void updateClassroom(NodoSalon salon) {
        // Update the classroom in the available classrooms list
        for (NodoSalon availableSalon : availableClassrooms) {
            if (availableSalon.getUbicacion().equalsIgnoreCase(salon.getUbicacion())) {
                availableSalon.setClase(salon.getClase());
                System.out.println("Updated " + salon.getUbicacion() + " with " + salon.getClase());

                // Update the classroom in the assigned classrooms list
                assignedClassrooms.add(salon);
                break;
            }
        }
    }
}
