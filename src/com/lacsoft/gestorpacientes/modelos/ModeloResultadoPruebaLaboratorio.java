package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.ResultadoDeLaboratorio;
import com.lacsoft.gestorpacientes.persistencia.ConsultasDeResultadosLaboratorios;

public class ModeloResultadoPruebaLaboratorio extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<ResultadoDeLaboratorio> resultados = new ArrayList<ResultadoDeLaboratorio>();
	private String[] encabezados = { "Nombre del paciente", "Cédula",
			"Nombre de la prueba", "Fecha", "Hora", "Estado", "Resultado" };
	private static ModeloResultadoPruebaLaboratorio instancia;
	private ConsultasDeResultadosLaboratorios conResultados = new ConsultasDeResultadosLaboratorios();

	public static synchronized ModeloResultadoPruebaLaboratorio getInstancia() {
		return instancia = (instancia == null) ? instancia = new ModeloResultadoPruebaLaboratorio()
				: instancia;
	}

	private ModeloResultadoPruebaLaboratorio() {
		resultados = conResultados.listarResultados("Completado");
	}

	public void listarResultados(String estado) {
		resultados.clear();
		resultados = conResultados.listarResultados(estado);
		fireTableDataChanged();
		
		
	}
	
	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return resultados.size();
	}

	public String getColumnName(int column) {
		return encabezados[column];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public ResultadoDeLaboratorio getResultadoSeleccionado(int fila){
		return resultados.get(fila);
	}

	public Object getValueAt(int row, int column) {
		ResultadoDeLaboratorio resultado = resultados.get(row);
		String retorno = "";
		switch (column) {
		case 0:
			retorno = resultado.getNombrePaciente();
			break;
		case 1:
			retorno = resultado.getCedulaPaciente();
			break;
		case 2:
			retorno = resultado.getNombrePrueba();
			break;
		case 3:
			retorno = resultado.getFecha();
			break;
		case 4:
			retorno = resultado.getHora();
			break;
		case 5:
			retorno = resultado.getEstado();
			break;
		case 6:
			retorno = resultado.getResultado();
			break;

		}
		return retorno;
	}

	public void setValueAt(Object avalue, int row, int column) {
		ResultadoDeLaboratorio result = resultados.get(row);
		String valor = String.valueOf(avalue);
		switch (column) {
		case 5:
			result.setEstado(valor);
			conResultados.actualizar("estado", valor, result.getIdResultado());
			break;

		}
		fireTableRowsUpdated(row, row);

	}



}
