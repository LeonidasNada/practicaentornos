import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Plantilla {

	private static String nombreJugador;
	private static int idJugador;

	// Métodos Plantillas

	public void menuPlantilla() {

		Connection conn;
		try {
			Scanner sca = new Scanner(System.in);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			
			System.out.println("");
			System.out.println("**************************");
			System.out.println("*** Menú de plantillas ***");
			System.out.println("**************************");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Ver plantillas");
			System.out.println("2. Eliminar una plantilla");
			System.out.println("3. Modificar una plantilla");
			System.out.println("4. Crear una plantilla nueva");
			System.out.println("5. Volver atrás");
			System.out.println("");
			
			System.out.print("Opción?: ");

			int opcion = sca.nextInt();

			//Evitamos error de scanner
			sca.nextLine();
			
			switch (opcion) {
			case 1:

				verPlantilla(conn, "Futbol");

				break;

			case 2:

				eliminarPlantilla(conn, "Futbol");

				break;

			case 3:

				modificarPlantilla(conn, "Futbol");

				break;

			case 4:

				crearPlantilla(conn, "Futbol");
				break;
			case 5:
				Principal prnc = new Principal();
				prnc.menuPrincipal();
				break;

			default:
				System.err.println("POR FAVOR, seleccione una de las opciones mostradas anteriormente por pantalla...");
				menuPlantilla();
				break;
			}
			menuPlantilla();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void verPlantilla(Connection conn, String BDNombre) throws SQLException {

		verIdPlantillas(conn, "Futbol");
		Statement stmt = null;
		Scanner sca = new Scanner(System.in);
		
		System.out.println("¿Qué plantilla desea visualizar? (ID):");
		System.out.print("");
		int idPlantilla = sca.nextInt();

		String query = "select * from plantillas where IdPlantilla = " + idPlantilla + "";

		try {

			stmt = conn.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("**************** Plantilla #" + idPlantilla + " ****************");
			System.out.println("**********************************************");
			System.out.println("");

			int numJugador = 1;

			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Jugador " + numJugador + " ***");
				System.out.println("*****************");

				idJugador = leer.getInt(2);
				System.out.println("Id Jugador: " + idJugador);

				obtNombreJugador(conn, "Futbol");

				String Posicion = leer.getString(5);
				System.out.println("Posicion: " + Posicion);

				String EstadoFisico = leer.getString(6);
				System.out.println("Estado físico: " + EstadoFisico);

				String disponibilidad = leer.getString(4);
				System.out.println("Disponibilidad: " + disponibilidad);

				String reserva = leer.getString(7);
				if (reserva.equals("")) {
					System.out.println("Reserva: No");
				} else {
					System.out.println("Reserva: " + reserva);
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

	public void verIdPlantillas(Connection conn, String BDNombre) throws SQLException {
		
		Statement stmt = null;
		Scanner sca = new Scanner(System.in);
		//System.out.println("¿Qué plantilla desea visualizar? (ID):");
		//System.out.print("");
		int idPlantilla;

		String query = "select IdPlantilla from plantillas GROUP BY IdPlantilla";

		try {

			stmt = conn.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("");
			System.out.println("**********************************************");
			System.out.println("******* ID Plantillas existentes *************");
			System.out.println("**********************************************");
			System.out.println("");

			int numPlantilla = 1;

			while (leer.next()) {

				System.out.println("********************");
				System.out.println("*** Plantilla #" + numPlantilla + " ***");
				System.out.println("********************");

				idPlantilla = leer.getInt(1);
				System.out.println("ID Plantilla: " + idPlantilla);
				System.out.println("");

				numPlantilla++;
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
		
	}

	// Método eliminación de datos
	public void eliminarPlantilla(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;
		Scanner sca = new Scanner(System.in);
		
		verIdPlantillas(con, "Futbol");
		
		System.out.println("Indique el ID de la plantilla que desee eliminar: ");
		int numeroInterno = sca.nextInt();
		System.out.println("");

		try {
		System.out.println("¿Está seguro que desea eliminar la plantilla?: ");
		String reserva = sca.nextLine();

		if (reserva.equals("S") || reserva.equals("s") || reserva.equals("Si") || reserva.equals("si")) {
			stmt = con.createStatement();

			stmt.executeUpdate(
					"DELETE FROM " + BDNombre + ".Plantillas  WHERE " + "IdPlantilla =" + numeroInterno + " ");

			System.out.println("");
			System.out.println("¡Se ha eliminado la plantilla!");
		} else {
			
		}

		

			

		} catch (SQLException e) {
			System.out.println("¡No se ha encontrado una plantilla con ese ID!");
			printSQLException(e);
		} finally {
			stmt.close();
		}


	}

	public void modificarPlantilla(Connection conn, String BDNombre) throws SQLException {
		
		verIdPlantillas(conn, "Futbol");
		String consulta = "";
		
		Statement stmt = null;
		Principal prnc = new Principal();
		Jugador jg = new Jugador();
		Scanner sca = new Scanner(System.in);


		System.out.println("");
		System.out.println("A continuación podrá modificar una plantilla...");
		System.out.println("***********************************************");
		System.out.println("");
		System.out.println("Ingrese el id de la plantilla que desee modificar: ");
		System.out.print("");
		int numeroInterno = sca.nextInt();
		System.out.println("");
		System.out.println("");

		System.out.println("¿Qué desea modificar de la plantilla?: ");
		System.out.println("1. Añadir un nuevo jugador");
		System.out.println("2. Eliminar un jugador");
		System.out.println("3. Volver atrás");
		System.out.println("");
		System.out.print("Opción?: ");
		int caracteristica = sca.nextInt();
		sca.nextLine();
		

		switch (caracteristica) {
		case 1:
			agregarJugadorPlantilla(conn, "Futbol", numeroInterno);
			break;
		case 2:
			eliminarJugadorPlantilla(conn, "Futbol", numeroInterno);
			break;
		case 3:
			menuPlantilla();
			break;
		case 4:
			
			break;
		default:
			menuPlantilla();
			break;
		}
		menuPlantilla();

		
	}

	private void eliminarJugadorPlantilla(Connection conn, String BDNombre, int idPlantilla) throws SQLException {
		
		Statement stmt = null;
		int idJugadorBase = 0;
		String posicion = "";
		String estadoFisico = "";
		String disponibilidad = "";
		String reserva = "";
		Scanner sca = new Scanner(System.in);
		Jugador jdr = new Jugador();
		
		// MEJOR MOSTRAR LOS JUGADORES DE LA PLANTILLA
		//jdr.listarJugadores(conn, "Futbol");
		
		//Mostrar jugadores de la plantilla seleccionada
		String query = "select * from plantillas where IdPlantilla = " + idPlantilla + "";

		try {

			stmt = conn.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			System.out.println("**********************************************");
			System.out.println("**************** Plantilla #" + idPlantilla + " ****************");
			System.out.println("**********************************************");
			System.out.println("");

			int numJugador = 1;

			while (leer.next()) {

				System.out.println("*****************");
				System.out.println("*** Jugador " + numJugador + " ***");
				System.out.println("*****************");

				idJugador = leer.getInt(2);
				System.out.println("Id Jugador: " + idJugador);

				obtNombreJugador(conn, "Futbol");

				String Posicion = leer.getString(5);
				System.out.println("Posicion: " + Posicion);

				String EstadoFisico = leer.getString(6);
				System.out.println("Estado físico: " + EstadoFisico);

				disponibilidad = leer.getString(4);
				System.out.println("Disponibilidad: " + disponibilidad);

				reserva = leer.getString(7);
				if (reserva.equals("")) {
					System.out.println("Reserva: No");
				} else {
					System.out.println("Reserva: " + reserva);
				}
				System.out.println("");

				numJugador++;
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
		
		
		
		System.out.println("");
		System.out.println("A continuación podrá eliminar un jugador de la plantilla...");
		System.out.println("**************************************************************");
		System.out.println("");
		System.out.println("Ingrese el ID del jugador que desea eliminar de la plantilla: ");
		System.out.print("");
		int idJugador = sca.nextInt();

		Principal prnc = new Principal();
		sca.nextLine();
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();
			
			query = "select * from jugadores where IdJugador = " + idJugador + "";
			ResultSet leer = stmt.executeQuery(query);

			while (leer.next()) {
				posicion = leer.getString(5);
				System.out.println("Posicion: " + posicion);

				estadoFisico = leer.getString(6);
				System.out.println("Estado físico: " + estadoFisico);

				disponibilidad = leer.getString(7);
				System.out.println("Disponibilidad: " + disponibilidad);
				System.out.println("");

				//sca.nextLine();
				System.out.println("¿Está seguro que desea eliminar al jugador de la plantilla?: ");
				reserva = sca.nextLine();

				if (reserva.equals("S") || reserva.equals("s") || reserva.equals("Si") || reserva.equals("si")) {

					query = "select * from Plantillas where IdJugador = " + idJugador + "AND idPlantilla = " + idPlantilla + "";
					ResultSet leerId = stmt.executeQuery(query);

					while (leerId.next()) {
						idJugadorBase = leerId.getInt(2);

					}

					if (idJugador != idJugadorBase) {
						System.out.println("");
						System.out.println(
								"¡No es posible eliminar al jugador (" + idJugador + ") de la plantilla \nporque no existe!");
						System.out.println("");
						agregarJugadorPlantilla(conn, "Futbol", idJugador);
					} else {

						stmt.executeUpdate("DELETE from " + BDNombre
								+ ".Plantillas WHERE idJugador = " + idJugador + "AND idPlantilla = " + idPlantilla + "");
					}

				} else {
					System.out.println("");
					System.out.println("¡Entendido! No borraremos el usuario de la plantilla, volviendo al menú de plantillas...");
				}

			}
			
			menuPlantilla();

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
		
	}

	private void agregarJugadorPlantilla(Connection conn, String BDNombre, int idPlantilla) throws SQLException {

		Statement stmt = null;
		int i = 1;
		int idJugadorBase = 0;
		int idEquipoPlantilla = 0;
		String posicion = "";
		String estadoFisico = "";
		String disponibilidad = "";
		String reserva = "";
		Scanner sca = new Scanner(System.in);
		

		//Mostrar jugadores de la plantilla seleccionada
				String query = "select * from plantillas where IdPlantilla = " + idPlantilla + "";

				try {

					stmt = conn.createStatement();

					ResultSet leer = stmt.executeQuery(query);

					System.out.println("**********************************************");
					System.out.println("**************** Plantilla #" + idPlantilla + " ****************");
					System.out.println("**********************************************");
					System.out.println("");

					int numJugador = 1;

					while (leer.next()) {

						System.out.println("*****************");
						System.out.println("*** Jugador " + numJugador + " ***");
						System.out.println("*****************");

						idJugador = leer.getInt(2);
						System.out.println("Id Jugador: " + idJugador);

						obtNombreJugador(conn, "Futbol");

						String Posicion = leer.getString(5);
						System.out.println("Posicion: " + Posicion);

						String EstadoFisico = leer.getString(6);
						System.out.println("Estado físico: " + EstadoFisico);

						disponibilidad = leer.getString(4);
						System.out.println("Disponibilidad: " + disponibilidad);

						reserva = leer.getString(7);
						if (reserva.equals("")) {
							System.out.println("Reserva: No");
						} else {
							System.out.println("Reserva: " + reserva);
						}
						System.out.println("");

						numJugador++;
					}

				} catch (SQLException e) {
					printSQLException(e);
				} finally {
					stmt.close();
				}
	
		System.out.println("En 5s se mostrará el listado con todos los jugadores así usted podrá ser consciente del ID \nde los jugadores que no pertenecen a la plantilla");
		System.out.println("");
		
		// Sleeper de 5 segundos
		try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(5*1000);
         } catch (Exception e) {
            System.out.println(e);
         }
		
		Jugador jdr = new Jugador();
		jdr.listarJugadores(conn, "Futbol");
		
		System.out.println("");
		System.out.println("A continuación podrá añadir un nuevo jugador a la plantilla...");
		System.out.println("**************************************************************");
		System.out.println("");
		System.out.println("Ingrese el ID del jugador: ");
		System.out.print("");
		int idJugador = sca.nextInt();

		sca.nextLine();
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();
			
			query = "select * from jugadores where IdJugador = " + idJugador + "";
			ResultSet leer = stmt.executeQuery(query);

			while (leer.next()) {
				posicion = leer.getString(5);
				System.out.println("Posicion: " + posicion);

				estadoFisico = leer.getString(6);
				System.out.println("Estado físico: " + estadoFisico);

				disponibilidad = leer.getString(7);
				System.out.println("Disponibilidad: " + disponibilidad);
				System.out.println("");

				//sca.nextLine();
				System.out.println("¿Es este un jugador de reserva? (S/N)");
				reserva = sca.nextLine();

				if (reserva.equals("S") || reserva.equals("s") || reserva.equals("Si") || reserva.equals("si")) {

					reserva = "Si";

				} else {
					reserva = "";
				}

			}

			query = "select * from Plantillas where IdJugador = " + idJugador + "";
			ResultSet leerId = stmt.executeQuery(query);

			while (leerId.next()) {
				idJugadorBase = leerId.getInt(2);
				idEquipoPlantilla = leerId.getInt(3);

			}

			if (idJugador == idJugadorBase) {
				System.out.println("");
				System.out.println(
						"¡No es posible añadir al jugador (" + idJugador + ") a la plantilla \nporque ya existe en alguna plantilla!");
				System.out.println("");
				agregarJugadorPlantilla(conn, "Futbol", idJugador);
			} else {

				stmt.executeUpdate("INSERT INTO " + BDNombre
						+ ".Plantillas(IdPlantilla, IdJugador, IdEquipo, Disponibilidad, Posicion, EstadoFisico, reserva) "
						+ "VALUES(" + idPlantilla +", " + idJugador + "," + idEquipoPlantilla + ",'" + disponibilidad + "','"
						+ posicion + "','" + estadoFisico + "','" + reserva + "' )");
				System.out.println("¡El jugador " + idJugador + " se ha añadido correctamente a la plantilla " + idPlantilla + "!");
				i++;
			}
			menuPlantilla();

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
		
		
	}

	public void crearPlantilla(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;
		
		Scanner sca = new Scanner(System.in);

		try {
			Principal.listarEquipos(conn, "Futbol");
			System.out.println("A continuación podrá crear una nueva plantilla para su equipo...");
			System.out.println("");
			System.out.print("¿De qué equipo desea realizar la nueva plantilla? (ID): ");
			String idEquipoPlantilla = sca.nextLine();
			System.out.println("");
			System.out.print("¿Cuántos jugadores formarán parte de la nueva plantilla? \n(Mín: 1, Máx: 14): ");
			int cantidadJugadores = sca.nextInt();
			System.out.println("");

			Jugador jg = new Jugador();
			jg.listarJugadores(conn, "Futbol");
			System.out.println(
					"Indique a continuación el ID de los jugadores que formarán parte de la plantilla uno por uno:");
			System.out.print("");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();

			int i = 1;
			int idJugador;
			int idJugadorBase = 0;
			int idPlantillaBase = 0;
			String posicion = "";
			String estadoFisico = "";
			String disponibilidad = "";
			String reserva = "";

			while (i <= cantidadJugadores) {
				System.out.println("");
				System.out.println("***********************");
				System.out.print("Jugador " + i + " (ID): ");
				idJugador = sca.nextInt();
				System.out.println("***********************");
				System.out.print("");
				

				String query = "select * from jugadores where IdJugador = " + idJugador + "";
				ResultSet leer = stmt.executeQuery(query);

				while (leer.next()) {
					posicion = leer.getString(5);
					System.out.println("Posicion: " + posicion);

					estadoFisico = leer.getString(6);
					System.out.println("Estado físico: " + estadoFisico);

					disponibilidad = leer.getString(7);
					System.out.println("Disponibilidad: " + disponibilidad);
					System.out.println("");

					sca.nextLine();
					System.out.println("¿Es este un jugador de reserva? (S/N)");
					reserva = sca.nextLine();

					if (reserva.equals("S") || reserva.equals("s") || reserva.equals("Si") || reserva.equals("si")) {

						reserva = "Si";

					} else {
						reserva = "";
					}

				}

				query = "select * from Plantillas where IdJugador = " + idJugador + " GROUP BY idPlantilla";
				ResultSet leerId = stmt.executeQuery(query);

				while (leerId.next()) {
					idJugadorBase = leerId.getInt(2);
					
				}
				query = "select idPlantilla from Plantillas GROUP BY idPlantilla";
				ResultSet leerIdPlantilla = stmt.executeQuery(query);

				while (leerIdPlantilla.next()) {
					idPlantillaBase = leerIdPlantilla.getInt(1);
					
				}

				if (idJugador == idJugadorBase) {
					System.out.println("");
					System.out.println(
							"¡No es posible añadir al jugador (" + idJugador + ") a la plantilla \nporque ya existe en alguna plantilla!");
					System.out.println("");
				} else {
					idPlantillaBase++;
					stmt.executeUpdate("INSERT INTO " + BDNombre
							+ ".Plantillas(IdPlantilla, IdJugador, IdEquipo, Disponibilidad, Posicion, EstadoFisico, reserva) "
							+ "VALUES( " + idPlantillaBase + ", " + idJugador + "," + idEquipoPlantilla + ",'" + disponibilidad + "','"
							+ posicion + "','" + estadoFisico + "','" + reserva + "' )");
					i++;
				}

			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}


	}

	public static void recuperarIdPlantilla(Connection conn, String BDNombre) throws SQLException {
		
		
		Statement stmt = null;

		try {

			stmt = conn.createStatement();
			int idPlantillaBase = 0;
				String query = "select idPlantilla from Plantillas GROUP BY idPlantilla";
				ResultSet leerIdPlantilla = stmt.executeQuery(query);

				while (leerIdPlantilla.next()) {
					idPlantillaBase = leerIdPlantilla.getInt(1);
					
				}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}
		
		
	}
	
	public static void obtNombreJugador(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;

		Scanner sca = new Scanner(System.in);

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();

			String query = "select * from jugadores where IdJugador = " + idJugador + "";
			ResultSet leerId = stmt.executeQuery(query);

			while (leerId.next()) {
				nombreJugador = leerId.getString(3);
				System.out.println("Nombre: " + nombreJugador);

			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}


	}

	// Método de control de excepciones SQL
	private static void printSQLException(SQLException ex) {

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
