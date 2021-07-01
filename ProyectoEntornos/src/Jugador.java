import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jugador {

	// Método listar jugadores

	public void listarJugadores(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from jugadores";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("*********** Listado de jugadores *************");
			System.out.println("**********************************************");
			System.out.println("");

			int numJugador = 1;

			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Jugador " + numJugador + " ***");
				System.out.println("*****************");

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
				System.out.println("Estado físico: " + EstadoFisico);

				String disponibilidad = leer.getString(7);
				System.out.println("Disponibilidad: " + disponibilidad);

				String Edad = leer.getString(8);
				System.out.println("Edad: " + Edad);

				int idEquipo = leer.getInt(9);
				
				if (idEquipo == 1) {
					System.out.println("Equipo: C.F. EDIB Alevín");
					
				} else if(idEquipo == 2) {
					System.out.println("Equipo: C.F. EDIB Infantíl");
				}else {
					System.out.println("Equipo C.F. EDIB Cadete");
				}
				System.out.println("");
				numJugador++;
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	// Método Listado en TXT equipos

	public void listarEquipostxt(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from equipos";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);
			System.out.println("");
			System.out.println("Listado equipos");
			System.out.println("");

			FileWriter escribe = new FileWriter("c:/informes/informe.txt", true);
			while (leer.next()) {

				String IdEquipo = leer.getString(1);
				System.out.println("IdEquipo: " + IdEquipo);

				escribe.write("IdEquipo:" + IdEquipo + "\n");

				String NombreEquipo = leer.getString(2);
				System.out.println("NombreEquipo: " + NombreEquipo);

				escribe.write("Nombre Equipo:" + NombreEquipo + "\n");

				String Categoria = leer.getString(3);
				System.out.println("Categoria: " + Categoria);

				escribe.write("Categoria:" + Categoria + "\n");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			stmt.close();
		}

	}

	// Método Listado en TXT Jugadores

	public void listarJugadorestxt(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;

		String query = "select * from jugadores";

		try {

			stmt = con.createStatement();

			ResultSet leer = stmt.executeQuery(query);
			System.out.println("");
			System.out.println("Listado Jugadores");
			System.out.println("");

			FileWriter escribe = new FileWriter("c:/informes/informe.txt", true);
			while (leer.next()) {

				int IdJugador = leer.getInt(1);
				System.out.println("Id Jugador: " + IdJugador);

				escribe.write("Id Jugador:" + IdJugador + "\n");

				String DNI = leer.getString(2);
				System.out.println("DNI: " + DNI);

				escribe.write("DNI:" + DNI + "\n");

				String Nombre = leer.getString(3);
				System.out.println("Nombre: " + Nombre);

				escribe.write("Nombre:" + Nombre + "\n");

				String Apellidos = leer.getString(4);
				System.out.println("Apellidos: " + Apellidos);

				escribe.write("Apellidos:" + IdJugador + "\n");

				String Posicion = leer.getString(5);
				System.out.println("Posicion: " + Posicion);

				escribe.write("Posicion:" + Posicion + "\n");

				String EstadoFisico = leer.getString(6);
				System.out.println("Estado físico: " + EstadoFisico);

				escribe.write("Estado físico:" + EstadoFisico + "\n");

				String Edad = leer.getString(7);
				System.out.println("Edad: " + Edad);

				escribe.write("Edad:" + IdJugador + "\n");

				int IdEquipo = leer.getInt(8);
				System.out.println("IdEquipo: " + IdEquipo);

				escribe.write("IdEquipo" + IdEquipo + "\n");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			stmt.close();
		}

	}

	// Creamos el método modificarPosicion
	public void modificarPosicion(Connection con, String BDNombre) {

		Statement stmt = null;
		// Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		// Damos la opción al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador al que quiera cambiar la posición: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		// Damos la opción al usuario de ingresar la ubicación
		System.out.println("Ingrese la nueva posición del jugador: ");
		String Posicion = entrada.nextLine();
		System.out.println("");

		// Creamos un try donde abrimos el flujo
		try {

			stmt = con.createStatement();
			// Creamos un execute update para poder modificar los atributos
			stmt.executeUpdate("UPDATE " + BDNombre + ".jugadores SET Posicion ='" + Posicion + "' " + " WHERE IdJugador = "
					+ IdJugador + "");
			// Ponemos un mensaje al usuario de que la ubicación ha sido cambiada
			// correctamente
			System.out.println("");
			System.out.println("Se ha modificado correctamente la posicion del jugador");

			// Cerramos flujo
			stmt.close();

			// Capturamos las excepciones "SQL"
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	// Creamos el método modificarEstadFisico
	public void modificarEstadoFisico(Connection con, String BDNombre) {

		Statement stmt = null;
		// Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		// Damos la opción al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador del cual desea cambiar el estado físico: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		// Damos la opción al usuario de ingresar la ubicación
		System.out.println("Ingrese nuevo estado físico: ");
		String EstadoFisico = entrada.nextLine();
		System.out.println("");

		// Creamos un try donde abrimos el flujo
		try {

			stmt = con.createStatement();
			// Creamos un executo update para poder modificar los atributos
			stmt.executeUpdate("UPDATE " + BDNombre + " SET EstadoFisico ='" + EstadoFisico + "' "
					+ " WHERE IdJugador = " + IdJugador + "");
			// Ponemos un mensaje al usuario de que la ubicación ha sido cambiada
			// correctamente
			System.out.println("");
			System.out.println("Se ha modificado correctamente el estado físico.");

			// Cerramos flujo
			stmt.close();

			// Capturamos las excepciones "SQL"
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	// Creamos el método modificarDisponibilidad
	public void modificarDisponibilidad(Connection con, String BDNombre) {

		Statement stmt = null;
		// Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		// Damos la opción al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		// Damos la opción al usuario de ingresar la ubicación
		System.out.println("Ingrese disponibilidad: ");
		String Disponibilidad = entrada.nextLine();
		System.out.println("");

		// Creamos un try donde abrimos el flujo
		try {

			stmt = con.createStatement();
			// Creamos un executo update para poder modificar los atributos
			stmt.executeUpdate("UPDATE " + BDNombre + " SET Disponibilidad ='" + Disponibilidad + "'"
					+ " WHERE IdJugador = " + IdJugador + "");
			// Ponemos un mensaje al usuario de que la ubicación ha sido cambiada
			// correctamente
			System.out.println("");
			System.out.println("Se ha modificado correctamente la disponibilidad del jugador");

			// Cerramos flujo
			stmt.close();

			// Capturamos las excepciones "SQL"
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// Métodos jugadores
	public void crearJugador(Connection conn, String BDNombre) throws SQLException {
		Statement stmt = null;

		Scanner sca = new Scanner(System.in);

		System.out.println("A continuación podrá crear un nuevo jugador...");
		System.out.println("");
		System.out.println("Ingrese el DNI del jugador: ");
		System.out.print("");
		String dni = sca.nextLine();
		System.out.println("");
		System.out.println("Nombre: ");
		System.out.print("");
		String nombre = sca.nextLine();
		System.out.println("");
		System.out.println("Apellidos: ");
		System.out.print("");
		String apellidos = sca.nextLine();
		System.out.println("");
		System.out.println("Posición del jugador en el equipo:");
		System.out.print("");
		String posicion = sca.nextLine();
		System.out.println("");
		System.out.println("Estado fisico actual del jugador:");
		System.out.print("");
		String estadoFisico = sca.nextLine();
		System.out.println("");
		System.out.println("Disponibilidad para asistir a partidos: ");
		System.out.print("");
		String disponibilidad = sca.nextLine();
		System.out.println("");
		System.out.println("Edad del jugador:");
		System.out.print("");
		int edad = sca.nextInt();
		System.out.println("Id del equipo:");
		System.out.print("");
		int idEquipo = sca.nextInt();

		Principal prnc = new Principal();
		sca.nextLine();
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre
					+ ".Jugadores(IdJugador, Dni, Nombre, Apellidos, Posicion, EstadoFisico, Disponibilidad, Edad, idEquipo) VALUES(NULL,'"
					+ dni + "','" + nombre + "','" + apellidos + "','" + posicion + "','" + estadoFisico + "','"
					+ disponibilidad + "','" + edad + "'," + idEquipo + ")");

			System.out.println("");
			System.out.println("¿Desea crear otro jugador? \nIndique (si) en caso afirmativo o (no) en caso de no quiera: ");
			String guardar = sca.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				crearJugador(conn, "Futbol");

			} else if (guardar.equals("N") || guardar.equals("n") || guardar.equals("no") || guardar.equals("NO")) {
				prnc.menuPrincipal();
			} else {
				System.out.println("No sabemos qué quiere, así que ¡Hasta pronto!");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}


	}

	public void eliminarJugador(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;
		Scanner sca = new Scanner(System.in);

		listarJugadores(conn, "Futbol");

		System.out.println("**********************************************");
		System.out.println("Indique el ID del jugador que desee eliminar: ");
		int numeroInterno = sca.nextInt();
		System.out.println("");

		try {

			stmt = conn.createStatement();

			Principal prnc = new Principal();
			stmt.executeUpdate("DELETE FROM " + BDNombre + ".Jugadores  WHERE " + "IdJugador =" + numeroInterno + " ");

			System.out.println("");
			System.out.println("¡Se ha eliminado el jugador!");

			// Bucle para volver a eliminar otro jugador
			System.out.println("");
			System.out.println("¿Desea eliminar otro jugador?");
			String guardar = sca.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				eliminarJugador(conn, "Futbol");

			} else if (guardar.equals("N") || guardar.equals("n") || guardar.equals("no") || guardar.equals("NO")) {
				prnc.menuPrincipal();
			} else {
				System.out.println("No sabemos qué quiere, así que ¡Hasta pronto!");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

	}

	public void modificarJugador(Connection conn, String BDNombre) throws SQLException {

		String nombre = "";
		String dni = "";
		String apellidos = "";
		String posicion = "";
		String estadoFisico = "";
		String disponibilidad = "";
		int edad = 0;
		int idEquipo = 0;
		String consulta = "";
		
		Statement stmt = null;
		Principal prnc = new Principal();
		Scanner sca = new Scanner(System.in);

		listarJugadores(conn, "Futbol");
		System.out.println("A continuación podrá modificar un jugador...");
		System.out.print("");
		System.out.println("Ingrese el id del jugador que desee modificar: ");
		System.out.print("");
		String numeroInterno = sca.nextLine();
		System.out.println("");
		System.out.println("");

		System.out.println("¿Qué característica del jugador desea modificar?: ");
		System.out.println("1. DNI del jugador");
		System.out.println("2. Nombre del jugador");
		System.out.println("3. Apellidos del jugador");
		System.out.println("4. Posición del jugador");
		System.out.println("5. Estado físico");
		System.out.println("6. Disponibilidad");
		System.out.println("7. Edad del jugador");
		System.out.println("8. ID equipo del jugador (Mover de equipo)");
		System.out.println("9. Modificar TODO");
		System.out.print("");
		int caracteristica = sca.nextInt();
		sca.nextLine();
		

		switch (caracteristica) {
		case 1:
			
			System.out.println("Ingrese el DNI del jugador: ");
			System.out.print("");
			dni = sca.nextLine();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET dni='"+ dni +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 2:
			System.out.println("Nombre: ");
			System.out.print("");
			nombre = sca.nextLine();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET nombre='"+ nombre +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 3:
			System.out.println("Apellidos: ");
			System.out.print("");
			apellidos = sca.nextLine();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET apellidos='"+ apellidos +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 4:
			System.out.println("Posición del jugador en el equipo:");
			System.out.print("");
			posicion = sca.nextLine();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET posicion='"+ posicion +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 5:
			System.out.println("Estado fisico actual del jugador:");
			System.out.print("");
			estadoFisico = sca.nextLine();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET estadoFisico='"+ estadoFisico +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 6:
			System.out.println("Disponibilidad para asistir a partidos: ");
			System.out.print("");
			disponibilidad = sca.nextLine();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET disponibilidad='"+ disponibilidad +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 7:
			System.out.println("Edad del jugador:");
			System.out.print("");
			edad = sca.nextInt();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET edad='"+ edad +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 8:
			System.out.println("Id del equipo:");
			System.out.print("");
			idEquipo = sca.nextInt();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET idEquipo='"+ idEquipo +"' WHERE IdJugador="+ numeroInterno +" ";
			break;
		case 9:
			System.out.println("Ingrese el DNI del jugador: ");
			System.out.print("");
			dni = sca.nextLine();
			System.out.println("");
			System.out.println("Nombre: ");
			System.out.print("");
			nombre = sca.nextLine();
			System.out.println("");
			System.out.println("Apellidos: ");
			System.out.print("");
			apellidos = sca.nextLine();
			System.out.println("");
			System.out.println("Posición del jugador en el equipo:");
			System.out.print("");
			posicion = sca.nextLine();
			System.out.println("");
			System.out.println("Estado fisico actual del jugador:");
			System.out.print("");
			estadoFisico = sca.nextLine();
			System.out.println("");
			System.out.println("Disponibilidad para asistir a partidos: ");
			System.out.print("");
			disponibilidad = sca.nextLine();
			System.out.println("");
			System.out.println("Edad del jugador:");
			System.out.print("");
			edad = sca.nextInt();
			System.out.println("Id del equipo:");
			System.out.print("");
			idEquipo = sca.nextInt();
			consulta = "UPDATE " + BDNombre + ".Jugadores SET dni='"+ dni +"', nombre='"+ nombre +"', apellidos='"+ apellidos +"', posicion='"+ posicion +"', estadoFisico='"+ estadoFisico +"', disponibilidad='"+ disponibilidad +"', edad="+ edad +", idEquipo="+ idEquipo +" WHERE IdJugador="+ numeroInterno +" ";
			break;
		default:
			prnc.menuPrincipal();
			break;
		}


		//Evitamos error del scanner
		sca.nextLine();
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();

			stmt.executeUpdate(consulta);

			System.out.println("");
			System.out.println("¿Desea modificar otro jugador?");
			String guardar = sca.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				modificarJugador(conn, "Futbol");

			} else if (guardar.equals("N") || guardar.equals("n") || guardar.equals("no") || guardar.equals("NO")) {
				prnc.menuPrincipal();
			} else {
				System.out.println("No sabemos qué quiere, así que ¡Hasta pronto!");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}


	}

	public void moverJugadorCategoria(Connection conn, String BDNombre) throws SQLException {

		
		int idEquipo = 0;
		String consulta = "";
		
		Statement stmt = null;
		Principal prnc = new Principal();
		Scanner sca = new Scanner(System.in);

		listarJugadores(conn, "Futbol");
		System.out.println("A continuación podrá mover un jugador de categoría/equipo...");
		System.out.print("");
		System.out.println("Ingrese el id del jugador que desee mover: ");
		System.out.print("");
		String numeroInterno = sca.nextLine();
		System.out.println("");
		System.out.println("");

		System.out.print("");
		System.out.println("¿A qué equipo desea mover el jugador?:");
		System.out.println("1. C.F. EDIB Alevín");
		System.out.println("2. C.F. EDIB Infantil");
		System.out.println("3. C.F. EDIB Cadete");

		int elige = sca.nextInt();
		if (elige == 1) {
			idEquipo = 1;
		} else if (elige == 2) {
			idEquipo = 2;
		} else {
			idEquipo = 3;
		}

		System.out.println("Id del equipo:");
		System.out.print("");
		idEquipo = sca.nextInt();
		consulta = "UPDATE " + BDNombre + ".Jugadores SET idEquipo='"+ idEquipo +"' WHERE IdJugador="+ numeroInterno +" ";

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();

			stmt.executeUpdate(consulta);

			System.out.println("");
			System.out.println("¿Desea modificar otro jugador?");
			String guardar = sca.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				modificarJugador(conn, "Futbol");

			} else if (guardar.equals("N") || guardar.equals("n") || guardar.equals("no") || guardar.equals("NO")) {
				prnc.menuPrincipal();
			} else {
				System.out.println("No sabemos qué quiere, así que ¡Hasta pronto!");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

		
	}

	public void printSQLException(SQLException ex) {

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
