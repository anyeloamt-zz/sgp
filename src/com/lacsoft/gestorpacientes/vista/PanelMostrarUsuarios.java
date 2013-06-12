package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;

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

public class PanelMostrarUsuarios extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tablaUsuarios;
	private JScrollPane scrollTabla;
	
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JPanel pnlBuscar;
	
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();

	private JToolBar toolBarUsuarios;
	private JButton btnVerUsuariosAdministradores;
	private JButton btnVerUsuariosMedicos;
	private JButton btnVerUsuariosAsistentes;
	private JButton btnVerTodosLosUsuarios;
	
	private JBusyComponent<JScrollPane> busyTabla;
	private DefaultBusyModel modeloBusy;
	
	public PanelMostrarUsuarios() {
		tablaUsuarios = factoriaDeTablas.crearTabla();
		scrollTabla = new JScrollPane(tablaUsuarios);
		setLayout(new BorderLayout());
		
		tablaUsuarios.setModel(ModeloUsuario.getInstancia());
		
		busyTabla = new JBusyComponent<>(scrollTabla);
		modeloBusy = new DefaultBusyModel();
		busyTabla.setBusyModel(modeloBusy);
		
		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Código de empleado");
		comboBuscar.addItem("Cédula");
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		
		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		
		pnlBuscar = new JPanel();
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		
		toolBarUsuarios = new JToolBar();
		toolBarUsuarios.setLayout(new GridLayout(1, 3));
		toolBarUsuarios.setFloatable(false);
		add(toolBarUsuarios);
		
		ImageIcon icon = new ImageIcon("Imagenes/iconoBotones.PNG");
		
		btnVerUsuariosAdministradores = factoriaDeBotones.crearBotonConBorde("Ver usuarios administradores", icon);
		btnVerUsuariosAdministradores.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerUsuariosAdministradores);
		
		btnVerUsuariosMedicos = factoriaDeBotones.crearBotonConBorde("Ver usuarios médicos", icon);
		btnVerUsuariosMedicos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerUsuariosMedicos);
		
		btnVerUsuariosAsistentes = factoriaDeBotones.crearBotonConBorde("Ver usuarios asistentes", icon);
		btnVerUsuariosAsistentes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerUsuariosAsistentes);
		
		btnVerTodosLosUsuarios = factoriaDeBotones.crearBotonConBorde("Ver todos los usuarios", icon);
		btnVerTodosLosUsuarios.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolBarUsuarios.add(btnVerTodosLosUsuarios);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.add(toolBarUsuarios);
		
		add(BorderLayout.NORTH, pnlBuscar);
		add(busyTabla);
		add(pnlBarra, BorderLayout.SOUTH);
		
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
	
	protected void cargarUsuarios(final String rol) {
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				modeloBusy.setBusy(true);

				ModeloUsuario.getInstancia().cargarUsuarios(rol);
				return null;
			}

			@Override
			protected void done() {
				modeloBusy.setBusy(false);
			}
		}.execute();
	}
}
