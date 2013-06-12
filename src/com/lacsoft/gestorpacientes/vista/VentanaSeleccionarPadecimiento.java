package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.entidades.Padecimiento;
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

public class VentanaSeleccionarPadecimiento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnSeleccionar;
	private JPanel pnlBuscar, pnlPrincipal;
	private JTable tablaPadecimiento;
	private JScrollPane scrollTabla;
	
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	
	private String emisor;
	
	private static VentanaSeleccionarPadecimiento instancia;

	public static synchronized VentanaSeleccionarPadecimiento getInstancia() {
		return instancia = (instancia == null) ? new VentanaSeleccionarPadecimiento() : instancia;
	}

	private VentanaSeleccionarPadecimiento() {
		
		super("Seleccionar padecimiento");
		setIconImage(new ImageIcon("Imagenes/icono.PNG").getImage());

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Código");
		comboBuscar.addItem("Nombre");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnSeleccionar = factoriaDeBotones.crearBoton("Seleccionar");

		pnlBuscar = new JPanel();
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnSeleccionar);

		tablaPadecimiento = factoriaDeTablas.crearTabla();
		tablaPadecimiento.setModel(ModeloPadecimiento.getInstancia());
		scrollTabla = new JScrollPane(tablaPadecimiento);
		
		pnlPrincipal = new JPanel();
		
		pnlPrincipal.add(pnlBuscar);
		pnlPrincipal.add(scrollTabla);
		
		add(pnlPrincipal);
		add(BorderLayout.NORTH, new JLabel(new ImageIcon(
				"Imagenes/mantenimientosBanner.JPG")));
		setSize(900, 600);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrarVentana();
			}
		});
		
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaPadecimiento.getSelectedRow();
				if (fila >= 0) {
					
					String codigo = (String) ModeloPadecimiento.getInstancia().getValueAt(fila, 0);
					String nombre = (String) ModeloPadecimiento.getInstancia().getValueAt(fila, 1);
					
					Padecimiento padecimiento = new Padecimiento(codigo, nombre);
					
					if (emisor.equals("agregarReceta")) {
						PanelAgregarReceta.getInstancia().setPadecimiento(padecimiento);
					} else if (emisor.equals("editarReceta")) {
						PanelEditarOEliminarReceta.getInstancia().setPadecimiento(padecimiento);
					}
					
					cerrarVentana();
				} else {
					JOptionPane.showMessageDialog(
							VentanaSeleccionarPadecimiento.this,
							"Debe seleccionar un padecimiento",
							"Padecimiento no seleccionado",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	
	private void cerrarVentana() {
		instancia = null;
		this.dispose();
	}

}

