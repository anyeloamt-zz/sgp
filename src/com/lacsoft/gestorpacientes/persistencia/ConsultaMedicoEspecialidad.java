package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.lacsoft.gestorpacientes.entidades.Medico;
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;

public class ConsultaMedicoEspecialidad {
	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Medico> listar() {
		ArrayList<Medico> medicos = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT u.codigo_empleado, u.nombre, u.apellido, u.cedula, " +
					"u.direccion, u.foto, t.telefono1, t.telefono2, t.celular, es.nombre AS 'especialidad' FROM " +
					"usuarios u join telefonos t ON u.codigo_empleado = t.codigo_empleado " +
					"JOIN medicos_especialidades me ON u.codigo_empleado = me.codigo_empleado " +
					"JOIN especialidades es ON me.codigo_especialidad = es.codigo_especialidad");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				medicos.add(new Medico(rs.getString("u.codigo_empleado"), 
						rs.getString("u.nombre"), rs.getString("u.apellido"), rs.getString("u.cedula"), 
						rs.getString("u.direccion"), rs.getString("t.telefono1"), 
						rs.getString("t.telefono2"), rs.getString("t.celular"), rs.getString("especialidad"),
						RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("u.foto")))));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicos;
	}
	
	public ArrayList<Medico> listarPorEspecialidad(String codigoEspecialidad) {
		ArrayList<Medico> medicos = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT u.codigo_empleado, u.nombre, "
					+ "u.apellido, u.cedula, u.direccion, u.foto, t.telefono1, t.telefono2,  "
					+ "t.celular, es.nombre AS 'especialidad' FROM usuarios u JOIN telefonos t ON u.codigo_empleado = "
					+ "t.codigo_empleado JOIN medicos_especialidades me ON u.codigo_empleado = "
					+ "me.codigo_empleado JOIN especialidades es ON me.codigo_especialidad = "
					+ "es.codigo_especialidad WHERE es.nombre = ?");
			
			ps.setString(1, (codigoEspecialidad));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				medicos.add(new Medico(rs.getString("u.codigo_empleado"), 
						rs.getString("u.nombre"), rs.getString("u.apellido"), rs.getString("u.cedula"),
						rs.getString("u.direccion"), rs.getString("t.telefono1"), 
						rs.getString("t.telefono2"), rs.getString("t.celular"), rs.getString("especialidad"),
						RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("u.foto")))));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicos;
	}
}
