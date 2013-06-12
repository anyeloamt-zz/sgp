package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Medico;
import com.lacsoft.gestorpacientes.persistencia.ConsultaMedicoEspecialidad;

public class ModeloMedicosPorEspecialidad extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] encabezados = {"Nombre", "Apellido", "Dirección", "Teléfono 1", "Teléfono 2", "Celular", "Especialidad"};
	private ArrayList<Medico> medicos;
	
	private ConsultaMedicoEspecialidad conMedico;
	
	private static ModeloMedicosPorEspecialidad instancia;
	
	public static synchronized ModeloMedicosPorEspecialidad getInstancia() {
		return instancia = (instancia == null) ? new ModeloMedicosPorEspecialidad() : instancia;
	}
	
	private ModeloMedicosPorEspecialidad() {
		conMedico = new ConsultaMedicoEspecialidad();
		medicos = conMedico.listar();
	}
	
	@Override
	public int getColumnCount() {
		return encabezados.length;
	}

	@Override
	public int getRowCount() {
		return medicos.size();
	}
	
	public String getColumnName(int columna) {
		return encabezados[columna];
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		String retorno = "";
		
		if (fila < medicos.size()) {
			
			Medico medico = medicos.get(fila);
			
			switch (columna) {
			case 0:
				retorno = medico.getNombre();
				break;
			case 1:
				retorno = medico.getApellido();
				break;
			case 2:
				retorno = medico.getDireccion();
				break;
			case 3:
				retorno = medico.getTelefono1();
				break;
			case 4:
				retorno = medico.getTelefono2();
				break;
			case 5:
				retorno = medico.getCelular();
				break;
			case 6:
				retorno = medico.getEspecialidad();
			}
		}
		
		return retorno;
	}

	public ImageIcon cargarFoto(int fila) {
		return medicos.get(fila).getFoto();
	}

	public void listarPorEsperialidad(String especialidad) {
		ArrayList<Medico> temp = new ArrayList<>();
		
		for (Medico medico : medicos) {
			if (medico.getEspecialidad().equals(especialidad)) {
				temp.add(medico);
			}
		}
		
		//medicos = temp;
		
		medicos = conMedico.listarPorEspecialidad(especialidad);
		fireTableDataChanged();
	}

	public Medico getPacienteSeleccionado(int fila) {
		return medicos.get(fila);
	}

}
