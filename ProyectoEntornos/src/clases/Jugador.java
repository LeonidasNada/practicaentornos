package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jugador {
	
	private int IdEquipo;
	private String NombreEquipo;
	private String Categoria;
	private int IdJugador;
	private String disponibilidad;
	private String DNI;
	private String Nombre;
	private String Apellidos;
	private String Posicion;
	private String EstadoFisico;
	private int Edad;
	
	
	

	public static void main(String[] args) {
		
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			System.out.println("Conexi�n establecida correctamente con la base de datos");
	

			System.out.println("Eliga una opci�n");
			System.out.println("1. Listar jugadores por equipo");
			System.out.println("");
			System.out.println("2. Listar todos los jugadores");
			System.out.println("");
			System.out.println("3. Modificar posicion del jugador");
			System.out.println("");
			System.out.println("4. Modificar estado fisico del jugador");
			System.out.println("");
			System.out.println("5. Modificar disponibilidad del jugador");
			
		

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Eliga una opci�n");
				System.out.println("1. Listar equipos");
				System.out.println("2. Listar y Guardar TXT");
				
				int elige = entrada.nextInt();
				if (elige == 1) {
					listarEquipos(conn, "Ordenadores");
				} else if(elige == 2) {
					listarEquipostxt(conn, "Ordenadores");
				} else {
					System.out.println("Elige una opci�n valida");
				}
				
				break;
			
			case 2:
				System.out.println("Eliga una opci�n");
				System.out.println("1. Listar Jugadores");
				System.out.println("2. Listar y Guardar TXT");
				
				int elige1 = entrada.nextInt();
				if (elige1 == 1) {
					listarJugadores(conn, "Ordenadores");
				} else if(elige1 == 2) {
					listarJugadorestxt(conn, "Ordenadores");
				} else {
					System.out.println("Elige una opci�n valida");
				}
				
				break;


				case 3:

				modificarPosicion(conn,"Jugadores");

				break;

				case 4:

				modificarEstadoFisico(conn,"Jugadores");

				case 5:

				modificarDisponibilidad(conn,"Jugadores");

				default:
				break;
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// M�todo Listar todos los equipos

		private static void listarEquipos(Connection con, String BDNombre) throws SQLException {

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
		
		
		
		// M�todo listar jugadores

		private static void listarJugadores(Connection con, String BDNombre) throws SQLException {

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
					System.out.println("Estado f�sico: " + EstadoFisico);
					
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
		
		
		
				

		// M�todo Listado en TXT equipos

		private static void listarEquipostxt(Connection con, String BDNombre) throws SQLException {

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
		

		// M�todo Listado en TXT Jugadores

		private static void listarJugadorestxt(Connection con, String BDNombre) throws SQLException {

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
					System.out.println("Estado f�sico: " + EstadoFisico);
					
					escribe.write("Estado f�sico:" + EstadoFisico + "\n");

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
		
		//Creamos el m�todo modificarPosicion
		private static void modificarPosicion(Connection con, String BDNombre) {

		Statement stmt = null;
		//Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		//Damos la opci�n al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		//Damos la opci�n al usuario de ingresar la ubicaci�n
		System.out.println("Ingrese posici�n: ");
		String Posicion = entrada.nextLine();
		System.out.println("");

		//Creamos un try donde abrimos el flujo
		try {

		stmt = con.createStatement();
		//Creamos un executo update para poder modificar los atributos
		stmt.executeUpdate("UPDATE " + BDNombre + "l SET Posicion ='" + Posicion + "' "
		+ " WHERE IdJugador = " + IdJugador + "");
		//Ponemos un mensaje al usuario de que la ubicaci�n ha sido cambiada correctamente
		System.out.println("");
		System.out.println("Se ha modificado correctamente la posicion del jugador");

		//Cerramos flujo
		stmt.close();

		//Capturamos las excepciones "SQL"
		} catch (SQLException e) {
		printSQLException(e);
		}

		}

		//Creamos el m�todo modificarEstadFisico
		private static void modificarEstadoFisico(Connection con, String BDNombre) {

		Statement stmt = null;
		//Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		//Damos la opci�n al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		//Damos la opci�n al usuario de ingresar la ubicaci�n
		System.out.println("Ingrese estado f�sico: ");
		String EstadoFisico = entrada.nextLine();
		System.out.println("");

		//Creamos un try donde abrimos el flujo
		try {

		stmt = con.createStatement();
		//Creamos un executo update para poder modificar los atributos
		stmt.executeUpdate("UPDATE " + BDNombre + " SET EstadoFisico ='" + EstadoFisico + "' "
		+ " WHERE IdJugador = " + IdJugador + "");
		//Ponemos un mensaje al usuario de que la ubicaci�n ha sido cambiada correctamente
		System.out.println("");
		System.out.println("Se ha modificado correctamente el estado f�sico");

		//Cerramos flujo
		stmt.close();

		//Capturamos las excepciones "SQL"
		} catch (SQLException e) {
		printSQLException(e);
		}

		}

		//Creamos el m�todo modificarDisponibilidad
		private static void modificarDisponibilidad(Connection con, String BDNombre) {

		Statement stmt = null;
		//Creamos el Scanner
		Scanner entrada = new Scanner(System.in);
		//Damos la opci�n al usuario de ingresa el id del jugador
		System.out.println("Ingrese id del jugador: ");
		int IdJugador = entrada.nextInt();
		System.out.println("");

		entrada.nextLine();
		//Damos la opci�n al usuario de ingresar la ubicaci�n
		System.out.println("Ingrese disponibilidad: ");
		String Disponibilidad = entrada.nextLine();
		System.out.println("");

		//Creamos un try donde abrimos el flujo
		try {

		stmt = con.createStatement();
		//Creamos un executo update para poder modificar los atributos
		stmt.executeUpdate("UPDATE " + BDNombre + " SET Disponibilidad ='" + Disponibilidad + "' "
		+ " WHERE IdJugador = " + IdJugador + "");
		//Ponemos un mensaje al usuario de que la ubicaci�n ha sido cambiada correctamente
		System.out.println("");
		System.out.println("Se ha modificado correctamente la disponibilidad del jugador");

		//Cerramos flujo
		stmt.close();

		//Capturamos las excepciones "SQL"
		} catch (SQLException e) {
		printSQLException(e);
		}
		}
		
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


