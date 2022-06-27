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
import models.Huesped;

public class HuespedDAO {
	
	private Coordinador miCoordinador;
	private Connection connection = null;
	private Conexion conexion = null;
	private PreparedStatement preStatement = null;
	
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
			JOptionPane.showMessageDialog(
				null,
				"No se pudo registrar el huesped",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo registrar el huesped",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
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
	
	public ArrayList<Huesped> listarHuespedes() throws SQLException {
		ArrayList<Huesped> resultado = new ArrayList<Huesped>();
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}

		String consulta = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";

		try {
			preStatement = connection.prepareStatement(consulta);
			final ResultSet resultSet = preStatement.executeQuery();		    
            
            while (resultSet.next()) {
            	Huesped huesped = new Huesped();
            	huesped.setId(resultSet.getInt(1));
            	huesped.setNombre(resultSet.getString(2));
            	huesped.setApellido(resultSet.getString(3));
            	huesped.setFecha_nacimiento(resultSet.getDate(4));
            	huesped.setNacionalidad(resultSet.getString(5));
            	huesped.setTelefono(resultSet.getString(6));
            	huesped.setId_reserva(resultSet.getInt(7));
                resultado.add(huesped);
            }
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo listar los huespedes",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo listar los huespedes",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}

	public ArrayList<Huesped> buscarHuespedPorId(int id) throws SQLException {
		ArrayList<Huesped> resultado = new ArrayList<Huesped>();
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}
		
		ResultSet resultSet = null;

		String consulta = "SELECT nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id_reserva = ?";


		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setInt(1, id);
			resultSet = preStatement.executeQuery();
			
            
            if(resultSet.next()) {
            	Huesped huesped = new Huesped();
            	huesped.setNombre(resultSet.getString(1));
            	huesped.setApellido(resultSet.getString(2));
            	huesped.setFecha_nacimiento(resultSet.getDate(3));
            	huesped.setNacionalidad(resultSet.getString(4));
            	huesped.setTelefono(resultSet.getString(5));
            	huesped.setId_reserva(resultSet.getInt(6));
                resultado.add(huesped);
            }
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo realizar la búsqueda",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo realizar la búsqueda",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}

	public ArrayList<Huesped> buscarHuespedPorApellido(String apellido) throws SQLException {
		ArrayList<Huesped> resultado = new ArrayList<Huesped>();
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}
		
		ResultSet resultSet = null;

		String consulta = "SELECT nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE apellido LIKE CONCAT( '%',?,'%')";
		
		try {			
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, apellido);
			resultSet = preStatement.executeQuery();			
            
            while(resultSet.next()) {
            	Huesped huesped = new Huesped();
            	huesped.setNombre(resultSet.getString(1));
            	huesped.setApellido(resultSet.getString(2));
            	huesped.setFecha_nacimiento(resultSet.getDate(3));
            	huesped.setNacionalidad(resultSet.getString(4));
            	huesped.setTelefono(resultSet.getString(5));
            	huesped.setId_reserva(resultSet.getInt(6));
                resultado.add(huesped);
            }
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo realizar la búsqueda",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo realizar la búsqueda",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}

	public int editarHuesped(Huesped huesped) throws SQLException {
		int resultado = 0;
		
		if (!conectar().equals("conectado")) {
			return resultado;
		}

		String consulta = "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?";

		try {
			preStatement = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, huesped.getNombre());
			preStatement.setString(2, huesped.getApellido());
			preStatement.setDate(3, huesped.getFecha_nacimiento());
			preStatement.setString(4, huesped.getNacionalidad());			
			preStatement.setString(5, huesped.getTelefono());
			preStatement.setInt(6, huesped.getId_reserva());
			preStatement.setInt(7, huesped.getId());
			preStatement.execute();

			final ResultSet resultSet = preStatement.getGeneratedKeys();		    
            
            while (resultSet.next()) {
                resultado = resultSet.getInt(1);                
                System.out.println(String.format("Fue editado el huesped con id: " +resultado));
            }
            
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo editar el huesped",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
				null,
				"No se pudo editar el huesped",
				"Error",
				JOptionPane.ERROR_MESSAGE
			);
			
		} finally {
			preStatement.close();
			connection.close();
			conexion.desconectar();
		}
		
		return resultado;
	}
}