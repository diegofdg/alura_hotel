package controllers;

import java.sql.SQLException;

import funciones.Funciones;
import views.Login;
import views.MenuPrincipal;
import views.MenuUsuario;

public class Coordinador {
	
	private MenuPrincipal miMenuPrincipal;
	private Login miLogin;
	private Funciones misFunciones;
	private MenuUsuario miMenuUsuario;
	private UsuarioController miUsuarioController;
	
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

	public boolean verificarLogin(String usuario, String password) throws SQLException {
		return miUsuarioController.verificarLogin(usuario, password);		
	}

	public void setUsuarioController(UsuarioController miUsuarioController) {
		this.miUsuarioController = miUsuarioController;		
	}	
}