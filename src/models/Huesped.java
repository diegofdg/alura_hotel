package models;

import java.sql.Date;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalidad;
	private String telefono;
	private int id_reserva;
	
	public Huesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, int id_reserva) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public int getId_reserva() {
		return id_reserva;
	}

	@Override
	public String toString() {
		return "Huesped [nombre=" + nombre + ", apellido=" + apellido + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", id_reserva=" + id_reserva + "]";
	}
	
	
}