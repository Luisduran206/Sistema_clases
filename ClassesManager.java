import java.util.ArrayList;
import java.util.List;

public class ClassesManager {
    private static final ClassesManager classesInstance = new ClassesManager();
    public static final List<NodoClase> availableClasses = new ArrayList<>();

    private ClassesManager() {
        // Private constructor to enforce singleton pattern
    }

    public static ClassesManager getInstance() {
        return classesInstance;
    }

    public List<NodoClase> getAvailableClasses() {
        return availableClasses;
    }

    public static void addClass(NodoClase clase) {
        availableClasses.add(clase);
    }

}
