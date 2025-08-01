package entidades.users;
import java.util.ArrayList;
import entidades.curso.Curso;
public class Subscriptor extends Usuario {
	
	private final String MEMBRESIA;
	private boolean active;
	private byte nivel;
	private final ArrayList<Curso> CURSOS;

	// Esto sirve para identificar al subscriptor
	public Subscriptor(
			int identificador, 
			String membresia
		) {
		super(identificador, "", "", "", ""); // valores vacíos por compatibilidad
		this.MEMBRESIA = membresia;
		this.CURSOS = new ArrayList<Curso>();
	}
	

	public Subscriptor(
			int identificador,
			String nombre,
			String apellidos,
			String email,
			String contraseña,
			String membresia,
			boolean active,
			byte nivel,
			ArrayList<Curso> cursos
			) {
		super(identificador, nombre, apellidos, email, contraseña);
		this.MEMBRESIA = membresia;
		this.active = active;
		this.nivel = nivel;
		this.CURSOS = cursos;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte getNivel() {
		return nivel;
	}

	public void setNivel(byte nivel) {
		this.nivel = nivel;
	}

	public String getMEMBRESIA() {
		return MEMBRESIA;
	}
	
	// Este método sirve para listar los cursos que tiene el subscriptor
	// Si no tiene cursos, devuelve un mensaje indicando que no hay cursos
	public String listarCursos() {
		
		if(this.CURSOS.size() > 0) {
			
			StringBuffer lista = new StringBuffer();
			
			lista.append("Lista de cursos del usuario\n\n");
			
			for(Curso x : this.CURSOS) {
				lista.append(String.format("\t%s", x.toString()));
			}
			return lista.toString();
		}else {
			return "No hay cursos para este usuario";
		}
	}
	
	
	public boolean addCurso(Curso curso) {
		this.CURSOS.add(curso);
		return true;
	}

}




