package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.entidades.Prueba;

public class ConsultaPrueba {

	private PreparedStatement ps;
	private ResultSet rs;
	private Connection con = Conexion.getInstancia().crearConexion();

	public ArrayList<Prueba> listar() {

		ArrayList<Prueba> pruebas = new ArrayList<>();

		try {

			ps = con.prepareStatement("select * from pruebas_de_laboratorio");
			rs = ps.executeQuery();
			while (rs.next()) {
				pruebas.add(new Prueba(rs.getString("codigo_prueba"), rs
						.getString("nombre")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pruebas;
	}

	public ArrayList<String> listarCodigos() {

		ArrayList<String> codigos = new ArrayList<>();

		try {

			ps = con.prepareStatement("select codigo_prueba from pruebas_de_laboratorio");
			rs = ps.executeQuery();
			while (rs.next()) {
				codigos.add(rs.getString("codigo_prueba"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return codigos;
	}

	public void agregar(Prueba prueba) {

		try {
			ps = con.prepareStatement("INSERT INTO pruebas_de_laboratorio values(?,?)");
			ps.setString(1, prueba.getCodigo());
			ps.setString(2, prueba.getNombre());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void eliminar(String codigo) {

		try {
			ps = con.prepareStatement("DELETE FROM pruebas_de_laboratorio WHERE codigo_prueba = ?");
			ps.setString(1, codigo);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void editar(String codigo, String valor){
		
		try {
			ps =  con.prepareStatement("UPDATE pruebas_de_laboratorio SET nombre=? where codigo_prueba=?");
			ps.setString(1, valor);
			ps.setString(2, codigo);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

