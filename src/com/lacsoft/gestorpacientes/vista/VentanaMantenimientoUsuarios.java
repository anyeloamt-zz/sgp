package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lacsoft.gestorpacientes.modelos.ModeloUsuario;

public class VentanaMantenimientoUsuarios extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VentanaMantenimientoUsuarios instancia;
	private JTabbedPane tabMantenimientoUsuarios;
	private ImageIcon iconMantenimientos;
	private JLabel lblBanner;

	public static synchronized VentanaMantenimientoUsuarios getInstancia() {
		if(instancia == null)
			instancia = new VentanaMantenimientoUsuarios();
		return instancia;
	}
	
	private VentanaMantenimientoUsuarios() {
		super("Mantenimiento de usuarios");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());
		
		iconMantenimientos = new ImageIcon("Imagenes/mantenimientos.PNG");
		tabMantenimientoUsuarios = new JTabbedPane(JTabbedPane.NORTH,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tabMantenimientoUsuarios.setFont(new Font("Calibri", Font.PLAIN, 14));

		tabMantenimientoUsuarios.addTab("Agregar usuario", iconMantenimientos,
				PanelAgregarUsuario.getInstancia(), "Agregar usuarios");
		tabMantenimientoUsuarios.addTab("Editar/Eliminar usuario", iconMantenimientos, 
				new PanelEditarOEliminarUsuario(), "Editar o eliminar usuarios");

		lblBanner = new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG"));
		getContentPane().add(lblBanner, BorderLayout.NORTH);
		getContentPane().add(tabMantenimientoUsuarios);
		setSize(1261, 734);
		setVisible(true);
		
		tabMantenimientoUsuarios.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ModeloUsuario.getInstancia().setIndicePestana(
						tabMantenimientoUsuarios.getSelectedIndex());
			}
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
	}
	
	private void cerrarVentana() {
		instancia = null;
		this.dispose();
		PanelAgregarUsuario.getInstancia().setInstanciaNull();
	}
}
