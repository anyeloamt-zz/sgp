package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Cita;
import com.lacsoft.gestorpacientes.persistencia.ConsultaCita;

public class ModeloCitasHoy extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] ENCABEZADOS = {"Nombre de paciente","Cédula de paciente", "Hora", "Causa" };
	private ArrayList<Cita> citas;
	
	private ConsultaCita conCita;
	
	private static ModeloCitasHoy instancia;
	
	public static synchronized ModeloCitasHoy getInstancia() {
		return instancia = (instancia == null) ? new ModeloCitasHoy() : instancia;
	}
	
	private ModeloCitasHoy(){
		conCita = new ConsultaCita();
		citas = conCita.listarCitasHoy();
	}
	
	@Override
	public int getColumnCount() {
		return ENCABEZADOS.length;
	}

	@Override
	public int getRowCount() {
		return citas.size();
	}

	@Override
	public String getColumnName(int column) {
		return ENCABEZADOS[column];
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		String retorno = "Mami Chulas";
		Cita cita = citas.get(fila);

		switch (columna) {
		case 0:
			retorno = cita.getNombrePaciente();
			break;
		case 1:
			retorno = cita.getCedulaPaciente();
			break;
		case 2:
			retorno = cita.getHora();
			break;
		case 3:
			retorno = cita.getCausa();
			break;
		}
		return retorno;
	}

}
