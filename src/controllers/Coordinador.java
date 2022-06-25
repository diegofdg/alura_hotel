package controllers;

import java.sql.SQLException;

import dao.HuespedDAO;
import dao.ReservaDAO;
import dao.UsuarioDAO;
import funciones.Funciones;
import models.Huesped;
import models.Reserva;
import views.Busqueda;
import views.Exito;
import views.Login;
import views.MenuPrincipal;
import views.MenuUsuario;
import views.RegistroHuesped;
import views.Reservas;

public class Coordinador {
	
	private MenuPrincipal miMenuPrincipal;
	private Login miLogin;
	private Funciones misFunciones;
	private MenuUsuario miMenuUsuario;
	private UsuarioDAO miUsuarioDAO;
	private Reservas misReservas;
	private Busqueda miBusqueda;
	private ReservaDAO miReservaDAO;
	private HuespedDAO miHuespedDAO;
	private RegistroHuesped miRegistroHuesped;
	private Exito miExito;
	
	public void setMenuPrincipal(MenuPrincipal miMenuPrincipal) {
		this.miMenuPrincipal = miMenuPrincipal;		
	}
	
	public void setLogin(Login miLogin) {
		this.miLogin = miLogin;
	}
	
	public void setFunciones(Funciones misFunciones) {
		this.misFunciones = misFunciones;		
	}
	
	public void setMenuUsuario(MenuUsuario miMenuUsuario) {
		this.miMenuUsuario = miMenuUsuario;		
	}
	
	public void setUsuarioDAO(UsuarioDAO miUsuarioDAO) {
		this.miUsuarioDAO = miUsuarioDAO;
	}

	public void setMisReservas(Reservas misReservas) {
		this.misReservas = misReservas;		
	}
	
	public void setMiBusqueda(Busqueda miBusqueda) {
		this.miBusqueda = miBusqueda;		
	}
	
	public void setMiReservaDAO(ReservaDAO miReservaDAO) {
		this.miReservaDAO = miReservaDAO;		
	}
	
	public void setMiRegistroHuesped(RegistroHuesped miRegistroHuesped) {
		this.miRegistroHuesped = miRegistroHuesped;		
	}
	
	public void mostrarMenuPrincipal() {
		miMenuPrincipal.setVisible(true);		
	}
	
	public void ocultarMenuPrincipal() {
		miMenuPrincipal.setVisible(false);
	}

	public void mostrarLogin() {
		miLogin.setVisible(true);		
	}
	
	public void ocultarLogin() {
		miLogin.setVisible(false);
	}
	
	public void mostrarMenuUsuario() {
		miMenuUsuario.setVisible(true);		
	}
	
	public void ocultarMenuUsuario() {
		miMenuUsuario.setVisible(false);		
	}
	
	public void mostrarReservas() {
		misReservas.setVisible(true);		
	}
	
	public void ocultarReservas() {
		misReservas.setVisible(false);		
	}
	
	public void mostrarBusqueda() {
		miBusqueda.setVisible(true);
	}
	
	public void ocultarBusqueda() {
		miBusqueda.setVisible(false);
	}

	public boolean verificarLogin(String usuario, String password) throws SQLException {
		return miUsuarioDAO.verificarLogin(usuario, password);		
	}

	public int guardarReserva(Reserva reserva) throws SQLException {
		return miReservaDAO.guardarReserva(reserva);		
	}
	
	public int guardarHuesped(Huesped huesped) throws SQLException {
		return miHuespedDAO.guardarHuesped(huesped);
	}

	
	public void mostrarRegistroHuesped(int id_reserva) {
		miRegistroHuesped.agregarIdReserva(id_reserva);
		miRegistroHuesped.setVisible(true);
		misReservas.setVisible(false);		
	}

	public void ocultarRegistroHuesped() {
		miRegistroHuesped.dispose();		
	}

	public void setMiHuespedDAO(HuespedDAO miHuespedDAO) {
		this.miHuespedDAO = miHuespedDAO;
		
	}

	public void setMiExito(Exito miExito) {
		this.miExito = miExito;	
	}

	public void mostrarExito() {
		miExito.setVisible(true);
		miRegistroHuesped.setVisible(false);		
	}

	public void ocultarExito() {
		miExito.setVisible(false);		
	}			
}