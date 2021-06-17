import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jugador {



//Método Listar todos los equipos

		public void listarEquipos(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from equipos";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);

				System.out.println("");
				System.out.println("Equipos");
				System.out.println("");

				while (leer.next()) {

					String IdEquipo = leer.getString(1);
				System.out.println("IdEquipo: " + IdEquipo);
				
				System.out.println();
				
				String NombreEquipo = leer.getString(2);
				System.out.println("NombreEquipo: " + NombreEquipo);
				
				System.out.println();
				
				String Categoria = leer.getString(3);
				System.out.println("Categoria: " + Categoria);
				
				System.out.println();

				}

			} catch (SQLException e) {
				printSQLException(e);
			} finally {
				stmt.close();
			}

		}
		
		
		
		// Método listar jugadores

		public void listarJugadores(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from jugadores";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);

				System.out.println("");
				System.out.println("Listado jugadores");
				System.out.println("");

				while (leer.next()) {

					int IdJugador = leer.getInt(1);
					System.out.println("Id Jugador: " + IdJugador);
					
					System.out.println();

					String DNI = leer.getString(2);
					System.out.println("DNI: " + DNI);
					
					System.out.println();

					String Nombre = leer.getString(3);
					System.out.println("Nombre: " + Nombre);
					
					System.out.println();

					String Apellidos = leer.getString(4);
					System.out.println("Apellidos: " + Apellidos);
					
					System.out.println();

					String Posicion = leer.getString(5);
					System.out.println("Posicion: " + Posicion);
					
					System.out.println();

					String EstadoFisico = leer.getString(6);
					System.out.println("Estado físico: " + EstadoFisico);
					
					System.out.println();

					String Edad = leer.getString(7);
					System.out.println("Edad: " + Edad);
					
					System.out.println();

					int IdEquipo = leer.getInt(8);
					System.out.println("IdEquipo: " + IdEquipo);
					

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
				
				escribe.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				
				escribe.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				stmt.close();
			}

		}
		
		//Creamos el método modificarPosicion
		public void modificarPosicion(Connection con, String BDNombre) {

		Statement stmt = null;
		//Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		//Damos la opción al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		//Damos la opción al usuario de ingresar la ubicación
		System.out.println("Ingrese posición: ");
		String Posicion = entrada.nextLine();
		System.out.println("");

		//Creamos un try donde abrimos el flujo
		try {

		stmt = con.createStatement();
		//Creamos un executo update para poder modificar los atributos
		stmt.executeUpdate("UPDATE " + BDNombre + "l SET Posicion ='" + Posicion + "' "
		+ " WHERE IdJugador = " + IdJugador + "");
		//Ponemos un mensaje al usuario de que la ubicación ha sido cambiada correctamente
		System.out.println("");
		System.out.println("Se ha modificado correctamente la posicion del jugador");

		//Cerramos flujo
		stmt.close();

		//Capturamos las excepciones "SQL"
		} catch (SQLException e) {
		printSQLException(e);
		}

		}

		//Creamos el método modificarEstadFisico
		public void modificarEstadoFisico(Connection con, String BDNombre) {

		Statement stmt = null;
		//Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		//Damos la opción al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		//Damos la opción al usuario de ingresar la ubicación
		System.out.println("Ingrese estado físico: ");
		String EstadoFisico = entrada.nextLine();
		System.out.println("");

		//Creamos un try donde abrimos el flujo
		try {

		stmt = con.createStatement();
		//Creamos un executo update para poder modificar los atributos
		stmt.executeUpdate("UPDATE " + BDNombre + " SET EstadoFisico ='" + EstadoFisico + "' "
		+ " WHERE IdJugador = " + IdJugador + "");
		//Ponemos un mensaje al usuario de que la ubicación ha sido cambiada correctamente
		System.out.println("");
		System.out.println("Se ha modificado correctamente el estado físico");

		//Cerramos flujo
		stmt.close();

		//Capturamos las excepciones "SQL"
		} catch (SQLException e) {
		printSQLException(e);
		}

		}

		//Creamos el método modificarDisponibilidad
		public void modificarDisponibilidad(Connection con, String BDNombre) {

		Statement stmt = null;
		//Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		//Damos la opción al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		//Damos la opción al usuario de ingresar la ubicación
		System.out.println("Ingrese disponibilidad: ");
		String Disponibilidad = entrada.nextLine();
		System.out.println("");

		//Creamos un try donde abrimos el flujo
		try {

		stmt = con.createStatement();
		//Creamos un executo update para poder modificar los atributos
		stmt.executeUpdate("UPDATE " + BDNombre + " SET Disponibilidad ='" + Disponibilidad + "'"
		+ " WHERE IdJugador = " + IdJugador + "");
		//Ponemos un mensaje al usuario de que la ubicación ha sido cambiada correctamente
		System.out.println("");
		System.out.println("Se ha modificado correctamente la disponibilidad del jugador");

		//Cerramos flujo
		stmt.close();

		//Capturamos las excepciones "SQL"
		} catch (SQLException e) {
		printSQLException(e);
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

