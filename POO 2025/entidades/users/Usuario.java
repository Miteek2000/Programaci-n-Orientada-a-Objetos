package entidades.users;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {
	
	private final int IDENTIFICADOR;
	private String nombre;
	private String apellidos;
	private String email;
	private String contraseña;
	
	public Usuario(
			int identificador
			) {
		this.IDENTIFICADOR = identificador;		
	}
	

	public Usuario(
			int identificador, 
			String nombre, 
			String apellidos,
			String email,
			String contraseña
			) {
		this.IDENTIFICADOR = identificador;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.contraseña = contraseña;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public int getIDENTIFICADOR() {
		return IDENTIFICADOR;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Usuario usuario = (Usuario) obj;
		return this.IDENTIFICADOR == usuario.getIDENTIFICADOR();
	}
	
	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		msg.append("ID: ").append(this.IDENTIFICADOR).append("\n");
		msg.append("Nombre completo: ").append(this.nombre).append(" ").append(this.apellidos).append("\n");
		msg.append("Email: ").append(this.email).append("\n");
		return msg.toString();
	}
}