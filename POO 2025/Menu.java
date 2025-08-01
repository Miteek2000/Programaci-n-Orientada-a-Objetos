import Servicios.ServicioUsuario;
import Servicios.ServicioInstructor;
import Servicios.ServicioSubscriptor;
import Servicios.ServicioAsistencia;
import Servicios.ServicioPago;
import Servicios.ServicioCurso;
import entidades.users.Instructor;
import entidades.users.Subscriptor;
import entidades.curso.Curso;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
   private static final Scanner sc = new Scanner (System.in);
      private final ServicioUsuario servicioUsuario = new ServicioUsuario();
   private final ServicioInstructor servicioInstructor = new ServicioInstructor();
   private final ServicioSubscriptor servicioSubscriptor = new ServicioSubscriptor();
   private final ServicioAsistencia servicioAsistencia = new ServicioAsistencia();
   private final ServicioPago servicioPago = new ServicioPago();
   private final ServicioCurso servicioCurso = new ServicioCurso();

   public Menu() {
   }

   public void startMenu() {
      int option = 0;
      do {
         System.out.println("Seleccione una opción principal\n");
         System.out.println("1. Crear Instructor");
         System.out.println("2. Crear Subscriptor");
         System.out.println("3. Crear Usuario");
         System.out.println("4. Visualizar todos los usuarios");
         System.out.println("5. Visualizar instructores");
         System.out.println("6. Visualizar subscriptores");
         System.out.println("7. Visualizar cursos");
         System.out.println("8. Registrar asistencia");
         System.out.println("9. Consultar asistencia");
         System.out.println("10. Registrar pago");
         System.out.println("11. Consultar pagos");
         System.out.println("12. Crear Curso");
         System.out.println("13. Eliminar Instructor");
         System.out.println("14. Eliminar Subscriptor");
         System.out.println("15. Eliminar Usuario");
         System.out.println("16. Eliminar Curso");
         System.out.println("0. Salir");
         option = this.selectOption();
         switch (option) {
            case 0:
               break;
            case 1:
               crearInstructor();
               break;
            case 2:
               crearSubscriptor();
               break;
            case 3:
               crearUsuario();
               break;
            case 4:
               visualizarTodosUsuarios();
               break;
            case 5:
               visualizarInstructores();
               break;
            case 6:
               visualizarSubscriptores();
               break;
            case 7:
               visualizarCursos();
               break;
            case 8:
               registrarAsistencia();
               break;
            case 9:
               consultarAsistencia();
               break;
            case 10:
               registrarPago();
               break;
            case 11:
               consultarPagos();
               break;
            case 12:
               crearCurso();
               break;
            case 13:
               eliminarInstructor();
               break;
            case 14:
               eliminarSubscriptor();
               break;
            case 15:
               eliminarUsuario();
               break;
            case 16:
               eliminarCurso();
               break;
            default:
               System.out.println("Opción no válida\n");
         }
      } while(option != 0);
   }

   private void visualizarTodosUsuarios() {
      System.out.println("\nUsuarios registrados:");
      boolean hayDatos = false;
      // Mostrar instructores
      var instructores = servicioInstructor.getAllInstructors();
      for (var i : instructores) {
         System.out.println("[INSTRUCTOR]");
         System.out.println(i);
         hayDatos = true;
      }
      // Mostrar subscriptores
      var subscriptores = servicioSubscriptor.getAllSubscriptores();
      for (var s : subscriptores) {
         System.out.println("[SUBSCRIPTOR]");
         System.out.println(s);
         hayDatos = true;
      }
      // Mostrar usuarios generales
      var generales = servicioUsuario.getUsuariosGenerales();
      for (var u : generales) {
         System.out.println("[USUARIO]");
         System.out.println(u);
         hayDatos = true;
      }
      if (!hayDatos) {
         System.out.println("No hay usuarios registrados.");
      }
   }

   private void crearInstructor() {

      Scanner sc = new Scanner(System.in);
      System.out.print("Nombre: ");
      String nombre = sc.nextLine();
      System.out.print("Apellidos: ");
      String apellidos = sc.nextLine();
      System.out.print("Email: ");
      String email = sc.nextLine();
      System.out.print("Contraseña: ");
      String contraseña = sc.nextLine();
      System.out.print("Numero: ");
      int numero = sc.nextInt();
      sc.nextLine(); // limpiar buffer
      System.out.print("Especialidad: ");
      String especialidad = sc.nextLine();
      int id = servicioUsuario.getNextIdentifierAvailable(); // Usar identificador global
      Instructor instructor = new Instructor(id, nombre, apellidos, email, contraseña, numero, especialidad);
      if(servicioInstructor.addInstructor(instructor)) {
         servicioUsuario.addUser(instructor); // Agregar a la lista general
         System.out.println("Instructor creado correctamente");
      } else {
         System.out.println("No se pudo crear el instructor (posible duplicado)");
      }
   }

   private void editarInstructor() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del instructor a editar: ");
      int id = sc.nextInt();
      sc.nextLine(); // limpiar buffer
      Instructor existente = servicioInstructor.findInstructor(id);
      if(existente == null) {
         System.out.println("No existe instructor con ese ID");
         return;
      }
      System.out.print("Nuevo nombre: ");
      String nombre = sc.nextLine();
      System.out.print("Nuevos apellidos: ");
      String apellidos = sc.nextLine();
      System.out.print("Nuevo contacto: ");
      String contacto = sc.nextLine();
      // Para compatibilidad, pasar email, contraseña y especialidad vacíos
      Instructor datosNuevos = new Instructor(id, nombre, apellidos, "", "", existente.getNumero(), "");
      if(servicioInstructor.editInstructor(id, datosNuevos)) {
         System.out.println("Instructor editado correctamente");
      } else {
         System.out.println("No se pudo editar el instructor");
      }
   }

   private void eliminarInstructor() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del instructor a eliminar: ");
      int id = sc.nextInt();
      if(servicioInstructor.deleteInstructor(id)) {
         System.out.println("Instructor eliminado correctamente");
      } else {
         System.out.println("No se pudo eliminar el instructor");
      }
   }

   private void crearSubscriptor() {
      Scanner sc = new Scanner(System.in);
      System.out.print("Nombre: ");
      String nombre = sc.nextLine();
      System.out.print("Apellidos: ");
      String apellidos = sc.nextLine();
      System.out.print("Email: ");
      String email = sc.nextLine();
      System.out.print("Contraseña: ");
      String contraseña = sc.nextLine();
      System.out.print("Membresía: ");
      String membresia = sc.nextLine();
      System.out.print("Activo (1=activo, 0=no activo): ");
      int activoInt = sc.nextInt();
      boolean activo = (activoInt == 1);
      byte nivel = 1; // Valor por defecto
      int id = servicioUsuario.getNextIdentifierAvailable(); // Usar identificador global
      Subscriptor subscriptor = new Subscriptor(id, nombre, apellidos, email, contraseña, membresia, activo, nivel, new java.util.ArrayList<>());
      if(servicioSubscriptor.addSubscriptor(subscriptor)) {
         servicioUsuario.addUser(subscriptor); // Agregar a la lista general
         System.out.println("Subscriptor creado correctamente");
      } else {
         System.out.println("No se pudo crear el subscriptor (posible duplicado)");
      }
   }

   private void editarSubscriptor() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del subscriptor a editar: ");
      int id = sc.nextInt();
      sc.nextLine(); // limpiar buffer
      Subscriptor existente = servicioSubscriptor.findSubscriptor(id);
      if(existente == null) {
         System.out.println("No existe subscriptor con ese ID");
         return;
      }
      System.out.print("Nuevo nombre: ");
      String nombre = sc.nextLine();
      System.out.print("Nuevos apellidos: ");
      String apellidos = sc.nextLine();
      System.out.print("Nuevo contacto: ");
      String contacto = sc.nextLine();
      System.out.print("Activo (1=activo, 0=no activo): ");
      int activoInt = sc.nextInt();
      boolean activo = (activoInt == 1);
      System.out.print("Nivel (byte): ");
      byte nivel = sc.nextByte();
      // Para compatibilidad, pasar email y contraseña vacíos
      Subscriptor datosNuevos = new Subscriptor(id, nombre, apellidos, "", "", existente.getMEMBRESIA(), activo, nivel, new java.util.ArrayList<>());
      if(servicioSubscriptor.editSubscriptor(id, datosNuevos)) {
         System.out.println("Subscriptor editado correctamente");
      } else {
         System.out.println("No se pudo editar el subscriptor");
      }
   }

   private void eliminarSubscriptor() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del subscriptor a eliminar: ");
      int id = sc.nextInt();
      if(servicioSubscriptor.deleteSubscriptor(id)) {
         System.out.println("Subscriptor eliminado correctamente");
      } else {
         System.out.println("No se pudo eliminar el subscriptor");
      }
   }

   private void crearUsuario() {
      
      System.out.print("Nombre: ");
      String nombre = sc.nextLine();
      System.out.print("Apellidos: ");
      String apellidos = sc.nextLine();
      System.out.print("Email: ");
      String email = sc.nextLine();
      System.out.print("Contraseña: ");
      String contraseña = sc.nextLine();
      int id = servicioUsuario.getNextIdentifierAvailable();
      entidades.users.Usuario usuario = new entidades.users.Usuario(id, nombre, apellidos, email, contraseña);
      if(servicioUsuario.addUser(usuario)) {
         System.out.println("Usuario creado correctamente");
      } else {
         System.out.println("No se pudo crear el usuario (posible duplicado)");
      }
   }

   private void editarUsuario() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del usuario a editar: ");
      int id = sc.nextInt();
      sc.nextLine(); // limpiar buffer
      entidades.users.Usuario existente = servicioUsuario.findUser(id);
      if(existente == null) {
         System.out.println("No existe usuario con ese ID");
         return;
      }
      System.out.print("Nuevo nombre: ");
      String nombre = sc.nextLine();
      System.out.print("Nuevos apellidos: ");
      String apellidos = sc.nextLine();
      System.out.print("Nuevo email: ");
      String email = sc.nextLine();
      System.out.print("Nueva contraseña: ");
      String contraseña = sc.nextLine();
      if(servicioUsuario.editUser(id, nombre, apellidos, email, contraseña)) {
         System.out.println("Usuario editado correctamente");
      } else {
         System.out.println("No se pudo editar el usuario");
      }
   }

   private void eliminarUsuario() {
      System.out.print("ID del usuario a eliminar: ");
      int id = sc.nextInt();
      if(servicioUsuario.deleteUser(id)) {
         System.out.println("Usuario eliminado correctamente");
      } else {
         System.out.println("No se pudo eliminar el usuario");
      }
   }

   private void registrarAsistencia() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del usuario: ");
      int id = sc.nextInt();
      System.out.print("¿Asistió? (true/false): ");
      boolean presente = sc.nextBoolean();
      servicioAsistencia.registrarAsistencia(id, presente);
      System.out.println("Asistencia registrada.");
   }

   private void consultarAsistencia() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del usuario: ");
      int id = sc.nextInt();
      var asistencias = servicioAsistencia.getAsistencias(id);
      System.out.println("Asistencias: " + asistencias);
      System.out.println("Total asistencias: " + servicioAsistencia.getTotalAsistencias(id));
      System.out.println("Total faltas: " + servicioAsistencia.getTotalFaltas(id));
   }

   private void registrarPago() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del usuario: ");
      int id = sc.nextInt();
      System.out.print("Monto del pago: ");
      double monto = sc.nextDouble();
      servicioPago.registrarPago(id, monto);
      System.out.println("Pago registrado.");
   }

   private void consultarPagos() {
      Scanner sc = new Scanner(System.in);
      System.out.print("ID del usuario: ");
      int id = sc.nextInt();
      var pagos = servicioPago.getPagos(id);
      System.out.println("Pagos: " + pagos);
      System.out.println("Total pagado: $" + servicioPago.getTotalPagado(id));
   }

   private void crearCurso() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Lista de instructores disponibles:");
      var instructores = servicioInstructor.getAllInstructors();
      if (instructores.isEmpty()) {
         System.out.println("No hay instructores registrados. Debe crear uno primero.");
         return;
      }
      for (var i : instructores) {
         System.out.println("ID: " + i.getIDENTIFICADOR() + " - " + i.getNombre() + " " + i.getApellidos());
      }
      System.out.print("Ingrese el ID del instructor para el curso: ");
      int idInstructor = sc.nextInt();
      Instructor instructor = servicioInstructor.findInstructor(idInstructor);
      if (instructor == null) {
         System.out.println("Instructor no encontrado.");
         return;
      }
      sc.nextLine(); // limpiar buffer
      System.out.print("Periodo del curso: ");
      String periodo = sc.nextLine();
      System.out.print("Información del curso: ");
      String info = sc.nextLine();
      int numero = servicioCurso.getNextNumeroDisponible();
      Curso curso = new Curso(numero, periodo, info, new java.util.ArrayList<>(), instructor);
      servicioCurso.addCurso(curso);
      System.out.println("Curso creado correctamente.");
   }

   private void eliminarCurso() {
      Scanner sc = new Scanner(System.in);
      var cursos = servicioCurso.getAllCursos();
      if (cursos.isEmpty()) {
         System.out.println("No hay cursos registrados.");
         return;
      }
      System.out.println("Cursos disponibles:");
      for (var c : cursos) {
         System.out.println("Número: " + c.getNUMERO() + " - " + c.getInformacion());
      }
      System.out.print("Ingrese el número del curso a eliminar: ");
      int numero = sc.nextInt();
      boolean eliminado = cursos.removeIf(c -> c.getNUMERO() == numero);
      if (eliminado) {
         System.out.println("Curso eliminado correctamente.");
      } else {
         System.out.println("No se encontró el curso con ese número.");
      }
   }

   private void visualizarInstructores() {
      var instructores = servicioInstructor.getAllInstructors();
      if (instructores.isEmpty()) {
         System.out.println("No hay instructores registrados.");
      } else {
         System.out.println("\nInstructores registrados:");
         for (var i : instructores) {
            System.out.println(i);
         }
      }
   }

   private void visualizarSubscriptores() {
      var subscriptores = servicioSubscriptor.getAllSubscriptores();
      if (subscriptores.isEmpty()) {
         System.out.println("No hay subscriptores registrados.");
      } else {
         System.out.println("\nSubscriptores registrados:");
         for (var s : subscriptores) {
            System.out.println(s);
         }
      }
   }

   private void visualizarCursos() {
      var cursos = servicioCurso.getAllCursos();
      if (cursos.isEmpty()) {
         System.out.println("No hay cursos registrados.");
      } else {
         System.out.println("\nCursos registrados:");
         for (var c : cursos) {
            System.out.println(c);
         }
      }
   }

   private int selectOption() {
      Scanner sc = new Scanner(System.in);
      while(true) {
         System.out.print("Escriba una opción:\t");
         int num = 0;
         boolean completed = false;
         try {
            num = sc.nextInt();
            completed = true;
         } catch (InputMismatchException var9) {
            System.out.println("Solo puedes escribir números");
         } catch (NoSuchElementException var10) {
            System.out.println("Excepción 1");
         } finally {
            if (completed) {
               return num;
            }
            sc.next();
         }
      }
   }
}