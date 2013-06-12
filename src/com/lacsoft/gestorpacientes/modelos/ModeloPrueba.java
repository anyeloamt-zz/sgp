package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Prueba;
import com.lacsoft.gestorpacientes.persistencia.ConsultaPrueba;

public class ModeloPrueba extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] encabezados = { "Código Prueba", "Nombre de la prueba" };
	private ArrayList<Prueba> pruebas;
	private static ModeloPrueba instancia;
	private ConsultaPrueba con = new ConsultaPrueba();
	private int indicePestana;

	public static synchronized ModeloPrueba getInstancia() {
		return instancia = (instancia == null) ? new ModeloPrueba() : instancia;
	}

	private ModeloPrueba() {
		pruebas = new ArrayList<Prueba>();
		pruebas = con.listar();

	}
	public void setIndicePestana(int indicePestana) {
		this.indicePestana = indicePestana;
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
		Prueba prueba = pruebas.get(fila);

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

	public boolean isCellEditable(int fila, int columna) {
		boolean retorno = false;
	
		if (indicePestana == 1) {
			if (columna == 1) {
				retorno = true;
			}
		}
		return retorno;
	}

	public void setValueAt(Object valor, int fila, int columna) {

		String valorAsignar = (String) valor;
		Prueba prueba = pruebas.get(fila);

		boolean existe = verificarNombre(valorAsignar);

		if (!existe) {
			prueba.setNombre(valorAsignar);
			con.editar(prueba.getCodigo(),valorAsignar);
		}

	}

	public boolean agregar(Prueba prueba) {
		boolean existe = verificarNombre(prueba.getNombre());

		if (!existe) {
			con.agregar(prueba);
			pruebas.add(prueba);
			fireTableDataChanged();
		}
		return false;
	}

	public boolean verificarNombre(String nombre) {
		boolean existe = false;

		for (Prueba p : pruebas) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
				existe = true;
			}
		}
		return existe;
	}

	public void eliminar(int fila) {

		con.eliminar(pruebas.get(fila).getCodigo());
		pruebas.remove(fila);
		fireTableDataChanged();

	}

	public Prueba getPruebaSeleccionada(int fila) {
		return pruebas.get(fila);
	}

}
