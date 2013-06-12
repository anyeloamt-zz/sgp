package com.lacsoft.gestorpacientes.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.lacsoft.gestorpacientes.Monitora;
import com.lacsoft.gestorpacientes.entidades.Usuario;
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;

public class ConsultaUsuario {

	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Usuario agregar(Usuario usuario) {

		try {

			File fotoString = new File(usuario.getFotoString());
			FileInputStream fis = new FileInputStream(fotoString);

			ps = con.prepareStatement("INSERT INTO usuarios VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, usuario.getCodigoDeEmpleado());
			ps.setString(2, usuario.getClave());
			ps.setString(3, usuario.getRol());
			ps.setString(4, usuario.getNombre());
			ps.setString(5, usuario.getApellido());
			ps.setString(6, usuario.getDireccion());
			ps.setString(7, usuario.getCedula());
			ps.setString(8, usuario.getCorreo());
			ps.setBinaryStream(9, fis, (int) fotoString.length());
			ps.execute();

			ps = con.prepareStatement("INSERT INTO telefonos VALUES (?, ?, ?, ?)");
			ps.setString(1, usuario.getCodigoDeEmpleado());
			ps.setString(2, usuario.getTelefono1());
			ps.setString(3, usuario.getTelefono2());
			ps.setString(4, usuario.getCelular());
			ps.execute();

			if (usuario.getEspecialidad() != null) {

				String codigoEspecialidad = "";

				ps = con.prepareStatement("SELECT codigo_especialidad FROM especialidades WHERE nombre = ?");
				ps.setString(1, usuario.getEspecialidad());
				rs = ps.executeQuery();

				if (rs.next()) {
					codigoEspecialidad = rs.getString("codigo_especialidad");
				}

				ps = con.prepareStatement("INSERT INTO medicos_especialidades VALUES (?, ?)");
				ps.setString(1, usuario.getCodigoDeEmpleado());
				ps.setString(2, codigoEspecialidad);
				ps.execute();
			}

			ps = con.prepareStatement("SELECT foto FROM usuarios WHERE CODIGO_EMPLEADO = ?");
			ps.setString(1, usuario.getCodigoDeEmpleado());
			rs = ps.executeQuery();

			if (rs.next()) {
				ImageIcon fotoBD = new ImageIcon(rs.getBytes("foto"));
				usuario.setFotoImagen(RedimensionadoraDeFotos.redimensionar(fotoBD));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public ArrayList<Usuario> listar() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT u.*, e.nombre AS 'especialidad', t.telefono1, t.telefono2, t.celular " +
					"FROM usuarios u LEFT OUTER JOIN medicos_especialidades me ON u.codigo_empleado  = me.codigo_empleado " +
					"LEFT OUTER JOIN especialidades e ON me.codigo_especialidad = e.codigo_especialidad LEFT OUTER JOIN " +
					"telefonos t ON u.codigo_empleado = t.codigo_empleado ORDER BY u.rol ");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Usuario u = new Usuario(rs.getString("rol"), rs.getString("clave"), 
						rs.getString("nombre"), rs.getString("apellido"), 
						rs.getString("direccion"), rs.getString("cedula"), 
						rs.getString("telefono1"), rs.getString("telefono2"), 
						rs.getString("cedula"), rs.getString("u.codigo_empleado"), 
						rs.getString("especialidad"), "", RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("foto"))));
				u.setCorreo(rs.getString("correo"));
				usuarios.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

	public ArrayList<Usuario> cargarUsuarios(String tipo) {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		if (tipo.equalsIgnoreCase("Todo")) {
			usuarios = listar();
			
		} else {
			try {
				ps = con.prepareStatement("select u.*, e.nombre as 'especialidad', t.telefono1, t.telefono2, t.celular " +
						"from usuarios u left outer join medicos_especialidades me on u.codigo_empleado = me.codigo_empleado " +
						"left outer join especialidades e on me.codigo_especialidad = e.codigo_especialidad " +
						"left outer join telefonos t on u.codigo_empleado = t.codigo_empleado where u.rol = ?");
				
				ps.setString(1, tipo);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Usuario u = new Usuario(rs.getString("rol"), rs.getString("clave"), 
							rs.getString("nombre"), rs.getString("apellido"), 
							rs.getString("direccion"), rs.getString("cedula"), 
							rs.getString("telefono1"), rs.getString("telefono2"), 
							rs.getString("cedula"), rs.getString("u.codigo_empleado"), 
							rs.getString("especialidad"), "", RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("foto"))));
					u.setCorreo(rs.getString("u.correo"));
					usuarios.add(u);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	}

	public void eliminar(String codigoEmpleado) {
		try {
			Monitora.testear("Eliminando usuario en ConsultaUsuario. código traído: " + codigoEmpleado);
			ps = con.prepareStatement("DELETE FROM usuarios WHERE codigo_empleado = ?");
			ps.setString(1, codigoEmpleado);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon actualizarImagen(String codigoDeEmpleado, String path) {
		ImageIcon retorno = new ImageIcon();
		
		File imagen = new File(path);
		try {
			FileInputStream fis = new FileInputStream(imagen);
			ps = con.prepareStatement("UPDATE usuarios SET foto = ? WHERE codigo_empleado = ?");
			ps.setBinaryStream(1, fis, (int) imagen.length());
			ps.setString(2, codigoDeEmpleado);
			ps.execute();
			
			ps = con.prepareStatement("SELECT foto FROM usuarios WHERE codigo_empleado = ?");
			ps.setString(1, codigoDeEmpleado);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				retorno = RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("foto")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void editar(String codigoDeEmpleado, String valor, int columna, String elemento) {

		try {
			if (columna < 7 || columna == 10) {

				ps = con.prepareStatement("UPDATE usuarios SET " + elemento
						+ " = ? WHERE codigo_empleado = ?");
				ps.setString(1, valor);
				ps.setString(2, codigoDeEmpleado);
				ps.execute();

			} else if (columna >= 7 && columna < 10) {

				ps = con.prepareStatement("UPDATE telefonos SET " + elemento
						+ " = ? WHERE codigo_empleado = ?");
				ps.setString(1, valor);
				ps.setString(2, codigoDeEmpleado);
				ps.execute();

			} else {
				ps = con.prepareStatement("UPDATE medicos_especialidades SET codigo_especialidad = " +
						"(SELECT codigo_especialidad FROM especialidades WHERE nombre = ?) " +
						"WHERE codigo_empleado = ?");
				ps.setString(1, valor);
				ps.setString(2, codigoDeEmpleado);
				ps.execute();
				System.out.println("Editando especialidad en ConsultaUsuario menol " + valor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
