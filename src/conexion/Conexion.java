package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class Conexion {
	private String nombreBd = "alura_hotel";
	private String usuario = "root";
	private String password = "";
	private String url = "jdbc:mysql://localhost:3306/"+nombreBd+"?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";

	Connection conn = null;
	
	public String conectar() {		
		String respuesta = "";
		
		try {
			// Obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Obtener la conexion
			conn = DriverManager.getConnection(url,usuario,password);
			
			if (conn != null) {
				respuesta = "conectado";
				
			} else {
				respuesta = "NO SE PUDO CONECTAR " +nombreBd;
			}
		}
		
		catch (ClassNotFoundException e) {
			respuesta = "Ocurre una ClassNotFoundException : "+e.getMessage();
		}
		
		catch (SQLSyntaxErrorException e) {
			respuesta = "Ocurre una SQLSyntaxErrorException: "+e.getMessage()+"\n";
			respuesta += "Verifique que se esté usando la base de datos y tablas correctas...";
		}
		
		catch (CommunicationsException e) {
			respuesta = "Ocurre una CommunicationsException: "+e.getMessage()+"\n";
			respuesta += "Verifique que la base de datos fué iniciada...";
		}
		catch (SQLException e) {
			respuesta = "Ocurre una SQLException: "+e.getMessage()+"\n";
			respuesta += "Este es un problema general de SQL, verifique con el administrador";
		}
		
		return respuesta;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void desconectar() {
		conn=null;
	}
}