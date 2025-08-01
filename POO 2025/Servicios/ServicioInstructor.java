package Servicios;

import entidades.users.Instructor;
import java.util.ArrayList;
import java.util.Optional;

public class ServicioInstructor {
    private final ArrayList<Instructor> instructores;
    private int identificadorDisponible;

    public ServicioInstructor() {
        this.instructores = new ArrayList<>();
        this.identificadorDisponible = 1;
    }

    // Crear un nuevo instructor
    public boolean addInstructor(Instructor instructor) {
        if (!repeatedInstructor(instructor)) {
            this.instructores.add(instructor);
            this.identificadorDisponible++;
            return true;
        }
        return false;
    }

    // Editar un instructor existente por identificador
    public boolean editInstructor(int id, Instructor datosNuevos) {
        Instructor existente = findInstructor(id);
        if (existente != null) {
            existente.setNombre(datosNuevos.getNombre());
            existente.setApellidos(datosNuevos.getApellidos());
            existente.setContacto(datosNuevos.getContacto());
          
            return true;
        }
        return false;
    }

    // Eliminar un instructor por identificador
    public boolean deleteInstructor(int id) {
        Instructor existente = findInstructor(id);
        if (existente != null) {
            return this.instructores.remove(existente);
        }
        return false;
    }

    // Buscar un instructor por identificador
    public Instructor findInstructor(int id) {
        Optional<Instructor> instructor = this.instructores.stream()
            .filter(i -> i.getIDENTIFICADOR() == id)
            .findFirst();
        return instructor.orElse(null);
    }

    // Verificar si el instructor ya existe
    public boolean repeatedInstructor(Instructor instructor) {
        return this.instructores.contains(instructor);
    }

    // Obtener todos los instructores
    public ArrayList<Instructor> getAllInstructors() {
        return this.instructores;
    }

    // Obtener el siguiente identificador disponible
    public int getNextIdentificadorDisponible() {
        return this.identificadorDisponible;
    }
}
