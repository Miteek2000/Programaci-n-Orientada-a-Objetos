package Servicios;

import entidades.curso.Curso;
import java.util.ArrayList;

public class ServicioCurso {
    private final ArrayList<Curso> cursos;
    private int numeroDisponible;

    public ServicioCurso() {
        this.cursos = new ArrayList<>();
        this.numeroDisponible = 1;
    }

    public boolean addCurso(Curso curso) {
        this.cursos.add(curso);
        this.numeroDisponible++;
        return true;
    }

    public ArrayList<Curso> getAllCursos() {
        return this.cursos;
    }

    public int getNextNumeroDisponible() {
        return this.numeroDisponible;
    }
}
