package com.lacsoft.gestorpacientes.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import com.lacsoft.gestorpacientes.modelos.ModeloPacienteRecetas;

public class PanelPacienteRecetas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente, txtPadecimiento, txtCedula, txtFecha;
	private JTextArea txtMedicamentos;
	private JLabel lblPaciente, lblMediacamento, lblPadecimiento, lblCedula,
			lblFecha;
	private JTable tablaRecetas;
	private JScrollPane scrollTabla, scrollMedicamentos;
	private JPanel pnlPaciente, pnlPadecimiento, pnlAgregar, pnlMedicamento,
			pnlCedula, pnlFecha;

	private JPanel pnlBuscar;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar;

	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();

	public PanelPacienteRecetas() {

		setLayout(null);
		lblPaciente = factoriaDeLabels.crearLabel("Paciente");
		lblCedula = factoriaDeLabels.crearLabel("Cedula");
		lblFecha = factoriaDeLabels.crearLabel("Fecha");
		lblPadecimiento = factoriaDeLabels.crearLabel("Padecimiento");
		lblMediacamento = factoriaDeLabels.crearLabel("Medicamentos");

		txtPaciente = factoriaDeTextos.crearJTextField(10);
		txtPadecimiento = factoriaDeTextos.crearJTextField(10);
		txtCedula = factoriaDeTextos.crearJTextField(10);
		txtFecha = factoriaDeTextos.crearJTextField(10);
		txtMedicamentos = new JTextArea(10, 35);
		txtMedicamentos.setWrapStyleWord(true);
		txtMedicamentos.setLineWrap(true);

		txtPaciente.setEditable(false);
		txtPadecimiento.setEditable(false);
		txtCedula.setEditable(false);
		txtFecha.setEditable(false);
		txtMedicamentos.setEditable(false);

		scrollMedicamentos = new JScrollPane(txtMedicamentos);

		tablaRecetas = factoriaDeTablas.crearTabla();
		tablaRecetas.setModel(ModeloPacienteRecetas.getInstancia());
		scrollTabla = new JScrollPane(tablaRecetas);
		scrollTabla.setBounds(379, 54, 517, 402);

		pnlPaciente = new JPanel();
		pnlPaciente.add(txtPaciente);
		pnlPaciente.setLayout(new BoxLayout(pnlPaciente, BoxLayout.X_AXIS));

		pnlPadecimiento = new JPanel();
		pnlPadecimiento.add(txtPadecimiento);
		pnlPadecimiento.setLayout(new BoxLayout(pnlPadecimiento,
				BoxLayout.X_AXIS));

		pnlCedula = new JPanel();
		pnlCedula.add(txtCedula);
		pnlCedula.setLayout(new BoxLayout(pnlCedula, BoxLayout.X_AXIS));

		pnlFecha = new JPanel();
		pnlFecha.add(txtFecha);
		pnlFecha.setLayout(new BoxLayout(pnlFecha, BoxLayout.X_AXIS));

		pnlMedicamento = new JPanel();
		pnlMedicamento.setBounds(20, 160, 310, 225);
		pnlMedicamento.add(lblMediacamento);
		pnlMedicamento.add(scrollMedicamentos);

		pnlAgregar = new JPanel(new GridLayout(4, 2));
		pnlAgregar.setBounds(20, 43, 310, 100);
		pnlAgregar.add(lblPaciente);
		pnlAgregar.add(pnlPaciente);
		pnlAgregar.add(lblPadecimiento);
		pnlAgregar.add(pnlPadecimiento);
		pnlAgregar.add(lblCedula);
		pnlAgregar.add(pnlCedula);
		pnlAgregar.add(lblFecha);
		pnlAgregar.add(pnlFecha);

		add(pnlAgregar);
		add(pnlMedicamento);
		add(scrollTabla);

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Fecha");
		comboBuscar.addItem("Paciente");
		comboBuscar.addItem("Padecimiento");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(375, 11, 535, 35);
		add(pnlBuscar);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);

		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				buscar("txt", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar("btn", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
			}
		});

		tablaRecetas.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaRecetas.getSelectedRow();

						if (fila >= 0) {
							String paciente = (String) ModeloPacienteRecetas
									.getInstancia().getValueAt(fila, 0);

							String cedula = (String) ModeloPacienteRecetas
									.getInstancia().getValueAt(fila, 1);

							String padecimiento = (String) ModeloPacienteRecetas
									.getInstancia().getValueAt(fila, 2);

							String medicamentos = (String) ModeloPacienteRecetas
									.getInstancia().getValueAt(fila, 3);

							String fecha = (String) ModeloPacienteRecetas
									.getInstancia().getValueAt(fila, 4);

							txtPaciente.setText(paciente);
							txtCedula.setText(cedula);
							txtPadecimiento.setText(padecimiento);
							txtMedicamentos.setText(medicamentos);
							txtFecha.setText(fecha);
						}
					}

				});

	}

	public void buscar(String emisor, String texto, String item) {
		int columna = 0;

		if (item.equals("Paciente")) {
			columna = 1;
		} else if (item.equals("Padecimiento")) {
			columna = 2;
		}

		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaRecetas.getRowCount(); i++) {
				if (((String) tablaRecetas.getValueAt(i, columna))
						.contains(texto))
					tablaRecetas.changeSelection(i, columna, false, false);
			}
		} else {
			for (int i = 0; i < tablaRecetas.getRowCount(); i++) {
				if (((String) tablaRecetas.getValueAt(i, columna))
						.equalsIgnoreCase(texto))
					tablaRecetas.changeSelection(i, columna, false, false);
			}
		}

	}
}
