package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaHistorialPacientes extends JFrame {
	private static final long serialVersionUID = 1L;

	private static VentanaHistorialPacientes instancia;

	public static synchronized VentanaHistorialPacientes getInstancia() {
		if (instancia == null) {
			instancia = new VentanaHistorialPacientes();
		}
		return instancia;
	}

	private VentanaHistorialPacientes() {
       super("Historial de pacientes");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());


		add(new PanelHistorialPacientes(), BorderLayout.CENTER);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon(
				"Imagenes/mantenimientosBanner.JPG")));
		setSize(1185, 710);
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
