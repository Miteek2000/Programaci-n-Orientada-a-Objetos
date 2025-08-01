package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioAsistencia {
    // Mapa: ID usuario lista de asistencias true=asistió, false=falta
    private final Map<Integer, ArrayList<Boolean>> asistencias;

    public ServicioAsistencia() {
        this.asistencias = new HashMap<>();
    }

    public void registrarAsistencia(int idUsuario, boolean presente) {
        asistencias.putIfAbsent(idUsuario, new ArrayList<>());
        asistencias.get(idUsuario).add(presente);
    }

    public ArrayList<Boolean> getAsistencias(int idUsuario) {
        return asistencias.getOrDefault(idUsuario, new ArrayList<>());
    }

    public int getTotalAsistencias(int idUsuario) {
        ArrayList<Boolean> lista = getAsistencias(idUsuario);
        int total = 0;
        for (Boolean b : lista) {
            if (b) total++;
        }
        return total;
    }

    public int getTotalFaltas(int idUsuario) {
        ArrayList<Boolean> lista = getAsistencias(idUsuario);
        int total = 0;
        for (Boolean b : lista) {
            if (!b) total++;
        }
        return total;
    }
}
