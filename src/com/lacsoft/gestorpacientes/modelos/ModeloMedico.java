package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Medico;
import com.lacsoft.gestorpacientes.persistencia.ConsultaMedico;

public class ModeloMedico extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Medico> medicos;
	private static final String[] ENCABEZADOS = {"Nombre", "Apellido", 
		"Dirección", "Teléfono 1", "Teléfono 2", "Celular", "Especialidad"};
	
	private ConsultaMedico conMedico;
	
	private static ModeloMedico instancia;
	
	private ModeloMedico() {
		conMedico = new ConsultaMedico();
		medicos = conMedico.listar();
	}
	
	public static ModeloMedico getInstancia() {
		return instancia = (instancia == null) ? new ModeloMedico() : instancia;
	}

	public Medico getPacienteSeleccionado(int fila) {
		return medicos.get(fila);
	}
	
	@Override
	public String getColumnName(int columna) {
		return ENCABEZADOS[columna];
	}

	@Override
	public int getColumnCount() {
		return ENCABEZADOS.length;
	}

	@Override
	public int getRowCount() {
		return medicos.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Medico medico = medicos.get(fila);
		
		switch (columna) {
		case 0: 
			return medico.getNombre();
		case 1:
			return medico.getApellido();
		case 2:
			return medico.getDireccion();
		case 3: 
			return medico.getTelefono1();
		case 4: 
			return medico.getTelefono2();
		case 5: 
			return medico.getCelular();
		case 6: 
			return medico.getEspecialidad();
		default:
			return "";
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Icon getImagenMedico(int fila) {
		return medicos.get(fila).getFoto();
	}

}
