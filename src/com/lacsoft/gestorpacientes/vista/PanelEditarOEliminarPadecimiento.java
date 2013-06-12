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

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombosGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPadecimiento;

public class PanelEditarOEliminarPadecimiento extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnEliminar, btnBuscar;
	private JScrollPane scrollTabla;
	private JTable tablaPadecimientos;
	private JPanel pnlBuscar;

	private FactoriaDeLabels factoriaDeLabels;
	private FactoriaDeTextos factoriaDeTextos;
	private FactoriaDeBotones factoriaDeBotones;
	private FactoriaDeTablas factoriaDeTablas;
	private FactoriaDeCombos factoriaDeCombos;

	public PanelEditarOEliminarPadecimiento() {

		factoriaDeLabels = new FactoriaDeLabelsGenericos();
		factoriaDeTextos = new FactoriaDeTextosGenericos();
		factoriaDeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		factoriaDeCombos = new FactoriaDeCombosGenericos();

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Código");
		comboBuscar.addItem("Nombre");

		txtBuscar = factoriaDeTextos.crearJTextField(15);

		btnEliminar = factoriaDeBotones.crearBoton("Eliminar");
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");

		tablaPadecimientos = factoriaDeTablas.crearTabla();
		tablaPadecimientos.setModel(ModeloPadecimiento.getInstancia());

		scrollTabla = new JScrollPane(tablaPadecimientos);

		pnlBuscar = new JPanel();

		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);
		add(pnlBuscar);
		add(scrollTabla);

		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				buscar("txt", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					btnBuscar.doClick();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar("btn", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
			}
		});

		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = tablaPadecimientos.getSelectedRow();
				
				if (fila >= 0) {
					int eliminar = JOptionPane.showConfirmDialog(
							PanelEditarOEliminarPadecimiento.this,
							"¿Seguro desea eliminar este padecimiento?",
							"Confirmar", JOptionPane.YES_NO_OPTION);

					if (eliminar == JOptionPane.YES_OPTION) {
						ModeloPadecimiento.getInstancia().eliminar(fila);
					}
				} else 
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarPadecimiento.this,
							"Debe seleccionar un padecimiento",
							"Padecimiento no seleccionado",
							JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 * Buscar pacedimientos en la tabla permitiendo cambiar de algoritmo en
	 * tiempo de ejecución
	 * 
	 * @param
	 */
	private void buscar(String emisor, String texto, String item) {
		int columna = 0;

		if (item.equals("Nombre"))
			columna = 1;

		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaPadecimientos.getRowCount(); i++) {
				if (((String) tablaPadecimientos.getValueAt(i, columna))
						.contains(texto)) {
					tablaPadecimientos
							.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaPadecimientos.getRowCount(); i++) {
				if (((String) tablaPadecimientos.getValueAt(i, columna))
						.equalsIgnoreCase(texto)) {
					tablaPadecimientos
							.changeSelection(i, columna, false, false);
				}
			}
		}
	}

}
