import java.util.ArrayList;
import java.util.List;

public class NodosClase{

    NodosSalon salones = new NodosSalon();

    public NodosClase() {
    }

    public String insertClase(String facultad, String nombreClase, int aforo, boolean proyector) {
        String confirmacion;
        NodoSalon salon = salones.findAndRemove(facultad, aforo, proyector);
        if(salon != null) {
            NodoClase clase = new NodoClase(facultad, nombreClase, aforo, proyector, salon.getUbicacion());
            ClassesManager.addClass(clase);

            salon.setClase(nombreClase);

            // Update the classroom manager with the new classroom (with the assigned class)
            ClassroomManager.getInstance().updateClassroom(salon);

            ClassroomManager.getInstance().assignClassroom(salon);

            confirmacion = "Su clase fue agregada: " + nombreClase + " en " + salon.getUbicacion();
            System.out.println(confirmacion);
        } else {
            confirmacion = "No se encontró un aula adecuada.";
            System.out.println(confirmacion);
        }
        return confirmacion;
    }

    public void crear() {
        salones.insertSalon("Edificio HU - Aula 102", "Humanidades", 20, false);
        salones.insertSalon("Edificio CN - Aula 102", "Ciencias", 30, true);
        salones.insertSalon("Edificio CN - Aula 103", "Ciencias", 40, true);
        salones.insertSalon("Edificio CN - Aula 104", "Ciencias", 40, false);
        salones.insertSalon("Edificio IA - Aula 103", "Ingenierías", 40, true);
        salones.insertSalon("Edificio CS - Aula 204", "Ciencias sociales", 50, true);
        salones.insertSalon("Edificio SL - Aula 206", "Salud", 25, true);
    }
}

class NodoClase {
    String facultad;
    String nombreClase;
    int aforo;
    boolean proyector;
    String salonAsignado;

    public NodoClase(String facultad, String nombreClase, int aforo, boolean proyector, String salonAsignado) {
        this.facultad = facultad;
        this.nombreClase = nombreClase;
        this.aforo = aforo;
        this.proyector = proyector;
        this.salonAsignado = salonAsignado;
    }

    public String toString() {
        return "Clase: " + nombreClase + ", Facultad: " + facultad + ", Aforo: " + aforo + ", Proyector: " + proyector + ", Salón Asignado: " + salonAsignado;
    }
}