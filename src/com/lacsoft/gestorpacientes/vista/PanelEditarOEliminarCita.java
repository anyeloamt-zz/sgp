package com.lacsoft.gestorpacientes.vista;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

import org.freixas.jcalendar.JCalendarCombo;

import com.lacsoft.gestorpacientes.entidades.Medico;
import com.lacsoft.gestorpacientes.entidades.Paciente;
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
import com.lacsoft.gestorpacientes.modelos.ModeloCita;

public class PanelEditarOEliminarCita extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnEliminar, btnGuardar, btnCancelar;
	private JPanel pnlBuscar;

	private JComboBox<String> comboHoras;
	private JButton btnAgregarCita, btnAgregarPaciente, btnPaciente, btnMedico,
			btnLimpiar;
	private JLabel lblPaciente, lblMedico, lblCausa, lblFecha, lblHora;
	private JTable tablaCitas;
	private JScrollPane scrollTabla, scrollCausa;
	private JTextArea txtCausa;
	private JTextField txtHora, txtPaciente, txtMedico;
	private JPanel pnlFormulario, pnlContenedorFormulario, pnlHora,
			pnlPaciente, pnlMedico;
	
	private String cedulaMedico, codigoEmpleado, cedulaPaciente, idPaciente;

	//private JDateChooser dateChooser;
	private JCalendarCombo calendarCombo = new JCalendarCombo();

	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	
	private UsuarioLogueado usuarioLogueado = UsuarioLogueado.getInstancia();

	private static PanelEditarOEliminarCita instancia;
	

	public static synchronized PanelEditarOEliminarCita getInstancia() {
		return instancia = (instancia == null) ? new PanelEditarOEliminarCita() : instancia;
	}
	
	private PanelEditarOEliminarCita() {
		if (usuarioLogueado.getRol().equals("Médico")) {
			cedulaMedico = usuarioLogueado.getCedula();
		}
		
		tablaCitas = factoriaDeTablas.crearTabla();
		tablaCitas.setModel(ModeloCita.getInstancia());
		scrollTabla = new JScrollPane(tablaCitas);
		scrollTabla.setBounds(358, 73, 533, 500);

		txtCausa = new JTextArea(10, 25);
		txtCausa.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtCausa.setLineWrap(true);
		txtCausa.setWrapStyleWord(true);
		txtCausa.setToolTipText("Escriba aquí la causa de la cita");

		scrollCausa = new JScrollPane(txtCausa);

		txtHora = factoriaDeTextos.crearJtextFieldEnmascarado("##:##");
		comboHoras = factoriaDeCombos.crearCombo();
		comboHoras.addItem("AM");
		comboHoras.addItem("PM");

		lblPaciente = factoriaDeLabels.crearLabel("Paciente");
		lblMedico = factoriaDeLabels.crearLabel("Médico");
		lblFecha = factoriaDeLabels.crearLabel("Fecha");
		lblHora = factoriaDeLabels.crearLabel("Hora");
		lblCausa = factoriaDeLabels.crearLabel("Causa");

		txtPaciente = factoriaDeTextos.crearJTextField(10);
		txtMedico = factoriaDeTextos.crearJTextField(10);

		txtPaciente.setEditable(false);
		txtMedico.setEditable(false);
		
		btnPaciente = factoriaDeBotones.crearBoton("...");
		btnPaciente.setToolTipText("Clic para agregar un paciente");
		btnMedico = factoriaDeBotones.crearBoton("...");
		btnMedico.setToolTipText("Clic para agregar un médico");
		
		if (usuarioLogueado.getRol().equals("Médico")) {
			btnMedico.setEnabled(false);
			txtMedico.setText(usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());
		}
		
		btnGuardar = factoriaDeBotones.crearBoton("Guardar");
		btnCancelar = factoriaDeBotones.crearBoton("Cancelar");

		pnlContenedorFormulario = new JPanel();
		pnlContenedorFormulario.setBounds(26, 245, 324, 226);
		pnlContenedorFormulario.add(lblCausa);
		pnlContenedorFormulario.add(scrollCausa);
		pnlContenedorFormulario.add(btnGuardar);
		pnlContenedorFormulario.add(btnCancelar);

		btnAgregarCita = factoriaDeBotones
				.crearBoton("         Agregar cita         ");
		btnAgregarCita.setToolTipText("Agregar la cita");
		btnAgregarPaciente = factoriaDeBotones
				.crearBoton("Registrar nuevo paciente");
		btnAgregarPaciente
				.setToolTipText("Clic para agregar un nuevo paciente");
		btnLimpiar = factoriaDeBotones
				.crearBoton("               Limpiar             ");
		btnLimpiar.setToolTipText("Limpiar todos los campos");

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		/*
		 * Aquí es que se toman las decisiones si el usuario es médico o no.
		 */
		if (!usuarioLogueado.getRol().equals("Médico")) {
			comboBuscar.addItem("Nombre de médico");
		}
		comboBuscar.addItem("Paciente");
		comboBuscar.addItem("Fecha");
		comboBuscar.addItem("Hora");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");

		btnEliminar = factoriaDeBotones.crearBoton("Eliminar");

		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(356, 32, 560, 35);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);
		setLayout(null);

		add(pnlContenedorFormulario);
		add(pnlBuscar);
		add(scrollTabla);

		pnlHora = new JPanel();
		pnlHora.add(txtHora);
		pnlHora.add(comboHoras);
		pnlHora.setLayout(new BoxLayout(pnlHora, BoxLayout.X_AXIS));

		pnlPaciente = new JPanel();
		pnlPaciente.add(txtPaciente);
		pnlPaciente.add(btnPaciente);
		pnlPaciente.setLayout(new BoxLayout(pnlPaciente, BoxLayout.X_AXIS));

		pnlMedico = new JPanel();
		pnlMedico.add(txtMedico);
		pnlMedico.add(btnMedico);
		pnlMedico.setLayout(new BoxLayout(pnlMedico, BoxLayout.X_AXIS));

		pnlFormulario = new JPanel(new GridLayout(4, 2));
		pnlFormulario.setBounds(26, 124, 322, 100);
		add(pnlFormulario);

		pnlFormulario.add(lblPaciente);
		pnlFormulario.add(pnlPaciente);
		pnlFormulario.add(lblMedico);
		pnlFormulario.add(pnlMedico);
		pnlFormulario.add(lblFecha);
		pnlFormulario.add(calendarCombo);
		pnlFormulario.add(lblHora);
		pnlFormulario.add(pnlHora);

		tablaCitas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				int fila = tablaCitas.getSelectedRow();
				
				if (fila >= 0) {
					String paciente = (String) ModeloCita.getInstancia().getValueAt(fila, 2);
					String fecha = (String) ModeloCita.getInstancia().getValueAt(fila, 4);
					String hora = (String) ModeloCita.getInstancia().getValueAt(fila, 5);
					String causa = (String) ModeloCita.getInstancia().getValueAt(fila, 6);
					
					int anio = Integer.parseInt(fecha.substring(0, 4));
					int mes = Integer.parseInt(fecha.substring(5, 7));
					int dia = Integer.parseInt(fecha.substring(8, 10));
					
					Calendar calendar = Calendar.getInstance();
					calendar.set(anio, mes, dia);
					
					txtPaciente.setText(paciente);
					calendarCombo.setDate(calendar.getTime());
					txtHora.setText(hora);
					txtCausa.setText(causa);
					
					if (!usuarioLogueado.getRol().equals("Médico")) {
						txtMedico.setText((String) ModeloCita.getInstancia().getValueAt(fila, 0));
					}
				}
			}
		});
		
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarPaciente.getInstancia().setEmisor("editarCita");
			}
		});
		
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarMedico.getInstancia().setEmisor("editarCita");
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaCitas.getSelectedRow();

				if (fila >= 0) {

					int confirmar = JOptionPane.showConfirmDialog(
							PanelEditarOEliminarCita.this,
							"¿Desea eliminar esta cita?", "Confirmar",
							JOptionPane.YES_NO_OPTION);

					if (confirmar == JOptionPane.YES_OPTION) {
						ModeloCita.getInstancia().eliminar(fila);
					}

				} else {
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarCita.this,
							"Debe seleccionar una cita");
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int fila = tablaCitas.getSelectedRow();
					if (fila >= 0) {
					int validar = validar();
					if (validar == 0) {
						
						String fecha = new SimpleDateFormat("yyyy-MM-dd")
								.format(calendarCombo.getDate());
						String hora = txtHora.getText();
						String causa = txtCausa.getText().trim();
						String nombrePaciente = txtPaciente.getText();
						String nombreMedico = txtMedico.getText();
						
						/*
						 * Si no se le ha seteado la cédula al paciente o médico
						 * desde la otra ventana, se le setea de lo que haya en la tabla.
						 */
						
						cedulaMedico = (cedulaMedico == null) ? (String) tablaCitas.getValueAt(fila, 1) : cedulaMedico;
						cedulaPaciente = (cedulaPaciente == null) ? (String) tablaCitas.getValueAt(fila, 3) : cedulaPaciente;
						idPaciente = (idPaciente == null) ? ModeloCita.getInstancia().getIdPaciente(fila) : idPaciente;
						codigoEmpleado = (codigoEmpleado == null) ? ModeloCita.getInstancia().getCodigoEmpleado(fila) : codigoEmpleado;
						
						String temp = cedulaPaciente;
						
						ModeloCita.getInstancia().editar(fila, nombrePaciente, 
								temp, idPaciente, nombreMedico, cedulaMedico, codigoEmpleado, fecha, hora, causa);
						
						limpiar();
						
					} else if (validar == 1) {
						JOptionPane.showMessageDialog(PanelEditarOEliminarCita.this, 
								"Debe llenar toda la información", 
								"Información imcompleta", 
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(PanelEditarOEliminarCita.this, 
								"Debe ingresar una hora correcta.", 
								"Hora mal formulada", 
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(PanelEditarOEliminarCita.this, 
							"Debe editar una cita antes de guardarla", 
							"Cita no editada", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				
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

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar("btn", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());
			}
		});
	}

	protected void limpiar() {
		tablaCitas.clearSelection();
		txtCausa.setText("");
		txtPaciente.setText("");
		txtHora.setText("");
		
		if (!usuarioLogueado.getRol().equals("Médico")) {
			txtMedico.setText("");
		}
	}

	/**
	 * Para validar la información ingresada.
	 * @return Retorna 0 si los campos están correctos,
	 * 1 si los campos están vacíos <br> y en caso de
	 * que estén llenos pero la hora esté mal puesta
	 * retornará 2.
	 */
	private int validar() {
		int retorno = 0;
		if(txtPaciente.getText().equals("") 
				|| txtMedico.getText().equals("") 
				|| txtHora.getText().equals("") 
				|| txtCausa.getText().equals("")) {
			retorno = 1;
		} else {
			int hora = Integer.parseInt(txtHora.getText().substring(0, 2));
			int minutos = Integer.parseInt(txtHora.getText().substring(3, 5));
			
			if (hora > 12 || minutos > 59) {
				retorno = 2;
			}
		}
		return retorno;
	}
	
	public void buscar(String emisor, String texto, String item) {
		int columna = 0;

		if (item.equals("Nombre de médico"))
			columna = 0;
		else if (item.equals("Fecha"))
			columna = 4;
		else if (item.equals("Hora"))
			columna = 5;
		else
			columna = 2;

		if (emisor.equals("txt")) {
			for (int i = 0; i < tablaCitas.getRowCount(); i++) {
				if (((String) tablaCitas.getValueAt(i, columna))
						.contains(texto)) {
					tablaCitas.changeSelection(i, columna, false, false);
				}
			}
		} else {
			for (int i = 0; i < tablaCitas.getRowCount(); i++) {

				if (((String) tablaCitas.getValueAt(i, columna))
						.equalsIgnoreCase(texto)) {
					tablaCitas.changeSelection(i, columna, false, false);
				}
			}
		}
	}

	public void setPaciente(Paciente paciente) {
		txtPaciente.setText(paciente.getNombre() + " " + paciente.getApellido());
		cedulaPaciente = paciente.getCedula();
		idPaciente = paciente.getId();
	}
	
	public void setMedico(Medico medico) {
		txtMedico.setText(medico.getNombre() + " " + medico.getApellido());
		cedulaMedico = medico.getCedula();
	}

}
