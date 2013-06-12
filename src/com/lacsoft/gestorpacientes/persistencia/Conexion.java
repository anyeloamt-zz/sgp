package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private static Conexion instancia;
	private GestorXML gestorXML;
	
	private Conexion(){
		gestorXML = new GestorXML();
	}
	
	public static synchronized Conexion getInstancia(){
		return instancia = (instancia == null) ? new Conexion() : instancia;
	}
	
	/**
	 * Retorna un objeto de tipo Connection para que sólo haya una conexión en la aplicación.
	 * @return java.sql.Connection
	 */
	public final Connection crearConexion(){
		Connection conexion = null;
		try {
			Class.forName(gestorXML.getNombreDriver()).newInstance();
			conexion = DriverManager.getConnection(gestorXML.getStringConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conexion;
	}
}

