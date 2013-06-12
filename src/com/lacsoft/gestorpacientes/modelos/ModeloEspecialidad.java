package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Especialidad;
import com.lacsoft.gestorpacientes.observables.Observable;
import com.lacsoft.gestorpacientes.observadores.Observador;
import com.lacsoft.gestorpacientes.observadores.ObservadorDeEspecialidades;
import com.lacsoft.gestorpacientes.persistencia.ConsultaEspecialidad;

public class ModeloEspecialidad extends AbstractTableModel implements Observable {
	private static final long serialVersionUID = 1L;
	private String[] encabezados = { "Código", "Nombre" };
	private ArrayList<Especialidad> especialidades;
	private static ModeloEspecialidad instancia;
	
	private int indicePestana;
	private ConsultaEspecialidad conexionEspecialidad;
	
	private ArrayList<Observador> observadores = new ArrayList<>();

	public static synchronized ModeloEspecialidad getInstancia() {
		return instancia = (instancia == null) ? new ModeloEspecialidad() : instancia;
	}

	private ModeloEspecialidad() {
		conexionEspecialidad = new ConsultaEspecialidad();
		especialidades = conexionEspecialidad.listar();
		Observador observadorEspecialidades = new ObservadorDeEspecialidades();
		agregarObservador(observadorEspecialidades);
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
		return especialidades.size();
	}

	@Override
	public String getColumnName(int column) {
		return encabezados[column];
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		String retorno = null;
		Especialidad especialidad = especialidades.get(fila);

		switch (columna) {
		case 0:
			retorno = especialidad.getCodigo();
			break;

		case 1:
			retorno = especialidad.getNombre();
			break;
		}
		return retorno;
	}

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
	public void setValueAt(Object aValue, int fila, int columna) {
		String valor = (String) aValue;
		if (!verificarNombre(valor)) {			
			if (columna == 1) {
				Especialidad e = especialidades.get(fila);
				e.setNombre(valor);
				conexionEspecialidad.editar(especialidades.get(fila).getCodigo(), valor);
				notificarObservadores(null);
			}
		}
	}
	
	public boolean agregar(Especialidad especialidad) {
		boolean existe = verificarNombre(especialidad.getNombre());
		
		if (!existe) {
			especialidades.add(especialidad);
			conexionEspecialidad.agregar(especialidad);
			fireTableDataChanged();
			notificarObservadores(null);
		}
		return existe;
	}

	public void eliminar(int fila) {
		conexionEspecialidad.eliminar(especialidades.get(fila).getCodigo());
		especialidades.remove(fila);
		fireTableRowsDeleted(fila, fila);
		notificarObservadores(null);
	}

	public boolean verificarNombre(String nombre) {
		boolean existe = false;
		
		for (Especialidad e : especialidades) {
			if (e.getNombre().equalsIgnoreCase(nombre)) {
				existe = true;
				break;
			}
		}
		return existe;
	}
	/*
	 * Retorna un ArrayList con todos los nombre de las especialidades.
	 * Fue diseñado para cargar las especialidades en el combo de 
	 * mantenimiento de usuarios.
	 */
	public ArrayList<String> listarEspecialidades() {
		ArrayList<String> lista = new ArrayList<>();
		for (Especialidad e : especialidades) {
			lista.add(e.getNombre());
		}
		return lista;
	}

	@Override
	public void agregarObservador(Observador o) {
		if (o != null)
			observadores.add(o);
	}

	@Override
	public void removerObservador(Observador o) {
		int indice = observadores.indexOf(o);
		
		if (indice >= 0) {
			observadores.remove(o);
		}
	}

	@Override
	public void notificarObservadores(ArrayList<String> datos) {
		for (Observador o : observadores) {
			o.actualizar(datos);
		}
	}
}
