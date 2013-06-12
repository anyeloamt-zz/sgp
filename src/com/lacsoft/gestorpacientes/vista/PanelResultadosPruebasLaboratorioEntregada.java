package com.lacsoft.gestorpacientes.vista;

import java.awt.GridLayout;
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
import com.lacsoft.gestorpacientes.modelos.ModeloResultadoPruebaLaboratorio;
import com.lacsoft.gestorpacientes.reportes.Reportes;

public class PanelResultadosPruebasLaboratorioEntregada extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel pnlBuscador, pnlDatosPruebas, pnlResultado;
	private JLabel lblBuscar, lblNombre, lblCedula, lblNombrePrueba,
			lblResultados, lblFechaResultados, lblHoraResultados;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar, txtNombre, txtCedula, txtNombrePrueba,
			txtFechaResultados, txtHoraResultados;
	private JButton btnBuscar,btnImprimirResultados;
	private JScrollPane scrollTabla, scrollResultados;
	private JTable tablaResultados;
	private JTextArea txtResultados;

	private FactoriaDeLabels factoriaLabel;
	private FactoriaDeTextos factoriaTexto;
	private FactoriaDeBotones factoriadeBotones;
	private FactoriaDeCombos factoriaDeCombos;
	private FactoriaDeTablas factoriaDeTablas;
	private Reportes report = new Reportes();

	public PanelResultadosPruebasLaboratorioEntregada() {

		factoriaLabel = new FactoriaDeLabelsGenericos();
		factoriaTexto = new FactoriaDeTextosGenericos();
		factoriadeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();

		lblBuscar = factoriaLabel.crearLabel("Buscar");
		lblNombre = factoriaLabel.crearLabel("Nombre");
		lblCedula = factoriaLabel.crearLabel("Cédula:");
		lblNombrePrueba = factoriaLabel.crearLabel("Nombre prueba:");
		lblResultados = factoriaLabel.crearLabel("Resultado");
		lblFechaResultados = factoriaLabel.crearLabel("Fecha de la prueba:");
		lblHoraResultados = factoriaLabel.crearLabel("Hora de la prueba");

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Pacientes");

		txtBuscar = factoriaTexto.crearJTextField(15);
		txtNombre = factoriaTexto.crearJTextField();

		txtCedula = factoriaTexto.crearJTextField();
		txtNombrePrueba = factoriaTexto.crearJTextField();
		txtFechaResultados = factoriaTexto.crearJTextField();
		txtHoraResultados = factoriaTexto.crearJTextField();

		txtNombre.setEditable(false);

		txtCedula.setEditable(false);
		txtNombrePrueba.setEditable(false);
		txtFechaResultados.setEditable(false);
		txtHoraResultados.setEditable(false);

		btnBuscar = factoriadeBotones.crearBoton("Buscar");
		btnImprimirResultados = factoriadeBotones.crearBoton("Imprimir Resultado");
		btnImprimirResultados.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaResultados.getSelectedRow();
				if (fila >= 0) {
					report.reportadorResultadosDeLaboratorio(ModeloResultadoPruebaLaboratorio
							.getInstancia().getResultadoSeleccionado(fila));
					ModeloResultadoPruebaLaboratorio.getInstancia().setValueAt(
							"Entregado", fila, 5);
				} else {
					JOptionPane
							.showMessageDialog(
									PanelResultadosPruebasLaboratorioEntregada.this,
									"Debe seleccionar un paciente para imprimir resultado",
									"Gestor Pacientes",
									JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
				
		

		pnlBuscador = new JPanel();
		pnlBuscador.setBounds(362, 5, 652, 33);
		pnlBuscador.add(lblBuscar);
		pnlBuscador.add(comboBuscar);
		pnlBuscador.add(txtBuscar);
		pnlBuscador.add(btnBuscar);
		pnlBuscador.add(btnImprimirResultados);
		

		tablaResultados = factoriaDeTablas.crearTabla();
		tablaResultados.setModel(ModeloResultadoPruebaLaboratorio
				.getInstancia());
		tablaResultados.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {
						int fila = tablaResultados.getSelectedRow();
						if (fila >= 0) {
							txtNombre.setText(String
									.valueOf(ModeloResultadoPruebaLaboratorio
											.getInstancia().getValueAt(fila, 0)));
							txtCedula.setText(String
									.valueOf(ModeloResultadoPruebaLaboratorio
											.getInstancia().getValueAt(fila, 1)));
							txtNombrePrueba.setText(String
									.valueOf(ModeloResultadoPruebaLaboratorio
											.getInstancia().getValueAt(fila, 2)));
							txtFechaResultados.setText(String
									.valueOf(ModeloResultadoPruebaLaboratorio
											.getInstancia().getValueAt(fila, 3)));
							txtHoraResultados.setText(String
									.valueOf(ModeloResultadoPruebaLaboratorio
											.getInstancia().getValueAt(fila, 4)));
							txtResultados.setText(String
									.valueOf(ModeloResultadoPruebaLaboratorio
											.getInstancia().getValueAt(fila, 6)));
						}
					}
				});

		scrollTabla = new JScrollPane(tablaResultados);
		scrollTabla.setBounds(362, 49, 660, 402);

		pnlDatosPruebas = new JPanel(new GridLayout(5, 2));
		pnlDatosPruebas.setBounds(10, 49, 342, 120);
		pnlDatosPruebas.add(lblNombre);
		pnlDatosPruebas.add(txtNombre);

		pnlDatosPruebas.add(lblCedula);
		pnlDatosPruebas.add(txtCedula);
		pnlDatosPruebas.add(lblNombrePrueba);
		pnlDatosPruebas.add(txtNombrePrueba);
		pnlDatosPruebas.add(lblFechaResultados);
		pnlDatosPruebas.add(txtFechaResultados);
		pnlDatosPruebas.add(lblHoraResultados);
		pnlDatosPruebas.add(txtHoraResultados);
		setLayout(null);

		pnlResultado = new JPanel();
		pnlResultado.setBounds(10, 170, 343, 299);
		pnlResultado.setLayout(null);

		lblResultados.setBounds(10, 11, 70, 14);
		pnlResultado.add(lblResultados);

		scrollResultados = new JScrollPane();
		scrollResultados.setBounds(90, 17, 243, 271);
		pnlResultado.add(scrollResultados);
		txtResultados = new JTextArea();
		txtResultados.setWrapStyleWord(true);
		txtResultados.setLineWrap(true);
		scrollResultados.setViewportView(txtResultados);
		txtResultados.setEditable(false);

		add(pnlBuscador);
		add(scrollTabla);
		add(pnlDatosPruebas);
		add(pnlResultado);

	}
}
