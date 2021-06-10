
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Administrador {

	private String user;
	private String password;
	private String nombre;
	private String apellidos;

	// Getters & Setters
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	// Constructor
	/*
	 * public Administrador(String user, String password) { super(); this.user =
	 * user; this.password = password; }
	 */

	public void login(Connection conn, String string) throws SQLException {

		Statement stmt = null;
		
		String userBase = null;
		String passBase = null;

		Scanner sca = new Scanner(System.in);

		System.out.println(
				"Bienvenido a nuestro programa, introduzca su nombre de usuario y contraseña para iniciar sesión:");
		System.out.println("");
		System.out.println("");

		System.out.print("Nombre de usuario: ");
		String user = sca.nextLine();
		System.out.println("");
		System.out.print("Contraseña: ");
		String password = sca.nextLine();

		String query = "select username, password from usuarios WHERE username = '" + user + "' && password = '" + password + "'";

		try {

			stmt = conn.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			while (leer.next()) {

				userBase = leer.getString(1);
				//System.out.println("Usuario: " + userBase);

				passBase = leer.getString(2);
				//System.out.println("Contraseña: " + passBase);

				System.out.println("***************************************");
			}
			
			if (user.equals(userBase) && password.equals(passBase)) {
				System.out.println("¡Ha iniciado sesión correctamente!");
			} else {
				System.out.println("Su nombre de usuario o contraseña son incorrectos, vuelva a intentarlo...");
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

		sca.close();
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
