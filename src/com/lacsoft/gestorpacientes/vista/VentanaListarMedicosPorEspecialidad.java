package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.divxdede.swing.busy.DefaultBusyModel;
import org.divxdede.swing.busy.JBusyComponent;

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
import com.lacsoft.gestorpacientes.modelos.ModeloEspecialidad;
import com.lacsoft.gestorpacientes.modelos.ModeloMedicosPorEspecialidad;

public class VentanaListarMedicosPorEspecialidad extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static VentanaListarMedicosPorEspecialidad instancia;
	private JTable tablaMedicos;
	private JTextField txtBuscar;
	private JPanel pnlPrincipal;
	private JLabel lblEspecialidades;
	private JScrollPane scrollEspecialidades;
	private JList<String> listaEspecialidades;
	private JLabel lblBuscarPor;
	private JComboBox<String> comboBuscar;
	private JButton btnBuscar;

	private JPanel pnlBuscar;

	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();

	private JScrollPane scrollTabla;

	private JLabel lblFoto;

	private JDesktopPane dpFoto;
	private JLabel lblNombreFoto;
	private JTextField txtBuscarEspecialidad;

	private JButton btnBuscarEspecialidad;

	private JLabel lblBuscarEspecialidad;

	private DefaultListModel<String> modeloLista = new DefaultListModel<>();
	private JButton btnListar;
	
	private JBusyComponent<JScrollPane> tablaBusy;
	private DefaultBusyModel modeloBusy;

	public static synchronized VentanaListarMedicosPorEspecialidad getInstancia() {
		return instancia = (instancia == null) ? new VentanaListarMedicosPorEspecialidad()
				: instancia;
	}

	private VentanaListarMedicosPorEspecialidad() {
		super("Lista de médicos (por especialidad)");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(1261, 700);

		pnlPrincipal = new JPanel();
		getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(null);

		lblEspecialidades = factoriaDeLabels.crearLabel("Especialidades");
		lblEspecialidades.setBounds(22, 45, 87, 14);
		pnlPrincipal.add(lblEspecialidades);

		scrollEspecialidades = new JScrollPane();
		scrollEspecialidades.setBounds(22, 102, 338, 450);
		pnlPrincipal.add(scrollEspecialidades);

		listaEspecialidades = new JList<>(modeloLista);
		listaEspecialidades.setFont(new Font("Calibri", Font.PLAIN, 14));
		listaEspecialidades.setToolTipText("Haga doble clic para listar");
		scrollEspecialidades.setViewportView(listaEspecialidades);

		cargarEspecialidades();

		scrollTabla = new JScrollPane();
		scrollTabla.setBounds(388, 70, 589, 482);
		//pnlPrincipal.add(scrollTabla);

		tablaMedicos = factoriaDeTablas.crearTabla();
		tablaMedicos.setModel(ModeloMedicosPorEspecialidad.getInstancia());
		scrollTabla.setViewportView(tablaMedicos);
		
		tablaBusy = new JBusyComponent<>(scrollTabla);
		modeloBusy = new DefaultBusyModel();
		tablaBusy.setBusyModel(modeloBusy);
		tablaBusy.setBounds(388, 70, 589, 482);
		pnlPrincipal.add(tablaBusy);

		lblBuscarPor = factoriaDeLabels.crearLabel("Buscar por");

		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");

		txtBuscar = factoriaDeTextos.crearJTextField(15);

		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		
		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(388, 33, 589, 35);
		pnlBuscar.add(lblBuscarPor);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		
		pnlPrincipal.add(pnlBuscar);

		dpFoto = new JDesktopPane();
		dpFoto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		dpFoto.setBounds(987, 70, 249, 215);
		pnlPrincipal.add(dpFoto);

		lblFoto = new JLabel(new ImageIcon("Imagenes/icono.PNG"));
		lblFoto.setBounds(10, 11, 229, 193);
		dpFoto.add(lblFoto);

		lblNombreFoto = factoriaDeLabels.crearLabel("Nombre del doctor");
		lblNombreFoto.setBounds(825, 296, 122, 21);
		pnlPrincipal.add(lblNombreFoto);

		lblBuscarEspecialidad = factoriaDeLabels.crearLabel("Buscar");
		lblBuscarEspecialidad.setBounds(22, 68, 43, 22);
		pnlPrincipal.add(lblBuscarEspecialidad);

		txtBuscarEspecialidad = factoriaDeTextos.crearJTextField(12);
		txtBuscarEspecialidad.setBounds(75, 67, 97, 28);
		txtBuscarEspecialidad.setToolTipText("Escriba el nombre de la especialidad");
		pnlPrincipal.add(txtBuscarEspecialidad);

		btnBuscarEspecialidad = factoriaDeBotones.crearBoton("Buscar");
		btnBuscarEspecialidad.setBounds(182, 66, 88, 30);
		pnlPrincipal.add(btnBuscarEspecialidad);

		btnListar = factoriaDeBotones.crearBoton("Listar");
		btnListar.setBounds(270, 66, 88, 30);
		btnListar.setToolTipText("Listar médicos por especialidad");
		pnlPrincipal.add(btnListar);

		getContentPane().add(BorderLayout.NORTH,
				new JLabel(new ImageIcon("Imagenes/mantenimientosBanner.JPG")));
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});

		txtBuscarEspecialidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				buscarEnLista("txt", txtBuscarEspecialidad.getText().trim());
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnBuscarEspecialidad.doClick();
				}
			}
		});

		btnBuscarEspecialidad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buscarEnLista("btn", txtBuscarEspecialidad.getText().trim());
			}
		});

		tablaMedicos.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaMedicos.getSelectedRow();
						if (fila >= 0) {
							lblFoto.setIcon(ModeloMedicosPorEspecialidad
									.getInstancia().cargarFoto(fila));
						}
					}
				});

		btnListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listarPorEspecialidad();
			}
		});
		
		txtBuscar.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				buscarEnTabla("txt", (String) comboBuscar.getSelectedItem(), 
						txtBuscar.getText().trim());
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarEnTabla("btn", (String) comboBuscar.getSelectedItem(), 
						txtBuscar.getText().trim());
			}
		});
		
		listaEspecialidades.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					listarPorEspecialidad();
				}
			}
		});
	}

	public void buscarEnLista(String emisor, String texto) {
		if (emisor.equals("txt")) {
			for (int i = 0; i < modeloLista.getSize(); i++) {
				if (modeloLista.get(i).contains(texto)) {
					listaEspecialidades.setSelectedIndex(i);
				}
			}

		} else {
			for (int i = 0; i < modeloLista.getSize(); i++) {
				if (modeloLista.get(i).equalsIgnoreCase(texto)) {
					listaEspecialidades.setSelectedIndex(i);
				}
			}
		}
	}

	public void buscarEnTabla(String emisor, String item, String texto) {
		int columna = 0;
		
		if (item.equals("Apellido")) {
			columna = 1;
		}
		
		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaMedicos.getRowCount(); i++) {
				if (((String) tablaMedicos.getValueAt(i, columna)).contains(texto)) {
					tablaMedicos.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaMedicos.getRowCount(); i++) {
				if (((String) tablaMedicos.getValueAt(i, columna)).equalsIgnoreCase(texto)) {
					tablaMedicos.changeSelection(i, columna, false, false);
				}
			}
		}
	}

	public void cargarEspecialidades() {
		for (String especialidad : ModeloEspecialidad.getInstancia()
				.listarEspecialidades()) {
			modeloLista.addElement(especialidad);
		}
	}
	
	public void listarPorEspecialidad() {

		int fila = listaEspecialidades.getSelectedIndex();

		if (fila >= 0) {
			new SwingWorker<Void, Void>() {

				@Override
				protected Void doInBackground() throws Exception {
					modeloBusy.setBusy(true);
					
					ModeloMedicosPorEspecialidad.getInstancia().listarPorEsperialidad(
							listaEspecialidades.getSelectedValue());
					lblFoto.setIcon(new ImageIcon("Imagenes/icono.PNG"));
					
					return null;
				}
				
				@Override
				protected void done() {
					modeloBusy.setBusy(false);
				}
			}.execute();
			
		} else {
			JOptionPane.showMessageDialog(this,
					"Debe seleccionar una especialidad",
					"Especialidad no seleccionada",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void cerrarVentana() {
		instancia = null;
		dispose();
	}
}
