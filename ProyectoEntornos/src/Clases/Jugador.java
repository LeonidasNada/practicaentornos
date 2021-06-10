package Clases;

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
			System.out.println("Conexión establecida correctamente con la base de datos");
	

			System.out.println("Eliga una opción");
			System.out.println("1. Listar jugadores por equipo");
			System.out.println("2. Listar todos los jugadores");
			
		

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Eliga una opción");
				System.out.println("1. Listar equipos");
				System.out.println("2. Listar y Guardar TXT");
				
				int elige = entrada.nextInt();
				if (elige == 1) {
					listarEquipos(conn, "Ordenadores");
				} else if(elige == 2) {
					listarEquipostxt(conn, "Ordenadores");
				} else {
					System.out.println("Elige una opción valida");
				}
				
				break;
			
			case 2:
				System.out.println("Eliga una opción");
				System.out.println("1. Listar Jugadores");
				System.out.println("2. Listar y Guardar TXT");
				
				int elige1 = entrada.nextInt();
				if (elige1 == 1) {
					listarJugadores(conn, "Ordenadores");
				} else if(elige1 == 2) {
					listarJugadorestxt(conn, "Ordenadores");
				} else {
					System.out.println("Elige una opción valida");
				}
				
				break;

			

			default:
				break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Método Listar todos los equipos

		private static void listarEquipos(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from futbol";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);

				System.out.println("");
				System.out.println("Equipos");
				System.out.println("");

				while (leer.next()) {

				//	String equipo = leer.getInt(1);
				//System.out.println("Equipo: " + equipo);

				}

			} catch (SQLException e) {
				printSQLException(e);
			} finally {
				stmt.close();
			}

		}
		
		
		
		// Método listar jugadores

		private static void listarJugadores(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from futbol";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);

				System.out.println("");
				System.out.println("Listado jugadores");
				System.out.println("");

				while (leer.next()) {

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

					int EstadoFisico = leer.getInt(6);
					System.out.println("Estado físico: " + EstadoFisico);

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
		
		
		
				

		// Método Listado en TXT equipos

		private static void listarEquipostxt(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from futbol";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);
				System.out.println("");
				System.out.println("Listado equipos");
				System.out.println("");

				FileWriter escribe = new FileWriter("c:/informes/informe.txt", true);
				while (leer.next()) {
					
		

					int numeroInterno = leer.getInt(1);
					System.out.println("Numero interno: " + numeroInterno);

					escribe.write("numeroInterno;" + numeroInterno + "\n");

					String marca = leer.getString(2);
					System.out.println("Marca: " + marca);

					escribe.write("Marca: " + marca + "\n");

					String modelo = leer.getString(3);
					System.out.println("Modelo: " + modelo);

					escribe.write("Modelo: " + modelo + "\n");

					String procesador = leer.getString(4);
					System.out.println("Procesador: " + procesador);

					escribe.write("Procesador: " + procesador + "\n");

					String tipoMemoria = leer.getString(5);
					System.out.println("Tipo de memória: " + tipoMemoria);

					escribe.write("Tipo de memória: " + tipoMemoria + "\n");

					int cantidadMemoria = leer.getInt(6);
					System.out.println("Cantidad de memória: " + cantidadMemoria + "gb");

					escribe.write("Cantidad de memória: " + cantidadMemoria + "gb" + "\n");

					String ubicacion = leer.getString(7);
					System.out.println("Ubicación: " + ubicacion);

					escribe.write("Ubicación: " + ubicacion + "\n");

					int numeroSerie = leer.getInt(8);
					System.out.println("Número de serie: " + numeroSerie);

					escribe.write("Número de serie: " + numeroSerie + "\n");

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
		

		// Método Listado en TXT equipos

		private static void listarJugadorestxt(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from futbol";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);
				System.out.println("");
				System.out.println("Listado Jugadores");
				System.out.println("");

				FileWriter escribe = new FileWriter("c:/informes/informe.txt", true);
				while (leer.next()) {
					
		

					int numeroInterno = leer.getInt(1);
					System.out.println("Numero interno: " + numeroInterno);

					escribe.write("numeroInterno;" + numeroInterno + "\n");

					String marca = leer.getString(2);
					System.out.println("Marca: " + marca);

					escribe.write("Marca: " + marca + "\n");

					String modelo = leer.getString(3);
					System.out.println("Modelo: " + modelo);

					escribe.write("Modelo: " + modelo + "\n");

					String procesador = leer.getString(4);
					System.out.println("Procesador: " + procesador);

					escribe.write("Procesador: " + procesador + "\n");

					String tipoMemoria = leer.getString(5);
					System.out.println("Tipo de memória: " + tipoMemoria);

					escribe.write("Tipo de memória: " + tipoMemoria + "\n");

					int cantidadMemoria = leer.getInt(6);
					System.out.println("Cantidad de memória: " + cantidadMemoria + "gb");

					escribe.write("Cantidad de memória: " + cantidadMemoria + "gb" + "\n");

					String ubicacion = leer.getString(7);
					System.out.println("Ubicación: " + ubicacion);

					escribe.write("Ubicación: " + ubicacion + "\n");

					int numeroSerie = leer.getInt(8);
					System.out.println("Número de serie: " + numeroSerie);

					escribe.write("Número de serie: " + numeroSerie + "\n");

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


