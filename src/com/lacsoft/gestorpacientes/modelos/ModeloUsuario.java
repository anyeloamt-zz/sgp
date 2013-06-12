package com.lacsoft.gestorpacientes.modelos;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import com.lacsoft.gestorpacientes.entidades.Usuario;
import com.lacsoft.gestorpacientes.observables.Observable;
import com.lacsoft.gestorpacientes.observadores.Observador;
import com.lacsoft.gestorpacientes.observadores.ObservadorDeFilasEnTablas;
import com.lacsoft.gestorpacientes.persistencia.ConsultaUsuario;

public class ModeloUsuario extends AbstractTableModel implements Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ModeloUsuario instancia;
	private String[] encabezados = { "Rol", "Código de empleado", "Clave",
			"Nombre", "Apellidos", "Dirección", "Cédula", "Teléfono 1",
			"Teléfono 2", "Celular", "Correo", "Especialidad" };
	private ArrayList<Usuario> usuarios;
	private int indicePestana; // Para saber cuál pestaña está seleccionada en la ventana.
	private ConsultaUsuario consultaUsuario;
	
	private ArrayList<Observador> observadores = new ArrayList<>();

	public static synchronized ModeloUsuario getInstancia() {
		return instancia = (instancia == null) ? new ModeloUsuario() : instancia;
	}

	private ModeloUsuario() {
		consultaUsuario = new ConsultaUsuario();
		usuarios = consultaUsuario.listar();
		agregarObservador(new ObservadorDeFilasEnTablas());
	}	

	@Override
	public int getColumnCount() {
		return encabezados.length;
	}

	@Override
	public String getColumnName(int columna) {
		return encabezados[columna];
	}
	
	@Override
	public int getRowCount() {
		return usuarios.size();
	}

	@Override
	public boolean isCellEditable(int fila, int columna) {
		boolean retorno = false;
		if(indicePestana == 1){
			if(columna == 1 || columna == 6 
					|| columna == 7 || columna == 8 
					|| columna == 9  || columna == 11){
				retorno = false;
				ArrayList<String> datos = new ArrayList<>();
				
				if (columna == 6) {
					datos.add("Editar cédula");
					datos.add("###-#######-#");
					datos.add("usuario");
					datos.add(String.valueOf(columna));
					datos.add(String.valueOf(fila));
					notificarObservadores(datos);
				} else if (columna == 7) {
					datos.add("Editar teléfono 1");
					datos.add("(###)-###-####");
					datos.add("usuario");
					datos.add(String.valueOf(columna));
					datos.add(String.valueOf(fila));
					notificarObservadores(datos);
				} else if (columna == 8) {
					datos.add("Editar teléfono 2");
					datos.add("(###)-###-####");
					datos.add("usuario");
					datos.add(String.valueOf(columna));
					datos.add(String.valueOf(fila));
					notificarObservadores(datos);
				} else if (columna == 9) {
					datos.add("Editar celular");
					datos.add("(###)-###-####");
					datos.add("usuario");
					datos.add(String.valueOf(columna));
					datos.add(String.valueOf(fila));
					notificarObservadores(datos);
				} else if (columna == 11) {
					System.out.println("Hola se está seleccionando la " +
							"especialidad del usuario");
					
					// Si el usuario que se está editando es médico.
					if (((String) getValueAt(fila, 0)).equals("Médico")) {
						datos.add("Editar especialidad");
						datos.add("combo especialidad");
						datos.add("usuario");
						datos.add(String.valueOf(columna));
						datos.add(String.valueOf(fila));
						notificarObservadores(datos);
						System.out.println("Hola soy un doctor con especialidades.");
					}
				}
			} else {
				retorno = true;
			}
		}
		return retorno;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String valor = (String) aValue;
		valor = valor.trim();
		
		Usuario usuario = usuarios.get(rowIndex);
		switch(columnIndex){
		case 0:
			if (valor.equalsIgnoreCase("Administrador") || valor.equalsIgnoreCase("Médico") 
					|| valor.equalsIgnoreCase("Medico") || valor.equalsIgnoreCase("Asistente")){
				if (valor.equals("administrador")) {
					valor = "Administrador";
				} else if (valor.equals("medico") || valor.equals("médico")) {
					valor = "Médico";
				} else if (valor.equals("asistente")) {
					valor = "Asistente";
				}
				usuario.setRol(valor);
				consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "rol");
			}
			break;
		case 2:
			usuario.setClave(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "clave");
			break;
		case 3:
			usuario.setNombre(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "nombre");
			break;
		case 4:
			usuario.setApellido(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "apellido");
			break;
		case 5:
			usuario.setDireccion(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "direccion");
			break;
		case 6:
			usuario.setCedula(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "cedula");
			break;
		case 7:
			usuario.setTelefono1(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "telefono1");
			break;
		case 8:
			usuario.setTelefono2(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "telefono2");
			break;
		case 9:
			usuario.setCelular(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "celular");
			break;
		case 10:
			usuario.setCorreo(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "correo");
			break;
		case 11:
			usuario.setEspecialidad(valor);
			consultaUsuario.editar(usuarios.get(rowIndex).getCodigoDeEmpleado(), valor, columnIndex, "especialidad");
			break;
		}
		
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		String retorno = "";
		
		if (fila < usuarios.size()) {
		
		Usuario usuario = usuarios.get(fila);
			switch (columna) {
			case 0:
				retorno = usuario.getRol();
				break;
			case 1:
				retorno = usuario.getCodigoDeEmpleado();
				break;
			case 2:
				retorno = usuario.getClave();
				break;
			case 3:
				retorno = usuario.getNombre();
				break;
			case 4:
				retorno = usuario.getApellido();
				break;
			case 5:
				retorno = usuario.getDireccion();
				break;
			case 6:
				retorno = usuario.getCedula();
				break;
			case 7:
				retorno = usuario.getTelefono1();
				break;
			case 8:
				retorno = usuario.getTelefono2();
				break;
			case 9:
				retorno = usuario.getCelular();
				break;
			case 10:
				retorno = usuario.getCorreo();
				break;
			case 11:
				retorno = usuario.getEspecialidad();
				break;
			}
		}
		return retorno;
	}
	
	/**
	 * Retorna true si el usuario ya existe.
	 */
	public boolean agregar(Usuario usuario) {
		boolean existe = false;

		for (Usuario u : usuarios) {
			if (u.getCedula().equals(usuario.getCedula()))
				existe = true;
		}
		if (!existe) {
			usuarios.add(consultaUsuario.agregar(usuario));
			fireTableDataChanged();
		}
		return existe;
	}
	
	public void setIndicePestana(int indicePestana) {
		this.indicePestana = indicePestana;
	}

	public ArrayList<String> listarCodigos() {
		ArrayList<String> codigos = new ArrayList<>();
		for(Usuario u : usuarios) {
			codigos.add(u.getCodigoDeEmpleado());
		}
		return codigos;
	}

	public ImageIcon cargarFoto(int fila) {
		return usuarios.get(fila).getFotoImagen();
	}

	/*
	 * Recibe el tipo de usuarios que va a cargar en la tabla
	 * (para que sólo se vea ese tipo de usuario)
	 * desde la base de datos.
	 */
	public void cargarUsuarios(String tipo) {
		usuarios = consultaUsuario.cargarUsuarios(tipo);
		fireTableDataChanged();
	}

	public void eliminar(int fila) {
		consultaUsuario.eliminar(usuarios.get(fila).getCodigoDeEmpleado());
		usuarios.remove(fila);
		fireTableRowsDeleted(fila, fila);
	}

	public void actualizarImagen(int fila, String path) {
		Usuario u = usuarios.get(fila);
		usuarios.get(fila).setFotoImagen(consultaUsuario.actualizarImagen(u.getCodigoDeEmpleado(), path));
		
	}

	@Override
	public void agregarObservador(Observador o) {
		if (!o.equals(null)) {
			observadores.add(o);
		}
	}

	@Override
	public void removerObservador(Observador o) {
		if (observadores.indexOf(o) >= 0) {
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
