package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import clases.Administrador;

public class Main {

	public static void main(String[] args) {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Futbol", "root", "");
			System.out.println("¡Conexión con la BBDD establecida correctamente!");

			Administrador prueba = new Administrador();
			
			prueba.login(conn, "Futbol");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}

}
