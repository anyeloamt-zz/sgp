package com.lacsoft.gestorpacientes.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombosGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPrueba;

public class PanelEditarOEliminarPrueba extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBuscar;
	private JComboBox<String> comboBuscar;
	private JLabel lblBuscar;
	private JButton btnBuscar, btnEliminar;
	private JTable tablaPrueba;
	private JScrollPane scroll;

	private FactoriaDeTextosGenericos factoriaDeTextosGenericos = new FactoriaDeTextosGenericos();
	private FactoriaDeLabelsGenericos factoriaDeLabelsGenericos = new FactoriaDeLabelsGenericos();
	private FactoriaDeBotonesGenericos factoriaDeBotonesGenericos = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablasGenericas factoriaDeTablasGenericas = new FactoriaDeTablasGenericas();
	private FactoriaDeCombosGenericos factoriaDeCombosGenericos = new FactoriaDeCombosGenericos();

	public PanelEditarOEliminarPrueba() {

		txtBuscar = factoriaDeTextosGenericos.crearJTextField(15);

		btnBuscar = factoriaDeBotonesGenericos.crearBoton("Buscar");
		btnEliminar = factoriaDeBotonesGenericos.crearBoton("Eliminar");

		comboBuscar = factoriaDeCombosGenericos.crearCombo();
		comboBuscar.addItem("Código");
		comboBuscar.addItem("Nombre");

		lblBuscar = factoriaDeLabelsGenericos.crearLabel("Buscar por");

		tablaPrueba = factoriaDeTablasGenericas.crearTabla();
		tablaPrueba.setModel(ModeloPrueba.getInstancia());
		scroll = new JScrollPane(tablaPrueba);

		add(lblBuscar);
		add(comboBuscar);
		add(txtBuscar);
		add(btnBuscar);
		add(btnEliminar);
		add(scroll);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar("btn", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
			}
		});

		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				buscar("txt", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					btnBuscar.doClick();
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaPrueba.getSelectedRow();
				if (fila >= 0) {

					int confirmar = JOptionPane.showConfirmDialog(
							PanelEditarOEliminarPrueba.this,
							"seguro que desea borrar esta prueba?");

					if (confirmar == JOptionPane.YES_OPTION) {
						ModeloPrueba.getInstancia().eliminar(fila);

					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una prueba");
				}

			}
		});

	}

	public void buscar(String emisor, String texto, String item) {

		int columna = 1;
		if (item.equals("Código"))
			columna = 0;
		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaPrueba.getRowCount(); i++) {
				if (((String) tablaPrueba.getValueAt(i, columna))
						.contains(texto)) {
					tablaPrueba.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaPrueba.getRowCount(); i++) {
				if (((String) tablaPrueba.getValueAt(i, columna))
						.equalsIgnoreCase(texto)) {
					tablaPrueba.changeSelection(i, columna, false, false);
				}
			}
		}

	}

}
