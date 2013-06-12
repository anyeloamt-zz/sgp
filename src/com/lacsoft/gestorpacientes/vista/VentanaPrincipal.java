package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.lacsoft.gestorpacientes.entidades.UsuarioLogueado;
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
import com.lacsoft.gestorpacientes.modelos.ModeloCitasHoy;

public class VentanaPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaCitas;
	private JScrollPane scrollTabla;
	
	private JPanel pnlPrincipal;
	
	private JButton btnManPacientes, btnManCitas, btnManPadecimientos,
		btnManRecetas, btnManPruebasLaboratorio, btnHacerPruebaLaboratorio,
		btnManResultadosPruebaLaboratorio, btnListarMedicosEspecialidad,
		btnBuscarPacientes, btnHistorialPacientes, btnEstadisticas, btnManUsuarios;
	
	private JToolBar barraIzquierda, barraSuperior;

	private JMenuBar menu;
	private JMenu menuArchivo, menuAyuda;
	private JMenuItem itemCerrarSesion, itemSalir, itemAcercaDe;
	
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	
	private JPanel pnlBuscar;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JPanel pnlTabla;
	private JPanel pnlSuperiorATabla;
	
	public VentanaPrincipal() {
		super("Gestor de pacientes | LAC-soft");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		if (UsuarioLogueado.getInstancia().getRol().equals("Administrador")) {
			System.out.println("Hola soy un administrador iniciando sesión");
		} else {
			System.out.println("Hola, soy un mierdo iniciando sesión");
		}
		
		itemCerrarSesion = new JMenuItem("Cerrar sesión", new ImageIcon("Imagenes/cerrarSesion.PNG"));
		itemSalir = new JMenuItem("Salir", new ImageIcon("Imagenes/cerrar.PNG"));
		menuArchivo = new JMenu("Archivo");
		menuArchivo.add(itemCerrarSesion);
		menuArchivo.add(itemSalir);
		
		itemAcercaDe = new JMenuItem("Acerca de Gestor de Pacientes", new ImageIcon("Imagenes/acercade.PNG"));
		menuAyuda = new JMenu("Ayuda");
		menuAyuda.add(itemAcercaDe);
		
		menu = new JMenuBar();
		menu.add(menuArchivo);
		menu.add(menuAyuda);
		
		setJMenuBar(menu);
		
		btnManPacientes = factoriaDeBotones.crearBotonConBorde("Mantenimiento de pacientes", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnManCitas = factoriaDeBotones.crearBotonConBorde("Mantenimiento de citas", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnManPadecimientos = factoriaDeBotones.crearBotonConBorde("Mantenimiento de padecimientos", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnManRecetas = factoriaDeBotones.crearBotonConBorde("Mantenimiento de recetas", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnManPruebasLaboratorio = factoriaDeBotones.crearBotonConBorde("Mantenimiento de pruebas de laboratorio", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnHacerPruebaLaboratorio = factoriaDeBotones.crearBotonConBorde("Hacer prueba de laboratorio", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnManResultadosPruebaLaboratorio = factoriaDeBotones.crearBotonConBorde("Resultados pruebas de laboratorio", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnListarMedicosEspecialidad = factoriaDeBotones.crearBotonConBorde("Listar médicos por especialidad", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnBuscarPacientes = factoriaDeBotones.crearBotonConBorde("Buscar pacientes", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnHistorialPacientes = factoriaDeBotones.crearBotonConBorde("Historial de pacientes", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		btnEstadisticas = factoriaDeBotones.crearBotonConBorde("Estadísticas", 
				new ImageIcon("Imagenes/iconoBotones.PNG"));
		
		btnManPacientes.addActionListener(this);
		btnManCitas.addActionListener(this);
		btnManPadecimientos.addActionListener(this);
		btnManRecetas.addActionListener(this);
		btnHacerPruebaLaboratorio.addActionListener(this);
		btnManPruebasLaboratorio.addActionListener(this);
		btnManResultadosPruebaLaboratorio.addActionListener(this);
		btnListarMedicosEspecialidad.addActionListener(this);
		btnBuscarPacientes.addActionListener(this);
		btnHistorialPacientes.addActionListener(this);
		btnEstadisticas.addActionListener(this);
		itemAcercaDe.addActionListener(this);
		itemCerrarSesion.addActionListener(this);
		itemSalir.addActionListener(this);
		
		barraIzquierda = new JToolBar("Mantenimientos", JToolBar.VERTICAL);
		barraIzquierda.setFloatable(false);
		barraIzquierda.setRollover(true);
		barraIzquierda.add(btnManPacientes);
		barraIzquierda.add(btnManCitas);
		barraIzquierda.add(btnManPadecimientos);
		barraIzquierda.add(btnHacerPruebaLaboratorio);
		barraIzquierda.add(btnManPruebasLaboratorio);
		barraIzquierda.add(btnManResultadosPruebaLaboratorio);
		barraIzquierda.add(btnManRecetas);
		barraIzquierda.setLayout(new GridLayout(7, 1));
		
		barraSuperior = new JToolBar("", JToolBar.HORIZONTAL);
		barraSuperior.setFloatable(false);
		barraSuperior.setRollover(true);
		barraSuperior.add(btnBuscarPacientes);
		barraSuperior.add(btnHistorialPacientes);
		barraSuperior.add(btnListarMedicosEspecialidad);
		barraSuperior.add(btnEstadisticas);
		if (UsuarioLogueado.getInstancia().getRol().equals("Administrador")) {
			btnManUsuarios = factoriaDeBotones.crearBotonConBorde("Mantenimiento de usuarios", 
					new ImageIcon("Imagenes/iconoBotones.PNG"));
			barraSuperior.add(btnManUsuarios);
			btnManUsuarios.addActionListener(this);
		}
		barraSuperior.setLayout(new GridLayout());
		
		tablaCitas = factoriaDeTablas.crearTabla();
		tablaCitas.setModel(ModeloCitasHoy.getInstancia());
		scrollTabla = new JScrollPane(tablaCitas);
		
		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre de paciente");
		comboBuscar.addItem("Cédula de paciente");
		comboBuscar.addItem("Hora");
		comboBuscar.addItem("Causa");
		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		
		JLabel lblTitulo = factoriaDeLabels.crearLabel("CITAS PAUTADAS PARA HOY");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 34));
		lblTitulo.setForeground(new Color(00, 72, 255));
		
		JPanel pnlLabel = new JPanel();
		pnlLabel.add(lblTitulo);
		
		pnlBuscar = new JPanel();
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		
		pnlPrincipal = new JPanel(new BorderLayout());
		pnlPrincipal.add(BorderLayout.EAST, barraIzquierda);
		pnlPrincipal.add(barraSuperior, BorderLayout.NORTH);
		
		if (UsuarioLogueado.getInstancia().getRol().equals("Médico")) {
		
			pnlSuperiorATabla = new JPanel(new BorderLayout());
			pnlSuperiorATabla.add(BorderLayout.NORTH, pnlLabel);
			pnlSuperiorATabla.add(pnlBuscar);

			pnlTabla = new JPanel(new BorderLayout());
			pnlTabla.add(pnlSuperiorATabla, BorderLayout.NORTH);
			pnlTabla.add(scrollTabla);
			
			pnlPrincipal.add(pnlTabla);
		} else {
			JScrollPane s = new JScrollPane();
			s.setViewportView(new PanelMostrarUsuarios());
			pnlPrincipal.add(s);
		}
		
		add(pnlPrincipal);
		add(new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")), BorderLayout.NORTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(950, 700);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itemSalir) {
			cerrarVentana();
		} else if (e.getSource() == itemCerrarSesion) {
			cerrarSesion();
		} else if (e.getSource() == itemAcercaDe) {
			new AcercaDe(this);
		} else if (e.getSource() == btnManPacientes) {
			VentanaMantenimientoPacientes.getInstancia();
		} else if (e.getSource() == btnManCitas) {
			VentanaMantenimientoCita.getInstancia();
		} else if (e.getSource() == btnManPadecimientos) {
			VentanaMantenimientoPadecimientos.getInstancia();
		} else if (e.getSource() == btnManRecetas) {
			VentanaMantenimientoRecetas.getInstancia();
		} else if (e.getSource() == btnHacerPruebaLaboratorio) {
			VentanaHacerPruebaLaboratorio.getInstancia();
		} else if (e.getSource() == btnManPruebasLaboratorio) {
			VentanaMantenimientoPrueba.getInstancia();
		} else if (e.getSource() == btnManResultadosPruebaLaboratorio) {
			VentanaResultadoPruebasLaboratorio.getInstancia();
		} else if (e.getSource() == btnListarMedicosEspecialidad) {
			VentanaListarMedicosPorEspecialidad.getInstancia();
		} else if (e.getSource() == btnBuscarPacientes) {
			VentanaHistorialPacientes.getInstancia();
		} else if (e.getSource() == btnHistorialPacientes) {
			VentanaHistorialPacientes.getInstancia();
		} else if (e.getSource() == btnEstadisticas) {
			VentanaReportes.getInstancia();
		} else {
			if (UsuarioLogueado.getInstancia().getRol().equals("Administrador")) {
				if (e.getSource() == btnManUsuarios) {
					VentanaMantenimientoUsuarios.getInstancia();
				}
			}
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
			dispose();
		}
	}

}
