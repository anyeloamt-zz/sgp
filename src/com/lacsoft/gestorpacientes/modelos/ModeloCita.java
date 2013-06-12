package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Cita;
import com.lacsoft.gestorpacientes.persistencia.ConsultaCita;

public class ModeloCita extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] ENCABEZADOS = { "Nombre de médico", "Cédula de médico", 
		"Nombre de paciente","Cédula de paciente", "Fecha", "Hora", "Causa" };
	private ArrayList<Cita> citas;
	
	private ConsultaCita conCita;
	
	private static ModeloCita instancia;

	public static synchronized ModeloCita getInstancia() {
		return instancia = (instancia == null) ? new ModeloCita() : instancia;
	}
	
	private ModeloCita(){
		conCita = new ConsultaCita();
		citas = conCita.listar();
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
			retorno = cita.getNombreMedico();
			break;
		case 1:
			retorno = cita.getCedulaMedico();
			break;
		case 2:
			retorno = cita.getNombrePaciente();
			break;
		case 3:
			retorno = cita.getCedulaPaciente();
			break;
		case 4:
			retorno = cita.getFecha();
			break;
		case 5:
			retorno = cita.getHora();
			break;
		case 6:
			retorno = cita.getCausa();
			break;
		}
		return retorno;
	}
	
	public void setValueAt(Object aValue, int fila, int columna, String nombrePaciente, 
			String cedulaPaciente, String idPaciente, String nombreMedico, String cedulaMedico, String codigoEmpleado) {
		
		String valor = (String) aValue;
		Cita cita = citas.get(fila);
		
		switch(columna) {
		case 0:
			cita.setNombreMedico(valor);
			cita.setCedulaMedico(cedulaMedico);
			conCita.editar("codigo_empleado", codigoEmpleado, cita.getIdCita(), false);
			break;
		case 2:
			cita.setNombrePaciente(valor);
			cita.setCedulaPaciente(cedulaPaciente);
			conCita.editar("paciente_id", idPaciente, cita.getIdCita(), true);
			break;
		case 4:
			cita.setFecha(valor);
			conCita.editar("fecha", valor, cita.getIdCita(), false);
			break;
		case 5:
			cita.setHora(valor);
			conCita.editar("hora", valor, cita.getIdCita(), false);
			break;
		case 6:
			cita.setCausa(valor);
			conCita.editar("causa", valor, cita.getIdCita(), false);
			break;
		}
		fireTableDataChanged();
	}
	
	/*
	 * Retorna true se agregó la cita
	 */
	public boolean agregar(Cita cita) {
		if (validar(cita)) {
			citas.add(conCita.agregar(cita));
			fireTableDataChanged();
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Retorna true si no existe
	 */
	public boolean validar(Cita cita) {
		boolean retorno = true;
		for (Cita c: citas) {
			if (c.getCodigoEmpleado().equals(cita.getCodigoEmpleado()) 
					&& c.getFecha().equals(cita.getFecha()) 
					&& c.getHora().equals(cita.getHora())) {
				retorno = false;
			}
		}
		return retorno;
	}

	public void eliminar(int fila) {
		conCita.eliminar(citas.get(fila).getIdCita());
		citas.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}

	public void editar(int fila, String nombrePaciente, String cedulaPaciente, 
			String idPaciente, String nombreMedico, String cedulaMedico, String codigoEmpleado,
			String fecha, String hora, String causa) {
		
		this.setValueAt(nombreMedico, fila, 0, nombrePaciente, cedulaPaciente, idPaciente, nombreMedico, cedulaMedico, codigoEmpleado);
		this.setValueAt(nombrePaciente, fila, 2, nombrePaciente, cedulaPaciente, idPaciente, nombreMedico, cedulaMedico, codigoEmpleado);
		this.setValueAt(fecha, fila, 4, nombrePaciente, cedulaPaciente, idPaciente, nombreMedico, cedulaMedico, codigoEmpleado);
		this.setValueAt(hora, fila, 5, nombrePaciente, cedulaPaciente, idPaciente, nombreMedico, cedulaMedico, codigoEmpleado);
		this.setValueAt(causa, fila, 6, nombrePaciente, cedulaPaciente, idPaciente, nombreMedico, cedulaMedico, codigoEmpleado);
	}

	public String getIdPaciente(int fila) {
		return citas.get(fila).getIdPaciente();
	}

	public String getCodigoEmpleado(int fila) {
		return citas.get(fila).getCodigoEmpleado();
	}
}