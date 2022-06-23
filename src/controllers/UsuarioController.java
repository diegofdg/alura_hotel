package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;

public class UsuarioController {
	
	private Coordinador miCoordinador;	
	Connection connection = null;
	Conexion conexion = null;
	PreparedStatement preStatement = null;
	
	private String conectar() {
		
		conexion = new Conexion();
		String resultado=conexion.conectar();
		
		if (resultado.equals("conectado")) {
			connection = conexion.getConnection();
		} else {
			JOptionPane.showMessageDialog(null, resultado,"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		return resultado;
	}
	
	public boolean verificarLogin(String usuario, String password) throws SQLException {
		if (!conectar().equals("conectado")) {
			return false;
		}
		
		ResultSet result = null;
	
		String consulta="SELECT * FROM usuarios where usuario = ? ";
		
		
		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, usuario);			
			result = preStatement.executeQuery();			
				
			if(result.next()){
				if(result.getString(3).equals(password)) {
					return true;					
				}				
			}			
			return false;
			   
		} catch (SQLException e) {
			System.out.println("Error: "+e.getMessage());
			return false;
			
		} finally {
			result.close();
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}		
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}