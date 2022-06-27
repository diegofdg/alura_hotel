package controllers;

import dao.HuespedDAO;
import dao.ReservaDAO;
import dao.UsuarioDAO;
import funciones.Funciones;
import views.Busqueda;
import views.Exito;
import views.Login;
import views.MenuPrincipal;
import views.MenuUsuario;
import views.RegistroHuesped;
import views.Reservas;

public class Relaciones {
	
	public void iniciar() {
		HuespedDAO miHuespedDAO = new HuespedDAO();
		ReservaDAO miReservaDAO = new ReservaDAO();
		UsuarioDAO miUsuarioDAO = new UsuarioDAO();
		Funciones misFunciones = new Funciones();
		Busqueda miBusqueda = new Busqueda();
		Exito miExito = new Exito();
		Login miLogin = new Login();
		MenuPrincipal miMenuPrincipal = new MenuPrincipal();
		MenuUsuario miMenuUsuario = new MenuUsuario();
		RegistroHuesped miRegistroHuesped = new RegistroHuesped();
		Reservas misReservas = new Reservas();
		Coordinador miCoordinador = new Coordinador();
		
		// Se establecen las relaciones entre clases
		miHuespedDAO.setCoordinador(miCoordinador);
		miReservaDAO.setCoordinador(miCoordinador);
		miUsuarioDAO.setCoordinador(miCoordinador);
		misFunciones.setCoordinador(miCoordinador);
		miBusqueda.setCoordinador(miCoordinador);
		miExito.setCoordinador(miCoordinador);
		miLogin.setCoordinador(miCoordinador);
		miMenuPrincipal.setCoordinador(miCoordinador);		
		miMenuUsuario.setCoordinador(miCoordinador);
		miRegistroHuesped.setCoordinador(miCoordinador);
		misReservas.setCoordinador(miCoordinador);
		
		// Se establecen relaciones con la clase Coordinador
		miCoordinador.setMiHuespedDAO(miHuespedDAO);
		miCoordinador.setMiReservaDAO(miReservaDAO);
		miCoordinador.setUsuarioDAO(miUsuarioDAO);
		miCoordinador.setFunciones(misFunciones);
		miCoordinador.setMiBusqueda(miBusqueda);
		miCoordinador.setMiExito(miExito);
		miCoordinador.setLogin(miLogin);
		miCoordinador.setMenuPrincipal(miMenuPrincipal);
		miCoordinador.setMenuUsuario(miMenuUsuario);
		miCoordinador.setMiRegistroHuesped(miRegistroHuesped);
		miCoordinador.setMisReservas(misReservas);
		
		miCoordinador.mostrarMenuPrincipal();
	}
}