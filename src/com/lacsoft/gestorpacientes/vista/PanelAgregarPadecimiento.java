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

import com.lacsoft.gestorpacientes.entidades.Padecimiento;
import com.lacsoft.gestorpacientes.fachadas.FachadaGestoraDeCodigos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPadecimiento;

public class PanelAgregarPadecimiento extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JButton btnAgregar, btnLimpiar;
	private JScrollPane scrollTabla;
	private JTable tablaPadecimientos;
	private JPanel pnlTexto;

	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();

	public PanelAgregarPadecimiento() {

		lblNombre = factoriaDeLabels.crearLabel("Nombre");
		txtNombre = factoriaDeTextos.crearJTextField(15);

		btnAgregar = factoriaDeBotones.crearBoton("Agregar");
		btnLimpiar = factoriaDeBotones.crearBoton("Limpiar");

		tablaPadecimientos = factoriaDeTablas.crearTabla();
		tablaPadecimientos.setModel(ModeloPadecimiento.getInstancia());
		scrollTabla = new JScrollPane(tablaPadecimientos);

		pnlTexto = new JPanel(new GridLayout(1, 2));

		pnlTexto.add(lblNombre);
		pnlTexto.add(txtNombre);

		add(pnlTexto);
		add(btnAgregar);
		add(btnLimpiar);
		add(scrollTabla);

		btnAgregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				String nombre = txtNombre.getText().trim();
				String codigo = FachadaGestoraDeCodigos.generarCodigo("Padecimiento");
				
				if (!nombre.equals("")) {
					boolean existePadecimiento = ModeloPadecimiento.getInstancia()
							.agregar(new Padecimiento(codigo, nombre));
					
					if(existePadecimiento) {
						JOptionPane.showMessageDialog(
								PanelAgregarPadecimiento.this,
								"El padecimiento "
								+ nombre + " ya está registrado.",
								"Padecimiento existente",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						limpiar();
					}
					
				} else {
					JOptionPane.showMessageDialog(
							PanelAgregarPadecimiento.this,
							"Debe llenar todos los campos", "Campos vacíos",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					btnAgregar.doClick();
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
	}

	private void limpiar() {
		txtNombre.setText("");
	}

}
