package controllers;

import funciones.Funciones;
import views.Login;
import views.MenuPrincipal;
import views.MenuUsuario;

public class Relaciones {
	
	public void iniciar() {
		MenuPrincipal miMenuPrincipal = new MenuPrincipal();
		Login miLogin = new Login();
		Funciones misFunciones = new Funciones();
		MenuUsuario miMenuUsuario = new MenuUsuario();
		UsuarioController miUsuarioController = new UsuarioController();
		Coordinador miCoordinador = new Coordinador();
		
		// Se establecen las relaciones entre clases
		miMenuPrincipal.setCoordinador(miCoordinador);		
		miLogin.setCoordinador(miCoordinador);
		misFunciones.setCoordinador(miCoordinador);
		miMenuUsuario.setCoordinador(miCoordinador);
		miUsuarioController.setCoordinador(miCoordinador);
		
		// Se establecen relaciones con la clase Coordinador
		miCoordinador.setMenuPrincipal(miMenuPrincipal);
		miCoordinador.setLogin(miLogin);
		miCoordinador.setFunciones(misFunciones);
		miCoordinador.setMenuUsuario(miMenuUsuario);
		miCoordinador.setUsuarioController(miUsuarioController);
				
		miCoordinador.mostrarMenuPrincipal();
	}

}
