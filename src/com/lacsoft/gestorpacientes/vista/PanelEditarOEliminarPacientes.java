package com.lacsoft.gestorpacientes.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

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

public class PanelEditarOEliminarPacientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar, lblFoto, lblPonerFoto;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnEliminar, btnGuardaFoto, btnGuardar,
			btnCancelar;
	private JTable tablaPacientes;
	private JScrollPane scrollTabla;
	private JPanel pnlBuscar, pnlFoto;
	private JComboBox<String> comboBuscar;
	private JTextField txtFoto;
	private JDesktopPane desktopFoto;

	private FactoriaDeLabels factoriaLabel;
	private FactoriaDeTextos factoriaText;
	private FactoriaDeBotones factoriadeBotones;
	private FactoriaDeCombos factoriaDeCombos;
	private FactoriaDeTablas factoriaDeTablas;

	public PanelEditarOEliminarPacientes() {

		factoriaLabel = new FactoriaDeLabelsGenericos();
		factoriaText = new FactoriaDeTextosGenericos();
		factoriadeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();

		lblBuscar = factoriaLabel.crearLabel("Buscar por");
		txtBuscar = factoriaText.crearJTextField(15);
		btnBuscar = factoriadeBotones.crearBoton("Buscar");
		btnEliminar = factoriadeBotones.crearBoton("Eliminar");
		btnEliminar.setToolTipText("Eliminar paciente seleccionado del sistema");
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = tablaPacientes.getSelectedRow();
				if (fila < 0) {
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarPacientes.this,
							"Debe seleccionar una fila para poder eliminar",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					int respuesta = JOptionPane.showConfirmDialog(
							PanelEditarOEliminarPacientes.this,
							"Desea eliminar a este paciente",
							"Gestor Pacientes", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						ModeloPaciente.getInstancia().eliminarPaciente(fila);

					}

				}
			}
		});
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Apellido");
		comboBuscar.addItem("Cédula");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(100, 11, 600, 33);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);

		tablaPacientes = factoriaDeTablas.crearTabla();
		tablaPacientes.setModel(ModeloPaciente.getInstancia());
		scrollTabla = new JScrollPane(tablaPacientes);
		scrollTabla.setBounds(32, 49, 711, 506);
		setLayout(null);

		tablaPacientes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						int fila = tablaPacientes.getSelectedRow();
						if (fila >= 0) {
							lblPonerFoto.setIcon(ModeloPaciente.getInstancia()
									.getImagenPaciente(
											fila));

						}
					}
				});

		pnlFoto = new JPanel();
		pnlFoto.setLayout(null);
		pnlFoto.setBounds(753, 47, 296, 282);

		desktopFoto = new JDesktopPane();
		desktopFoto.setLayout(null);
		desktopFoto.setToolTipText("Foto");
		desktopFoto.setBounds(0, 0, 252, 210);
		pnlFoto.add(desktopFoto);

		lblPonerFoto = new JLabel(new ImageIcon("Imagenes/icono.PNG"));
		lblPonerFoto.setBounds(10, 11, 232, 188);
		desktopFoto.add(lblPonerFoto);

		btnGuardaFoto = factoriadeBotones.crearBoton("...");
		btnGuardaFoto.setToolTipText("Buscar nueva foto para el paciente seleccionado");
		btnGuardaFoto.setBounds(172, 212, 50, 25);
		btnGuardaFoto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = tablaPacientes.getSelectedRow();
				if (fila >= 0) {
					JFileChooser selector = new JFileChooser();
					FileNameExtensionFilter formatos = new FileNameExtensionFilter(
							"Archivos de imágenes", "JPG", "PNG", "GIF");
					selector.setFileFilter(formatos);

					int seleccion = selector
							.showOpenDialog(PanelEditarOEliminarPacientes.this);

					if (seleccion == JFileChooser.APPROVE_OPTION) {
						String archivo = selector.getSelectedFile()
								.getAbsolutePath();
						txtFoto.setText(archivo);
						lblPonerFoto.setIcon(RedimensionadoraDeFotos
								.redimensionar(new ImageIcon(archivo)));
					}
				} else {
					JOptionPane
							.showMessageDialog(
									PanelEditarOEliminarPacientes.this,
									"Debe seleccionar una fila para poder escoger una imagen",
									"Gestor Pacientes",
									JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		pnlFoto.add(btnGuardaFoto);

		lblFoto = factoriaLabel.crearLabel("Foto");
		lblFoto.setBounds(20, 221, 46, 14);
		pnlFoto.add(lblFoto);

		txtFoto = factoriaText.crearJTextField(15);
		txtFoto.setEditable(false);
		txtFoto.setBounds(76, 215, 86, 20);
		pnlFoto.add(txtFoto);
		txtFoto.setColumns(10);

		btnGuardar = factoriadeBotones.crearBoton("Guardar");
		btnGuardar.setToolTipText("Actualizar foto del paciente seleccionado");
		btnGuardar.setBounds(46, 259, 91, 23);
		btnGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String path = txtFoto.getText();
				int fila = tablaPacientes.getSelectedRow();
				if (fila >= 0 && !path.equals("")) {
					ModeloPaciente.getInstancia().actualizarFotoPaciente(fila, path);
				} else {
					if (fila < 0) {
						JOptionPane
								.showMessageDialog(
										PanelEditarOEliminarPacientes.this,
										"Debe seleccionar una fila para poder editar",
										"Gestor Pacientes",
										JOptionPane.WARNING_MESSAGE);
					} else if (path.equals("")) {
						JOptionPane
								.showMessageDialog(
										PanelEditarOEliminarPacientes.this,
										"Debe seleccionar una imagen para poder editar",
										"Gestor Pacientes",
										JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		pnlFoto.add(btnGuardar);

		btnCancelar = factoriadeBotones.crearBoton("Cancelar");
		btnCancelar.setToolTipText("Cancelar actualizaci´´on de foto");
		btnCancelar.setBounds(145, 259, 91, 23);
		pnlFoto.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFoto.setText("");
				
				int fila = tablaPacientes.getSelectedRow();
				
				if (fila >= 0) {
					lblPonerFoto.setIcon(ModeloPaciente.getInstancia().getImagenPaciente(fila));
				} else {
					lblPonerFoto.setIcon(new ImageIcon("Imagenes/icono.PNG"));
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

		add(pnlBuscar);
		add(scrollTabla);
		add(pnlFoto);
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
