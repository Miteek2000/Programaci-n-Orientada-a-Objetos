package Servicios;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import entidades.users.Usuario;

public class ServicioUsuario {
	// Lista que almacena los usuarios registrados en el sistema
	private final ArrayList<Usuario> users;
	// Identificador incremental para asignar a nuevos usuarios
	private int indentifier_available;

	// Constructor: inicializa la lista de usuarios y el identificador
	public ServicioUsuario() {
		this.indentifier_available = 1;
		this.users = new ArrayList<Usuario>();
	}
	
	// Devuelve una cadena con la lista de todos los usuarios registrados
	public String listAllUsers() {
		StringBuilder users = new StringBuilder("Lista de Usuarios:\n");
		this.users.forEach((user)->{
			users.append("\t").append(user);
		});
		return users.toString();
	}
	
	// Devuelve la lista completa de usuarios
	public ArrayList<Usuario> getAllUsers(){
		return this.users;
	}
	
	// Busca un usuario por su identificador. Si existe, lo retorna; si no, retorna null
	public Usuario findUser(int identificador) {
		Optional<Usuario> user = this.users.stream()
			.filter(obj -> obj.getIDENTIFICADOR() == identificador)
			.findFirst();
		return user.isPresent() ? user.get() : null;
	}
	


	
	// Verifica si un usuario ya está registrado en la lista
	public boolean repeatedUser(Usuario user) {
		if(this.users.size() == 0) {
			return false;
		}
		return this.users.contains(user);
	}

	// Devuelve el siguiente identificador disponible para un nuevo usuario
	public int getNextIdentifierAvailable() {
		return this.indentifier_available;
	}
	
	// Edita los datos de un usuario existente por su identificador
	public boolean editUser(int id, String nombre, String apellidos, String email, String contraseña) {
		Usuario existente = findUser(id);
		if (existente != null) {
			existente.setNombre(nombre);
			existente.setApellidos(apellidos);
			existente.setEmail(email);
			existente.setContraseña(contraseña);
			return true;
		}
		return false;
	}
	
	// Devuelve una lista de usuarios que son subscriptores
	public ArrayList<Usuario> getSubscriptores() {
		ArrayList<Usuario> subscriptores = new ArrayList<>();
		for (Usuario u : users) {
			if (u instanceof entidades.users.Subscriptor) {
				subscriptores.add(u);
			}
		}
		return subscriptores;
	}

	// Devuelve una lista de usuarios generales (ni instructor ni subscriptor)
	public ArrayList<Usuario> getUsuariosGenerales() {
		ArrayList<Usuario> generales = new ArrayList<>();
		for (Usuario u : users) {
			if (!(u instanceof entidades.users.Instructor) && !(u instanceof entidades.users.Subscriptor)) {
				generales.add(u);
			}
		}
		return generales;
	}

	// Datos de conexión a MariaDB
	private static final String URL = "jdbc:mariadb://localhost:3306/subcriptor";
	private static final String USER = "root"; 
	private static final String PASS = "";    





public boolean addUser(Usuario usuario) {
        String sql = "INSERT INTO usuarios(id_usuario, nombre, apellido, email, contrasena) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        

		pstmt.setInt(1, usuario.getIDENTIFICADOR());
		pstmt.setString(2, usuario.getNombre());					
        pstmt.setString(3, usuario.getApellidos());
        pstmt.setString(4, usuario.getEmail());
		pstmt.setString(5, usuario.getContraseña());

        
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    users.add(usuario);
                }
            }
            return true;
        }
        return false;
    } catch (SQLException e) {
        System.err.println("Error al insertar usuario: " + e.getMessage());
        return false;
    }
}
public boolean deleteUser(int idUser) {
    String sql_D = "DELETE FROM usuarios WHERE id_usuario = ?";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement(sql_D)) {

        pstmt.setInt(1, idUser);

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            // También puedes eliminar de la lista en memoria si lo deseas
            Usuario user = findUser(idUser);
            if (user != null) {
                users.remove(user);
            }
            return true;
        } else {
            System.out.println("No se encontró el usuario con ese ID.");
            return false;
        }

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }


}




