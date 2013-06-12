package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Alergia;
import com.lacsoft.gestorpacientes.persistencia.ConsultaDeAlergias;

public class ModeloAlergia extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Alergia> alergias = new ArrayList<Alergia>();
	private String[] encabezados = { "Código", "Nombre", "Descripción" };
	private static ModeloAlergia instancia;
	private ConsultaDeAlergias conAlergias = new ConsultaDeAlergias();

	public static synchronized ModeloAlergia getInstancia() {
		return instancia = (instancia == null) ? instancia = new ModeloAlergia()
				: instancia;
	}

	private ModeloAlergia() {
		alergias = conAlergias.getAlergias();
	}

	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return alergias.size();
	}

	public Object getValueAt(int row, int column) {
		Alergia alergia = alergias.get(row);
		String registro = "";
		switch (column) {
		case 0:
			registro = alergia.getCodigo();
			break;
		case 1:
			registro = alergia.getNombre();
			break;
		case 2:
			registro = alergia.getDescripcion();
			break;

		}

		return registro;
	}

	public String getColumnName(int i) {
		return encabezados[i];
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void agregarAlergia(Alergia alergia) {
		alergias.add(conAlergias.agregarAlergia(alergia));
		fireTableDataChanged();
	}

	public boolean existeAlergia(String nombre) {
		{
			return conAlergias.existeAlergia(nombre);
		}
	}

	public void setValueAt(Object value, int row, int column) {
		Alergia alergia = alergias.get(row);
		String valor = (String) value;
		switch (column) {
		case 1:
			if (existeAlergia(valor)) {
				break;
			} else {
				conAlergias.actualizar("nombre", valor, alergia.getCodigo());
				alergia.setNombre(valor);
				break;
			}
		case 2:
			conAlergias.actualizar("descripcion", valor, alergia.getCodigo());
			alergia.setDescripcion(valor);
			break;

		}

	}
	
	public void eliminarAlergia(int fila) {
		Alergia alergia = alergias.get(fila);
		conAlergias.eliminar(alergia.getCodigo());
		alergias.remove(fila);
		fireTableDataChanged();

	}
	
}
