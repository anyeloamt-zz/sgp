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

import com.lacsoft.gestorpacientes.entidades.Medico;
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
import com.lacsoft.gestorpacientes.modelos.ModeloMedico;

public class VentanaSeleccionarMedico extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnSeleccionar;
	private JPanel pnlBuscar;
	private JPanel pnlPrincipal;
	private JTable tablaMedicos;
	private JScrollPane scrollTabla;
	private static Medico medicoSeleccionado;
	
	private JLabel lblFoto;
	private JDesktopPane dpFoto;

	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();

	private String emisor;
	
	private static VentanaSeleccionarMedico instancia;

	public static synchronized VentanaSeleccionarMedico getInstancia() {
		return instancia = (instancia == null) ? new VentanaSeleccionarMedico() : instancia;
	}

	private VentanaSeleccionarMedico() {
		super("Seleccionar médico");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Cédula");
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Especialidad");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnSeleccionar = factoriaDeBotones.crearBoton("Seleccionar");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(65, 30, 580, 35);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnSeleccionar);

		tablaMedicos = factoriaDeTablas.crearTabla();
		tablaMedicos.setModel(ModeloMedico.getInstancia());
		scrollTabla = new JScrollPane(tablaMedicos);
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
				int fila = tablaMedicos.getSelectedRow();
				if (fila >= 0) {
					
					medicoSeleccionado = ModeloMedico.getInstancia()
							.getPacienteSeleccionado(fila);
					
					if ("agregarCita".equals(emisor)) {
						PanelAgregarCita.getInstancia().setMedico(medicoSeleccionado);
					} else if ("editarCita".equals(emisor)) {
						PanelEditarOEliminarCita.getInstancia().setMedico(medicoSeleccionado);
					}
					
					cerrarVentana();
				} else {
					JOptionPane.showMessageDialog(
							VentanaSeleccionarMedico.this,
							"Debe seleccionar una fila", "Gestor Pacientes",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		
		tablaMedicos.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaMedicos.getSelectedRow();

						if (fila >= 0) {
							lblFoto.setIcon(ModeloMedico.getInstancia().getImagenMedico(fila));
						}
					}
				});
		
		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				buscar("txt",txtBuscar.getText().trim(),(String)comboBuscar.getSelectedItem());
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					btnBuscar.doClick();
				}
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar("btn",txtBuscar.getText().trim(),(String)comboBuscar.getSelectedItem());
			}
		});
	}
	
	public void buscar(String emisor, String texto, String item) {
		int columna = 0;

		if (item.equals("Cédula")) {
			columna = 3;
		} else if (item.equals("Nombre")) {
			columna = 0;
		} else {
			columna = 7;
		}

		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaMedicos.getRowCount(); i++) {
				if (((String) tablaMedicos.getValueAt(i, columna))
						.contains(texto)) {
					tablaMedicos.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaMedicos.getRowCount(); i++) {
				if (((String) tablaMedicos.getValueAt(i, columna))
						.equalsIgnoreCase(texto)) {
					tablaMedicos.changeSelection(i, columna, false, false);
				}
			}
		}
	}

	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}
	
	public void setEmisor(String emisor) {
		this.emisor = emisor;
		System.out.println(emisor);
	}

}
