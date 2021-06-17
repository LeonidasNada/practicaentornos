package main;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Futbol", "root", "");
			System.out.println("¡Conexión con la BBDD establecida correctamente!");

			Administrador prueba = new Administrador();

			// prueba.login(conn, "Futbol");

			// prueba.crearAdmin(conn, "Futbol");

			// prueba.eliminarAdmin(conn, "Futbol");

			// prueba.modificarAdmin(conn, "Futbol");

			Jugador jugPrueba = new Jugador();

			menuJugador();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void menuPrincipal() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("***************************************************************");
			System.out.println("********¡Bienvenido a nuestro programa, Administrador!*********");
			System.out.println("***************************************************************");
			System.out.println("");
			System.out.println("Indique a continuación qué desea hacer: ");
			System.out.println("");
			System.out.println("1. Opciones de jugadores");
			System.out.println("2. Opciones de equipos");
			System.out.println("3. Opciones de plantilla");
			System.out.println("4. Opciones de administrador");
			System.out.print("");

			Jugador jug = new Jugador();

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

				jug.modificarPosicion(conn, "Jugadores");

				break;

			case 4:

				jug.modificarEstadoFisico(conn, "Jugadores");

			case 5:

				jug.modificarDisponibilidad(conn, "Jugadores");

			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void menuJugador() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Listar jugadores por equipo");
			System.out.println("2. Listar todos los jugadores");
			System.out.println("3. Modificar posicion del jugador");
			System.out.println("4. Modificar estado fisico del jugador");
			System.out.println("5. Modificar disponibilidad del jugador");
			System.out.print("");

			Jugador jug = new Jugador();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				System.out.print("");
				System.out.println("Elija una opción");
				System.out.println("1. Listar por equipos");
				System.out.println("2. Listar por equipos y Guardar .TXT");

				int elige = entrada.nextInt();
				if (elige == 1) {
					jug.listarEquipos(conn, "Futbol");
				} else if (elige == 2) {
					jug.listarEquipostxt(conn, "Futbol");
				} else {
					System.out.println("Elige una opción valida");
				}

				break;

			case 2:
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
					System.out.println("Elige una opción valida");
				}

				break;

			case 3:

				System.out.print("");
				jug.modificarPosicion(conn, "Jugadores");

				break;

			case 4:

				System.out.print("");
				jug.modificarEstadoFisico(conn, "Jugadores");

			case 5:

				System.out.print("");
				jug.modificarDisponibilidad(conn, "Jugadores");

			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void menuAdmin() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Listar jugadores por equipo");
			System.out.println("2. Listar todos los jugadores");
			System.out.println("3. Modificar posicion del jugador");
			System.out.println("4. Modificar estado fisico del jugador");
			System.out.println("5. Modificar disponibilidad del jugador");
			System.out.print("");

			Jugador jug = new Jugador();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Elija una opción");
				System.out.println("1. Listar equipos");
				System.out.println("2. Listar y Guardar TXT");

				int elige = entrada.nextInt();
				if (elige == 1) {
					jug.listarEquipos(conn, "Ordenadores");
				} else if (elige == 2) {
					jug.listarEquipostxt(conn, "Ordenadores");
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
					jug.listarJugadores(conn, "Ordenadores");
				} else if (elige1 == 2) {
					jug.listarJugadorestxt(conn, "Ordenadores");
				} else {
					System.out.println("Elige una opción valida");
				}

				break;

			case 3:

				jug.modificarPosicion(conn, "Jugadores");

				break;

			case 4:

				jug.modificarEstadoFisico(conn, "Jugadores");

			case 5:

				jug.modificarDisponibilidad(conn, "Jugadores");

			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void menuEquipos() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			// System.out.println("Conexión establecida correctamente con la base de
			// datos");
			System.out.println("");

			System.out.println("Elija una opción:");
			System.out.println("");
			System.out.println("1. Listar jugadores por equipo");
			System.out.println("2. Listar todos los jugadores");
			System.out.println("3. Modificar posicion del jugador");
			System.out.println("4. Modificar estado fisico del jugador");
			System.out.println("5. Modificar disponibilidad del jugador");
			System.out.print("");

			Jugador jug = new Jugador();

			Scanner entrada = new Scanner(System.in);

			int opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Elija una opción");
				System.out.println("1. Listar equipos");
				System.out.println("2. Listar y Guardar TXT");

				int elige = entrada.nextInt();
				if (elige == 1) {
					jug.listarEquipos(conn, "Ordenadores");
				} else if (elige == 2) {
					jug.listarEquipostxt(conn, "Ordenadores");
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
					jug.listarJugadores(conn, "Ordenadores");
				} else if (elige1 == 2) {
					jug.listarJugadorestxt(conn, "Ordenadores");
				} else {
					System.out.println("Elige una opción valida");
				}

				break;

			case 3:

				jug.modificarPosicion(conn, "Jugadores");

				break;

			case 4:

				jug.modificarEstadoFisico(conn, "Jugadores");

			case 5:

				jug.modificarDisponibilidad(conn, "Jugadores");

			default:
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}