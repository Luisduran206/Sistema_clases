import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodosSalon{

    List<NodoSalon> salones = new ArrayList<>();
    List<NodoSalon> eliminados = new ArrayList<>();

    public NodosSalon() {
    }

    public void insertSalon(String locationSalon, String facultad, int aforo, boolean proyector) {
        NodoSalon salon = new NodoSalon(locationSalon, facultad, aforo, proyector, null);

        // Add the new classroom to both ClassroomManager and the salones list
        ClassroomManager.getInstance().addClassroom(salon);
        salones.add(salon);
    }

    public boolean insertSalon(NodoSalon salon) {
        // Check if the classroom is already in the list

        List<NodoSalon> availableClassrooms = ClassroomManager.getInstance().getAvailableClassrooms();

        for (NodoSalon existingSalon : availableClassrooms) {
            if (existingSalon.getUbicacion().equalsIgnoreCase(salon.getUbicacion())) {
                System.out.println("The classroom name is already in the list.");
                return false;
            }
        }

        // Add the new classroom to both ClassroomManager and the salones list
        ClassroomManager.getInstance().addClassroom(salon);
        salones.add(salon);
        return true;
    }

    public NodoSalon findAndRemove(String escuela, int capacidad, boolean proyector) {
        String[] priorityList = escuelas(escuela);

        for (String faculty : priorityList) {
            NodoSalon assignedClassroom = null;

            List<NodoSalon> availableClassrooms = ClassroomManager.getInstance().getAvailableClassrooms();

            for (NodoSalon salon : availableClassrooms) {
                if (Objects.equals(faculty, salon.facultad) && salon.getCapacidad() >= capacidad && salon.isProyector() == proyector) {
                    assignedClassroom = salon;
                    break;  // Exit the loop once a suitable classroom is found
                }
            }

            if (assignedClassroom != null) {
                return assignedClassroom;
            }
        }

        // Return null or an appropriate message if no suitable classroom is found
        System.out.println("No suitable classroom found.");
        return null;
    }


    public int mejor(int aforo) {
        int mejor = 0;
        for (NodoSalon salone : salones) {
            int salon = salone.aforo;
            if (salon >= aforo) {
                if (mejor == 0 || salon < mejor) {
                    mejor = salon;
                }
            }
        }
        return mejor;
    }


    public String[] escuelas(String escuela) {
        List<String> priorityList = new ArrayList<>();

        // Ensure the selected faculty is added first to the list
        priorityList.add(escuela);

        // Add faculties in the original order of priority
        String[] originalOrder = new String[]{"Ingenierías", "Ciencias", "Humanidades", "Ciencias sociales", "Salud"};
        for (String faculty : originalOrder) {
            if (!faculty.equalsIgnoreCase(escuela)) {
                priorityList.add(faculty);
            }
        }

        return priorityList.toArray(new String[0]);
    }
}

class NodoSalon {

    String locationSalon;
    String facultad;
    int aforo;
    boolean proyector;
    String clase;

    public NodoSalon(String locationSalon, String facultad, int aforo, boolean proyector, String clase) {
        this.locationSalon = locationSalon;
        this.facultad = facultad;
        this.aforo = aforo;
        this.proyector = proyector;
        this.clase = clase;
    }

    public String getEscuela() {
        return facultad;
    }

    public int getCapacidad() {
        return aforo;
    }

    public boolean isProyector() {
        return proyector;
    }

    public String getUbicacion() {
        return locationSalon;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String nombreClase) {
        this.clase = nombreClase;
    }
}