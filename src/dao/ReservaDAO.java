package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexion.Conexion;
import controllers.Coordinador;
import models.Reserva;

public class ReservaDAO {
	
	private Coordinador miCoordinador;
	private Connection connection = null;
	private Conexion conexion = null;
	private PreparedStatement preStatement = null;
	
	private String conectar() {
		conexion = new Conexion();
		String resultado = conexion.conectar();
		
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
		
		} catch (Exception e) {
			return resultado;
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}	
	
	public ArrayList<Reserva> listarReservas() throws SQLException {
		ArrayList<Reserva> resultado = new ArrayList<Reserva>();
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}

		String consulta = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";

		try {
			preStatement = connection.prepareStatement(consulta);
			final ResultSet resultSet = preStatement.executeQuery();		    
            
            while (resultSet.next()) {
            	Reserva reserva = new Reserva();
            	reserva.setId(resultSet.getInt(1));
            	reserva.setFecha_entrada(resultSet.getDate(2));
            	reserva.setFecha_salida(resultSet.getDate(3));
            	reserva.setValor(resultSet.getInt(4));
            	reserva.setForma_pago(resultSet.getString(5));            	
                resultado.add(reserva);                
            }            
		
		} catch (Exception e) {
			return resultado;
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}

	public ArrayList<Reserva> buscarReservaPorId(int id) throws SQLException {		
		ArrayList<Reserva> resultado = new ArrayList<Reserva>();
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}
		
		ResultSet resultSet = null;

		String consulta = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas WHERE id = ?";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setInt(1, id);
			resultSet = preStatement.executeQuery();				
            
            if(resultSet.next()) {
            	Reserva reserva = new Reserva();
            	reserva.setId(resultSet.getInt(1));
            	reserva.setFecha_entrada(resultSet.getDate(2));
            	reserva.setFecha_salida(resultSet.getDate(3));
            	reserva.setValor(resultSet.getInt(4));
            	reserva.setForma_pago(resultSet.getString(5));            	
                resultado.add(reserva);        
            }
		
		} catch (Exception e) {
			return resultado;
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}

	public int editarReserva(Reserva reserva) throws SQLException {
		int resultado = 0;
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}
		
		String consulta = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?";

		try {
			preStatement = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			preStatement.setDate(1, reserva.getFecha_entrada());
			preStatement.setDate(2, reserva.getFecha_salida());
			preStatement.setInt(3, reserva.getValor());
			preStatement.setString(4, reserva.getForma_pago());			
			preStatement.setInt(5, reserva.getId());
			preStatement.execute();

			final ResultSet resultSet = preStatement.getGeneratedKeys();		    
            
            while (resultSet.next()) {
                resultado = resultSet.getInt(1);
                System.out.println(String.format("Fue editada la reserva con id: " +resultado));
            }
            
		
		} catch (Exception e) {
			return resultado;
			
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

	public String eliminarReserva(Integer id) throws SQLException {
		String respuesta = "";
		
		if (!conectar().equals("conectado")) {
			return "error";
		}
		
		try {
			String consulta = "DELETE FROM reservas WHERE id = ? ";

			PreparedStatement statement = connection.prepareStatement(consulta);
			statement.setInt(1, id);			
			statement.executeUpdate();
						
			respuesta = "ok";
			
		} catch (SQLException e) {
			respuesta = "error";
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		return respuesta;	
	}
}