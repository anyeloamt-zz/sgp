package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import com.lacsoft.gestorpacientes.entidades.Usuario;
import com.lacsoft.gestorpacientes.entidades.UsuarioLogueado;
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;

public class ConsultaLogin {
	
	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public String comprobarUsusario(String codigoEmpleado ,String clave){
		
		String retorno = "";
		
		try {
			ps = con.prepareStatement("SELECT rol FROM usuarios WHERE codigo_empleado=? AND clave=?");
			ps.setString(1, codigoEmpleado);
			ps.setString(2, clave);
			rs = ps.executeQuery();
			
			if (rs.next()){
				retorno = rs.getString("rol");
				cargarUsuarioLogueado(codigoEmpleado, clave);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno; 
	
	}

	/**
	 * Hace una instancia de la clase UsuarioLogueado para cargar
	 * el usuario que se acaba de loguear en el Sistema.
	 * @see com.lacsoft.gestorpacientes.entidades.UsuarioLogueado
	 * @param codigoEmpleado Código del usuario logueado.
	 * @param clave Contraseña del usuario logueado.
	 */
	private void cargarUsuarioLogueado(String codigoEmpleado, String clave) {
		try {
			ps = con.prepareStatement("SELECT u.*, e.nombre AS 'especialidad', t.telefono1, t.telefono2, t.celular " +
					"FROM usuarios u LEFT OUTER JOIN medicos_especialidades me ON u.codigo_empleado  = me.codigo_empleado " +
					"LEFT OUTER JOIN especialidades e ON me.codigo_especialidad = e.codigo_especialidad LEFT OUTER JOIN " +
					"telefonos t ON u.codigo_empleado = t.codigo_empleado WHERE u.codigo_empleado = ? AND clave = ? ORDER BY u.rol ");
			ps.setString(1, codigoEmpleado);
			ps.setString(2, clave);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				UsuarioLogueado.getInstancia().crear(rs.getString("rol"), 
						rs.getString("u.clave"), rs.getString("u.nombre"), 
						rs.getString("u.apellido"), rs.getString("u.direccion"), 
						rs.getString("u.cedula"), rs.getString("t.telefono1"), 
						rs.getString("t.telefono2"), rs.getString("t.celular"), 
						rs.getString("u.codigo_empleado"), rs.getString("especialidad"), 
						"", RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("u.foto"))), 
						rs.getString("correo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario obtenerDatosCorreo(String codigoUsuario) {
		Usuario usuario = new Usuario();
		
		try {
			ps = con.prepareStatement("SELECT nombre, apellido, correo, clave FROM usuarios WHERE codigo_empleado = ?");
			ps.setString(1, codigoUsuario);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}

	public boolean verificarCodigo(String codigoUsuario) {
		boolean retorno = false;
		try {
			ps = con.prepareStatement("SELECT codigo_empleado FROM usuarios WHERE codigo_empleado = ?");
			ps.setString(1, codigoUsuario);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				retorno = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return retorno;
	}
	
}
