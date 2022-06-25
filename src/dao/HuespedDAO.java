package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import conexion.Conexion;
import controllers.Coordinador;
import models.Huesped;

public class HuespedDAO {
	
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
			JOptionPane.showMessageDialog(
				null,
				resultado,
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
		}
		
		return resultado;
	}
	
	public int guardarHuesped(Huesped huesped) throws SQLException {
		int resultado = 0;
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}

		String consulta = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva)"
				+ "  VALUES (?, ?, ?, ?, ?, ?)";

		try {
			preStatement = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, huesped.getNombre());
			preStatement.setString(2, huesped.getApellido());
			preStatement.setDate(3, huesped.getFecha_nacimiento());
			preStatement.setString(4, huesped.getNacionalidad());			
			preStatement.setString(5, huesped.getTelefono());
			preStatement.setInt(6, huesped.getId_reserva());
			preStatement.execute();

			final ResultSet resultSet = preStatement.getGeneratedKeys();		    
            
            while (resultSet.next()) {
                resultado = resultSet.getInt(1);
                
                System.out.println(String.format("Fue insertado el huesped con id: " +resultado));
            }
            
		
		} catch (SQLException e) {
			System.out.println("No se pudo registrar el huesped " + e.getMessage());
			
		} catch (Exception e) {
			System.out.println("No se pudo registrar el huesped " + e.getMessage());
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}
	
	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}