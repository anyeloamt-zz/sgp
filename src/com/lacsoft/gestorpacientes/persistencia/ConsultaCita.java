package com.lacsoft.gestorpacientes.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lacsoft.gestorpacientes.entidades.Cita;
import com.lacsoft.gestorpacientes.entidades.UsuarioLogueado;

public class ConsultaCita {
	private Connection con = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet rs;
	
	/*
	 * Para sacar sólo las citas del usuario que está logueado 
	 * en caso de que éste sea un médico.
	 */
	private UsuarioLogueado usuarioLogueado = UsuarioLogueado.getInstancia();
	
	/**
	 * Retorna un ArrayList con todas las citas si el 
	 * <b>com.primetech.entidades.UsuarioLogueado</b>
	 * es un <b>Administrador</b> o <b>Asistente</b>, si es un <b>Médico</b>, 
	 * sólo retorna las citas pertenecientes a él.
	 */
	public ArrayList<Cita> listar() {
		ArrayList<Cita> citas = new ArrayList<>();
		
		try {
			
			if (usuarioLogueado.getRol().equals("Médico")) {
				ps = con.prepareStatement("SELECT c.*, p.nombre as paciente, " +
						"CONCAT(u.nombre,' ', u.apellido) AS medico, u.cedula as " +
						"'cedula medico', p.cedula FROM citas c " +
						"JOIN usuarios u ON u.codigo_empleado = c.codigo_empleado " +
						"JOIN pacientes p ON p.paciente_id = c.paciente_id " +
						"WHERE u.codigo_empleado = ? ORDER BY u.codigo_empleado");
				
				ps.setString(1, usuarioLogueado.getCodigoDeEmpleado());
			} else {
				ps = con.prepareStatement("SELECT c.*, p.nombre AS paciente, " +
						"CONCAT(u.nombre,' ', u.apellido) AS medico, u.cedula AS " +
						"'cedula medico', p.cedula FROM citas c " +
						"JOIN usuarios u ON u.codigo_empleado = c.codigo_empleado " +
						"JOIN pacientes p ON p.paciente_id = c.paciente_id ORDER BY u.codigo_empleado");
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				citas.add(new Cita(rs.getString("c.cita_id"),
						rs.getString("c.codigo_empleado"),
						rs.getString("c.paciente_id"),
						rs.getString("medico"),
						rs.getString("cedula medico"),
						rs.getString("paciente"),
						rs.getString("p.cedula"),
						rs.getString("c.fecha"), 
						rs.getString("c.hora"), 
						rs.getString("c.causa")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citas;
	}
	 
	public ArrayList<Cita> listarCitasHoy() {
		ArrayList<Cita> citas = new ArrayList<>();
		try {
			ps = con.prepareStatement("SELECT c.*, p.nombre as paciente, " +
					"CONCAT(u.nombre,' ', u.apellido) AS medico, u.cedula as " +
					"'cedula medico', p.cedula FROM citas c " +
					"JOIN usuarios u ON u.codigo_empleado = c.codigo_empleado " +
					"JOIN pacientes p ON p.paciente_id = c.paciente_id " +
					"WHERE u.codigo_empleado = ? " +
					"AND fecha = (DATE_FORMAT(CURDATE(), '%Y-%m-%d'))");
			
			ps.setString(1, usuarioLogueado.getCodigoDeEmpleado());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				citas.add(new Cita(rs.getString("c.cita_id"),
						rs.getString("c.codigo_empleado"),
						rs.getString("c.paciente_id"),
						rs.getString("medico"),
						rs.getString("cedula medico"),
						rs.getString("paciente"),
						rs.getString("p.cedula"),
						rs.getString("c.fecha"), 
						rs.getString("c.hora"), 
						rs.getString("c.causa")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citas;
	}
	
	/**
	 * Agrega una nueva cita a la base de datos.
	 * @param cita La cita que se quiere agregar
	 * @return <b>cita</b> La misma cita que agregó, pero con el id seteado por la bd.
	 */
	public Cita agregar(Cita cita) {
		try {
			
			ps = con.prepareStatement("INSERT INTO citas VALUES (NULL, ?, ?, ?, ?, ?)");
			ps.setString(1, cita.getCodigoEmpleado());
			ps.setInt(2, Integer.parseInt(cita.getIdPaciente()));
			ps.setString(3, cita.getFecha());
			ps.setString(4, cita.getHora());
			ps.setString(5, cita.getCausa());
			ps.execute();
			
			ps = con.prepareStatement("SELECT cita_id FROM citas");
			rs = ps.executeQuery();
			
			if (rs.last()) {
				cita.setIdCita(rs.getString("cita_id"));
				System.out.println(cita.getCedulaPaciente());;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cita;
	}

	public void eliminar(String idCita) {
		try {
			ps = con.prepareStatement("DELETE FROM citas WHERE cita_id = ?");
			ps.setInt(1, Integer.parseInt(idCita));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * Editar datos en la base de datos.
	 * @param elemento Campo que se va a editar en la base de datos.
	 * @param valor El nuevo valor.
	 * @param idCita Cita que se va a actualizar.
	 * @param esIdPaciente <br> Si es el id del paciente que se va a editar entonces <br>
	 * se pasa true para parsearlo ya que en la BD es de tipo INTEGER, <br> de lo contrario
	 * se pasa false.
	 */
	public void editar(String elemento, String valor, String idCita, boolean esIdPaciente) {
		try {
			ps = con.prepareStatement("UPDATE citas SET " + elemento + " = ? WHERE cita_id = ?");
			// Si es un paciente.
			if (esIdPaciente) {
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
