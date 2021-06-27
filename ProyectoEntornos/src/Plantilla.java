import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Plantilla {

	private static String nombreJugador;
	private static int idJugador;

	// M�todos Plantillas

	public void menuPlantilla() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Ver plantillas");
			System.out.println("2. Eliminar una plantilla");
			System.out.println("3. Modificar una plantilla");
			System.out.println("4. Crear una plantilla nueva");
			System.out.println("5. Volver atrás");
			System.out.print("");

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:

				verPlantilla(conn, "Jugadores");

				break;

			case 2:

				eliminarPlantilla(conn, "Jugadores");

				break;

			case 3:

				modificarPlantilla(conn, "Jugadores");

				break;

			case 4:

				crearPlantilla(conn, "Jugadores");
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
			entrada.nextLine();
			System.out.println("");
			System.out.println("¿Desea volver al menú de jugadores?");
			String guardar = entrada.nextLine();
			if (guardar.equals("S") || guardar.equals("s") || guardar.equals("si") || guardar.equals("SI")) {

				menuPlantilla();

			} else  {
				Principal prnc = new Principal();
				prnc.menuPrincipal();
			} 
			entrada.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	

	public void verPlantilla(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;
		Scanner sca = new Scanner(System.in);
		System.out.println("Â¿QuÃ© plantilla desea visualizar? (ID)");
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
				System.out.println("Estado fÃ­sico: " + EstadoFisico);

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
			sca.close();
		}

	}

	

	// M�todo eliminaci�n de datos
	public void eliminarPlantilla(Connection con, String BDNombre) throws SQLException {

		Statement stmt = null;
		Scanner sca = new Scanner(System.in);

		System.out.println("Indique el ID de la plantilla que desee eliminar: ");
		int numeroInterno = sca.nextInt();
		System.out.println("");

		try {

			stmt = con.createStatement();

			stmt.executeUpdate(
					"DELETE FROM " + BDNombre + ".Plantillas  WHERE " + "IdPlantilla =" + numeroInterno + " ");

			System.out.println("");
			System.out.println("¡Se ha eliminado el ordenador!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

		sca.close();

	}

	public void modificarPlantilla(Connection conn, String BDNombre) throws SQLException {

	}

	public void crearPlantilla(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;

		Scanner sca = new Scanner(System.in);

		try {

			System.out.println("A continuaciÃ³n podrÃ¡ una nueva plantilla para su equipo...");
			System.out.println("");
			System.out.println("Â¿De quÃ© equipo desea realizar la nueva plantilla? (ID)");
			System.out.print("");
			String idEquipoPlantilla = sca.nextLine();

			System.out.println(
					"Indique a continuaciÃ³n el ID de los jugadores que formarÃ¡n parte de la plantilla uno por uno:");
			System.out.print("");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("ConexiÃ³n establecida correctamente con la base de
			// datos");
			stmt = conn.createStatement();

			int i = 1;
			int idJugador;
			int idJugadorBase = 0;
			String posicion = "";
			String estadoFisico = "";
			String disponibilidad = "";
			String reserva = "";

			while (i <= 10) {
				System.out.println("");
				System.out.println("***********************");
				System.out.println("Jugador " + i + " (ID): ");
				System.out.println("***********************");
				System.out.print("");
				idJugador = sca.nextInt();

				String query = "select * from jugadores where IdJugador = " + idJugador + "";
				ResultSet leer = stmt.executeQuery(query);

				while (leer.next()) {
					posicion = leer.getString(5);
					System.out.println("Posicion: " + posicion);

					estadoFisico = leer.getString(6);
					System.out.println("Estado fÃ­sico: " + estadoFisico);

					disponibilidad = leer.getString(7);
					System.out.println("Disponibilidad: " + disponibilidad);
					System.out.println("");

					sca.nextLine();
					System.out.println("Â¿Es este un jugador de reserva? (S/N)");
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

				}

				if (idJugador == idJugadorBase) {
					System.out.println("");
					System.out.println(
							"Â¡No es posible aÃ±adir el jugador (" + idJugador + ") a la plantilla porque ya existe!");
					System.out.println("");
				} else {

					stmt.executeUpdate("INSERT INTO " + BDNombre
							+ ".Plantillas(IdPlantilla, IdJugador, IdEquipo, Disponibilidad, Posicion, EstadoFisico, reserva) "
							+ "VALUES( 1, " + idJugador + "," + idEquipoPlantilla + ",'" + disponibilidad + "','"
							+ posicion + "','" + estadoFisico + "','" + reserva + "' )");
					i++;
				}

			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

		sca.close();

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

		sca.close();

	}

	// M�todo de control de excepciones SQL
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
