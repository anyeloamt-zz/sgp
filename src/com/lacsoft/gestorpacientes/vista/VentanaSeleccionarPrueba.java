package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaSeleccionarPrueba extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static VentanaSeleccionarPrueba instancia;

	public static synchronized VentanaSeleccionarPrueba getInstancia() {
		if (instancia == null) {
			instancia = new VentanaSeleccionarPrueba();
		}
		return instancia;
	}

	
	private VentanaSeleccionarPrueba() {

		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		

		add(new PanelSeleccionarPrueba(),BorderLayout.CENTER);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon(
				"Imagenes/mantenimientosBanner.JPG")));
		setSize(900, 600);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
	}

	public void cerrarVentana() {
		instancia = null;
		this.dispose();
	}

}


