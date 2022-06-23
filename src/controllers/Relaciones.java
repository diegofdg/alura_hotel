package controllers;

import funciones.Funciones;
import views.Busqueda;
import views.Login;
import views.MenuPrincipal;
import views.MenuUsuario;
import views.Reservas;

public class Relaciones {
	
	public void iniciar() {
		MenuPrincipal miMenuPrincipal = new MenuPrincipal();
		Login miLogin = new Login();
		Funciones misFunciones = new Funciones();
		MenuUsuario miMenuUsuario = new MenuUsuario();
		UsuarioController miUsuarioController = new UsuarioController();
		Reservas misReservas = new Reservas();
		Busqueda miBusqueda = new Busqueda();
		Coordinador miCoordinador = new Coordinador();
		
		// Se establecen las relaciones entre clases
		miMenuPrincipal.setCoordinador(miCoordinador);		
		miLogin.setCoordinador(miCoordinador);
		misFunciones.setCoordinador(miCoordinador);
		miMenuUsuario.setCoordinador(miCoordinador);
		misReservas.setCoordinador(miCoordinador);
		miUsuarioController.setCoordinador(miCoordinador);
		miBusqueda.setCoordinador(miCoordinador);
		
		// Se establecen relaciones con la clase Coordinador
		miCoordinador.setMenuPrincipal(miMenuPrincipal);
		miCoordinador.setLogin(miLogin);
		miCoordinador.setFunciones(misFunciones);
		miCoordinador.setMenuUsuario(miMenuUsuario);
		miCoordinador.setUsuarioController(miUsuarioController);
		miCoordinador.setMisReservas(misReservas);
		miCoordinador.setMiBusqueda(miBusqueda);
				
		miCoordinador.mostrarMenuPrincipal();
	}
}