package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.entidades.ResultadoDeLaboratorio;

public class ConsultasDeResultadosLaboratorios {
	private Connection conResultados = Conexion.getInstancia().crearConexion();
	private ArrayList<ResultadoDeLaboratorio> resultados = new ArrayList<ResultadoDeLaboratorio>();
	private PreparedStatement ps;
	private ResultSet rs;

	public ArrayList<ResultadoDeLaboratorio> listarResultados(String estado) {
		try {
			ps = conResultados
					.prepareStatement("SELECT p.nombre, p.apellido,p.cedula,r.fecha,r.hora,r.resultado_id,"
							+ "r.estado,r.resultado,pl.nombre FROM resultados_de_pruebas_de_laboratorio r "
							+ "join pacientes p on p.paciente_id = r.paciente_id "
							+ "join pruebas_de_laboratorio pl on pl.codigo_prueba = r.codigo_prueba "
							+ "where r.estado=?");
			ps.setString(1, estado);
			rs = ps.executeQuery();
			while (rs.next()) {
				resultados.add(new ResultadoDeLaboratorio(rs
						.getString("r.fecha"), rs.getString("pl.nombre"), rs
						.getString("r.hora"), rs.getString("r.estado"), rs
						.getString("r.resultado"), rs.getString("p.nombre")
						+ " " + rs.getString("p.apellido"), rs
						.getString("p.cedula"), rs.getString("r.resultado_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultados;

	}
	
	

	public ResultadoDeLaboratorio agregarResultado(
			ResultadoDeLaboratorio resultado) {
		try {
			ps = conResultados
					.prepareStatement("insert into resultados_de_pruebas_de_laboratorio "
							+ "values(?,?,?,?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, resultado.getCodigoPrueba());
			ps.setString(3, resultado.getFecha());
			ps.setString(4, resultado.getHora());
			ps.setString(5, resultado.getEstado());
			ps.setString(6, resultado.getResultado());
			ps.setString(7, resultado.getIdPaciente());
			ps.execute();

			ps = conResultados
					.prepareStatement("select nombre,apellido,cedula from pacientes where paciente_id = ? ");
			ps.setString(1, resultado.getIdPaciente());
			rs = ps.executeQuery();
			if (rs.next()) {
				resultado.setNombrePaciente(rs.getString("nombre") + " "
						+ rs.getString("apellido"));
				resultado.setCedulaPaciente(rs.getString("cedula"));
			}

			ps = conResultados
					.prepareStatement("select nombre from pruebas_de_laboratorio where codigo_prueba = ? ");
			ps.setString(1, resultado.getCodigoPrueba());
			rs = ps.executeQuery();
			if (rs.next()) {
				resultado.setNombrePrueba(rs.getString("nombre"));
			}

			ps = conResultados
					.prepareStatement("select resultado_id from resultados_de_pruebas_de_laboratorio "
							+ "where paciente_id = ? and fecha = ? and hora = ? and codigo_prueba = ?");
			ps.setString(1, resultado.getIdPaciente());
			ps.setString(2, resultado.getFecha());
			ps.setString(3, resultado.getHora());
			ps.setString(4, resultado.getCodigoPrueba());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.last()) {
                    resultado.setIdResultado(rs.getString("resultado_id"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	public void actualizar(String campo, String valor, String parametro) {
		try {
			ps = conResultados
					.prepareStatement("update resultados_de_pruebas_de_laboratorio set "
							+ campo + "=? where resultado_id = ? ");
			ps.setString(1, valor);
			ps.setString(2, parametro);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
