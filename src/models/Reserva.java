package models;

import java.sql.Date;

public class Reserva {
	private int id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private int valor;
	private String forma_pago;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	
	public Date getFecha_salida() {
		return fecha_salida;
	}
	
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getForma_pago() {
		return forma_pago;
	}
	
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", valor="
				+ valor + ", forma_pago=" + forma_pago;
	}
}