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
import com.lacsoft.gestorpacientes.modelos.ModeloResultadoPruebaLaboratorio;

public class VentanaResultadoPruebasLaboratorios extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon iconMantenimientos, banner;
	private JTabbedPane tabMantenimientos;
	private static VentanaResultadoPruebasLaboratorios instancia;
	private FactoriaDeJTabbedPane factoriaJtabbed;

	public static synchronized VentanaResultadoPruebasLaboratorios getInstancia() {
		if (instancia == null)
			instancia = new VentanaResultadoPruebasLaboratorios();
		return instancia;
	}

	private VentanaResultadoPruebasLaboratorios() {
		setTitle("Mantenimiento de resultados de pruebas de laboratorio");
         factoriaJtabbed = new FactoriaDeJTabbedPaneGenericos();
		banner = new ImageIcon("Imagenes/mantenimientosBanner.JPG");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		iconMantenimientos = new ImageIcon("Imagenes/mantenimientos.PNG");

		

		tabMantenimientos = factoriaJtabbed.crearJTabbedPane();

		tabMantenimientos.addTab("Ver resultados pruebas laboratorios completada", iconMantenimientos,
				new PanelResultadosPruebasLaboratorioCompletada());
		
		tabMantenimientos.addTab("Ver resultados pruebas laboratorios entregada", iconMantenimientos,
				new PanelResultadosPruebasLaboratorioEntregada());
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		
		tabMantenimientos.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int pestañaActual = tabMantenimientos.getSelectedIndex();
				if(pestañaActual == 0){
					ModeloResultadoPruebaLaboratorio.getInstancia().listarResultados("Completado");
				}
				else{
					ModeloResultadoPruebaLaboratorio.getInstancia().listarResultados("Entregado");
				}
				
			}
		});

		add(tabMantenimientos);
		add(BorderLayout.NORTH, new JLabel(banner));
		setSize(1075, 625);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

private void cerrarVentana() {
	instancia = null;
	this.dispose();
}

}
