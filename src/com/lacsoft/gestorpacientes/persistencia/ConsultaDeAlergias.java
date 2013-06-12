package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.entidades.Alergia;

public class ConsultaDeAlergias {
	private Connection conexionAlergia = Conexion.getInstancia()
			.crearConexion();
	private ArrayList<Alergia> alergias = new ArrayList<Alergia>();
	private PreparedStatement ps;
	private Statement st;
	private ResultSet resultados;

	public ArrayList<Alergia> getAlergias() {
		try {
			st = conexionAlergia.createStatement();
			resultados = st.executeQuery("select * from alergias");
			while (resultados.next()) {
				alergias.add(new Alergia(
						resultados.getString("codigo_alergia"), resultados
								.getString("nombre"), resultados
								.getString("descripcion")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alergias;
	}

	public Alergia agregarAlergia(Alergia alergia) {
		try {
			ps = conexionAlergia
					.prepareStatement("insert into alergias values(?,?,?)");
			ps.setString(2, alergia.getNombre());
			ps.setString(3, alergia.getDescripcion());
			ps.setString(1, alergia.getCodigo());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alergia;

	}

	public void actualizar(String campo, String valor, String parametro) {
		try {
			ps = conexionAlergia.prepareStatement("update alergias set "
					+ campo + "=? where codigo_alergia = ? ");
			ps.setString(1, valor);
			ps.setString(2, parametro);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void eliminar(String codigo) {
		try {
			ps = conexionAlergia
					.prepareStatement("delete from alergias where codigo_alergia = ?");
			ps.setString(1, codigo);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getCodigo() {

		ArrayList<String> retorno = new ArrayList<String>();
		try {
			st = conexionAlergia.createStatement();
			resultados = st.executeQuery("select codigo_alergia from alergias");
			if (resultados.next()) {
				retorno.add(resultados.getString("codigo_alergia"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean existeAlergia(String nombre) {
		boolean retorno = false;

		try {
			ps = conexionAlergia
					.prepareStatement("select * from alergias where nombre = ?");
			ps.setString(1, nombre);
			resultados = ps.executeQuery();
			if (resultados.last()) {
				retorno = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retorno;

	}

}
