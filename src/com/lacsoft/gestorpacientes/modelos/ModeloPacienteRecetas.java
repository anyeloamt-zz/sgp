package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.PacienteRecetas;
import com.lacsoft.gestorpacientes.persistencia.ConsultaDeHistorialPacientes;

public class ModeloPacienteRecetas extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<PacienteRecetas> pacienteRecetas = new ArrayList<PacienteRecetas>();
	private String[] encabezados = { "Nombre del paciente", "Cédula",
			"Nombre del padecimiento", "Medicamentos", "fecha"};
	
	private static ModeloPacienteRecetas instancia;
	private ConsultaDeHistorialPacientes conHistorial = new ConsultaDeHistorialPacientes();

	public static synchronized ModeloPacienteRecetas getInstancia() {
		return instancia = (instancia == null) ? instancia = new ModeloPacienteRecetas()
				: instancia;
	}

	private ModeloPacienteRecetas() {
		pacienteRecetas = conHistorial.getPacienteRecetas();
	}

	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return pacienteRecetas.size();
	}

	public String getColumnName(int column) {
		return encabezados[column];
	}

	

	public Object getValueAt(int row, int column) {
		PacienteRecetas receta = pacienteRecetas.get(row);
		String retorno = "";
		switch (column) {
		case 0:
			retorno = receta.getNombrePaciente();
			break;
		case 1:
			retorno = receta.getCedulaPaciente();
			break;
		case 2:
			retorno = receta.getNombrePadecimientos();
			break;
		case 3:
			retorno = receta.getMediciamentoPacientes();
			break;
		case 4:
			retorno = receta.getFechaReceta();
			break;
		

		}
		return retorno;
	}
	
	public void setInstanciaNull() {
		instancia = null;
	}

}
