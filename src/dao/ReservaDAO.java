package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import conexion.Conexion;
import controllers.Coordinador;
import models.Reserva;

public class ReservaDAO {
	
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
	
	public int guardarReserva(Reserva reserva) throws SQLException {
		
		int resultado = 0;
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}

		String consulta = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago)"
				+ "  VALUES (?, ?, ?, ?)";

		try {
			preStatement = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			preStatement.setDate(1, reserva.getFecha_entrada());
			preStatement.setDate(2, reserva.getFecha_salida());
			preStatement.setInt(3, reserva.getValor());
			preStatement.setString(4, reserva.getForma_pago());			
			preStatement.execute();


			final ResultSet resultSet = preStatement.getGeneratedKeys();		    
            
            while (resultSet.next()) {
                resultado = resultSet.getInt(1);
                
                System.out.println(String.format("Fue insertada la reserva con id: " +resultado));
            }
            
		
		} catch (SQLException e) {
			System.out.println("No se pudo registrar la reserva " + e.getMessage());
			//e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("No se pudo registrar la reserva " + e.getMessage());
			//e.printStackTrace();
			
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