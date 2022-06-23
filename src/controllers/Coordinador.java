package controllers;

import java.sql.SQLException;

import funciones.Funciones;
import views.Busqueda;
import views.Login;
import views.MenuPrincipal;
import views.MenuUsuario;
import views.Reservas;

public class Coordinador {
	
	private MenuPrincipal miMenuPrincipal;
	private Login miLogin;
	private Funciones misFunciones;
	private MenuUsuario miMenuUsuario;
	private UsuarioController miUsuarioController;
	private Reservas misReservas;
	private Busqueda miBusqueda;
	
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
	
	public void setUsuarioController(UsuarioController miUsuarioController) {
		this.miUsuarioController = miUsuarioController;		
	}

	public void setMisReservas(Reservas misReservas) {
		this.misReservas = misReservas;		
	}
	
	public void setMiBusqueda(Busqueda miBusqueda) {
		this.miBusqueda = miBusqueda;		
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
		return miUsuarioController.verificarLogin(usuario, password);		
	}	
}