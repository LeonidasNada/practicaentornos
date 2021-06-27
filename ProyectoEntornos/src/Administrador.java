import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Administrador {

	boolean autenticacion = false;
	String nombreUsuario;

	public void login() throws SQLException {

		Connection conn;

		Statement stmt = null;

		String userBase = null;
		String passBase = null;

		Scanner sca = new Scanner(System.in);

		System.out.println(
				"######################################################################################################");
		System.out.println(
				"## Bienvenido a nuestro programa, introduzca su nombre de usuario y contrase�a para iniciar sesi�n: ##");
		System.out.println(
				"######################################################################################################");
		System.out.println("");

		System.out.print("Nombre de usuario: ");
		String user = sca.nextLine();
		System.out.print("Contrase�a: ");
		String password = sca.nextLine();

		String query = "select username, password, Apellidos from usuarios WHERE username = '" + user
				+ "' && password = '" + password + "'";

		try {

			Principal prnc = new Principal();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			stmt = conn.createStatement();

			ResultSet leer = stmt.executeQuery(query);

			while (leer.next()) {

				userBase = leer.getString(1);
				// System.out.println("Usuario: " + userBase);

				passBase = leer.getString(2);
				// System.out.println("Contrase�a: " + passBase);

				prnc.apellidos = leer.getString(3);
				//System.out.println(prnc.apellidos);

				System.out.println("***************************************");
				System.out.println("");
			}

			if (user.equals(userBase) && password.equals(passBase)) {
				System.out.println("�Ha iniciado sesi�n correctamente!");
				
				try {
		            //Ponemos a "Dormir" el programa durante los ms que queremos
		            Thread.sleep(2*1000);
		         } catch (Exception e) {
		            System.out.println(e);
		         }
				autenticacion = true;

				comprobacionLogin(autenticacion);

			} else {
				System.out.println("*************************************************************************");
				System.out.println("Su nombre de usuario o contrase�a son incorrectos, vuelva a intentarlo...");
				System.out.println("*************************************************************************");
				System.out.println("");
				login();
			}

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

		sca.close();
	}

	public void comprobacionLogin(boolean autenticacion) {

		Principal ini = new Principal();

		if (autenticacion == true) {
			ini.menuPrincipal();
		}

	}
	

	// M�todos Administradores
	public void crearAdmin(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;

		Scanner sca = new Scanner(System.in);

		System.out.println("A continuaci�n podr� crear un nuevo usuario administrador...");
		System.out.println("");
		System.out.println("Nuevo nombre de usuario: ");
		System.out.print("");
		String usernameAdmin = sca.nextLine();
		System.out.println("");
		System.out.println("Nueva contrase�a: ");
		System.out.print("");
		String passAdmin = sca.nextLine();
		System.out.println("");
		System.out.println("Ingrese el nombre del usuario: ");
		System.out.print("");
		String nombreAdmin = sca.nextLine();
		System.out.println("");
		System.out.println("Ingrese los apellidos del usuario: ");
		System.out.print("");
		String apellidosAdmin = sca.nextLine();

		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "");
			System.out.println("Conexi�n establecida correctamente con la base de datos");
			stmt = conn.createStatement();

			stmt.executeUpdate("INSERT INTO " + BDNombre + ".Usuarios(Username, Password, Nombre, Apellidos) VALUES('"
					+ usernameAdmin + "','" + passAdmin + "','" + nombreAdmin + "','" + apellidosAdmin + "')");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
		}

		sca.close();
	}

	public void eliminarAdmin(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;
		Scanner sca = new Scanner(System.in);

		System.out.println("Indique el ID del administrador que quiera eliminar: ");
		int numeroInterno = sca.nextInt();
		System.out.println("");

		try {

			stmt = conn.createStatement();

			stmt.executeUpdate("DELETE FROM " + BDNombre + ".Usuarios  WHERE " + "IdUsuario =" + numeroInterno + " ");

			System.out.println("");
			System.out.println("�Se ha eliminado el administrador " + numeroInterno + "!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
			sca.close();
		}

	}

	public void modificarAdmin(Connection conn, String BDNombre) throws SQLException {

		Statement stmt = null;
		Scanner sca = new Scanner(System.in);
		String query = "";
		System.out.println("Indique el ID del administrador que quiera modificar: ");
		int numeroInterno = sca.nextInt();
		System.out.println("Seleccione qu� desea modificar del administrador: ");
		System.out.println("1. Cambiar nombre de usuario");
		System.out.println("2. Cambiar contrase�a");
		System.out.println("3. Nombre");
		System.out.println("4. Apellidos");
		System.out.println("5. Modificar todo");

		try {

			int opcion = sca.nextInt();

			switch (opcion) {

			case 1:

				System.out.println("");
				System.out.print("Ingrese el nuevo nombre de usuario: ");
				sca.nextLine();
				String username = sca.nextLine();
				query = "UPDATE "+ BDNombre +".usuarios SET Username='" + username + "' WHERE IdUsuario='" + numeroInterno + "'";

				break;

			case 2:
				System.out.println("");
				System.out.print("Ingrese la nueva contrase�a: ");
				String password = sca.nextLine();
				query = "UPDATE "+ BDNombre +".usuarios SET Password='" + password + "' WHERE IdUsuario='" + numeroInterno + "'";

				break;
			case 3:
				System.out.println("");
				System.out.print("Ingrese el nuevo nombre: ");
				String nombreUser = sca.nextLine();
				query = "UPDATE "+ BDNombre +".usuarios SET Nombre='" + nombreUser + "' WHERE IdUsuario='" + numeroInterno + "'";

				break;
			case 4:
				System.out.println("");
				System.out.print("Ingrese el nuevo apellido: ");
				String apellidos = sca.nextLine();
				query = "UPDATE "+ BDNombre +" SET Apellidos='" + apellidos + "' WHERE IdUsuario='" + numeroInterno + "'";

				break;
			case 5:
				sca.nextLine();
				System.out.println("");
				System.out.print("Ingrese el nuevo nombre de usuario: ");
				username = sca.nextLine();
				query = "UPDATE "+ BDNombre +".usuarios SET Username='" + username + "' WHERE IdUsuario='" + numeroInterno + "'";
				System.out.println("");
				System.out.print("Ingrese la nueva contrase�a: ");
				password = sca.nextLine();
				query = "UPDATE "+ BDNombre +".usuarios SET Password='" + password + "' WHERE IdUsuario='" + numeroInterno + "'";
				System.out.println("");
				System.out.print("Ingrese el nuevo nombre: ");
				nombreUser = sca.nextLine();
				System.out.println("");
				System.out.print("Ingrese el nuevo apellido: ");
				apellidos = sca.nextLine();
				query = "UPDATE "+ BDNombre +".usuarios SET Username='" + username + "', Password='" + password + "', Nombre='"
						+ nombreUser + "', Apellidos='" + apellidos + "' WHERE IdUsuario='" + numeroInterno + "'";

			default:
				System.out.println("Por favor, seleccione una de las opciones ofrecidas en el men�.");
				System.out.println("qu� desea hacer:");
			}

			stmt = conn.createStatement();

			stmt.executeUpdate(query);

			System.out.println("");
			System.out.println("�Se han modificado los datos del administrador " + numeroInterno + "!");

		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			stmt.close();
			sca.close();
		}

	}

	public void verAdmins(Connection conn, String BDNombre) throws SQLException{
		
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
