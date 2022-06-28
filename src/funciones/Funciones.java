package funciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import controllers.Coordinador;

public class Funciones {
	
	Coordinador miCoordinador;
	
	public boolean verificarLogin(String usuario, String password) {
		return true;		
	}
	
	public java.sql.Date convertirDateASqlDate(java.util.Date fecha) {
		// Convertir Date a String
		SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");		
        String fechaFormateada = formatoSalida.format(fecha);
        
        // Convertir String a Sql Date
        java.sql.Date fechaSql = java.sql.Date.valueOf(fechaFormateada);
        return fechaSql;
	}
	
	public java.util.Date convertirStringADate(String fecha) {
		java.util.Date fechaDate = null;		
		
		try {
			// Convertir String a java.util.Date
			SimpleDateFormat formatoDate = new SimpleDateFormat("yyyy-MM-dd");
			fechaDate = formatoDate.parse(fecha);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechaDate;		
	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;		
	}
}