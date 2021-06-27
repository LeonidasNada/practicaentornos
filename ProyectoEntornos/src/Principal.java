import java.sql.*;
import java.util.*;



public class Principal {
	
	String apellidos;

	public static void main(String[] args) throws SQLException {

		Administrador prueba = new Administrador();

		// prueba.login(conn, "Futbol");

		// prueba.crearAdmin(conn, "Futbol");

		// prueba.eliminarAdmin(conn, "Futbol");

		// prueba.modificarAdmin(conn, "Futbol");

		//Jugador jugPrueba = new Jugador();
		//Principal prnc = new Principal();
		
		prueba.login();

		//prnc.menuPrincipal();

	}

	public void menuPrincipal() {

			System.out.println("***************************************************************");
			System.out.println("******** ¡Bienvenido a nuestro programa! *********");
			System.out.println("***************************************************************");
			System.out.println("");
			System.out.println("Indique a continuación qué desea hacer: ");
			System.out.println("");
			System.out.println("1. Menú de jugadores");
			System.out.println("2. Menú de equipos");
			System.out.println("3. Menú de plantilla");
			System.out.println("4. Opciones de administrador");
			System.out.print("");

			Plantilla plnt = new Plantilla();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:

				menuJugador();
				break;

			case 2:
				menuEquipos();

				break;
			case 3:

				plnt.menuPlantilla();

				break;

			case 4:

				menuAdmin();
				break;

			default:
				System.out.println("Porfavor, seleccione una de las opciones mostradas en el menú.");
				menuPrincipal();
				break;
			}
			entrada.close();


	}

	public static void menuJugador() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Listar todos los jugadores");
			System.out.println("2. Listar jugadores por equipo");
			System.out.println("3. Modificar posicion del jugador");
			System.out.println("4. Modificar estado fisico del jugador");
			System.out.println("5. Modificar disponibilidad del jugador");
			System.out.println("6. Dar de alta jugador");
			System.out.println("7. Dar de baja jugador*");
			System.out.println("8. Modificar jugador*");
			System.out.println("9. Mover jugador de categoría*");
			System.out.println("10. Volver atrás");
			System.out.print("");

			Jugador jug = new Jugador();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {
			case 1:
				System.out.print("");
				System.out.println("Elija una opción:");
				System.out.println("1. Listar jugadores");
				System.out.println("2. Listar jugadores y Guardar TXT");

				int elige1 = entrada.nextInt();
				if (elige1 == 1) {
					jug.listarJugadores(conn, "Futbol");
				} else if (elige1 == 2) {
					jug.listarJugadorestxt(conn, "Futbol");
				} else {
					System.out.println("Elige una opción válida, vuelva a intentarlo...");
					System.out.println("");
					menuJugador();
				}

				break;

			case 2:

				System.out.print("");
				System.out.println("Elija una opciÃ³n:");
				System.out.println("1. C.F. EDIB AlevÃ­n");
				System.out.println("2. C.F. EDIB Infantil");
				System.out.println("3. C.F. EDIB Cadete");

				int elige = entrada.nextInt();
				if (elige == 1) {
					listarEquipo1(conn, "Futbol");
				} else if (elige == 2) {
					listarEquipo2(conn, "Futbol");
				} else {
					listarEquipo3(conn, "Futbol");
				}

				break;

			case 3:

				System.out.print("");
				jug.modificarPosicion(conn, "Futbol");

				break;

			case 4:

				System.out.print("");
				jug.modificarEstadoFisico(conn, "Futbol");

			case 5:

				System.out.print("");
				jug.modificarDisponibilidad(conn, "Futbol");
			case 6:
				System.out.print("");
				jug.crearJugador(conn, "Futbol");
				break;
			case 7:
				System.out.print("");
				jug.eliminarJugador(conn, "Futbol");
				break;
			case 8:
				System.out.print("");
				jug.modificarJugador(conn, "Futbol");
				break;
			case 9:
				System.out.print("");
				jug.moverJugadorCategoria(conn, "Futbol");
				break;
			case 10:
				Principal prnc = new Principal();
				prnc.menuPrincipal();
				break;
				
			default:
				break;
			}
			
			System.out.println("");
			System.out.println("¿Desea volver al menú de jugadores?");
			String guardar = entrada.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				menuJugador();

			} else  {
				Principal prnc = new Principal();
				prnc.menuPrincipal();
			} 
			entrada.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void menuAdmin() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Crear administrador");
			System.out.println("2. Eliminar administrador");
			System.out.println("3. Modificar información del administrador");
			System.out.println("4. Ver administradores existentes");
			System.out.print("");

			Administrador admin = new Administrador();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				admin.crearAdmin(conn, "Futbol");
				break;

			case 2:
				admin.eliminarAdmin(conn, "Futbol");

				break;

			case 3:
				admin.modificarAdmin(conn, "Futbol");
				break;
			case 4:
				admin.verAdmins(conn, "Futbol");
				break;

			}
			
			entrada.nextLine();
			System.out.println("");
			System.out.println("¿Desea volver a las opciones de administrador?");
			String guardar = entrada.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				menuAdmin();

			} else  {
				Principal prnc = new Principal();
				prnc.menuPrincipal();
			} 
			entrada.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void menuEquipos() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opciÃ³n:");
			System.out.println("");
			System.out.println("1. Listar equipos");
			System.out.println("2. Listar jugadores por equipo");
			System.out.println("3. Mover jugador de equipo");
			System.out.print("");

			Jugador jug = new Jugador();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Elija una opciÃ³n");
				System.out.println("1. Listar equipos");
				System.out.println("2. Listar y Guardar TXT");

				int elige = entrada.nextInt();
				if (elige == 1) {
					listarEquipos(conn, "Futbol");
				} else if (elige == 2) {
					jug.listarEquipostxt(conn, "Futbol");
				} else {
					System.out.println("Elige una opciÃ³n valida");
				}

				break;

			case 2:
				System.out.println("Eliga una opciÃ³n");
				System.out.println("1. Listar Jugadores");
				System.out.println("2. Listar y Guardar TXT");

				int elige1 = entrada.nextInt();
				if (elige1 == 1) {
					jug.listarJugadores(conn, "Futbol");
				} else if (elige1 == 2) {
					jug.listarJugadorestxt(conn, "Futbol");
				} else {
					System.out.println("Elige una opciÃ³n valida");
				}

				break;

			case 3:

				System.out.println("AQUI VA EL MEDOTO PARA MODIFICAR LA POSICION DE LOS JUGADORES");
				
				break;

			case 4:

				jug.modificarEstadoFisico(conn, "Jugadores");

			case 5:

				jug.modificarDisponibilidad(conn, "Jugadores");

			default:
				break;
			}
			
			entrada.nextLine();
			System.out.println("");
			System.out.println("¿Desea volver al menú de equipos?");
			String guardar = entrada.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				menuEquipos();

			} else  {
				Principal prnc = new Principal();
				prnc.menuPrincipal();
			} 

			entrada.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// MÃ©todo Listar todos los equipos

	public static void listarEquipos(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from equipos";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("****************** EQUIPOS *******************");
			System.out.println("**********************************************");
			System.out.println("");

			int numEquipo = 1;
			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Equipo " + numEquipo + " ***");
				System.out.println("*****************");
				System.out.println("");

				String IdEquipo = leer.getString(1);
				System.out.println("IdEquipo: " + IdEquipo);

				System.out.println();

				String NombreEquipo = leer.getString(2);
				System.out.println("NombreEquipo: " + NombreEquipo);

				System.out.println();

				String Categoria = leer.getString(3);
				System.out.println("Categoria: " + Categoria);

				System.out.println();

				numEquipo++;
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	public static void listarEquipo1(Connection con, String BDNombre) throws SQLException {
		Statement stmt = null;

		String query = "select * from jugadores WHERE IdEquipo = 1";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("******Listado de jugadores del equipo 1*******");
			System.out.println("**********************************************");
			System.out.println("");

			int numJugador = 1;

			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Jugador " + numJugador + " ***");
				System.out.println("*****************");
				System.out.println("");

				int IdJugador = leer.getInt(1);
				System.out.println("Id Jugador: " + IdJugador);

				String DNI = leer.getString(2);
				System.out.println("DNI: " + DNI);

				String Nombre = leer.getString(3);
				System.out.println("Nombre: " + Nombre);

				String Apellidos = leer.getString(4);
				System.out.println("Apellidos: " + Apellidos);

				String Posicion = leer.getString(5);
				System.out.println("Posicion: " + Posicion);

				String EstadoFisico = leer.getString(6);
				System.out.println("Estado fÃ­sico: " + EstadoFisico);

				String Edad = leer.getString(8);
				System.out.println("Edad: " + Edad);

				int IdEquipo = leer.getInt(9);
				System.out.println("IdEquipo: " + IdEquipo);

				numJugador++;

			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
	}

	public static void listarEquipo2(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from jugadores WHERE IdEquipo = 1";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("******Listado de jugadores del equipo 2*******");
			System.out.println("**********************************************");
			System.out.println("");

			int numJugador = 1;

			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Jugador " + numJugador + " ***");
				System.out.println("*****************");
				System.out.println("");

				int IdJugador = leer.getInt(1);
				System.out.println("Id Jugador: " + IdJugador);

				String DNI = leer.getString(2);
				System.out.println("DNI: " + DNI);

				String Nombre = leer.getString(3);
				System.out.println("Nombre: " + Nombre);

				String Apellidos = leer.getString(4);
				System.out.println("Apellidos: " + Apellidos);

				String Posicion = leer.getString(5);
				System.out.println("Posicion: " + Posicion);

				String EstadoFisico = leer.getString(6);
				System.out.println("Estado fÃ­sico: " + EstadoFisico);

				String Edad = leer.getString(7);
				System.out.println("Edad: " + Edad);

				int IdEquipo = leer.getInt(8);
				System.out.println("IdEquipo: " + IdEquipo);

			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
	}

	public static void listarEquipo3(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from jugadores WHERE IdEquipo = 3";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("******Listado de jugadores del equipo 3*******");
			System.out.println("**********************************************");
			System.out.println("");

			int numJugador = 1;

			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Jugador " + numJugador + " ***");
				System.out.println("*****************");
				System.out.println("");

				int IdJugador = leer.getInt(1);
				System.out.println("Id Jugador: " + IdJugador);

				String DNI = leer.getString(2);
				System.out.println("DNI: " + DNI);

				String Nombre = leer.getString(3);
				System.out.println("Nombre: " + Nombre);

				String Apellidos = leer.getString(4);
				System.out.println("Apellidos: " + Apellidos);

				String Posicion = leer.getString(5);
				System.out.println("Posicion: " + Posicion);

				String EstadoFisico = leer.getString(6);
				System.out.println("Estado fÃ­sico: " + EstadoFisico);

				String Edad = leer.getString(7);
				System.out.println("Edad: " + Edad);

				int IdEquipo = leer.getInt(8);
				System.out.println("IdEquipo: " + IdEquipo);

			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
	}

	public static void printSQLException(SQLException ex) {

		ex.printStackTrace(System.err);
		System.err.println("SQLState: " + ex.getSQLState()); // getSQLState()
		System.err.println("Error Code: " + ex.getErrorCode()); // getErrorCode()
		System.err.println("Message: " + ex.getMessage()); // getMessage()

		Throwable t = ex.getCause(); // getCause() - Leemos la primera causa

		while (t != null) {
			System.out.println("Cause: " + t); // Imprimimos una causa
			t = t.getCause(); // Leemos otra causa
		}

	}
}