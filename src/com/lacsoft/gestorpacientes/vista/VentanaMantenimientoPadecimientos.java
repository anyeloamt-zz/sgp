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
import com.lacsoft.gestorpacientes.modelos.ModeloPadecimiento;

public class VentanaMantenimientoPadecimientos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabMantenimiento;
	private ImageIcon iconMantenimientos;
	private static VentanaMantenimientoPadecimientos instancia;
	
	private FactoriaDeJTabbedPane factoriaDePestanas = new FactoriaDeJTabbedPaneGenericos();
	
	public static synchronized VentanaMantenimientoPadecimientos getInstancia() {
		if(instancia == null)
			instancia = new VentanaMantenimientoPadecimientos();
		return instancia;
	}
	
	private VentanaMantenimientoPadecimientos() {
		super("Mantenimiento de padecimientos");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		iconMantenimientos = new ImageIcon("Imagenes/mantenimientos.PNG");
		
		tabMantenimiento = factoriaDePestanas.crearJTabbedPane();
		
		tabMantenimiento.addTab("Agregar padecimiento", iconMantenimientos, new PanelAgregarPadecimiento());
		tabMantenimiento.addTab("Editar o eliminar padecimiento", iconMantenimientos, new PanelEditarOEliminarPadecimiento());
		add(BorderLayout.NORTH, new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		add(tabMantenimiento);
		
		setSize(860, 640);
		setVisible(true);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		
		tabMantenimiento.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				ModeloPadecimiento.getInstancia().setIndicePestana(
						tabMantenimiento.getSelectedIndex());
			}
		});
	}

	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}
}
