package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Padecimiento;
import com.lacsoft.gestorpacientes.persistencia.ConsultaPadecimiento;

public class ModeloPadecimiento extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] encabezados = { "Código", "Nombre" };
	private ArrayList<Padecimiento> padecimientos;
	private static ModeloPadecimiento instancia;
	private ConsultaPadecimiento conexionPadecimiento;
	private int indicePestana;
	
	public void setIndicePestana(int indicePestana) {
		this.indicePestana = indicePestana;
	}

	public static synchronized ModeloPadecimiento getInstancia() {
		return instancia = (instancia == null) ? new ModeloPadecimiento() : instancia;
	}

	private ModeloPadecimiento() {
		conexionPadecimiento = new ConsultaPadecimiento();
		padecimientos = conexionPadecimiento.listar();
	}

	@Override
	public String getColumnName(int columna) {
		return encabezados[columna];
	}
	
	/*
	 * Se revisa el índice de la pestaña en la ventana para que sólo
	 * se pueda editar en la pestaña de editar, y sólo se pueda editar
	 * el nombre
	 */
	@Override
	public boolean isCellEditable(int fila, int columna) {
		boolean retorno = false;
		if (indicePestana == 1) {
			if (columna == 1)
				retorno = true;
		}
		return retorno;
	}

	@Override
	public void setValueAt(Object valor, int fila, int columna) {
		String valorAsignar = (String) valor;
		Padecimiento padecimiento = padecimientos.get(fila);
		
		boolean existe = verificarNombre(valorAsignar);
		
		if (!existe) {
			padecimiento.setNombre(valorAsignar);
			conexionPadecimiento.editar(padecimiento.getCodigo(), valorAsignar);
		}

	}

	@Override
	public int getColumnCount() {
		return encabezados.length;
	}

	@Override
	public int getRowCount() {
		return padecimientos.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		String retorno = null;

		Padecimiento padecimiento = padecimientos.get(fila);
		switch (columna) {

		case 0:
			retorno = padecimiento.getCodigo();
			break;
		case 1:
			retorno = padecimiento.getNombre();
			break;
		}

		return retorno;
	}

	/**
	 * Retorna true si el padecimiento existe.
	 * 
	 * @param padecimiento
	 * @return boolean
	 */
	public boolean agregar(Padecimiento padecimiento) {
		boolean existe = verificarNombre(padecimiento.getNombre());
		
		if (!existe) {
			padecimientos.add(padecimiento);
			conexionPadecimiento.agregar(padecimiento);
			fireTableDataChanged();
		}
		return existe;
	}
	
	/**
	 * Si el nombre existe retorna true
	 * @param nombre
	 * @return
	 */
	private boolean verificarNombre(String nombre) {
		boolean existe = false;
		
		for (Padecimiento p : padecimientos) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
				existe = true;
			}
		}
		return existe;
	}

	public void eliminar(int fila) {
		conexionPadecimiento.eliminar(padecimientos.get(fila).getCodigo());
		padecimientos.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}
	
	public ArrayList<String> listarCodigos() {
		ArrayList<String> codigos = new ArrayList<>();
		for (Padecimiento p : padecimientos) {
			codigos.add(p.getCodigo());
		}
		return codigos;
	}
}
