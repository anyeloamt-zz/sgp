package com.lacsoft.gestorpacientes.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import com.lacsoft.gestorpacientes.entidades.Paciente;
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
import com.lacsoft.gestorpacientes.utilidades.RedimensionadoraDeFotos;
import com.toedter.calendar.JDateChooser;

public class PanelAgregaPacientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNombre, lblApellido, lblTelefono, lblCedula, lblFecNac,
			lblFumador, lblAlergico, lblBuscar, lblFotoMostrar, lblFoto,
			lblAlergias, lblDireccion;
	private JTextField txtNombre, txtApellido, txtCedula, txtTelefono, txtFoto,
			txtBuscar;
	private JComboBox<String> comboFumador, comboAlergico, comboBuscar;
	private JDateChooser selectorFecha;
	private JPanel pnlDatos, pnlFoto, pnlBotones, pnlBuscador;
	private JDesktopPane desktopPaneFoto;
	private JTable tablaPacientes;
	private JButton btnAgregar, btnLimpiar, btnBuscar, btnSelectorImagen,
			btnAlergia;
	private JScrollPane scrollTabla, scrollDireccion;
	private ArrayList<JTextComponent> textos;
	private JTextArea txtDireccion;

	private FactoriaDeLabels factoriaLabel;
	private FactoriaDeTextos factoriaText;
	private FactoriaDeBotones factoriadeBotones;
	private FactoriaDeCombos factoriaDeCombos;
	private FactoriaDeTablas factoriaDeTablas;
	private static PanelAgregaPacientes instancia = null;

	public static synchronized PanelAgregaPacientes getInstancia() {
		return instancia = (instancia == null) ? new PanelAgregaPacientes()
				: instancia;
	}

	private PanelAgregaPacientes() {

		factoriaLabel = new FactoriaDeLabelsGenericos();
		factoriaText = new FactoriaDeTextosGenericos();
		factoriadeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		textos = new ArrayList<JTextComponent>();

		lblNombre = factoriaLabel.crearLabel("Nombre");
		lblApellido = factoriaLabel.crearLabel("Apellido");
		lblTelefono = factoriaLabel.crearLabel("Teléfono");
		lblCedula = factoriaLabel.crearLabel("Cédula");
		lblFecNac = factoriaLabel.crearLabel("Fecha de nacimiento");
		lblFumador = factoriaLabel.crearLabel("Fuma");
		lblAlergico = factoriaLabel.crearLabel("Alérgico");
		lblAlergias = factoriaLabel.crearLabel("Alergias");
		lblAlergias.setEnabled(false);
		btnAlergia = factoriadeBotones.crearBoton("Mantenimiento de alergias");
		btnAlergia.setEnabled(false);
		btnAlergia.setToolTipText("Clic para agregar alergias a este paciente");
		btnAlergia.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				VentanaMantenimientoAlergia.getInstancia();
			}
		});
	

		txtNombre = factoriaText.crearJTextField();
		txtApellido = factoriaText.crearJTextField();
		txtTelefono = factoriaText.crearJtextFieldEnmascarado("(###) ###-####");
		txtCedula = factoriaText.crearJtextFieldEnmascarado("###-#######-#");
		selectorFecha = new JDateChooser(new Date());
		selectorFecha.setToolTipText("Clic para agregar la fecha de nacimiento");

		comboFumador = factoriaDeCombos.crearCombo();
		comboFumador.addItem("No");
		comboFumador.addItem("Si");

		comboAlergico = factoriaDeCombos.crearCombo();
		comboAlergico.addItem("No");
		comboAlergico.addItem("Si");
		comboAlergico.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent item) {
				if (comboAlergico.getSelectedItem().equals("Si")) {
					lblAlergias.setEnabled(true);
					btnAlergia.setEnabled(true);
				} else {
					lblAlergias.setEnabled(false);
					btnAlergia.setEnabled(false);
				}

			}
		});

		pnlDatos = new JPanel();
		pnlDatos.setBounds(10, 248, 342, 205);
		pnlDatos.setLayout(new GridLayout(8, 2));

		pnlDatos.add(lblNombre);
		pnlDatos.add(txtNombre);
		pnlDatos.add(lblApellido);
		pnlDatos.add(txtApellido);
		pnlDatos.add(lblTelefono);
		pnlDatos.add(txtTelefono);
		pnlDatos.add(lblCedula);
		pnlDatos.add(txtCedula);
		pnlDatos.add(lblFecNac);
		pnlDatos.add(selectorFecha);
		pnlDatos.add(lblFumador);
		pnlDatos.add(comboFumador);
		pnlDatos.add(lblAlergico);
		pnlDatos.add(comboAlergico);
		pnlDatos.add(lblAlergias);
		pnlDatos.add(btnAlergia);

		pnlFoto = new JPanel();
		pnlFoto.setBounds(42, 11, 296, 238);
		pnlFoto.setLayout(null);

		desktopPaneFoto = new JDesktopPane();
		desktopPaneFoto.setBounds(0, 0, 252, 210);
		desktopPaneFoto.setToolTipText("Foto");
		desktopPaneFoto.setLayout(null);

		lblFotoMostrar = new JLabel(new ImageIcon("Imagenes/icono.png"));
		lblFotoMostrar.setLocation(10, 11);
		lblFotoMostrar.setSize(232, 188);
		desktopPaneFoto.add(lblFotoMostrar);
		pnlFoto.add(desktopPaneFoto);

		lblFoto = factoriaLabel.crearLabel("Foto");
		lblFoto.setBounds(12, 215, 52, 14);
		pnlFoto.add(lblFoto);

		txtFoto = factoriaText.crearJTextField(15);
		txtFoto.setEditable(false);
		txtFoto.setBounds(73, 212, 86, 20);
		pnlFoto.add(txtFoto);

		btnSelectorImagen = factoriadeBotones.crearBoton("...");
		btnSelectorImagen.setBounds(169, 210, 75, 23);
		btnSelectorImagen.setToolTipText("Clic para agregar foto");
		btnSelectorImagen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				FileNameExtensionFilter formatos = new FileNameExtensionFilter(
						"Archivos de imágenes", "JPG", "PNG", "GIF");
				selector.setFileFilter(formatos);

				int seleccion = selector
						.showOpenDialog(PanelAgregaPacientes.this);

				if (seleccion == JFileChooser.APPROVE_OPTION) {
					String archivo = selector.getSelectedFile()
							.getAbsolutePath();
					txtFoto.setText(archivo);
					lblFotoMostrar.setIcon(RedimensionadoraDeFotos
							.redimensionar(new ImageIcon(archivo)));
				}
			}
		});
		pnlFoto.add(btnSelectorImagen);

		scrollTabla = new JScrollPane();
		scrollTabla.setBounds(373, 55, 661, 539);

		tablaPacientes = factoriaDeTablas.crearTabla();
		tablaPacientes.setModel(ModeloPaciente.getInstancia());
		scrollTabla.setViewportView(tablaPacientes);

		pnlBotones = new JPanel();
		pnlBotones.setBounds(10, 576, 342, 44);
		pnlBotones.setLayout(null);

		btnAgregar = factoriadeBotones.crearBoton("Agregar");
		btnAgregar.setBounds(0, 0, 164, 28);
		btnAgregar.setToolTipText("Agregar paciente al sistema");
		btnAgregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (comboAlergico.getSelectedItem().equals("Si")
						&& PanelMantenimientoAlergia.getAlergiaSeleccionada()
								.isEmpty()) {

					JOptionPane
							.showMessageDialog(
									PanelAgregaPacientes.this,
									"Debe seleccionar alguna alergia si el pacientes es alérgico",
									"Advertencia", JOptionPane.WARNING_MESSAGE);

				} else {
					if (validar()) {
						JOptionPane.showMessageDialog(
								PanelAgregaPacientes.this,
								"Debe de completar todos los datos",
								"Advertencia", JOptionPane.WARNING_MESSAGE);
					} else {
						String fecha = new SimpleDateFormat("yyyy/MM/dd")
								.format(selectorFecha.getDate());
						ModeloPaciente.getInstancia().agregarPacientes(
								new Paciente(txtNombre.getText().trim(),
										txtApellido.getText().trim(),
										txtTelefono.getText().trim(), txtCedula
												.getText().trim(), fecha,
										String.valueOf(comboFumador
												.getSelectedIndex()), String
												.valueOf(comboAlergico
														.getSelectedIndex()),
										txtFoto.getText().trim(), txtDireccion
												.getText().trim()),
								PanelMantenimientoAlergia
										.getAlergiaSeleccionada());
						limpiar();
					}
				}
			}

		});
		pnlBotones.add(btnAgregar);

		btnLimpiar = factoriadeBotones.crearBoton("Limpiar");
		btnLimpiar.setBounds(174, 0, 168, 28);
		btnLimpiar.setToolTipText("Limpiar todos los campos");
		btnLimpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpiar();

			}
		});
		pnlBotones.add(btnLimpiar);

		pnlBuscador = new JPanel();
		pnlBuscador.setBounds(456, 11, 600, 33);
		pnlBuscador.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblBuscar = factoriaLabel.crearLabel("Buscar por: ");
		pnlBuscador.add(lblBuscar);

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		comboBuscar.addItem("Cédula");
		pnlBuscador.add(comboBuscar);

		txtBuscar = factoriaText.crearJTextField(15);
		pnlBuscador.add(txtBuscar);

		btnBuscar = factoriadeBotones.crearBoton("Buscar");
		pnlBuscador.add(btnBuscar);

		setLayout(null);
		add(pnlFoto);
		
		add(pnlDatos);
		add(pnlBotones);
		add(pnlBuscador);
		add(scrollTabla);

		lblDireccion = factoriaLabel.crearLabel("Direccion");
		lblDireccion.setBounds(10, 493, 70, 14);
		add(lblDireccion);

		scrollDireccion = new JScrollPane();
		scrollDireccion.setBounds(182, 464, 170, 101);
		add(scrollDireccion);

		txtDireccion = new JTextArea();
		txtDireccion.setWrapStyleWord(true);
		txtDireccion.setLineWrap(true);
		scrollDireccion.setViewportView(txtDireccion);

		textos.add(txtApellido);
		textos.add(txtNombre);
		textos.add(txtCedula);
		textos.add(txtTelefono);
		textos.add(txtFoto);
		textos.add(txtDireccion);
		
		tablaPacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			public void valueChanged(ListSelectionEvent e) {
				int fila = tablaPacientes.getSelectedRow();
				
				if (fila >= 0) {
					lblFotoMostrar.setIcon(ModeloPaciente.getInstancia()
							.getImagenPaciente(fila));
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

	public boolean validar() {
		boolean retorno = false;
		for (JTextComponent texto : textos) {
			if (texto.getText().trim().equals("")
					|| texto.getText().trim().equals(" ")) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	public void limpiar() {
		for (JTextComponent texto : textos) {
			texto.setText("");
			comboFumador.setSelectedIndex(0);
			comboAlergico.setSelectedIndex(0);
			lblFotoMostrar.setIcon(new ImageIcon("Imagenes/icono.PNG"));
			setBotonMantenimientoAlergia(false);
			PanelMantenimientoAlergia.limpiarAlergiaSeleccionada();
		}
	}

	public void setBotonMantenimientoAlergia(boolean estaActivo) {
		if (estaActivo == true) {
			btnAlergia.setForeground(Color.red);
		} else {
			btnAlergia.setForeground(Color.black);
		}
	}
}
