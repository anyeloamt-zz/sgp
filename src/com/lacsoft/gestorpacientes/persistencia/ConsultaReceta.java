package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.Monitora;
import com.lacsoft.gestorpacientes.entidades.Receta;

public class ConsultaReceta {
	
	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Receta> listar() {
		ArrayList<Receta> recetas = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT recetas.*, padecimientos.nombre AS 'padecimiento', " +
					"CONCAT(pacientes.nombre, ' ', pacientes.apellido) 'paciente' FROM recetas JOIN padecimientos ON " +
					"recetas.codigo_padecimiento = padecimientos.codigo_padecimiento " +
					"JOIN pacientes ON pacientes.paciente_id = recetas.paciente_id;");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				recetas.add(new Receta(rs.getString("recetas.receta_id"), rs.getString("recetas.fecha"), 
						rs.getString("paciente"), rs.getString("recetas.paciente_id"), rs.getString("padecimiento"), 
						rs.getString("recetas.codigo_padecimiento"), rs.getString("recetas.medicamentos")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recetas;
	}
	
	public Receta agregar(String idPaciente, String codigoPadecimiento, Receta receta) {
		
		try {
			ps = con.prepareStatement("INSERT INTO recetas VALUES (NULL, ?, ?, ?, CURDATE())");
			ps.setInt(1, Integer.parseInt(idPaciente));
			ps.setString(2, codigoPadecimiento);
			ps.setString(3, receta.getMedicamentos());
			
			ps.execute();
			Monitora.testear("Agregando receta en ConsultaReceta");
			
			rs = ps.executeQuery("SELECT recetas.*, padecimientos.nombre AS 'padecimiento', " +
					"CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'paciente' FROM recetas JOIN padecimientos ON " +
					"recetas.codigo_padecimiento = padecimientos.codigo_padecimiento " +
					"JOIN pacientes ON pacientes.paciente_id = recetas.paciente_id;");
			
			if (rs.last()) {
				receta = new Receta(rs.getString("recetas.receta_id"), rs.getString("recetas.fecha"), 
						rs.getString("paciente"), rs.getString("recetas.paciente_id"), rs.getString("padecimiento"), 
						rs.getString("recetas.codigo_padecimiento"), rs.getString("recetas.medicamentos"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return receta;
	}

	public void eliminar(String id) {
		try {
			ps = con.prepareStatement("DELETE FROM recetas WHERE receta_id = ?");
			ps.setInt(1, Integer.parseInt(id));
			ps.execute();
			Monitora.testear("Eliminando receta en ConsultaReceta");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(String elemento, String valor, String idCita) {
		try {
			ps = con.prepareStatement("UPDATE recetas SET " + elemento + " = ? WHERE receta_id = ?");
			if (elemento.equals("paciente_id")) {
				ps.setInt(1, Integer.parseInt(valor));
			} else {
				ps.setString(1, valor);
			}
			ps.setInt(2, Integer.parseInt(idCita));
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
