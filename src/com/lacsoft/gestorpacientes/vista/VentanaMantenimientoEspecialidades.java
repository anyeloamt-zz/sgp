package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPane;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPaneGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloEspecialidad;

public class VentanaMantenimientoEspecialidades extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabMantenimientos;
	private ImageIcon icono;
	
	private static VentanaMantenimientoEspecialidades instancia;
	private FactoriaDeJTabbedPane factoriaDePestanas;

	public static synchronized VentanaMantenimientoEspecialidades getInstancia() {
		if (instancia == null) 
			instancia = new VentanaMantenimientoEspecialidades(); 
		return instancia;
	}
	
	private VentanaMantenimientoEspecialidades() {
		super("Mantenimiento de especialidades");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		icono = new ImageIcon("Imagenes/mantenimientos.PNG");

		factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();

		tabMantenimientos = factoriaDePestanas.crearJTabbedPane();
		tabMantenimientos.addTab("Agregar especialidad", icono, 
				new PanelAgregarEspecialidad());
		
		tabMantenimientos.addTab("Editar o eliminar especialidad", icono, 
				new PanelEditarOEliminarEspecialidad());

		add(tabMantenimientos);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setSize(900, 650);
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		
		tabMantenimientos.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				ModeloEspecialidad.getInstancia().setIndicePestana(
						tabMantenimientos.getSelectedIndex());
			}
		});
	}
	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}

}
