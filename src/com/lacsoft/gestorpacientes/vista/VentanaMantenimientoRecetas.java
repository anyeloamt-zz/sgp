package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPaneGenericos;

public class VentanaMantenimientoRecetas extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabMantenimientos;
	private ImageIcon icono;
	private FactoriaDeJTabbedPaneGenericos factoriaDePestanas;
	
	private static VentanaMantenimientoRecetas instancia;
	
	public static synchronized VentanaMantenimientoRecetas getInstancia() {
		if (instancia == null)
			instancia = new VentanaMantenimientoRecetas();
		return instancia;
	}
	
	private VentanaMantenimientoRecetas() {
		super("Mantenimiento de recetas");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		icono = new ImageIcon("Imagenes/mantenimientos.PNG");

		factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();

		tabMantenimientos = factoriaDePestanas.crearJTabbedPane();
		
		tabMantenimientos.addTab("Agregar receta", icono, 
				PanelAgregarReceta.getInstancia());
		
		tabMantenimientos.addTab("Editar o eliminar receta", icono, 
				PanelEditarOEliminarReceta.getInstancia());
		
		add(tabMantenimientos);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setSize(930, 600);
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
	}
	
	private void cerrarVentana() {
		instancia = null;
		PanelAgregarReceta.getInstancia().setInstanciaNull();
		PanelEditarOEliminarReceta.getInstancia().setInstanciaNull();
		this.dispose();
	}
}
