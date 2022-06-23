package controllers;

import conexion.Conexion;

public class Main {

	public static void main(String[] args) {
		new Relaciones().iniciar();
		Conexion miConexion = new Conexion();
		String respuesta = miConexion.conectar();
		System.out.println(respuesta);
	}
}