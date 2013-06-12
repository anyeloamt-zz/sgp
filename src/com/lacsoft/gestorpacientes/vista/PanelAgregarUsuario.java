package com.lacsoft.gestorpacientes.vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.divxdede.swing.busy.DefaultBusyModel;
import org.divxdede.swing.busy.JBusyComponent;

import com.lacsoft.gestorpacientes.entidades.Usuario;
import com.lacsoft.gestorpacientes.fachadas.FachadaGestoraDeCodigos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombosGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloEspecialidad;
import com.lacsoft.gestorpacientes.modelos.ModeloUsuario;
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;

public class PanelAgregarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblRol, lblNombre, lblApellido,
			lblDireccion, lblCedula, lblClave,
			lblTelefono1, lblTelefono2, lblCelular, 
			lblEspecialidad, lblFoto, lblFotoTexto, lblBuscar;
	private JTextField txtNombre, txtApellido,
			txtDireccion, txtCedula,txtClave, 
			txtTelefono1, txtTelefono2, txtCelular, 
			txtFoto, txtBuscar, txtCorreo;
	private JButton btnAgregarFoto, btnAgregar, btnLimpiar, btnBuscar;
	private JComboBox<String> comboRol, comboEspecialidad, comboBuscar;
	private JPanel pnlTextosLabels, pnlFoto, pnlBotones, pnlBuscar;
	private JTable tablaUsuarios;
	private JScrollPane scrollTabla;
	private JDesktopPane dpFoto;
	private ImageIcon fotoUsuario;
	
	private FactoriaDeLabels factoriaDeLabels;
	private FactoriaDeTextos factoriaDeTextos;
	private FactoriaDeTablas factoriaDeTablas;
	private FactoriaDeBotones factoriaDeBotones;
	private FactoriaDeCombos factoriaDeCombos;
	private JToolBar toolBarUsuarios;
	private JButton btnVerUsuariosAdministradores;
	private JButton btnVerUsuariosMedicos;
	private JButton btnVerUsuariosAsistentes;
	private JButton btnVerTodosLosUsuarios;
	private JLabel lblCorreo;

	private JBusyComponent<JScrollPane> busyTabla;
	private DefaultBusyModel modeloBusy;
	
	private static PanelAgregarUsuario instancia;
	
	public static synchronized PanelAgregarUsuario getInstancia() {
		return instancia = (instancia == null) ? new PanelAgregarUsuario() : instancia;
	}
	
	private PanelAgregarUsuario() {
		
		factoriaDeLabels = new FactoriaDeLabelsGenericos();
		factoriaDeTextos = new FactoriaDeTextosGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		factoriaDeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		setLayout(null);
		
		lblRol = factoriaDeLabels.crearLabel("Rol");
		lblNombre = factoriaDeLabels.crearLabel("Nombre");
		lblApellido = factoriaDeLabels.crearLabel("Apellido");
		lblDireccion= factoriaDeLabels.crearLabel("Dirección");
		lblCedula = factoriaDeLabels.crearLabel("Cédula");
		lblClave = factoriaDeLabels.crearLabel("Clave");
		lblTelefono1 = factoriaDeLabels.crearLabel("Teléfono 1");
		lblTelefono2 = factoriaDeLabels.crearLabel("Teléfono 2");
		lblCelular = factoriaDeLabels.crearLabel("Celular");
		lblCorreo = factoriaDeLabels.crearLabel("Correo");
		lblEspecialidad = factoriaDeLabels.crearLabel("Especialidad");
		
		comboRol = factoriaDeCombos.crearCombo();
		comboRol.addItem("Administrador");
		comboRol.addItem("Médico");
		comboRol.addItem("Asistente");
		
		txtNombre = factoriaDeTextos.crearJTextField();
		txtApellido = factoriaDeTextos.crearJTextField();
		txtDireccion = factoriaDeTextos.crearJTextField();
		txtCedula = factoriaDeTextos.crearJtextFieldEnmascarado("###-#######-#");
		txtClave = factoriaDeTextos.crearJTextFieldEstiloClave();
		txtTelefono1 = factoriaDeTextos.crearJtextFieldEnmascarado("(###)-###-####");
		txtTelefono2 = factoriaDeTextos.crearJtextFieldEnmascarado("(###)-###-####");
		txtCelular = factoriaDeTextos.crearJtextFieldEnmascarado("(###)-###-####");
		txtCorreo = factoriaDeTextos.crearJTextField(20);
		
		comboEspecialidad = factoriaDeCombos.crearCombo();
		cargarEspecialidades();
		comboEspecialidad.setEnabled(false);
		
		pnlTextosLabels = new JPanel(new GridLayout(11, 2));
		pnlTextosLabels.setBounds(34, 270, 248, 279);
		pnlTextosLabels.add(lblRol);
		pnlTextosLabels.add(comboRol);
		pnlTextosLabels.add(lblClave);
		pnlTextosLabels.add(txtClave);
		pnlTextosLabels.add(lblNombre);
		pnlTextosLabels.add(txtNombre);
		pnlTextosLabels.add(lblApellido);
		pnlTextosLabels.add(txtApellido);
		pnlTextosLabels.add(lblDireccion);
		pnlTextosLabels.add(txtDireccion);
		pnlTextosLabels.add(lblTelefono1);
		pnlTextosLabels.add(txtTelefono1);
		pnlTextosLabels.add(lblTelefono2);
		pnlTextosLabels.add(txtTelefono2);
		pnlTextosLabels.add(lblCelular);
		pnlTextosLabels.add(txtCelular);
		pnlTextosLabels.add(lblCorreo);
		pnlTextosLabels.add(txtCorreo);
		pnlTextosLabels.add(lblEspecialidad);
		pnlTextosLabels.add(comboEspecialidad);
		pnlTextosLabels.add(lblCedula);
		pnlTextosLabels.add(txtCedula);
		
		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Código de empleado");
		comboBuscar.addItem("Cédula");
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		
		pnlBuscar = new JPanel();
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		
		pnlBuscar.setBounds(311, 31, 579, 35);
		add(pnlBuscar);
		
		tablaUsuarios = factoriaDeTablas.crearTabla();
		tablaUsuarios.setModel(ModeloUsuario.getInstancia());
		scrollTabla = new JScrollPane(tablaUsuarios);
		scrollTabla.setBounds(311, 77, 897, 460);
		
		busyTabla = new JBusyComponent<>(scrollTabla);
		busyTabla.setBounds(311, 77, 897, 460);
		modeloBusy = new DefaultBusyModel();
		busyTabla.setBusyModel(modeloBusy);
		
		add(pnlTextosLabels);
		add(busyTabla);
		
		dpFoto = new JDesktopPane();
		dpFoto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		dpFoto.setBounds(30, 11, 252, 210);
		add(dpFoto);
		
		lblFoto = new JLabel(new ImageIcon("Imagenes/icono.PNG"));
		lblFoto.setBounds(10, 11, 232, 188);
		dpFoto.add(lblFoto);
		
		lblFotoTexto = factoriaDeLabels.crearLabel("Foto");
		txtFoto = factoriaDeTextos.crearJTextField(10);
		txtFoto.setEditable(false);
		btnAgregarFoto = factoriaDeBotones.crearBoton("...");
		btnAgregarFoto.setToolTipText("Agregar foto del nuevo usuario");
		btnAgregar = factoriaDeBotones.crearBoton("Agregar");
		btnAgregar.setToolTipText("Agregar usuario al sistema");
		btnLimpiar = factoriaDeBotones.crearBoton("Limpiar");
		btnLimpiar.setToolTipText("Limpiar todos los campos");
		
		pnlFoto = new JPanel();
		pnlFoto.add(lblFotoTexto);
		pnlFoto.add(txtFoto);
		pnlFoto.add(btnAgregarFoto);
		
		pnlFoto.setBounds(34, 232, 248, 35);
		add(pnlFoto);
		
		pnlBotones = new JPanel();
		pnlBotones.setBounds(34, 547, 248, 35);
		
		pnlBotones.add(btnAgregar);
		pnlBotones.add(btnLimpiar);
		
		add(pnlBotones);
		
		toolBarUsuarios = new JToolBar();
		toolBarUsuarios.setLayout(new GridLayout(1, 3));
		toolBarUsuarios.setFloatable(false);
		toolBarUsuarios.setBounds(355, 547, 805, 35);
		add(toolBarUsuarios);
		
		btnVerUsuariosAdministradores = factoriaDeBotones.crearBoton("Ver usuarios administradores");
		btnVerUsuariosAdministradores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerUsuariosAdministradores);
		
		btnVerUsuariosMedicos = factoriaDeBotones.crearBoton("Ver usuarios médicos");
		btnVerUsuariosMedicos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerUsuariosMedicos);
		
		btnVerUsuariosAsistentes = factoriaDeBotones.crearBoton("Ver usuarios asistentes");
		btnVerUsuariosAsistentes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerUsuariosAsistentes);
		
		btnVerTodosLosUsuarios = factoriaDeBotones.crearBoton("Ver todos los usuarios");
		btnVerTodosLosUsuarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerTodosLosUsuarios);

		comboRol.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (!comboRol.getSelectedItem().equals("Médico")) {
					comboEspecialidad.setEnabled(false);
				} else {
					comboEspecialidad.setEnabled(true);
				}
			}
		});
		
		btnAgregarFoto.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				FileNameExtensionFilter formatos = new FileNameExtensionFilter(
						"Archivos de imágenes JPG, PNG, GIF", "JPG", "PNG", "GIF");
				selector.setFileFilter(formatos);
				
				int seleccion = selector.showOpenDialog(PanelAgregarUsuario.this);
				
				if(seleccion == JFileChooser.APPROVE_OPTION){
					String archivo = selector.getSelectedFile().getAbsolutePath();
					txtFoto.setText(archivo);
					fotoUsuario = RedimensionadoraDeFotos.redimensionar(new ImageIcon(archivo));
					lblFoto.setIcon(fotoUsuario);
				}
			}
		});
		
		tablaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int fila = tablaUsuarios.getSelectedRow();
				if (fila >= 0) {
					lblFoto.setIcon(ModeloUsuario.getInstancia().cargarFoto(fila));
				}
			}
		});
		
		btnVerUsuariosAdministradores.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cargarUsuarios("Administrador");
			}
		});
		
		btnVerTodosLosUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarUsuarios("Todo");
			}
		});
		
		btnVerUsuariosAsistentes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarUsuarios("Asistente");
			}
		});
		
		btnVerUsuariosMedicos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarUsuarios("Médico");
			}
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String rol = (String) comboRol.getSelectedItem();
				rol = rol.trim();
				String clave = txtClave.getText().trim();
				String nombre = txtNombre.getText().trim();
				String apellido = txtApellido.getText().trim();
				String direccion = txtDireccion.getText().trim();
				String telefono1 = txtTelefono1.getText();
				String telefono2 = txtTelefono2.getText();
				String celular = txtCelular.getText();
				String especialidad = (String) comboEspecialidad.getSelectedItem();
				String cedula = txtCedula.getText().trim();
				String correo = txtCorreo.getText().trim();
				String fotoString = txtFoto.getText().trim();
				String codigo = FachadaGestoraDeCodigos.generarCodigo("Empleado");
				
				if (!clave.equals("") && !nombre.equals("")
						&& !apellido.equals("") && !direccion.equals("")
						&& !cedula.equals("   -       - ") 
						&& !txtFoto.getText().equals("") && !correo.equals("")) {
					/**
					 * Para que se ingrese por lo menos un teléfono.
					 */
					boolean tieneTelefonos = true;
					
					if (telefono1.equals("(   )-   -    ") 
							&& celular.equals("(   )-   -    ") 
							&& telefono2.equals("(   )-   -    ")) {
						
						JOptionPane.showMessageDialog(PanelAgregarUsuario.this,
								"Debe ingresar por lo menos un teléfono",
								"Campos vacíos",
								JOptionPane.INFORMATION_MESSAGE);
						tieneTelefonos = false;
					}
					
					if (tieneTelefonos) {
						Usuario usuario;

						if (rol.equals("Médico")) {
							usuario = new Usuario(rol, clave, nombre,
									apellido, direccion, cedula, telefono1,//****************************
									telefono2, celular, codigo, especialidad,
									fotoString, null);
							usuario.setCorreo(correo);
						} else {
							usuario = new Usuario(rol, clave, nombre, apellido,
									direccion, cedula, telefono1, telefono2,
									celular, codigo, fotoString, null);//****************************
							usuario.setCorreo(correo);
						}

						boolean existeUsuario = ModeloUsuario.getInstancia()
								.agregar(usuario);

						if (existeUsuario) {
							JOptionPane.showMessageDialog(
									PanelAgregarUsuario.this,
									"El usuario con la cédula: " + cedula
											+ " ya está registrado.",
									"Usuario existente",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							limpiar();
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(PanelAgregarUsuario.this,
							"Debe llenar todos los campos", "Campos vacíos",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		
		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				buscar("txt", txtBuscar.getText().trim(), (String) comboBuscar.getSelectedItem());
				if(e.getKeyChar() == KeyEvent.VK_ENTER)
					btnBuscar.doClick();
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				buscar("btn", txtBuscar.getText().trim(), (String) comboBuscar.getSelectedItem());
			}
		});
	}
	
	protected void cargarUsuarios(final String rol) {
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				modeloBusy.setBusy(true);
				
				ModeloUsuario.getInstancia().cargarUsuarios(rol);
				lblFoto.setIcon(new ImageIcon("Imagenes/icono.PNG"));
				
				return null;
			}
			
			@Override
			protected void done() {
				modeloBusy.setBusy(false);
			}
		}.execute();
	}
	
	public void setInstanciaNull() {
		instancia = null;
	}
	
	protected void limpiar() {
		fotoUsuario = new ImageIcon("Imagenes/icono.PNG");
		lblFoto.setIcon(fotoUsuario);
		txtFoto.setText("");
		comboRol.setSelectedIndex(0);
		txtClave.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDireccion.setText("");
		txtTelefono1.setText("");
		txtTelefono2.setText("");
		txtCelular.setText("");
		txtCorreo.setText("");
		comboEspecialidad.setSelectedIndex(0);
		txtCedula.setText("");
	}
	
	public void cargarEspecialidades() {
		comboEspecialidad.removeAllItems();
		for (String especialidad : ModeloEspecialidad.getInstancia().listarEspecialidades()) {
			comboEspecialidad.addItem(especialidad);
		}
	}
	
	protected void buscar(String emisor, String texto, String item) {
		int columna = 0;

		if (item.equals("Código de empleado"))
			columna = 1;
		else if (item.equals("Cédula"))
			columna = 6;
		else if (item.equals("Nombre"))
			columna = 3;
		else 
			columna = 4;

		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaUsuarios.getRowCount(); i++) {
				if (((String) tablaUsuarios.getValueAt(i, columna))
						.contains(texto)) {
					tablaUsuarios
							.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaUsuarios.getRowCount(); i++) {
				if (((String) tablaUsuarios.getValueAt(i, columna))
						.equalsIgnoreCase(texto)) {
					tablaUsuarios
							.changeSelection(i, columna, false, false);
				}
			}
		}
	}
	
}
