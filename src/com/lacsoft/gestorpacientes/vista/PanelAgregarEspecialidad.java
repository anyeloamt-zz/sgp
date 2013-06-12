package com.lacsoft.gestorpacientes.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.entidades.Especialidad;
import com.lacsoft.gestorpacientes.fachadas.FachadaGestoraDeCodigos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloEspecialidad;

public class PanelAgregarEspecialidad extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JButton btnAgregar, btnLimpiar;
	private JTable tablaEspecialidades;
	private JScrollPane scrollTabla;
	private JPanel pnlFormulario;

	private FactoriaDeLabels factoriaDeLabels;
	private FactoriaDeTextos factoriaDeTextos;
	private FactoriaDeTablas factoriaDeTablas;
	private FactoriaDeBotones factoriaDeBotones;

	public PanelAgregarEspecialidad() {
		factoriaDeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeLabels = new FactoriaDeLabelsGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		factoriaDeTextos = new FactoriaDeTextosGenericos();

		lblNombre = factoriaDeLabels.crearLabel("Nombre");

		txtNombre = factoriaDeTextos.crearJTextField(15);

		btnAgregar = factoriaDeBotones.crearBoton("Agregar");
		btnLimpiar = factoriaDeBotones.crearBoton("Limpiar");

		tablaEspecialidades = factoriaDeTablas.crearTabla();
		tablaEspecialidades.setModel(ModeloEspecialidad.getInstancia());
		scrollTabla = new JScrollPane(tablaEspecialidades);

		pnlFormulario = new JPanel(new GridLayout(1, 2));
		pnlFormulario.add(lblNombre);
		pnlFormulario.add(txtNombre);

		add(pnlFormulario);
		add(btnAgregar);
		add(btnLimpiar);
		add(scrollTabla);
		
		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText().trim();
				String codigo = FachadaGestoraDeCodigos.generarCodigo("Especialidad");
				
				if (!nombre.equals("")) {
					Especialidad especialidad = new Especialidad(codigo, nombre);
					
					boolean existeEspecialidad = ModeloEspecialidad.getInstancia().agregar(especialidad);
					
					if (existeEspecialidad) {
						JOptionPane.showMessageDialog(
								PanelAgregarEspecialidad.this,
								"La especialidad " + nombre+ " ya está registrada",
								"Especialidad existente",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						limpiar();
					}
					
				} else {
					JOptionPane.showMessageDialog(
							PanelAgregarEspecialidad.this,
							"Debe ingresar el nombre de la especialidad",
							"Nombre incompleto",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAgregar.doClick();
				}
			}
		});
	}

	private void limpiar() {
		txtNombre.setText("");
	}

}
