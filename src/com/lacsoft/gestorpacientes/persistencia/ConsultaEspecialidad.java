package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.entidades.Especialidad;

public class ConsultaEspecialidad {

	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Especialidad> listar() {
		ArrayList<Especialidad> especialidades = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT * FROM ESPECIALIDADES");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				especialidades.add(new Especialidad(rs.getString("CODIGO_ESPECIALIDAD"), rs.getString("NOMBRE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especialidades;
	}
	
	public ArrayList<String> listarCodigos() {
		ArrayList<String> codigos = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT CODIGO_ESPECIALIDAD FROM ESPECIALIDADES");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				codigos.add(rs.getString("CODIGO_ESPECIALIDAD"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigos;
	}

	public void agregar(Especialidad especialidad) {
		try {
			ps = con.prepareStatement("INSERT INTO ESPECIALIDADES VALUES (?, ?)");
			ps.setString(1, especialidad.getCodigo());
			ps.setString(2, especialidad.getNombre());

			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminar(String codigo) {
		try {
			ps = con.prepareStatement("DELETE FROM ESPECIALIDADES WHERE CODIGO_ESPECIALIDAD = ?");
			ps.setString(1, codigo);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(String codigo, String valor) {
		try {
			ps = con.prepareStatement("UPDATE ESPECIALIDADES SET NOMBRE = ? WHERE CODIGO_ESPECIALIDAD = ?");
			ps.setString(1, valor);
			ps.setString(2, codigo);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public ArrayList<String> listarEspecialidades() {
//
//		return null;
//	}

}
