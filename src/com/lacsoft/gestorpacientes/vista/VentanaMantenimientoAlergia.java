package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaMantenimientoAlergia extends JFrame {

	private static final long serialVersionUID = 1L;
	private static VentanaMantenimientoAlergia instancia;

	public static synchronized VentanaMantenimientoAlergia getInstancia() {
		return instancia = (instancia == null) ? instancia = new VentanaMantenimientoAlergia()
				: instancia;
	}

	private VentanaMantenimientoAlergia() {

		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		add(new PanelMantenimientoAlergia(), BorderLayout.CENTER);
		setTitle("Mantenimiento de alergias");
		
		add(BorderLayout.NORTH, new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setSize(850, 615);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){
				setInstanciaNull();
			}		
		});
		
	}

	public void setInstanciaNull(){
		instancia = null;
	}
		
}
