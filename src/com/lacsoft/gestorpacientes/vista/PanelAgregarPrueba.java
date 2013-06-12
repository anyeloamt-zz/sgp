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

import com.lacsoft.gestorpacientes.entidades.Prueba;
import com.lacsoft.gestorpacientes.fachadas.FachadaGestoraDeCodigos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPrueba;

public class PanelAgregarPrueba extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JTable tablaPrueba;
	private JScrollPane scroll;
	private JButton btnAgregar, btnLimpiar;
	private JPanel pnlLblText;

	private FactoriaDeTextosGenericos factoriaDeTextosGenericos = new FactoriaDeTextosGenericos();
	private FactoriaDeLabelsGenericos factoriaDeLabelsGenericos = new FactoriaDeLabelsGenericos();
	private FactoriaDeBotonesGenericos factoriaDeBotonesGenericos = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablasGenericas factoriaDeTablasGenericas = new FactoriaDeTablasGenericas();

	public PanelAgregarPrueba() {

		txtNombre = factoriaDeTextosGenericos.crearJTextField(15);

		lblNombre = factoriaDeLabelsGenericos.crearLabel("Nombre de la prueba");

		btnAgregar = factoriaDeBotonesGenericos.crearBoton("Agregar");
		btnLimpiar = factoriaDeBotonesGenericos.crearBoton("Limpiar");

		tablaPrueba = factoriaDeTablasGenericas.crearTabla();
		tablaPrueba.setModel(ModeloPrueba.getInstancia());
		scroll = new JScrollPane(tablaPrueba);

		pnlLblText = new JPanel(new GridLayout(1, 2, 2, 2));

		pnlLblText.add(lblNombre);
		pnlLblText.add(txtNombre);
		add(pnlLblText);
		add(btnAgregar);
		add(btnLimpiar);
		add(scroll);

		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					btnAgregar.doClick();
			}
		});

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtNombre.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Debe ingresar un nombre de prueba");

				} else {
					ModeloPrueba.getInstancia().agregar(
							new Prueba(FachadaGestoraDeCodigos
									.generarCodigo("Prueba"), txtNombre
									.getText().trim()));
					limpiar();
				}
			}
		});

		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
	}

	public void limpiar() {
		txtNombre.setText("");
	}

}
