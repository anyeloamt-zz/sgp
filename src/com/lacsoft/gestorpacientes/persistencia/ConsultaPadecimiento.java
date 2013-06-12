package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.Monitora;
import com.lacsoft.gestorpacientes.entidades.Padecimiento;

public class ConsultaPadecimiento {
	private Connection con = Conexion.getInstancia().crearConexion();
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<Padecimiento> listar() {
		ArrayList<Padecimiento> padecimientos = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT * FROM padecimientos");
			rs = ps.executeQuery();
			while(rs.next()) {
				padecimientos.add(new Padecimiento(
						rs.getString("CODIGO_PADECIMIENTO"),
						rs.getString("NOMBRE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return padecimientos;
	}
	
	public ArrayList<String> listarCodigos() {
		ArrayList<String> codigos = new ArrayList<>();
		
		try {
			ps = con.prepareStatement("SELECT CODIGO_PADECIMIENTO FROM PADECIMIENTOS");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				codigos.add(rs.getString("CODIGO_PADECIMIENTO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return codigos;
	}
	
	public void agregar(Padecimiento padecimiento) {
		try {
			Monitora.testear("Agregando padecimiento en" + this.getClass().getName());
			ps = con.prepareStatement("INSERT INTO PADECIMIENTOS VALUES (?, ?)");
			ps.setString(1, padecimiento.getCodigo());
			ps.setString(2, padecimiento.getNombre());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(String codigo, String valorAsignar) {
		
		try {
			ps = con.prepareStatement("UPDATE PADECIMIENTOS SET NOMBRE = ? WHERE CODIGO_PADECIMIENTO = ?");
			ps.setString(1, valorAsignar);
			ps.setString(2, codigo);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminar(String codigo) {
		try {
			ps = con.prepareStatement("DELETE FROM PADECIMIENTOS WHERE CODIGO_PADECIMIENTO = ?");
			ps.setString(1, codigo);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
