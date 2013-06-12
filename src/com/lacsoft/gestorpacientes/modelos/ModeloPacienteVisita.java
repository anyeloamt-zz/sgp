package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.PacienteVisitas;
import com.lacsoft.gestorpacientes.persistencia.ConsultaDeHistorialPacientes;

public class ModeloPacienteVisita extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<PacienteVisitas> visitas = new ArrayList<PacienteVisitas>();
	private String[] encabezados = { "Nombre", "Cedula", "Fecha", "Hora",
			"Causa" };
	private static ModeloPacienteVisita instancia;
	private ConsultaDeHistorialPacientes conVisitas = new ConsultaDeHistorialPacientes();

	public static synchronized ModeloPacienteVisita getInstancia() {
		return instancia = (instancia == null) ? instancia = new ModeloPacienteVisita()
				: instancia;
	}

	private ModeloPacienteVisita() {
		visitas = conVisitas.getPacientesVisitas();
	}

	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return visitas.size();
	}

	public Object getValueAt(int row, int column) {
		PacienteVisitas visita = visitas.get(row);
		String registro = "";
		switch (column) {
		case 0:
			registro = visita.getNombrePaciente();
			break;
		case 1:
			registro = visita.getCedulaPaciente();
			break;
		case 2:
			registro = visita.getFechaCita();
			break;
		case 3:
			registro = visita.getHoraCita();
			break;
		case 4:
			registro = visita.getCausaCita();
			break;

		}

		return registro;
	}

	public String getColumnName(int i) {
		return encabezados[i];
	}

	public void setInstanciaNull() {
		instancia = null;
	}

}
