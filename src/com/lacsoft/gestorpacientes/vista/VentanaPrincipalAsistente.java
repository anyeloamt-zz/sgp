package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;

public class VentanaPrincipalAsistente extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnAgregarPacientes, btnListarPacientes, 
			btnListarMedicosEspecialidad, btnManCitas;
	
	private JMenuBar menu;
	private JMenu menuArchivo, menuAyuda;
	private JMenuItem itemCerrarSesion, itemSalir, itemAcercaDe;
	
	private JPanel pnlPrincipal;
	private JToolBar barraSuperior;
	
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	
	public VentanaPrincipalAsistente() {
		super("Gestor de pacientes | LAC-soft");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		itemCerrarSesion = new JMenuItem("Cerrar sesión", 
				new ImageIcon("Imagenes/cerrarSesion.PNG"));
		itemSalir = new JMenuItem("Salir", new ImageIcon("Imagenes/cerrar.PNG"));
		menuArchivo = new JMenu("Archivo");
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		
		itemAcercaDe = new JMenuItem("Acerca de Gestor de Pacientes", 
				new ImageIcon("Imagenes/acercade.PNG"));
		menuAyuda = new JMenu("Ayuda");
		menuAyuda.add(itemAcercaDe);
		
		menu = new JMenuBar();
		menu.add(menuArchivo);
		menu.add(menuAyuda);
		
		setJMenuBar(menu);
		
		btnManCitas = factoriaDeBotones.crearBotonConBorde("Mantenimiento de citas", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		
		btnListarMedicosEspecialidad = factoriaDeBotones.crearBotonConBorde("Listar médicos por especialidad", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		
		btnListarPacientes = factoriaDeBotones.crearBotonConBorde("Listar pacientes", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		
		btnAgregarPacientes = factoriaDeBotones.crearBotonConBorde("Agregar pacientes", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));

		btnManCitas.addActionListener(this);
		btnListarMedicosEspecialidad.addActionListener(this);
		btnListarPacientes.addActionListener(this);
		btnAgregarPacientes.addActionListener(this);
		itemAcercaDe.addActionListener(this);
		itemCerrarSesion.addActionListener(this);
		itemSalir.addActionListener(this);
		
		barraSuperior = new JToolBar("Barra superior", JToolBar.HORIZONTAL);
		barraSuperior.setFloatable(false);
		barraSuperior.setRollover(true);
		barraSuperior.add(btnManCitas);
		barraSuperior.add(btnAgregarPacientes);
		barraSuperior.add(btnListarPacientes);
		barraSuperior.add(btnListarMedicosEspecialidad);
		barraSuperior.setLayout(new GridLayout());
		
		pnlPrincipal = new JPanel(new BorderLayout());
		pnlPrincipal.add(barraSuperior, BorderLayout.NORTH);
		pnlPrincipal.add(new PanelHistorialPacientes());
		
		add(pnlPrincipal);
		add(new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")), BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(950, 700);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnManCitas) {
			VentanaMantenimientoCita.getInstancia();
		} else if (e.getSource() == btnListarMedicosEspecialidad) {
			VentanaListarMedicosPorEspecialidad.getInstancia();
		} else if (e.getSource() == btnListarPacientes) {
			VentanaHistorialPacientes.getInstancia();
		} else if (e.getSource() == btnAgregarPacientes) {
			VentanaAgregarPaciente.getInstancia();
		} else if (e.getSource() == itemAcercaDe) {
			new AcercaDe(this);
		} else if (e.getSource() == itemCerrarSesion) {
			cerrarSesion();
		} else if (e.getSource() == itemSalir) {
			cerrarVentana();
		}
	}
	
	private void cerrarSesion() {
		int confirmar = JOptionPane.showConfirmDialog(this, 
				"Podría tener trabajo sin guardar. " +
				"¿Desea salir de todas maneras?", 
				"Salir", JOptionPane.YES_NO_OPTION);
		
		if (confirmar == JOptionPane.YES_OPTION) {
			dispose();
			new VentanaLogin();
		}
	}

	private void cerrarVentana() {
		int confirmar = JOptionPane.showConfirmDialog(this, 
				"Podría tener trabajo sin guardar. " +
				"¿Desea salir de todas maneras?", 
				"Salir", JOptionPane.YES_NO_OPTION);
		
		if (confirmar == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}
