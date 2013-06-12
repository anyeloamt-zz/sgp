package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class VentanaMantenimientoCita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabMantenimientoCitas;
	private ImageIcon iconMantenimientos, banner;
	private static VentanaMantenimientoCita instancia;

	public static synchronized VentanaMantenimientoCita getInstancia() {
		if(instancia == null)
			instancia = new VentanaMantenimientoCita();
		return instancia;
	}
	
	private VentanaMantenimientoCita() {
		super("Mantenimiento de citas");
		banner = new ImageIcon("Imagenes/mantenimientosBanner.JPG");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		iconMantenimientos = new ImageIcon("Imagenes/mantenimientos.PNG");

		tabMantenimientoCitas = new JTabbedPane(JTabbedPane.NORTH,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tabMantenimientoCitas.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		tabMantenimientoCitas.addTab("Agregar citas", iconMantenimientos, 
				PanelAgregarCita.getInstancia());
		tabMantenimientoCitas.addTab("Editar o eliminar citas", iconMantenimientos, 
				PanelEditarOEliminarCita.getInstancia());

		add(new JLabel(banner), BorderLayout.NORTH);
		add(tabMantenimientoCitas);

		
		setSize(920, 740);
		setLocationRelativeTo(null);
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
				/*
				 *  Para que cuando se cierre la ventana se limpien los 
				 *  campos de la cita que se estaba agregando.
				 */
				PanelAgregarCita.getInstancia().limpiar(); 
			}
		});
	}
	
	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}
}
