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
		MenuPrincipal miMenuPrincipal = new MenuPrincipal();
		Login miLogin = new Login();
		Funciones misFunciones = new Funciones();
		MenuUsuario miMenuUsuario = new MenuUsuario();
		UsuarioDAO miUsuarioDAO = new UsuarioDAO();
		Reservas misReservas = new Reservas();
		ReservaDAO miReservaDAO = new ReservaDAO();
		Busqueda miBusqueda = new Busqueda();
		RegistroHuesped miRegistroHuesped = new RegistroHuesped();
		HuespedDAO miHuespedDAO = new HuespedDAO();
		Exito miExito = new Exito();
		Coordinador miCoordinador = new Coordinador();
		
		// Se establecen las relaciones entre clases
		miMenuPrincipal.setCoordinador(miCoordinador);		
		miLogin.setCoordinador(miCoordinador);
		misFunciones.setCoordinador(miCoordinador);
		miMenuUsuario.setCoordinador(miCoordinador);
		misReservas.setCoordinador(miCoordinador);
		miUsuarioDAO.setCoordinador(miCoordinador);
		miBusqueda.setCoordinador(miCoordinador);
		miReservaDAO.setCoordinador(miCoordinador);
		miHuespedDAO.setCoordinador(miCoordinador);
		miExito.setCoordinador(miCoordinador);
		miRegistroHuesped.setCoordinador(miCoordinador);
		
		// Se establecen relaciones con la clase Coordinador
		miCoordinador.setMenuPrincipal(miMenuPrincipal);
		miCoordinador.setLogin(miLogin);
		miCoordinador.setFunciones(misFunciones);
		miCoordinador.setMenuUsuario(miMenuUsuario);
		miCoordinador.setUsuarioDAO(miUsuarioDAO);
		miCoordinador.setMisReservas(misReservas);
		miCoordinador.setMiBusqueda(miBusqueda);
		miCoordinador.setMiReservaDAO(miReservaDAO);
		miCoordinador.setMiRegistroHuesped(miRegistroHuesped);
		miCoordinador.setMiHuespedDAO(miHuespedDAO);
		miCoordinador.setMiExito(miExito);
		
		miCoordinador.mostrarMenuPrincipal();
	}
}