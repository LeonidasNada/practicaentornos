package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jugador {
	
	

	public static void main(String[] args) {
		
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ordenadores", "root", "");
			System.out.println("Conexión establecida correctamente con la base de datos");
	

			System.out.println("Eliga una opción");
			System.out.println("1. Listar todos los equipos");
			System.out.println("2. Listar todos los jugadores");
			System.out.println("3. Listar todos los jugadores de cada categoría");
			System.out.println("4. Guardar en txt");

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				listarequipos(conn, "Ordenadores");
				
				break;
			case 2:
				//insertsOrdenadores(conn, "Ordenadores");
				break;

			case 3:
				//cambiarUbicacion(conn, "Ordenadores");

				break;

			case 4:
				//eliminarordenador(conn, "Ordenadores");
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

		private static void listarequipos(Connection con, String BDNombre) throws SQLException {

			Statement stmt = null;

			String query = "select * from Ordenador";

			try {

				stmt = con.createStatement();

				ResultSet leer = stmt.executeQuery(query);

				System.out.println("");
				System.out.println("Equipos");
				System.out.println("");

				while (leer.next()) {

					//String equipo = leer.getInt(1);
				//	System.out.println("Equipo: " + equipo);

				}

			} catch (SQLException e) {
				//printSQLException(e);
			} finally {
				stmt.close();
			}

		}


	}


