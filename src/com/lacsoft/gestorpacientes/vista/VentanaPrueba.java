package com.lacsoft.gestorpacientes.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;

public class VentanaPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlVentanas;
	private JButton btnMantenimientoPacientes, btnMantenimientoUsuarios,
			btnMantenimientoPadecimientos, btnMantenimientoCitas,
			btnMantenimientoResultadosPruebasLaboratorio,
			btnMantenimientoDeEspecialidades, btnMantenimientoDeRecetas,
			btnListarMedicosPorEspecialidad, btnHacerPruebaLaboratorio,
			btnVentanaMantenimientoDePruebasDeLaboratorio,btnHistorialPacientes;

	private FactoriaDeBotones factoriaDeBotones;

	public VentanaPrueba() {
		super("Ventana de prueba");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		factoriaDeBotones = new FactoriaDeBotonesGenericos();

		btnMantenimientoCitas = factoriaDeBotones
				.crearBoton("Mantenimiento de citas");

		btnMantenimientoPacientes = factoriaDeBotones
				.crearBoton("Mantenimiento de pacientes");

		btnMantenimientoUsuarios = factoriaDeBotones
				.crearBoton("Mantenimiento de usuarios");

		btnMantenimientoPadecimientos = factoriaDeBotones
				.crearBoton("Mantenimiento de padecimientos");

		btnMantenimientoResultadosPruebasLaboratorio = factoriaDeBotones
				.crearBoton("Mantenimiento de resultados de pruebas de laboratorio");
		
		btnMantenimientoDeEspecialidades = factoriaDeBotones
				.crearBoton("Mantenimiento de especialidades");

		btnMantenimientoDeRecetas = factoriaDeBotones
				.crearBoton("Mantenimiento de recetas");
		
		btnListarMedicosPorEspecialidad = factoriaDeBotones
				.crearBoton("Listar médicos por especialidad");
		
		btnHacerPruebaLaboratorio = factoriaDeBotones
				.crearBoton("Hacer pruebas de laboratorio");
		
		btnVentanaMantenimientoDePruebasDeLaboratorio = 
		factoriaDeBotones.crearBoton("Mantenimiento de pruebas de laboratorio");
		
		btnHistorialPacientes = factoriaDeBotones
				.crearBoton("Historial Pacientes");
		
		pnlVentanas = new JPanel(new GridLayout(11, 1));

		pnlVentanas.add(btnMantenimientoCitas);
		pnlVentanas.add(btnMantenimientoPacientes);
		pnlVentanas.add(btnMantenimientoUsuarios);
		pnlVentanas.add(btnMantenimientoPadecimientos);
		pnlVentanas.add(btnMantenimientoResultadosPruebasLaboratorio);
		pnlVentanas.add(btnMantenimientoDeEspecialidades);
		pnlVentanas.add(btnMantenimientoDeRecetas);
		pnlVentanas.add(btnListarMedicosPorEspecialidad);
		pnlVentanas.add(btnHacerPruebaLaboratorio);
		pnlVentanas.add(btnVentanaMantenimientoDePruebasDeLaboratorio);
		pnlVentanas.add(btnHistorialPacientes);
		
		
		add(pnlVentanas);
		setSize(380, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		btnMantenimientoPacientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoPacientes.getInstancia();
			}
		});

		btnMantenimientoUsuarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoUsuarios.getInstancia();
			}
		});
		
		btnHistorialPacientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaHistorialPacientes.getInstancia();
			}
		});

		btnMantenimientoPadecimientos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoPadecimientos.getInstancia();
			}
		});

		btnMantenimientoResultadosPruebasLaboratorio
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						VentanaResultadoPruebasLaboratorios.getInstancia();
					}
				});

		btnMantenimientoCitas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoCita.getInstancia();
			}
		});
		
		btnMantenimientoDeEspecialidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoEspecialidades.getInstancia();
			}
		});
		
		btnMantenimientoDeRecetas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoRecetas.getInstancia();
			}
		});
		
		btnListarMedicosPorEspecialidad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaListarMedicosPorEspecialidad.getInstancia();
			}
		});

		btnHacerPruebaLaboratorio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaHacerPruebaLaboratorio.getInstancia();
			}
		});
		
		btnVentanaMantenimientoDePruebasDeLaboratorio.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoPrueba.getInstancia();
			}
		});
		
	}

}
