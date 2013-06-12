package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.entidades.PacienteRecetas;
import com.lacsoft.gestorpacientes.entidades.PacienteSeleccionadoEnHistorial;
import com.lacsoft.gestorpacientes.entidades.PacienteVisitas;
import com.lacsoft.gestorpacientes.entidades.ResultadoDeLaboratorio;

public class ConsultaDeHistorialPacientes {

	private Connection conPacienteHistorial = Conexion.getInstancia()
			.crearConexion();
	private PreparedStatement ps;

	private ResultSet resultado;
	private ArrayList<PacienteVisitas> pacienteVisitas = new ArrayList<PacienteVisitas>();
	private ArrayList<ResultadoDeLaboratorio> pacienteResultados = new ArrayList<ResultadoDeLaboratorio>();
	private ArrayList<PacienteRecetas> pacienteRecetas = new ArrayList<PacienteRecetas>();

	public ArrayList<PacienteVisitas> getPacientesVisitas() {
		try {
			ps = conPacienteHistorial
					.prepareStatement("SELECT p.nombre,p.cedula,c.fecha,c.hora,c.causa FROM citas c "
							+ "join pacientes p on c.paciente_id = p.paciente_id "
							+ "where p.paciente_id=? order by c.fecha desc;");
			ps.setString(1, PacienteSeleccionadoEnHistorial.getInstacia().getPacienteId());
			resultado = ps.executeQuery();
			while (resultado.next()) {
				pacienteVisitas.add(new PacienteVisitas(resultado
						.getString("p.nombre"),
						resultado.getString("p.cedula"), resultado
								.getString("c.fecha"), resultado
								.getString("c.hora"), resultado
								.getString("c.causa")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pacienteVisitas;
	}

	public ArrayList<ResultadoDeLaboratorio> getPacienteResultados(String estado) {
		try {
			ps = conPacienteHistorial
					.prepareStatement("SELECT p.nombre, p.apellido,p.cedula,r.fecha,r.hora,r.resultado_id,"
							+ "r.estado,r.resultado,pl.nombre FROM resultados_de_pruebas_de_laboratorio r "
							+ "join pacientes p on p.paciente_id = r.paciente_id "
							+ "join pruebas_de_laboratorio pl on pl.codigo_prueba = r.codigo_prueba "
							+ "where r.estado=? and p.paciente_id=?");
			ps.setString(1, estado);
			ps.setString(2, PacienteSeleccionadoEnHistorial.getInstacia().getPacienteId());
			resultado = ps.executeQuery();
			while (resultado.next()) {
				pacienteResultados.add(new ResultadoDeLaboratorio(resultado
						.getString("r.fecha"),
						resultado.getString("pl.nombre"), resultado
								.getString("r.hora"), resultado
								.getString("r.estado"), resultado
								.getString("r.resultado"), resultado
								.getString("p.nombre")
								+ " "
								+ resultado.getString("p.apellido"), resultado
								.getString("p.cedula"), resultado
								.getString("r.resultado_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacienteResultados;

	}

	public ArrayList<PacienteRecetas> getPacienteRecetas() {
		try {
			ps = conPacienteHistorial
					.prepareStatement("SELECT p.nombre,p.cedula,pa.nombre,r.medicamentos,r.fecha FROM recetas r "
							+ "join pacientes p on p.paciente_id = r.paciente_id "
							+ "join padecimientos pa on pa.codigo_padecimiento = r.codigo_padecimiento where p.paciente_id=?");
			ps.setString(1, PacienteSeleccionadoEnHistorial.getInstacia().getPacienteId());
			resultado = ps.executeQuery();
			while (resultado.next()) {
				pacienteRecetas.add(new PacienteRecetas(resultado
						.getString("p.nombre"),
						resultado.getString("p.cedula"), resultado
								.getString("pa.nombre"), resultado
								.getString("r.medicamentos"), resultado
								.getString("r.fecha")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacienteRecetas;
	}

}
