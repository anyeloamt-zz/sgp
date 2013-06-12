package com.lacsoft.gestorpacientes.persistencia;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.lacsoft.gestorpacientes.entidades.Alergia;
import com.lacsoft.gestorpacientes.entidades.Paciente;

public class ConsultaDePacientes {
	private Connection conPacientes = Conexion.getInstancia().crearConexion();
	private PreparedStatement ps;
	private ResultSet resultado;
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private ArrayList<Alergia> alergias ;
	private ImageIcon imagen;

	public ArrayList<Paciente> listarPacientes() {
		try {
			ps = conPacientes.prepareStatement("select * from pacientes");
			resultado = ps.executeQuery();
			while (resultado.next()) {
				byte[] imagenData = resultado.getBytes("foto");
				Image i = new ImageIcon(imagenData).getImage();
				Image escala = i.getScaledInstance(252, 210,
						Image.SCALE_DEFAULT);
				String fuma = (resultado.getString("fumador").equals("0")) ? "No"
						: "Si";

				String alergico = (resultado.getString("alergico").equals("0")) ? "No"
						: "Si";

				pacientes.add(new Paciente(resultado.getString("nombre"),
						resultado.getString("apellido"), resultado
								.getString("telefono"), resultado
								.getString("cedula"), resultado
								.getString("fecha_nacimiento"), fuma, alergico,
						new ImageIcon(escala), resultado
								.getString("paciente_id"), resultado
								.getString("direccion")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacientes;
	}

	public void actualizar(String campo, String valor, String parametro) {
		try {
			ps = conPacientes.prepareStatement("update pacientes set " + campo
					+ " =? where paciente_id = ? ");
			ps.setString(1, valor);
			ps.setString(2, parametro);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Paciente agregarPaciente(Paciente paciente,
			ArrayList<String> alergias) {
		try {
			File file = new File(paciente.getFotoString());
			FileInputStream fis = new FileInputStream(file);

			ps = conPacientes
					.prepareStatement("insert into pacientes values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, paciente.getNombre());
			ps.setString(3, paciente.getApellido());
			ps.setString(4, paciente.getFechaNacimiento());
			ps.setString(5, paciente.getCedula());
			ps.setString(6, paciente.getDireccion());
			ps.setString(7, paciente.getTelefono());
			ps.setString(8, paciente.getFumador());
			ps.setString(9, paciente.getAlergico());
			ps.setBinaryStream(10, fis, (int) file.length());
			ps.execute();

			ps = conPacientes
					.prepareStatement("select foto, paciente_id from pacientes where cedula = ?");
			ps.setString(1, paciente.getCedula());
			resultado = ps.executeQuery();

			if (resultado.next()) {
				byte[] imagenData = resultado.getBytes("foto");
				Image i = new ImageIcon(imagenData).getImage();
				Image escala = i.getScaledInstance(252, 210,
						Image.SCALE_DEFAULT);
				paciente.setFotoImagen(new ImageIcon(escala));
				int id_paciente = resultado.getInt("paciente_id");
				if (paciente.getAlergico().equals("1")) {
					for (String alergia : alergias) {
						ps = conPacientes
								.prepareStatement("insert into pacientes_alergias values(?,?)");
						ps.setString(1, String.valueOf(id_paciente));
						ps.setString(2, alergia);
						ps.execute();

					}

				}
				String fuma = (paciente.getFumador().equals("0")) ? "No" : "Si";
				String alergico = (paciente.getAlergico().equals("0")) ? "No"
						: "Si";
				paciente.setFumador(fuma);
				paciente.setAlergico(alergico);
				paciente.setId(String.valueOf(id_paciente));

			}

		} catch (SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return paciente;
	}

	public void eliminarPaciente(String id) {
		try {
			ps = conPacientes
					.prepareStatement("delete from pacientes where paciente_id =?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ImageIcon actualizarImagen(String id, String path) {

		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ps = conPacientes
					.prepareStatement("update pacientes set foto = ? where paciente_id=?");
			ps.setBinaryStream(1, fis, (int) file.length());
			ps.setString(2, id);
			ps.execute();
			Image i = new ImageIcon(file.getAbsolutePath()).getImage();
			Image escala = i.getScaledInstance(222, 143, Image.SCALE_DEFAULT);
			imagen = new ImageIcon(escala);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return imagen;
	}

	public ArrayList<Alergia> getAlergiasPacientes(String paciente_id) {
		try {
			alergias = new ArrayList<Alergia>();
			ps = conPacientes
					.prepareStatement("SELECT a.nombre,a.codigo_alergia FROM alergias a "
							+ "join pacientes_alergias pa on a.codigo_alergia = pa.codigo_alergia "
							+ "join pacientes p on p.paciente_id = pa.paciente_id where p.paciente_id = ?;");
			ps.setString(1, paciente_id);
			resultado = ps.executeQuery();
			while (resultado.next()) {
				alergias.add(new Alergia(resultado
						.getString("a.codigo_alergia"), resultado
						.getString("a.nombre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alergias;
	}

}
