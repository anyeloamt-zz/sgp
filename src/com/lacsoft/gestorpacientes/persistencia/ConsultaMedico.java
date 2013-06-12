package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.lacsoft.gestorpacientes.entidades.Medico;
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;

public class ConsultaMedico {
	
	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Medico> listar() {
		ArrayList<Medico> medicos = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("select u.codigo_empleado, u.clave, u.nombre, " +
					"u.apellido, u.direccion, u.cedula, u.correo, u.foto, t.telefono1, " +
					"t.telefono2, t.celular, e.nombre especialidad from usuarios u join " +
					"medicos_especialidades me on u.codigo_empleado = me.codigo_empleado " +
					"join especialidades e on e.codigo_especialidad = me.codigo_especialidad " +
					"join telefonos t on u.codigo_empleado = t.codigo_empleado where u.rol = 'medico';");
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				medicos.add(new Medico(rs.getString("u.codigo_empleado"), rs.getString("u.nombre"), 
						rs.getString("u.apellido"), rs.getString("u.cedula"), rs.getString("u.direccion"), 
						rs.getString("t.telefono1"), rs.getString("t.telefono2"), 
						rs.getString("t.celular"), rs.getString("especialidad"), 
						RedimensionadoraDeFotos.redimensionar(new ImageIcon(rs.getBytes("u.foto")))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicos;
	}

}
