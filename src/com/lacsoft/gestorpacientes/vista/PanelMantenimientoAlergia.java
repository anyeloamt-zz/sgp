package com.lacsoft.gestorpacientes.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

import com.lacsoft.gestorpacientes.entidades.Alergia;
import com.lacsoft.gestorpacientes.fachadas.FachadaGestoraDeCodigos;
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
import com.lacsoft.gestorpacientes.modelos.ModeloAlergia;

public class PanelMantenimientoAlergia extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaAlergias;
	private JLabel lblBuscar, lblNombreAlergia, lblDescripcion,
			lblDatosDeLaAlergia, lblAlegiasSeleccionada;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar, txtNombreAlergia;
	private JButton btnBuscar, btnseleccionar, btnAgregar, btnLimpiar,
			btnAgregarAlergiaSeleccionada, btnQuitarAlergiaSeleccionada,
			btnEliminar;
	private JPanel pnlBuscar, pnlDatosAlegias, pnlAlergiaSeleccionada;
	private JTextArea txtDescripcion;
	private JList<String> listAlergia;
	private ArrayList<JTextComponent> textos = new ArrayList<JTextComponent>();
	
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	
	private JScrollPane scrollResultados, scrollTablaAlergia,
			scrollListAlegias;
	private DefaultListModel<String> modeloLista = new DefaultListModel<String>();
	private static ArrayList<String> alergiasSeleccionadas = new ArrayList<String>();

	public PanelMantenimientoAlergia() {
		setLayout(null);

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Código");
		comboBuscar.addItem("Nombre");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnEliminar = factoriaDeBotones.crearBoton("Eliminar");
		btnEliminar.setToolTipText("Eliminar alergia seleccionada del sistema");
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaAlergias.getSelectedRow();
				if (fila < 0) {
					JOptionPane.showMessageDialog(
							PanelMantenimientoAlergia.this,
							"Debe seleccionar una fila", "Alergia no seleccionada",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int respuesta = JOptionPane.showConfirmDialog(
							PanelMantenimientoAlergia.this,
							"¿Desea eliminar esta alergia?", "Confirmación",
							JOptionPane.OK_CANCEL_OPTION);
					if (respuesta == JOptionPane.OK_OPTION) {
						ModeloAlergia.getInstancia().eliminarAlergia(fila);
					}
				}
			}
		});
		
		btnseleccionar = factoriaDeBotones.crearBoton("Seleccionar");
		btnseleccionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaAlergias.getSelectedRow();
				if (fila < 0) {
					JOptionPane.showMessageDialog(
							PanelMantenimientoAlergia.this,
							"Debe de seleccionar una de las alergias",
							"Advertencia", JOptionPane.WARNING_MESSAGE);

				} else {
					String codigo = String.valueOf(ModeloAlergia
							.getInstancia().getValueAt(fila, 0));
					if (validarAlergiaSeleccionada(codigo)) {
						JOptionPane.showMessageDialog(
								PanelMantenimientoAlergia.this,
								"Esta alergia ya está seleccionada",
								"Advertencia", JOptionPane.WARNING_MESSAGE);
					} else {
						modeloLista.addElement(codigo);
					}
				}
			}
		});

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(150, 9, 636, 35);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnseleccionar);
		pnlBuscar.add(btnEliminar);

		add(pnlBuscar);

		pnlDatosAlegias = new JPanel();
		pnlDatosAlegias.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlDatosAlegias.setBounds(10, 283, 290, 217);
		add(pnlDatosAlegias);
		pnlDatosAlegias.setLayout(null);

		lblNombreAlergia = factoriaDeLabels.crearLabel("Nombre");
		lblNombreAlergia.setBounds(10, 27, 76, 14);
		pnlDatosAlegias.add(lblNombreAlergia);

		txtNombreAlergia = factoriaDeTextos.crearJTextField(15);
		txtNombreAlergia.setBounds(81, 24, 199, 25);
		pnlDatosAlegias.add(txtNombreAlergia);

		lblDescripcion = factoriaDeLabels.crearLabel("Descripción ");
		lblDescripcion.setBounds(10, 52, 89, 14);
		pnlDatosAlegias.add(lblDescripcion);

		scrollResultados = new JScrollPane();
		scrollResultados.setBounds(81, 50, 199, 127);
		pnlDatosAlegias.add(scrollResultados);

		txtDescripcion = new JTextArea();
		scrollResultados.setViewportView(txtDescripcion);

		btnAgregar = factoriaDeBotones.crearBoton("Agregar");
		btnAgregar.setToolTipText("Registrar nueva alergia en el sistema");
		btnAgregar.setBounds(10, 188, 128, 23);
		btnAgregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (validar()) {
					JOptionPane
							.showMessageDialog(
									PanelMantenimientoAlergia.this,
							"Debe completar la información acerca de la alergia",
									"Advertencia", JOptionPane.WARNING_MESSAGE);
				} else {
					if (ModeloAlergia.getInstancia().existeAlergia(
							txtNombreAlergia.getText().trim())) {
						JOptionPane.showMessageDialog(
								PanelMantenimientoAlergia.this,
								"Esta alergia ya existe", "Error",
								JOptionPane.ERROR_MESSAGE);
						limpiar();
					} else {
						ModeloAlergia.getInstancia().agregarAlergia(
								new Alergia(FachadaGestoraDeCodigos.generarCodigo("Alergia"),
										txtNombreAlergia.getText().trim(),
										txtDescripcion.getText().trim()));
						limpiar();
					}

				}
			}
		});
		pnlDatosAlegias.add(btnAgregar);

		btnLimpiar = factoriaDeBotones.crearBoton("Limpiar");
		btnLimpiar.setBounds(148, 188, 132, 23);
		btnLimpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		pnlDatosAlegias.add(btnLimpiar);

		lblDatosDeLaAlergia = factoriaDeLabels
				.crearLabel("Agregar alergia");
		lblDatosDeLaAlergia.setBounds(10, 2, 158, 14);
		pnlDatosAlegias.add(lblDatosDeLaAlergia);

		scrollTablaAlergia = new JScrollPane();
		scrollTablaAlergia.setBounds(322, 55, 484, 439);
		add(scrollTablaAlergia);

		tablaAlergias = factoriaDeTablas.crearTabla();
		tablaAlergias.setModel(ModeloAlergia.getInstancia());
		scrollTablaAlergia.setViewportView(tablaAlergias);

		pnlAlergiaSeleccionada = new JPanel();
		pnlAlergiaSeleccionada.setBorder(BorderFactory
				.createLineBorder(Color.black));
		pnlAlergiaSeleccionada.setBounds(10, 55, 290, 217);
		add(pnlAlergiaSeleccionada);
		pnlAlergiaSeleccionada.setLayout(null);

		scrollListAlegias = new JScrollPane();
		scrollListAlegias.setBounds(20, 34, 241, 138);
		pnlAlergiaSeleccionada.add(scrollListAlegias);

		listAlergia = new JList<String>(modeloLista);
		listAlergia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarAlergiaSeleccionadaEnLista();
		scrollListAlegias.setViewportView(listAlergia);

		btnAgregarAlergiaSeleccionada = factoriaDeBotones.crearBoton("Aceptar");
		btnAgregarAlergiaSeleccionada.setToolTipText("Agregar alergias seleccionadas al paciente y salir de la ventana");
		btnAgregarAlergiaSeleccionada.setBounds(20, 183, 116, 23);
		btnAgregarAlergiaSeleccionada.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (modeloLista.getSize() != 0) {
					agregarAlergiaSeleccionada();
					PanelAgregaPacientes.getInstancia()
							.setBotonMantenimientoAlergia(true);
					VentanaMantenimientoAlergia.getInstancia().dispose();
					VentanaMantenimientoAlergia.getInstancia()
							.setInstanciaNull();
				}
			}
		});
		
		pnlAlergiaSeleccionada.add(btnAgregarAlergiaSeleccionada);

		btnQuitarAlergiaSeleccionada = factoriaDeBotones.crearBoton("Quitar");
		btnQuitarAlergiaSeleccionada.setToolTipText("Remover alergia seleccionada de la lista");
		btnQuitarAlergiaSeleccionada.setBounds(146, 183, 115, 23);
		btnQuitarAlergiaSeleccionada.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int fila = listAlergia.getSelectedIndex();
				if (fila < 0) {
					JOptionPane.showMessageDialog(
							PanelMantenimientoAlergia.this,
							"Debe seleccionar un elemento de la lista",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					modeloLista.remove(fila);
					agregarAlergiaSeleccionada();
				}
			}
		});
		pnlAlergiaSeleccionada.add(btnQuitarAlergiaSeleccionada);

		lblAlegiasSeleccionada = factoriaDeLabels
				.crearLabel("Alergias seleccionadas");
		lblAlegiasSeleccionada.setBounds(20, 9, 125, 14);
		pnlAlergiaSeleccionada.add(lblAlegiasSeleccionada);

		textos.add(txtNombreAlergia);
		textos.add(txtDescripcion);

	}

	public boolean validar() {
		boolean retorno = false;
		for (JTextComponent texto : textos) {
			if (texto.getText().trim().equals("") || texto.getText().trim().equals(" "))
				retorno = true;
		}
		return retorno;
	}

	public void limpiar() {
		for (JTextComponent texto : textos) {
			texto.setText("");
		}
	}

	public boolean validarAlergiaSeleccionada(String alergiaSeleccionada) {
		boolean retorno = false;
		for (int i = 0; i < modeloLista.getSize(); i++) {
			if (modeloLista.get(i).equalsIgnoreCase(alergiaSeleccionada)) {
				retorno = true;
			}
		}

		return retorno;

	}

	public void agregarAlergiaSeleccionada() {
		alergiasSeleccionadas.clear();
		for (int i = 0; i < modeloLista.getSize(); i++) {
			alergiasSeleccionadas.add(modeloLista.get(i));
		}
	}

	public void cargarAlergiaSeleccionadaEnLista() {
		modeloLista.removeAllElements();
		for (int i = 0; i < alergiasSeleccionadas.size(); i++) {
			modeloLista.addElement(alergiasSeleccionadas.get(i));
		}
	}

	public static ArrayList<String> getAlergiaSeleccionada() {
		return alergiasSeleccionadas;
	}

	public static void limpiarAlergiaSeleccionada() {
		alergiasSeleccionadas.clear();
	}
}