package com.lacsoft.gestorpacientes.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.lacsoft.gestorpacientes.modelos.ModeloHacerPruebaLaboratorio;

public class PanelEditarOEliminarPruebaDeLaboratorio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnlBuscar, pnlDatosResultados;
	private JLabel lblBuscar, lblDatosResultados, lblNombrePaciente,
			lblNombreDeLaPrueba, lblResultadoDeLaPrueba;
	private JTextField txtBuscar, txtPaciente, txtNombrePrueba;
	private JComboBox<String> comboBuscar;
	private JButton btnBuscar, btnEliminar, btnGuardar, btnLimpiar;
	private FactoriaDeLabels factoriaLabel;
	private FactoriaDeCombos factoriaDeCombos;
	private FactoriaDeBotones factoriadeBotones;
	private FactoriaDeTablas factoriaTabla;
	private FactoriaDeTextos factoriaText;
	private JScrollPane scrollTablaPruebas, scrollResultados;
	private JTable tablaPruebasLaboratorio;
	private JTextArea areaResultados;

	public PanelEditarOEliminarPruebaDeLaboratorio() {

		factoriaLabel = new FactoriaDeLabelsGenericos();
		factoriaText = new FactoriaDeTextosGenericos();
		factoriadeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		factoriaTabla = new FactoriaDeTablasGenericas();

		lblBuscar = factoriaLabel.crearLabel("Buscar por");
		txtBuscar = factoriaText.crearJTextField(15);
		btnBuscar = factoriadeBotones.crearBoton("Buscar");
		btnEliminar = factoriadeBotones.crearBoton("Eliminar");

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		comboBuscar.addItem("Cédula");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(330, 30, 489, 33);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);

		scrollTablaPruebas = new JScrollPane();
		scrollTablaPruebas.setBounds(330, 74, 534, 315);

		setLayout(null);

		add(pnlBuscar);
		add(scrollTablaPruebas);

		tablaPruebasLaboratorio = factoriaTabla.crearTabla();
		tablaPruebasLaboratorio.setModel(ModeloHacerPruebaLaboratorio
				.getInstancia());
		tablaPruebasLaboratorio.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {
						int fila = tablaPruebasLaboratorio.getSelectedRow();
						if (fila >= 0) {
							txtPaciente.setText(String
									.valueOf(ModeloHacerPruebaLaboratorio
											.getInstancia().getValueAt(fila, 0)));
							txtNombrePrueba.setText(String
									.valueOf(ModeloHacerPruebaLaboratorio
											.getInstancia().getValueAt(fila, 2)));
							areaResultados.setText(String
									.valueOf(ModeloHacerPruebaLaboratorio
											.getInstancia().getValueAt(fila, 6)));

						}
					}
				});
		scrollTablaPruebas.setViewportView(tablaPruebasLaboratorio);

		pnlDatosResultados = new JPanel();
		pnlDatosResultados.setBounds(10, 60, 306, 349);
		add(pnlDatosResultados);

		lblDatosResultados = factoriaLabel.crearLabel("Datos del resultado");
		lblDatosResultados.setBounds(10, 11, 135, 14);

		lblNombrePaciente = factoriaLabel.crearLabel("Nombre Paciente");
		lblNombrePaciente.setBounds(10, 46, 115, 14);

		lblNombreDeLaPrueba = factoriaLabel.crearLabel("Nombre de la prueba");
		lblNombreDeLaPrueba.setBounds(10, 80, 135, 14);

		txtPaciente = factoriaText.crearJTextField(15);
		txtPaciente.setBounds(130, 43, 129, 20);
		txtPaciente.setEditable(false);

		txtNombrePrueba = factoriaText.crearJTextField(15);
		txtNombrePrueba.setBounds(130, 74, 129, 20);
		txtNombrePrueba.setEditable(false);

		lblResultadoDeLaPrueba = factoriaLabel
				.crearLabel("Resultado de la prueba");
		lblResultadoDeLaPrueba.setBounds(10, 112, 135, 14);

		scrollResultados = new JScrollPane();
		scrollResultados.setBounds(19, 137, 277, 157);

		areaResultados = new JTextArea();
		areaResultados.setLineWrap(true);
		areaResultados.setWrapStyleWord(true);
		scrollResultados.setViewportView(areaResultados);
		pnlDatosResultados.add(lblDatosResultados);
		pnlDatosResultados.add(lblNombrePaciente);
		pnlDatosResultados.add(lblNombreDeLaPrueba);
		pnlDatosResultados.add(txtPaciente);
		pnlDatosResultados.add(txtNombrePrueba);
		pnlDatosResultados.add(lblResultadoDeLaPrueba);
		pnlDatosResultados.setLayout(null);
		pnlDatosResultados.add(scrollResultados);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(19, 296, 136, 30);
		btnGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaPruebasLaboratorio.getSelectedRow();
				if (fila >= 0 && !areaResultados.getText().trim().equals("")) {
					ModeloHacerPruebaLaboratorio.getInstancia().setValueAt(
							"Completado", fila, 5);
					ModeloHacerPruebaLaboratorio.getInstancia().setValueAt(
							areaResultados.getText().trim(), fila, 6);
				} else {
					JOptionPane
							.showMessageDialog(
									PanelEditarOEliminarPruebaDeLaboratorio.this,
									"Debe de llenar el campo de resultado y seleccionar un registro",
									"Gestor Pacientes",
									JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		pnlDatosResultados.add(btnGuardar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(165, 296, 131, 30);
		btnLimpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				areaResultados.setText("");
				txtPaciente.setText("");
				txtNombrePrueba.setText("");
				tablaPruebasLaboratorio.clearSelection();
			}
		});
		pnlDatosResultados.add(btnLimpiar);

	}
}
