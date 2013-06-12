package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

public class VentanaSeleccionarPaciente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnSeleccionar;
	private JPanel pnlBuscar;
	private JPanel pnlPrincipal;
	private JTable tablaPacientes;
	private JScrollPane scrollTabla;
	private static Paciente pacienteSeleccionado;
	
	private JLabel lblFoto;
	private JDesktopPane dpFoto;

	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();

	private String emisor;
	
	private static VentanaSeleccionarPaciente instancia;

	public static synchronized VentanaSeleccionarPaciente getInstancia() {
		return instancia = (instancia == null) ? new VentanaSeleccionarPaciente() : instancia;
	}

	private VentanaSeleccionarPaciente() {
		super("Seleccionar paciente");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Cédula");
		comboBuscar.addItem("Nombre");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnSeleccionar = factoriaDeBotones.crearBoton("Seleccionar");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(89, 30, 509, 35);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnSeleccionar);

		tablaPacientes = factoriaDeTablas.crearTabla();
		tablaPacientes.setModel(ModeloPaciente.getInstancia());
		scrollTabla = new JScrollPane(tablaPacientes);
		scrollTabla.setBounds(89, 69, 509, 412);

		pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(null);

		pnlPrincipal.add(pnlBuscar);
		pnlPrincipal.add(scrollTabla);

		getContentPane().add(pnlPrincipal);

		dpFoto = new JDesktopPane();
		dpFoto.setBounds(622, 69, 252, 210);
		dpFoto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		pnlPrincipal.add(dpFoto);

		lblFoto = new JLabel();
		lblFoto.setBounds(10, 11, 232, 188);
		dpFoto.add(lblFoto);
		getContentPane().add(BorderLayout.NORTH,
				new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setSize(900, 610);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		
		btnSeleccionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = tablaPacientes.getSelectedRow();
				if (fila >= 0) {
					
					pacienteSeleccionado = ModeloPaciente.getInstancia()
							.getPacienteSeleccionado(fila);
					
					if("agregarReceta".equals(emisor)) {
						PanelAgregarReceta.getInstancia().setPaciente(pacienteSeleccionado);
					} else if ("editarReceta".equals(emisor)) {
						PanelEditarOEliminarReceta.getInstancia().setPaciente(pacienteSeleccionado);
					} else if ("hacerPruebaLaboratorio".equals(emisor)) {
						PanelHacerPruebaLaboratorio.getInstancia()
						.setTxtNombrePaciente(
								pacienteSeleccionado.getNombre());
					} else if ("agregarCita".equals(emisor)) {
						PanelAgregarCita.getInstancia().setPaciente(pacienteSeleccionado);
					} else if ("editarCita".equals(emisor)) {
						PanelEditarOEliminarCita.getInstancia().setPaciente(pacienteSeleccionado);
					}
					
					cerrarVentana();
				} else {
					JOptionPane.showMessageDialog(
							VentanaSeleccionarPaciente.this,
							"Debe seleccionar una fila", "Gestor Pacientes",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		
		tablaPacientes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaPacientes.getSelectedRow();

						if (fila >= 0) {
							lblFoto.setIcon(ModeloPaciente.getInstancia()
									.getImagenPaciente(fila));
						}
					}
				});
		
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
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});
	}
	
	public void buscar(String emisor, String texto, String item) {
		int columna = 0;

		if (item.equals("Cédula")) {
			columna = 3;
		} else {
			columna = 0;
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

	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}

	public static Paciente getPacienteSeleccionado() {
		return pacienteSeleccionado;
	}
	
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

}
