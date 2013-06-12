package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeJTabbedPaneGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPacienteRecetas;
import com.lacsoft.gestorpacientes.modelos.ModeloPacienteResultado;
import com.lacsoft.gestorpacientes.modelos.ModeloPacienteVisita;

public class VentanaHistorialPacientesDetalles extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabMantenimientos;
	private ImageIcon icono;
	private FactoriaDeJTabbedPaneGenericos factoriaDePestanas;
	
	private static VentanaHistorialPacientesDetalles instancia;
	
	public static synchronized VentanaHistorialPacientesDetalles getInstancia() {
		if (instancia == null)
			instancia = new VentanaHistorialPacientesDetalles();
		return instancia;
	}
	
	private VentanaHistorialPacientesDetalles() {
		setTitle("Historial pacientes");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		icono = new ImageIcon("Imagenes/mantenimientos.PNG");

		factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();

		tabMantenimientos = factoriaDePestanas.crearJTabbedPane();
		
		tabMantenimientos.addTab("Fechas de visitas y causas", icono, 
				new PanelVisitaPacientes());
		
		tabMantenimientos.addTab("Recetas del paciente", icono, 
				new PanelPacienteRecetas());
		
		tabMantenimientos.addTab("Resultados del paciente", icono, 
				new PanelPacienteResultados());
				
		
		add(tabMantenimientos);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setSize(1000, 600);
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
	}
	
	public void setTitulo(String titulo){
		this.setTitle(titulo);
	}
	
	private void cerrarVentana() {
		instancia = null;
		ModeloPacienteRecetas.getInstancia().setInstanciaNull();
		ModeloPacienteResultado.getInstancia().setInstanciaNull();
		ModeloPacienteVisita.getInstancia().setInstanciaNull();
		this.dispose();
	}
}
