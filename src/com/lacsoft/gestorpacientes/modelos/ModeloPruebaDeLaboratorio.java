package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.PruebaDeLaboratorio;

public class ModeloPruebaDeLaboratorio extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] encabezados = { "Código Prueba", "Nombre de la prueba" };
	private ArrayList<PruebaDeLaboratorio> pruebas;
	private static ModeloPruebaDeLaboratorio instancia;

	public static synchronized ModeloPruebaDeLaboratorio getInstancia() {
		return instancia = (instancia == null) ? new ModeloPruebaDeLaboratorio() : instancia;
	}

	private ModeloPruebaDeLaboratorio() {
		pruebas = new ArrayList<PruebaDeLaboratorio>();
		pruebas.add(new PruebaDeLaboratorio("12332", "Cristhian"));
	}

	@Override
	public int getColumnCount() {
		return encabezados.length;
	}

	@Override
	public int getRowCount() {
		return pruebas.size();
	}

	@Override
	public String getColumnName(int columna) {
		return encabezados[columna];
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		String retorno = null;
		PruebaDeLaboratorio prueba = pruebas.get(fila);

		switch (columna) {
		case 0:
			retorno = prueba.getCodigo();
			break;
		case 1:
			retorno = prueba.getNombre();
			break;
		}
		return retorno;
	}

	public PruebaDeLaboratorio getPruebaSeleccionada(int fila) {
		return pruebas.get(fila);
	}

}
