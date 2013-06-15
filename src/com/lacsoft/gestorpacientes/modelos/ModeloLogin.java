package com.lacsoft.gestorpacientes.modelos;

import com.lacsoft.gestorpacientes.entidades.Usuario;
import com.lacsoft.gestorpacientes.persistencia.ConsultaLogin;

public class ModeloLogin {
	
	private ConsultaLogin conexionLogin = new ConsultaLogin();
	private static ModeloLogin instancia;
	
	public static synchronized ModeloLogin getInstancia(){
		return instancia = (instancia == null) ? new ModeloLogin() : instancia;
	}
	/**
	 * Recibe el c�digo de empleado y la clave <br>
	 * para verificar si el usuario existe.
	 * @param codigoEmpleado C�digo del empleado a loguearse
	 * @param clave Clave del empleado a loguearse.
	 * @return <b><i>String</i> Rol</b> 
	 * el rol del usuario que ingres�,<br> 
	 * o un String vac�o en caso de que el usuario no exista.
	 */
	public String comprobarUsuario (String codigoEmpleado, String clave){		
		return conexionLogin.comprobarUsusario(codigoEmpleado, clave);
	}

	public Usuario obtenerDatosCorreo(String codigoUsuario) {
		return conexionLogin.obtenerDatosCorreo(codigoUsuario);
	}
	/**
	 * Retorna true si el c�digo de usuario existe
	 */
	public boolean verificarCodigo(String codigoUsuario) {
		return conexionLogin.verificarCodigo(codigoUsuario);
	}

}
