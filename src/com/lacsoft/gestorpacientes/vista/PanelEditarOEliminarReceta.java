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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lacsoft.gestorpacientes.entidades.Paciente;
import com.lacsoft.gestorpacientes.entidades.Padecimiento;
import com.lacsoft.gestorpacientes.entidades.Receta;
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
import com.lacsoft.gestorpacientes.modelos.ModeloReceta;
import com.lacsoft.gestorpacientes.reportes.Reportes;

public class PanelEditarOEliminarReceta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente, txtPadecimiento;
	private JTextArea txtMedicamentos;
	private JButton btnGuardar,btnImprimir, btnCancelar, btnPaciente, btnPadecimiento;
	private JLabel lblPaciente, lblMediacamento, lblPadecimiento;
	private JTable tablaRecetas;
	private Reportes reporte = new Reportes();
	private JScrollPane scrollTabla, scrollMedicamentos;
	private JPanel pnlPaciente, pnlPadecimiento, pnlAgregar, pnlMedicamento,
			pnlBotones;

	private JPanel pnlBuscar;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JButton btnEliminar;

	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	private String codigoPadecimiento, idPaciente;

	private static PanelEditarOEliminarReceta instancia;

	public static synchronized PanelEditarOEliminarReceta getInstancia() {
		return instancia = (instancia == null) ? new PanelEditarOEliminarReceta() : instancia;
	}

	private PanelEditarOEliminarReceta() {

		setLayout(null);
		lblPaciente = factoriaDeLabels.crearLabel("Paciente");
		lblPadecimiento = factoriaDeLabels.crearLabel("Padecimiento");
		lblMediacamento = factoriaDeLabels.crearLabel("Medicamentos");

		txtPaciente = factoriaDeTextos.crearJTextField(10);
		txtPadecimiento = factoriaDeTextos.crearJTextField(10);
		txtMedicamentos = new JTextArea(10, 35);
		txtMedicamentos.setWrapStyleWord(true);
		txtMedicamentos.setLineWrap(true);

		txtPaciente.setEditable(false);
		txtPadecimiento.setEditable(false);

		scrollMedicamentos = new JScrollPane(txtMedicamentos);

		btnPaciente = factoriaDeBotones.crearBoton("...");
		btnPadecimiento = factoriaDeBotones.crearBoton("...");
		btnImprimir = factoriaDeBotones.crearBoton("Imprimir receta");

		tablaRecetas = factoriaDeTablas.crearTabla();
		tablaRecetas.setModel(ModeloReceta.getInstancia());
		scrollTabla = new JScrollPane(tablaRecetas);
		scrollTabla.setBounds(379, 54, 517, 402);

		pnlPaciente = new JPanel();
		pnlPaciente.add(txtPaciente);
		pnlPaciente.add(btnPaciente);
		pnlPaciente.setLayout(new BoxLayout(pnlPaciente, BoxLayout.X_AXIS));

		pnlPadecimiento = new JPanel();
		pnlPadecimiento.add(txtPadecimiento);
		pnlPadecimiento.add(btnPadecimiento);
		pnlPadecimiento.setLayout(new BoxLayout(pnlPadecimiento,
				BoxLayout.X_AXIS));

		pnlMedicamento = new JPanel();
		pnlMedicamento.setBounds(20, 98, 310, 225);
		pnlMedicamento.add(lblMediacamento);
		pnlMedicamento.add(scrollMedicamentos);

		pnlAgregar = new JPanel(new GridLayout(2, 2));
		pnlAgregar.setBounds(20, 43, 310, 50);
		pnlAgregar.add(lblPaciente);
		pnlAgregar.add(pnlPaciente);
		pnlAgregar.add(lblPadecimiento);
		pnlAgregar.add(pnlPadecimiento);

		add(pnlAgregar);
		add(pnlMedicamento);
		add(scrollTabla);

		btnGuardar = factoriaDeBotones.crearBoton("Guardar");
		btnCancelar = factoriaDeBotones.crearBoton("Cancelar");

		pnlBotones = new JPanel();
		pnlBotones.setBounds(20, 334, 300, 50);
		pnlBotones.add(btnGuardar);
		pnlBotones.add(btnCancelar);
		pnlBotones.add(btnImprimir);
		add(pnlBotones);

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Fecha");
		comboBuscar.addItem("Paciente");
		comboBuscar.addItem("Padecimiento");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnEliminar = factoriaDeBotones.crearBoton("Eliminar");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(375, 11, 535, 35);
		add(pnlBuscar);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);
		
		btnPaciente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarPaciente.getInstancia().setEmisor(
						"editarReceta");
			}
		});
		btnPadecimiento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarPadecimiento.getInstancia().setEmisor(
						"editarReceta");
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
		
		btnImprimir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				imprimirRecetas();
				
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar("btn", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaRecetas.getSelectedRow();

				if (fila >= 0) {
					int confirmar = JOptionPane.showConfirmDialog(
							PanelEditarOEliminarReceta.this,
							"¿Desea eliminar esta receta?", "Eliminar receta",
							JOptionPane.YES_NO_OPTION);

					if (confirmar == JOptionPane.YES_OPTION) {
						ModeloReceta.getInstancia().eliminar(fila);
						limpiar();
					}
				} else {
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarReceta.this,
							"Debe seleccionar una receta",
							"Receta no seleccionada",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		tablaRecetas.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaRecetas.getSelectedRow();

						if (fila >= 0) {
							String paciente = (String) ModeloReceta
									.getInstancia().getValueAt(fila, 1);
							String padecimiento = (String) ModeloReceta
									.getInstancia().getValueAt(fila, 2);
							String medicamentos = (String) ModeloReceta
									.getInstancia().getValueAt(fila, 3);

							txtPaciente.setText(paciente);
							txtPadecimiento.setText(padecimiento);
							txtMedicamentos.setText(medicamentos);
						}
					}

				});

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarTextos()) {
					int fila = tablaRecetas.getSelectedRow();
					
					if (fila >= 0) {
						codigoPadecimiento = (codigoPadecimiento == null) ? ModeloReceta.getInstancia().getCodigoPadecimiento(fila) 
								: codigoPadecimiento;
						idPaciente = (idPaciente == null) ? ModeloReceta.getInstancia().getIdPaciente(fila) : idPaciente;
						
						Receta receta = new Receta(null, txtPaciente.getText(), idPaciente, 
								txtPadecimiento.getText(), codigoPadecimiento, 
								txtMedicamentos.getText().trim());
						
						ModeloReceta.getInstancia().editar(fila, receta);
						
						limpiar();
						
					}

				} else {
					if (tablaRecetas.getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(
								PanelEditarOEliminarReceta.this,
								"Debe editar una receta", "Receta no editada",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(
								PanelEditarOEliminarReceta.this,
								"Debe completar toda la información",
								"Información incompleta",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpiar();
				tablaRecetas.getSelectionModel().clearSelection();
			}
		});

	}

	protected boolean validarTextos() {
		return (txtPaciente.getText().trim().equals("")
				|| txtPadecimiento.getText().trim().equals("") || txtMedicamentos
				.getText().trim().equals("")) ? false : true;
	}

	protected void limpiar() {
		txtPaciente.setText("");
		txtPadecimiento.setText("");
		txtMedicamentos.setText("");
	}

	public void setInstanciaNull() {
		instancia = null;
	}

	public void setPadecimiento(Padecimiento p) {
		txtPadecimiento.setText(p.getNombre());
		codigoPadecimiento = p.getCodigo();
	}

	public void setPaciente(Paciente p) {
		txtPaciente.setText(p.getNombre());
		idPaciente = p.getId();
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
	
	public void imprimirRecetas() {
		int fila = tablaRecetas.getSelectedRow();
		if (fila >= 0) {
			reporte.reportadorDeReceta(ModeloReceta.getInstancia()
					.getRecetaSeleccionada(fila));
		} else {
			JOptionPane.showMessageDialog(PanelEditarOEliminarReceta.this,
					"debe seleccionar una receta", "Gestor Pacientes",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
