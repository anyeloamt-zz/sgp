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

import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPaneGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPrueba;

public class VentanaMantenimientoPrueba extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabMantenimientos;
	private ImageIcon icono;
	private FactoriaDeJTabbedPaneGenericos factoriaDePestanas;

	private static VentanaMantenimientoPrueba instancia;

	public static synchronized VentanaMantenimientoPrueba getInstancia() {
		if (instancia == null)
			instancia = new VentanaMantenimientoPrueba();
		return instancia;
	}

	private VentanaMantenimientoPrueba() {
		super("Mantenimiento de Pruebas");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		icono = new ImageIcon("Imagenes/mantenimientos.PNG");

		factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();

		tabMantenimientos = factoriaDePestanas.crearJTabbedPane();

		tabMantenimientos.addTab("Agregar prueba", icono,
				new PanelAgregarPrueba());

		tabMantenimientos.addTab("Editar o eliminar prueba", icono,
				new PanelEditarOEliminarPrueba());

		add(tabMantenimientos);
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

		tabMantenimientos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ModeloPrueba.getInstancia().setIndicePestana(
						tabMantenimientos.getSelectedIndex());

			}
		});
	}

	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}
}
