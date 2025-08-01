package entidades.users;

public class Instructor extends Usuario {

	private final int numero;
	private String especialidad;

	// Constructor mínimo (no recomendado para uso general)
	public Instructor(int identificador, int numero) {
		super(identificador, "", "", "", ""); // valores vacíos por compatibilidad
		this.numero = numero;
		this.especialidad = "";
	}

	// Constructor principal actualizado
	public Instructor(int identificador, String nombre, String apellidos, String email, String contraseña, int numero, String especialidad) {
		super(identificador, nombre, apellidos, email, contraseña);
		this.numero = numero;
		this.especialidad = especialidad;
	}

	public int getNumero() {
		return numero;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return super.toString() + "Especialidad: " + especialidad + "\n";
	}
}
