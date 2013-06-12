package com.lacsoft.gestorpacientes.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.entidades.ResultadoDeLaboratorio;
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

public class PanelHacerPruebaLaboratorio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente;
	private JTextField txtNombrePrueba;
	private JPanel pnlBuscar;
	private JLabel lblBuscar, lblDatosDeLaPrueba,lblPaciente,lblNombreDeLaPrueba;
	private JTextField txtBuscar;
	private JComboBox<String> comboBuscar;
	private JButton btnBuscar, btnLimpiar, btnAgregarPruebasExistentes,
			btnAgregarPrueba, btnAgregarPacientes;
	private FactoriaDeLabels factoriaLabel;
	private FactoriaDeCombos factoriaDeCombos;
	private FactoriaDeBotones factoriadeBotones;
	private FactoriaDeTextos factoriaText;
	private FactoriaDeTablas factoriaTabla;
	private JScrollPane scrollPruebas;
	private JTable tablaPruebaLaboratorio;
	private JPanel pnlComponentes;
	private static PanelHacerPruebaLaboratorio instancia;

	public static synchronized PanelHacerPruebaLaboratorio getInstancia() {
		return instancia = (instancia == null) ? instancia = new PanelHacerPruebaLaboratorio()
				: instancia;
	}

	private PanelHacerPruebaLaboratorio() {

		factoriaLabel = new FactoriaDeLabelsGenericos();
		factoriaText = new FactoriaDeTextosGenericos();
		factoriadeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		factoriaTabla = new FactoriaDeTablasGenericas();

		lblBuscar = factoriaLabel.crearLabel("Buscar por");
		txtBuscar = factoriaText.crearJTextField(15);
		btnBuscar = factoriadeBotones.crearBoton("Buscar");

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre del paciente");
		comboBuscar.addItem("Nombre de la prueba");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(322, 11, 600, 33);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		

		pnlComponentes = new JPanel();
		pnlComponentes.setBounds(10, 71, 288, 135);

		pnlComponentes.setLayout(null);

		lblPaciente = factoriaLabel.crearLabel("Paciente");
		lblPaciente.setBounds(0, 4, 55, 14);
		pnlComponentes.add(lblPaciente);

		lblNombreDeLaPrueba = factoriaLabel.crearLabel("Nombre de la prueba");
		lblNombreDeLaPrueba.setBounds(0, 31, 120, 14);
		pnlComponentes.add(lblNombreDeLaPrueba);

		txtPaciente = factoriaText.crearJTextField(15);
		txtPaciente.setBounds(120, 1, 92, 23);
		pnlComponentes.add(txtPaciente);
		txtPaciente.setColumns(10);
		txtPaciente.setEditable(false);

		txtNombrePrueba = factoriaText.crearJTextField(15);
		txtNombrePrueba.setBounds(120, 28, 92, 23);
		pnlComponentes.add(txtNombrePrueba);
		txtNombrePrueba.setColumns(10);
		txtNombrePrueba.setEditable(false);

		btnAgregarPruebasExistentes = factoriadeBotones.crearBoton("...");
		btnAgregarPruebasExistentes.setBounds(212, 27, 30, 25);
		pnlComponentes.add(btnAgregarPruebasExistentes);

		btnAgregarPacientes = factoriadeBotones.crearBoton("...");
		btnAgregarPacientes.setBounds(212, 0, 30, 25);
		pnlComponentes.add(btnAgregarPacientes);

		btnAgregarPrueba = factoriadeBotones.crearBoton("Agregar prueba");
		btnAgregarPrueba.setBounds(0, 67, 135, 28);
		pnlComponentes.add(btnAgregarPrueba);

		btnLimpiar = factoriadeBotones.crearBoton("Limpiar");
		btnLimpiar.setBounds(145, 67, 89, 28);
		pnlComponentes.add(btnLimpiar);

		tablaPruebaLaboratorio = factoriaTabla.crearTabla();
		tablaPruebaLaboratorio.setModel(ModeloHacerPruebaLaboratorio
				.getInstancia());

		scrollPruebas = new JScrollPane(tablaPruebaLaboratorio);
		scrollPruebas.setBounds(322, 71, 555, 315);

		lblDatosDeLaPrueba = factoriaLabel.crearLabel("Datos de la prueba");
		lblDatosDeLaPrueba.setBounds(10, 46, 120, 14);

		setLayout(null);

		add(pnlComponentes);
		add(lblDatosDeLaPrueba);
		add(pnlBuscar);
		add(scrollPruebas);

		btnAgregarPruebasExistentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSeleccionarPrueba.getInstancia();

			}
		});

		btnAgregarPacientes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				VentanaSeleccionarPaciente.getInstancia().setEmisor("hacerPruebaLaboratorio");

			}
		});

		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});

		btnAgregarPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtNombrePrueba.getText().trim().equals("")
						|| txtPaciente.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Debe llenar todos los campos");
				} else {
					Calendar calendario = Calendar.getInstance();
					int hora = calendario.get(Calendar.HOUR);
					int minuto = calendario.get(Calendar.MINUTE);
					int segundo = calendario.get(Calendar.SECOND);

					String fecha = new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date());
					String horaCompleta = String.valueOf(hora) + ":"
							+ String.valueOf(minuto) + ":"
							+ String.valueOf(segundo);

					ModeloHacerPruebaLaboratorio.getInstancia()
							.agregarResultado(
									new ResultadoDeLaboratorio(
											PanelSeleccionarPrueba
													.getPruebaSeleccionado()
													.getCodigo(), fecha,
											horaCompleta, "Pendiente", "",
											VentanaSeleccionarPaciente
													.getPacienteSeleccionado()
													.getId()));
					limpiar();
				}
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtBuscar.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Rellene el campo busar");
				} else {

				}

			}
		});
	}

	public void buscar(String emisor, String texto, String item) {

		int columna = 0;
		if (item.equals("Nombre del paciente"))
			columna = 1;
		else {
			columna = 2;
		}
		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaPruebaLaboratorio.getRowCount(); i++) {
				if (((String) tablaPruebaLaboratorio.getValueAt(i, columna))
						.contains(texto)) {
					tablaPruebaLaboratorio.changeSelection(i, columna, false,
							false);
				}
			}
		} else {
			for (int j = 0; j < tablaPruebaLaboratorio.getRowCount(); j++) {
				if (((String) tablaPruebaLaboratorio.getValueAt(j, columna))
						.equalsIgnoreCase(texto)) {
					tablaPruebaLaboratorio.changeSelection(j, columna, false,
							false);
				}
			}

		}
	}

	public void setTxtNombrePaciente(String texto) {
		txtPaciente.setText(texto);
	};

	public void limpiar() {
		txtNombrePrueba.setText("");
		txtPaciente.setText("");
	}

	public void setTxtNombrePrueba(String texto) {
		txtNombrePrueba.setText(texto);
	};
}
