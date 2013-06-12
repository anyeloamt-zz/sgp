package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Alergia;
import com.lacsoft.gestorpacientes.entidades.Paciente;
import com.lacsoft.gestorpacientes.observables.Observable;
import com.lacsoft.gestorpacientes.observadores.Observador;
import com.lacsoft.gestorpacientes.observadores.ObservadorDeFilasEnTablas;
import com.lacsoft.gestorpacientes.persistencia.ConsultaDePacientes;

public class ModeloPaciente extends AbstractTableModel implements Observable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Paciente> pacientes;
	private String[] encabezados = { "Nombre", "Apellido", "Teléfono",
			"Cédula", "Fecha de nacimiento", "Dirección", "Fuma", "Alérgico" };
	private static ModeloPaciente instancia;
	private ConsultaDePacientes conPacientes = new ConsultaDePacientes();
	private int indicePestana = 0;
	private ArrayList<Observador> observadores = new ArrayList<>();
	private ArrayList<Alergia> alergias;

	public static synchronized ModeloPaciente getInstancia() {
		return instancia = (instancia == null) ? instancia = new ModeloPaciente()
				: instancia;
	}

	private ModeloPaciente() {
		pacientes = new ArrayList<Paciente>();
		pacientes = conPacientes.listarPacientes();
		agregarObservador(new ObservadorDeFilasEnTablas());

	}

	public int getIndicePestana() {
		return indicePestana;
	}

	public void setIndicePestana(int indicePestana) {
		this.indicePestana = indicePestana;
	}

	public int getColumnCount() {
		return encabezados.length;
	}

	public int getRowCount() {
		return pacientes.size();
	}
	
	public Paciente getPacienteSeleccionado(int fila){
		return pacientes.get(fila);
	}

	public Object getValueAt(int row, int column) {
		Paciente p = pacientes.get(row);
		String registro = "";

		switch (column) {
		case 0:
			registro = p.getNombre();
			break;
		case 1:
			registro = p.getApellido();
			break;
		case 2:
			registro = p.getTelefono();
			break;
		case 3:
			registro = p.getCedula();
			break;
		case 4:
			registro = p.getFechaNacimiento();
			break;
		case 5:
			registro = p.getDireccion();
			break;
		case 6:
			registro = p.getFumador();
			break;
		case 7:
			registro = p.getAlergico();
			break;
		}
		return registro;
	}

	public String getColumnName(int i) {
		return encabezados[i];
	}

	public void agregarPacientes(Paciente paciente, ArrayList<String> alergias) {
		pacientes.add(conPacientes.agregarPaciente(paciente, alergias));
		fireTableDataChanged();

	}

	public boolean isCellEditable(int row, int column) {
		boolean retorno = false;
		if (getIndicePestana() == 1) {
			retorno = true;
			if (column == 2 || column == 3 || column == 4 || column == 7) {
				retorno = false;
				ArrayList<String> datos = new ArrayList<>();

				if (column == 2) {
					datos.add("Editar telefono");
					datos.add("(###)-###-####");
					datos.add("paciente");
					datos.add(String.valueOf(row));
					datos.add(String.valueOf(column));
					notificarObservadores(datos);

				} else if (column == 3) {
					datos.add("Editar cédula");
					datos.add("###-#######-#");
					datos.add("paciente");
					datos.add(String.valueOf(row));
					datos.add(String.valueOf(column));
					notificarObservadores(datos);
				} else if (column == 4) {
					datos.add("Editar fecha de nacimiento");
					datos.add("####-##-##");
					datos.add("paciente");
					datos.add(String.valueOf(row));
					datos.add(String.valueOf(column));
					datos.add("true");
					notificarObservadores(datos);
				} else if (column == 7) {
					System.out.println("Hola soy el alergico");
				}

			}
		} else {

			retorno = false;
		}
		return retorno;
	}

	public void setValueAt(Object value, int row, int column) {
		Paciente p = pacientes.get(row);
		String valor = (String) value;
		switch (column) {
		case 0:
			p.setNombre(valor);
			conPacientes.actualizar("nombre", valor, p.getId());
			break;
		case 1:
			p.setApellido(valor);
			conPacientes.actualizar("apellido", valor, p.getId());
			break;
		case 2:
			p.setTelefono(valor);
			conPacientes.actualizar("telefono", valor, p.getId());
			break;
		case 3:
			p.setCedula(valor);
			conPacientes.actualizar("cedula", valor, p.getId());
			break;
		case 4:

			p.setFechaNacimiento(valor);
			conPacientes.actualizar("fecha_nacimiento", valor, p.getId());
			break;
		case 5:
			p.setDireccion(valor);
			conPacientes.actualizar("direccion", valor, p.getId());
			break;
		case 6:
			valor = valor.substring(0, 1).toUpperCase() + valor.substring(1, valor.length());
			if (valor.equals("Si")) {
				p.setFumador("Si");
				conPacientes.actualizar("fumador", "1", p.getId());
			} else if (valor.equals("No")) {
				p.setFumador("No");
				conPacientes.actualizar("fumador", "0", p.getId());
			}
			break;
		case 7:
			p.setAlergico(valor);
			conPacientes.actualizar("alergico", valor, p.getId());
			break;

		}

	}

	public ImageIcon getImagenPaciente(int fila) {
		return pacientes.get(fila).getFotoImagen();
	}

	public void eliminarPaciente(int fila) {
		conPacientes.eliminarPaciente(pacientes.get(fila).getId());
		pacientes.remove(fila);
		fireTableRowsDeleted(fila, fila);

	}

	public void actualizarFotoPaciente(int fila, String path) {
		pacientes.get(fila).setFotoImagen(
				(conPacientes.actualizarImagen(pacientes.get(fila).getId(),
						path)));
	}

	public void agregarObservador(Observador o) {
		if (!o.equals(null)) {
			observadores.add(o);
		}
	}

	public void removerObservador(Observador o) {
		if (observadores.indexOf(o) >= 0) {
			observadores.remove(o);
		}
	}

	public void notificarObservadores(ArrayList<String> datos) {
		for (Observador o : observadores) {
			o.actualizar(datos);
		}
	}

	public String getAlergiasPacientes(int fila) {
		String paciente_id = pacientes.get(fila).getId();
		String retorno = "";
		alergias = conPacientes.getAlergiasPacientes(paciente_id);
		for(Alergia ale : alergias){
			retorno += (ale.getNombre() + "\n"); 
		}
		return retorno;
	}

}
