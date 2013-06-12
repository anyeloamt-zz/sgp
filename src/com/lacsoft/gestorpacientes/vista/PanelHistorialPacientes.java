package com.lacsoft.gestorpacientes.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lacsoft.gestorpacientes.entidades.Paciente;
import com.lacsoft.gestorpacientes.entidades.PacienteSeleccionadoEnHistorial;
import com.lacsoft.gestorpacientes.entidades.UsuarioLogueado;
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
import com.lacsoft.gestorpacientes.modelos.ModeloPaciente;

public class PanelHistorialPacientes extends JPanel {
	private JLabel lblBuscar, lblNombre, lblTelefono, lblApellido,
			lblFechaDeNacimiento, lblCedula, lblDireccion, lblFumador,
			lblAlergico, lblAlergias, lblFoto;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar, txtNombre, txtApellido, txtTelefono,
			txtFechaDeNacimiento, txtCedula, txtFumador, txtAlergico;
	private JTextArea textDireccion;
	private JButton btnBuscar, btnMasInformacion;
	private JPanel pnlBuscar, pnlTabla, pnlDatosPacientes, pnlDatosDireccion,
			pnlDatos;

	private JScrollPane scrollTablaPacientes;

	private FactoriaDeLabels factoriaDeLabels;
	private FactoriaDeTextos factoriaDeTextos;
	private FactoriaDeBotones factoriaDeBotones;
	private FactoriaDeTablas factoriaDeTablas;
	private FactoriaDeCombos factoriaDeCombos;
	private static final long serialVersionUID = 1L;
	private JTable tablaPacientes;
	private JPanel pnlDatosGeneralesPacientes;
	private JScrollPane scrollDireccion;
	private JPanel pnlDatosAlergias;
	private JTextArea textAlergias;
	private JScrollPane scrollAlergias;
	private JPanel pnlFoto;
	private JDesktopPane desktopFoto;

	public PanelHistorialPacientes() {

		factoriaDeLabels = new FactoriaDeLabelsGenericos();
		factoriaDeTextos = new FactoriaDeTextosGenericos();
		factoriaDeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		factoriaDeCombos = new FactoriaDeCombosGenericos();

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		comboBuscar.addItem("Cédula");

		txtBuscar = factoriaDeTextos.crearJTextField(15);

		btnBuscar = factoriaDeBotones.crearBoton("Buscar");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(125, 5, 400, 35);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);

		tablaPacientes = factoriaDeTablas.crearTabla();
		tablaPacientes.setModel(ModeloPaciente.getInstancia());
		scrollTablaPacientes = new JScrollPane();
		scrollTablaPacientes.setBounds(0, 51, 561, 498);
		scrollTablaPacientes.setViewportView(tablaPacientes);

		pnlTabla = new JPanel();
		pnlTabla.setLayout(null);
		pnlTabla.add(pnlBuscar);
		pnlTabla.add(scrollTablaPacientes);
		pnlTabla.setBounds(329, 11, 567, 560);
		add(pnlTabla);
		setLayout(null);
		lblNombre = factoriaDeLabels.crearLabel("Nombre");
		lblApellido = factoriaDeLabels.crearLabel("Apellido");
		lblTelefono = factoriaDeLabels.crearLabel("Teléfono");
		lblCedula = factoriaDeLabels.crearLabel("Cédula");
		lblDireccion = factoriaDeLabels.crearLabel("Direccion");
		lblFechaDeNacimiento = factoriaDeLabels
				.crearLabel("Fecha de nacimiento");
		lblFumador = factoriaDeLabels.crearLabel("Fuma");
		lblAlergico = factoriaDeLabels.crearLabel("Alérgico");
		lblAlergias = factoriaDeLabels.crearLabel("Alergias");

		txtNombre = factoriaDeTextos.crearJTextField();
		txtApellido = factoriaDeTextos.crearJTextField();
		txtTelefono = factoriaDeTextos.crearJTextField();
		txtCedula = factoriaDeTextos.crearJTextField();
		txtFechaDeNacimiento = factoriaDeTextos.crearJTextField();
		txtFumador = factoriaDeTextos.crearJTextField();
		txtAlergico = factoriaDeTextos.crearJTextField();

		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtTelefono.setEditable(false);
		txtCedula.setEditable(false);
		txtFechaDeNacimiento.setEditable(false);
		txtFumador.setEditable(false);
		txtAlergico.setEditable(false);

		pnlDatosPacientes = new JPanel();
		pnlDatosPacientes.setBounds(10, -114, 311, 685);
		pnlDatosPacientes.setLayout(null);

		pnlDatosGeneralesPacientes = new JPanel();
		pnlDatosGeneralesPacientes.setBounds(10, 175, 293, 454);
		pnlDatosPacientes.add(pnlDatosGeneralesPacientes);
		pnlDatosGeneralesPacientes.setLayout(null);

		pnlDatos = new JPanel();
		pnlDatos.setBounds(0, 0, 282, 227);
		pnlDatosGeneralesPacientes.add(pnlDatos);
		pnlDatos.setLayout(new GridLayout(7, 2));

		pnlDatos.add(lblNombre);
		pnlDatos.add(txtNombre);
		pnlDatos.add(lblApellido);
		pnlDatos.add(txtApellido);
		pnlDatos.add(lblTelefono);
		pnlDatos.add(txtTelefono);
		pnlDatos.add(lblCedula);
		pnlDatos.add(txtCedula);
		pnlDatos.add(lblFechaDeNacimiento);
		pnlDatos.add(txtFechaDeNacimiento);
		pnlDatos.add(lblFumador);
		pnlDatos.add(txtFumador);
		pnlDatos.add(lblAlergico);
		pnlDatos.add(txtAlergico);

		pnlDatosDireccion = new JPanel();
		pnlDatosDireccion.setBounds(0, 225, 291, 94);
		pnlDatosGeneralesPacientes.add(pnlDatosDireccion);
		pnlDatosDireccion.setLayout(null);
		lblDireccion.setBounds(0, 16, 60, 14);

		scrollDireccion = new JScrollPane();
		scrollDireccion.setBounds(66, 11, 219, 73);
		textDireccion = new JTextArea(3, 35);
		scrollDireccion.setViewportView(textDireccion);

		pnlDatosDireccion.add(lblDireccion);
		pnlDatosDireccion.add(scrollDireccion);

		pnlDatosAlergias = new JPanel();
		pnlDatosAlergias.setBounds(0, 329, 291, 114);
		pnlDatosAlergias.setLayout(null);
		scrollAlergias = new JScrollPane();
		scrollAlergias.setBounds(67, 11, 220, 103);
		pnlDatosAlergias.add(scrollAlergias);

		textAlergias = new JTextArea();
		textAlergias.setRows(5);
		scrollAlergias.setViewportView(textAlergias);

		lblAlergias.setBounds(0, 11, 46, 14);
		pnlDatosAlergias.add(lblAlergias);

		pnlDatosGeneralesPacientes.add(pnlDatosAlergias);
		add(pnlDatosPacientes);

		textDireccion.setEditable(false);
		textAlergias.setEditable(false);
		
		btnMasInformacion = new JButton("Más información");
		btnMasInformacion.setBounds(10, 640, 293, 34);
		if (!UsuarioLogueado.getInstancia().getRol().equals("Asistente")) {
			pnlDatosPacientes.add(btnMasInformacion);
		}

		lblFoto = new JLabel();

		pnlFoto = new JPanel();
		pnlFoto.setBounds(906, 60, 252, 210);
		add(pnlFoto);
		pnlFoto.setLayout(null);

		desktopFoto = new JDesktopPane();
		desktopFoto.setLayout(null);
		desktopFoto.setToolTipText("Foto");
		desktopFoto.setBounds(10, 11, 252, 210);
		pnlFoto.add(desktopFoto);

		lblFoto.setBounds(10, 11, 222, 177);
		desktopFoto.add(lblFoto);

		tablaPacientes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {
						int fila = tablaPacientes.getSelectedRow();
						if (fila >= 0) {
							txtNombre.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 0)));

							txtApellido.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 1)));

							txtTelefono.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 2)));

							txtCedula.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 3)));

							txtFechaDeNacimiento.setText(String
									.valueOf(ModeloPaciente.getInstancia()
											.getValueAt(fila, 4)));

							textDireccion.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 5)));

							txtFumador.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 6)));

							txtAlergico.setText(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 7)));

							if ("Si".equals(String.valueOf(ModeloPaciente
									.getInstancia().getValueAt(fila, 7)))) {
								textAlergias.setText(ModeloPaciente
										.getInstancia().getAlergiasPacientes(
												fila));
							} else {
								textAlergias.setText("");
							}

							lblFoto.setIcon(ModeloPaciente.getInstancia()
									.getImagenPaciente(fila));

						}
					}
				});

		btnMasInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaPacientes.getSelectedRow();
				if (fila >= 0) {
					Paciente p = ModeloPaciente.getInstancia()
							.getPacienteSeleccionado(fila);
					PacienteSeleccionadoEnHistorial.getInstacia()
							.setPacienteId(p.getId());
					VentanaHistorialPacientesDetalles.getInstancia();

				} else {
					JOptionPane.showMessageDialog(PanelHistorialPacientes.this,
							"Debe seleccionar un paciente", "Gestor Pacientes",
							JOptionPane.WARNING_MESSAGE);
				}
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
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar("btn",txtBuscar.getText().trim(),(String)comboBuscar.getSelectedItem());
			}
		});

	}
	
	public void buscar(String emisor, String texto, String item) {

		int columna = 1;

		if (item.equals("Nombre")) {
			columna = 0;
		} else if (item.equals("Apellido")) {
			columna = 1;
		} else {
			columna = 3;
		}
		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaPacientes.getRowCount(); i++) {
				if (((String) tablaPacientes.getValueAt(i, columna))
						.contains(texto)) {
					tablaPacientes.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaPacientes.getRowCount(); i++) {

				if (((String) tablaPacientes.getValueAt(i, columna))
						.equalsIgnoreCase(texto)) {
					tablaPacientes.changeSelection(i, columna, false, false);
				}
			}
		}
	}
}
