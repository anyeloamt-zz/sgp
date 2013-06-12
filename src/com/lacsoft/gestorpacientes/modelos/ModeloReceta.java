package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Receta;
import com.lacsoft.gestorpacientes.persistencia.ConsultaReceta;


public class ModeloReceta extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] encabezados = {"Fecha","Paciente","Padecimineto","Medicamentos"};
	private ArrayList<Receta> recetas;
	
	private ConsultaReceta conReceta;
	
	private static ModeloReceta instancia;
	
	public static synchronized ModeloReceta getInstancia() {
		return instancia = (instancia == null) ? new ModeloReceta() : instancia;
	}

	private ModeloReceta() {
		conReceta = new ConsultaReceta();
		recetas = conReceta.listar();
	}

	
	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return recetas.size();
	}

	public String getColumnName(int columna) {
		return encabezados[columna];
	}

	public Object getValueAt(int fila, int columna) {
		String retorno = null;
		Receta receta = recetas.get(fila);
		
		switch (columna) {
		case 0:
			retorno = receta.getFecha();
			break;
		case 1:
			retorno = receta.getNombrePaciente();
			break;
		case 2:
			retorno = receta.getPadecimiento();
			break;
		case 3:
			retorno = receta.getMedicamentos();
			break;
		
		}
		return retorno;
	}
	
	public void setValueAt(Object aValue, int fila, int columna) {
		Receta r = (Receta) aValue;
		Receta receta = recetas.get(fila);
		
		switch (columna) {
		case 1:
			receta.setNombrePaciente(r.getNombrePaciente());
			conReceta.editar("paciente_id", r.getIdPaciente(), receta.getId());
			break;
		case 2:
			receta.setPadecimiento(r.getPadecimiento());
			conReceta.editar("codigo_padecimiento", r.getCodigoPadecimiento(), receta.getId());
			break;
		case 3:
			receta.setMedicamentos(r.getMedicamentos());
			conReceta.editar("medicamentos", r.getMedicamentos(), receta.getId());
			break;
		}
		fireTableDataChanged();
		
	}

	public int agregar(String idPaciente, String codigoPadecimiento, Receta receta, int confirmacion) {
		int retorno = validarReceta(receta);
		
		if (retorno == 0 || confirmacion == 1) {
			Receta re = conReceta.agregar(idPaciente, codigoPadecimiento, receta);
			recetas.add(re);
			fireTableDataChanged();
		}
		return retorno;
	}

	public void eliminar(int fila) {
		conReceta.eliminar(recetas.get(fila).getId());
		recetas.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}
	
	public int validarReceta(Receta receta) {
		int retorno = 0;
		for (Receta r : recetas) {
			if (r.getNombrePaciente().equalsIgnoreCase(receta.getNombrePaciente()) 
					&& r.getMedicamentos().equalsIgnoreCase(receta.getMedicamentos()) 
					&& receta.getPadecimiento().equalsIgnoreCase(receta.getPadecimiento())) {
				if (r.getFecha().equalsIgnoreCase(receta.getFecha())) {
					retorno = 2;
				} else {
					retorno = 1;
				}
				break;
			}
		}
		System.out.println(retorno);
		return retorno;
	}

	public String getCodigoPadecimiento(int fila) {
		return recetas.get(fila).getCodigoPadecimiento();
	}
	
	public String getIdPaciente(int fila) {
		return recetas.get(fila).getIdPaciente();
	}
	
	public Receta getRecetaSeleccionada(int fila){
		return recetas.get(fila);
	}

	public void editar(int fila, Receta receta) {
		setValueAt(receta, fila, 1);
		setValueAt(receta, fila, 2);
		setValueAt(receta, fila, 3);
	}
}
