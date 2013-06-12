package com.lacsoft.gestorpacientes.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.entidades.Paciente;
import com.lacsoft.gestorpacientes.entidades.Padecimiento;
import com.lacsoft.gestorpacientes.entidades.Receta;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloReceta;
import com.lacsoft.gestorpacientes.reportes.Reportes;

public class PanelAgregarReceta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente, txtPadecimiento;
	private JTextArea txtMedicamentos;
	private JButton btnAgregar, btnLimpiar, btnImprimir, btnPaciente,
			btnPadecimiento;
	private JLabel lblPaciente, lblMediacamento, lblPadecimiento;
	private JTable tablaRecetas;
	private JScrollPane scrollTabla, scrollMedicamentos;
	private JPanel pnlPaciente, pnlPadecimiento, pnlAgregar, pnlMedicamento,
			pnlBotones;
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private Reportes reporte = new Reportes();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private String codigoPadecimiento;
	private String idPaciente;

	private static PanelAgregarReceta instancia;

	public static synchronized PanelAgregarReceta getInstancia() {
		return instancia = (instancia == null) ? new PanelAgregarReceta()
				: instancia;
	}

	private PanelAgregarReceta() {

		setLayout(null);
		lblPaciente = factoriaDeLabels.crearLabel("Paciente");
		lblPadecimiento = factoriaDeLabels.crearLabel("Padecimiento");
		lblMediacamento = factoriaDeLabels.crearLabel("Medicamentos");

		txtPaciente = factoriaDeTextos.crearJTextField(10);
		txtPadecimiento = factoriaDeTextos.crearJTextField(10);
		txtMedicamentos = new JTextArea(10, 35);
		txtMedicamentos
				.setToolTipText("Medicamentos para el paciente seleccionado");
		txtMedicamentos.setWrapStyleWord(true);
		txtMedicamentos.setLineWrap(true);

		txtPaciente.setEditable(false);
		txtPadecimiento.setEditable(false);

		scrollMedicamentos = new JScrollPane(txtMedicamentos);

		btnPaciente = factoriaDeBotones.crearBoton("...");
		btnPaciente.setToolTipText("Agregar paciente de la receta");
		btnPadecimiento = factoriaDeBotones.crearBoton("...");
		btnPadecimiento.setToolTipText("Agregar padecimiento del paciente");
		btnImprimir = factoriaDeBotones.crearBoton("Imprimir receta");
		btnImprimir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				imprimirRecetas();

			}
		});

		tablaRecetas = factoriaDeTablas.crearTabla();
		tablaRecetas.setModel(ModeloReceta.getInstancia());
		scrollTabla = new JScrollPane(tablaRecetas);
		scrollTabla.setBounds(347, 24, 537, 402);

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

		btnAgregar = factoriaDeBotones.crearBoton("Agregar");
		btnAgregar.setToolTipText("Agregar la receta");
		btnLimpiar = factoriaDeBotones.crearBoton("Limpiar");

		pnlBotones = new JPanel();
		pnlBotones.setBounds(10, 334, 350, 40);
		pnlBotones.add(btnAgregar);
		pnlBotones.add(btnLimpiar);
		pnlBotones.add(btnImprimir);
		add(pnlBotones);

		btnPaciente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarPaciente.getInstancia().setEmisor(
						"agregarReceta");
			}
		});

		btnPadecimiento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarPadecimiento.getInstancia().setEmisor(
						"agregarReceta");
			}
		});

		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});

		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validarTextos()) {
					JOptionPane.showMessageDialog(PanelAgregarReceta.this,
							"Debe llenar toda la información");
				} else {
					String paciente = txtPaciente.getText().trim();
					String padecimiento = txtPadecimiento.getText().trim();
					String medicamentos = txtMedicamentos.getText().trim();

					Receta receta = new Receta(null, paciente, idPaciente,
							padecimiento, codigoPadecimiento, medicamentos);

					int existe = ModeloReceta.getInstancia().agregar(
							idPaciente, codigoPadecimiento, receta, 0);
					imprimirRecetas();

					if (existe == 0) {
						limpiar();
					} else if (existe == 1) {
						int confirmar = JOptionPane
								.showConfirmDialog(
										PanelAgregarReceta.this,
										"Esta receta existe con una fecha diferente a hoy día "
												+ "¿Desea agregarla con la fecha de hoy?",
										"Receta repetida",
										JOptionPane.YES_NO_OPTION);
						if (confirmar == JOptionPane.YES_OPTION) {
							System.out
									.println("Agregar la receta con fecha diferente");
							ModeloReceta.getInstancia().agregar(idPaciente,
									codigoPadecimiento, receta, 1);
							limpiar();
						} else {
							limpiar();
							System.out.println("No gregar la receta");
						}
					}

				}
			}
		});

	}

	/**
	 * Retorna true si los campos están llenos
	 * 
	 * @return
	 */
	protected boolean validarTextos() {
		return (txtPaciente.getText().trim().equals("")
				|| txtPadecimiento.getText().trim().equals("") || txtMedicamentos
				.getText().trim().equals("")) ? true : false;
	}

	protected void limpiar() {
		txtMedicamentos.setText("");
		txtPaciente.setText("");
		txtPadecimiento.setText("");
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

	public void imprimirRecetas() {
		int fila = tablaRecetas.getSelectedRow();
		if (fila >= 0) {
			reporte.reportadorDeReceta(ModeloReceta.getInstancia()
					.getRecetaSeleccionada(fila));
		} else {
			JOptionPane.showMessageDialog(PanelAgregarReceta.this,
					"debe seleccionar una receta", "Gestor Pacientes",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
