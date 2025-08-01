package Servicios;

import entidades.users.Subscriptor;
import java.util.ArrayList;
import java.util.Optional;

public class ServicioSubscriptor {
    private final ArrayList<Subscriptor> subscriptores;
    private int identificadorDisponible;

    public ServicioSubscriptor() {
        this.subscriptores = new ArrayList<>();
        this.identificadorDisponible = 1;
    }

    // Crear un nuevo subscriptor
    public boolean addSubscriptor(Subscriptor subscriptor) {
        if (!repeatedSubscriptor(subscriptor)) {
            this.subscriptores.add(subscriptor);
            this.identificadorDisponible++;
            return true;
        }
        return false;
    }

    // Editar un subscriptor existente por identificador
    public boolean editSubscriptor(int id, Subscriptor datosNuevos) {
        Subscriptor existente = findSubscriptor(id);
        if (existente != null) {
            existente.setNombre(datosNuevos.getNombre());
            existente.setApellidos(datosNuevos.getApellidos());
            existente.setContacto(datosNuevos.getContacto());
            existente.setActive(datosNuevos.isActive());
            // No se puede cambiar membresía ni identificador porque son finales
            return true;
        }
        return false;
    }

    // Eliminar un subscriptor por identificador
    public boolean deleteSubscriptor(int id) {
        Subscriptor existente = findSubscriptor(id);
        if (existente != null) {
            return this.subscriptores.remove(existente);
        }
        return false;
    }

    // Buscar un subscriptor por identificador
    public Subscriptor findSubscriptor(int id) {
        Optional<Subscriptor> subscriptor = this.subscriptores.stream()
            .filter(s -> s.getIDENTIFICADOR() == id)
            .findFirst();
        return subscriptor.orElse(null);
    }

    // Verificar si el subscriptor ya existe
    public boolean repeatedSubscriptor(Subscriptor subscriptor) {
        return this.subscriptores.contains(subscriptor);
    }

    // Obtener todos los subscriptores
    public ArrayList<Subscriptor> getAllSubscriptores() {
        return this.subscriptores;
    }

    // Obtener el siguiente identificador disponible
    public int getNextIdentificadorDisponible() {
        return this.identificadorDisponible;
    }
}
