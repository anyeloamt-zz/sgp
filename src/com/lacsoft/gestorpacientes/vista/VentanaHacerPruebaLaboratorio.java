package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPane;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPaneGenericos;

public class VentanaHacerPruebaLaboratorio extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabHacerPruebasLaboratorios;
	private FactoriaDeJTabbedPane factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();
	private ImageIcon icono;
	
	private static VentanaHacerPruebaLaboratorio instancia;
	
	public static synchronized VentanaHacerPruebaLaboratorio getInstancia() {
		if (instancia == null)
			instancia = new VentanaHacerPruebaLaboratorio();
		return instancia;
	}
	
	private VentanaHacerPruebaLaboratorio() {
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		tabHacerPruebasLaboratorios = factoriaDePestanas.crearJTabbedPane();

		icono = new ImageIcon("Imagenes/mantenimientos.PNG");
		
		tabHacerPruebasLaboratorios.addTab("Agregar Prueba", icono,
				PanelHacerPruebaLaboratorio.getInstancia());
		tabHacerPruebasLaboratorios.addTab(
				"Editar o eliminar pruebas de laboratorio", icono,
				new PanelEditarOEliminarPruebaDeLaboratorio());
		
		add(tabHacerPruebasLaboratorios);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setTitle("Hacer prueba de laboratorio");
		setSize(910, 590);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
	}
	
	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}

}
