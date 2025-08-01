package entidades.curso;

import java.util.ArrayList;

import entidades.users.Instructor;
import entidades.users.Subscriptor;

public class Curso {

	private final int NUMERO;
	private String periodo;
	private String informacion;
	private final ArrayList<Subscriptor> SUBSCRIPTORES;
	private final Instructor INSTRUCTOR;
	
	public Curso(int numero, Instructor instructor) {
		this.NUMERO = numero;
		this.SUBSCRIPTORES = new ArrayList<Subscriptor>();
		this.INSTRUCTOR = instructor;
	}
	
	public Curso(
			int numero,
			String periodo,
			String informacion,
			ArrayList<Subscriptor> subscriptores,
			Instructor instructor
			) {
		
		this.NUMERO = numero;
		this.periodo = periodo;
		this.informacion = informacion;
		this.SUBSCRIPTORES = subscriptores;
		this.INSTRUCTOR = instructor;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public int getNUMERO() {
		return NUMERO;
	}
	
	public String listarSubscriptores() {
		
		if(this.SUBSCRIPTORES.size() > 0) {
			
			StringBuffer lista = new StringBuffer();
			
			lista.append("Lista de subscriptores del curso\n\n");
			
			for(Subscriptor x : this.SUBSCRIPTORES) {
				lista.append(String.format("\t%s", x.toString()));
			}
			return lista.toString();
		}else {
			return "No hay subscritores en esta clase";
		}
	}

	public boolean addSubscriptor(Subscriptor subscriptor) {
		this.SUBSCRIPTORES.add(subscriptor);
		return true;
	}
	
	public String getInfoInstructor() {
		return this.INSTRUCTOR.toString();
	}

}

