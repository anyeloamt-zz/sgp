package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPane;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPaneGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPaciente;

public class VentanaMantenimientoPacientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlAgregarPacientes, pnlModificarOEliminarPacientes;
	private ImageIcon iconMantenimiento;
	private JTabbedPane tabbedPestanas;
	private static VentanaMantenimientoPacientes instancia;

	private FactoriaDeJTabbedPane factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();

	public static synchronized VentanaMantenimientoPacientes getInstancia() {
		return instancia = (instancia == null) ? new VentanaMantenimientoPacientes() : instancia;
	}

	private VentanaMantenimientoPacientes() {
		super("Mantenimiento de pacientes");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		pnlAgregarPacientes = PanelAgregaPacientes.getInstancia();
		pnlModificarOEliminarPacientes = new PanelEditarOEliminarPacientes();

		iconMantenimiento = new ImageIcon("Imagenes/mantenimientos.PNG");

		tabbedPestanas = factoriaDePestanas.crearJTabbedPane();

		tabbedPestanas.addTab("Agregar pacientes", iconMantenimiento,
				pnlAgregarPacientes, "Agregar pacientes al sistema");

		tabbedPestanas.addTab("Modificar o eliminar pacientes",
				iconMantenimiento, pnlModificarOEliminarPacientes, 
				"Editar o eliminar pacientes del sistema");
		
		tabbedPestanas.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				ModeloPaciente.getInstancia().setIndicePestana(
						tabbedPestanas.getSelectedIndex());

			}
		});

		getContentPane().add(tabbedPestanas);
		getContentPane().add(BorderLayout.NORTH,
				new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));

		setSize(1100, 750);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PanelAgregaPacientes.getInstancia().limpiar();
				cerrarVentana();
			}
		});

	}

	private void cerrarVentana() {
		instancia = null;
		this.dispose();
		ModeloPaciente.getInstancia().setIndicePestana(0);
	}
}
