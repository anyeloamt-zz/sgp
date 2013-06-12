package com.lacsoft.gestorpacientes.vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.lacsoft.gestorpacientes.modelos.ModeloUsuario;
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;

public class PanelEditarOEliminarUsuario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar, lblCambiarFoto, lblFotoInterna;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar, txtCambiarFoto;
	private JButton btnBuscar, btnEliminar, 
		btnCambiarFoto, btnGuardar, btnCancelar;
	private JTable tablaUsuarios;
	private JScrollPane scrollTabla;
	private JPanel pnlBuscar, pnlFoto;
	private JDesktopPane dpFoto;
	private ImageIcon iconFoto;
	
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	
	private JToolBar toolBarUsuarios;
	private JButton btnVerUsuariosAdministradores;
	private JButton btnVerUsuariosMedicos;
	private JButton btnVerUsuariosAsistentes;
	private JButton btnVerTodosLosUsuarios;
	
	private JBusyComponent<JScrollPane> busyTabla;
	private DefaultBusyModel modeloBusy;
	
	public PanelEditarOEliminarUsuario() {
		
		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Código de empleado");
		comboBuscar.addItem("Cédula");
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		
		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnEliminar = factoriaDeBotones.crearBoton("Eliminar");
		btnEliminar.setToolTipText("Eliminar usuario del sistema");
		
		tablaUsuarios = factoriaDeTablas.crearTabla();
		tablaUsuarios.setModel(ModeloUsuario.getInstancia());
		tablaUsuarios.setToolTipText("Doble clic para editar");
		scrollTabla = new JScrollPane(tablaUsuarios);
		scrollTabla.setBounds(10, 57, 927, 473);
		setLayout(null);
		
		busyTabla = new JBusyComponent<JScrollPane>(scrollTabla);
		busyTabla.setBounds(10, 57, 927, 473);
		modeloBusy = new DefaultBusyModel();
		busyTabla.setBusyModel(modeloBusy);
		
		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(10, 11, 556, 35);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);
		
		add(pnlBuscar);
		add(busyTabla);
		
		dpFoto = new JDesktopPane();
		dpFoto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		dpFoto.setBounds(960, 57, 252, 210);
		add(dpFoto);
		
		lblFotoInterna = new JLabel(new ImageIcon("Imagenes/icono.PNG"));
		lblFotoInterna.setBounds(10, 11, 232, 188);
		dpFoto.add(lblFotoInterna);
		
		lblCambiarFoto = factoriaDeLabels.crearLabel("Cambiar foto");
		txtCambiarFoto = factoriaDeTextos.crearJTextField(7);
		txtCambiarFoto.setEditable(false);
		btnCambiarFoto = factoriaDeBotones.crearBoton("...");
		btnCambiarFoto.setToolTipText("Buscar foto para el usuario seleccionado");
		btnGuardar = factoriaDeBotones.crearBoton("Guardar foto");
		btnGuardar.setToolTipText("Actualizar foto del usuario seleccionado");
		btnCancelar = factoriaDeBotones.crearBoton("Cancelar");
		btnCancelar.setToolTipText("Cancelar la actualizacion de la foto");
		
		pnlFoto = new JPanel();
		pnlFoto.setBounds(960, 304, 263, 156);
		
		pnlFoto.add(lblCambiarFoto);
		pnlFoto.add(txtCambiarFoto);
		pnlFoto.add(btnCambiarFoto);
		pnlFoto.add(btnGuardar);
		pnlFoto.add(btnCancelar);
		
		add(pnlFoto);
		
		toolBarUsuarios = new JToolBar();
		toolBarUsuarios.setLayout(new GridLayout(1, 3));
		toolBarUsuarios.setFloatable(false);
		toolBarUsuarios.setBounds(34, 551, 878, 40);
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
		
		btnCambiarFoto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = tablaUsuarios.getSelectedRow();
				
				if (fila >= 0) {
					JFileChooser selector = new JFileChooser();
					FileNameExtensionFilter formatos = new FileNameExtensionFilter(
							"Archivos de imágenes JPG, PNG, GIF", "JPG", "PNG","GIF");
					selector.setFileFilter(formatos);

					int seleccion = selector
							.showOpenDialog(PanelEditarOEliminarUsuario.this);

					if (seleccion == JFileChooser.APPROVE_OPTION) {
						String archivo = selector.getSelectedFile().getAbsolutePath();
						
						txtCambiarFoto.setText(archivo);
						iconFoto = RedimensionadoraDeFotos.redimensionar(new ImageIcon(archivo));
						lblFotoInterna.setIcon(iconFoto);
					}
				} else {
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarUsuario.this,
							"Debe seleccionar un usuario",
							"Usuario no seleccionado",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(txtCambiarFoto.getText().equals("")){
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarUsuario.this,
							"Debe seleccionar una imagen",
							"Imagen no seleccionada",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int fila = tablaUsuarios.getSelectedRow();
					if (fila >= 0) {
						ModeloUsuario.getInstancia().actualizarImagen(fila, txtCambiarFoto.getText());
					}
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirmar = JOptionPane.showConfirmDialog(
						PanelEditarOEliminarUsuario.this,
						"¿Desea eliminar este usuario?", "Confirmar",
						JOptionPane.YES_NO_OPTION);
				
				if (confirmar == JOptionPane.YES_OPTION) {

					int fila = tablaUsuarios.getSelectedRow();
					if (fila >= 0) {
						ModeloUsuario.getInstancia().eliminar(fila);
						lblFotoInterna.setIcon(new ImageIcon(
								"Imagenes/icono.JPG"));
					}

				}
			}
		});
		
		tablaUsuarios.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaUsuarios.getSelectedRow();
						if (fila >= 0) {
							lblFotoInterna.setIcon(ModeloUsuario.getInstancia()
									.cargarFoto(fila));
						}
					}
		});
		
		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
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
		
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtCambiarFoto.setText("");
				int fila = tablaUsuarios.getSelectedRow();
				if (fila >= 0) {
					lblFotoInterna.setIcon(ModeloUsuario.getInstancia().cargarFoto(fila));
				}
			}
		});

	}
	
	protected void cargarUsuarios(final String rol) {
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				modeloBusy.setBusy(true);
				
				ModeloUsuario.getInstancia().cargarUsuarios(rol);
				lblFotoInterna.setIcon(new ImageIcon("Imagenes/icono.PNG"));
				
				return null;
			}
			
			@Override
			protected void done() {
				modeloBusy.setBusy(false);
			}
		}.execute();
	}
	
	private void buscar(String emisor, String texto, String item) {
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
