package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaAgregarPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static VentanaAgregarPaciente instancia;
	
	public static synchronized VentanaAgregarPaciente getInstancia() {
		return instancia = (instancia == null) ? new VentanaAgregarPaciente() : instancia;
	}
	
	private VentanaAgregarPaciente() {
		super("Gestor de pacientes | LAC-soft");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		add(PanelAgregaPacientes.getInstancia());
		
		add(new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")), BorderLayout.NORTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(950, 700);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				instancia = null;
				dispose();
			}
		});
		
	}
	
	public void cerrarVentana() {
		int confirmar = JOptionPane.showConfirmDialog(this, 
				"Podría tener trabajo sin guardar. " +
				"¿Desea salir de todas maneras?", 
				"Salir", JOptionPane.YES_NO_OPTION);
		
		if (confirmar == JOptionPane.YES_OPTION) {
			instancia = null;
			dispose();
		}
	}

}
