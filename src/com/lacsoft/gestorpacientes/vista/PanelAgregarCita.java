package com.lacsoft.gestorpacientes.vista;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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

import org.freixas.jcalendar.JCalendarCombo;

import com.lacsoft.gestorpacientes.entidades.Cita;
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

public class PanelAgregarCita extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboHoras;
	private JButton btnAgregarCita, btnAgregarPaciente, btnPaciente, btnMedico, btnLimpiar;
	private JLabel lblPaciente, lblMedico, lblCausa, lblFecha, lblHora;
	private JTable tablaCitas;
	private JScrollPane scrollTabla, scrollCausa;
	private JTextArea txtCausa;
	private JTextField txtHora, txtPaciente, txtMedico;
	private JPanel pnlFormulario, pnlContenedorFormulario, pnlHora, pnlBotones,
			pnlPaciente, pnlMedico;

	private String cedulaMedico, cedulaPaciente;
	
	private JCalendarCombo calendarCombo;

	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	private FactoriaDeTablas factoriaDeTablas = new FactoriaDeTablasGenericas();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();

	// *Para enviárselo al ModeloCita y almacenarlo en la BD (Como parte de la
	// información de una cita.)
	private Paciente paciente;
	private Medico medico;
	private UsuarioLogueado usuarioLogueado = UsuarioLogueado.getInstancia();
	private static PanelAgregarCita instancia;

	public static synchronized PanelAgregarCita getInstancia() {
		return instancia = (instancia == null) ? new PanelAgregarCita()
				: instancia;
	}

	private PanelAgregarCita() {
		
		calendarCombo = new JCalendarCombo();
		
		tablaCitas = factoriaDeTablas.crearTabla();
		tablaCitas.setModel(ModeloCita.getInstancia());
		scrollTabla = new JScrollPane(tablaCitas);

		txtCausa = new JTextArea(6, 18);
		txtCausa.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtCausa.setToolTipText("Escriba aquí la causa de la cita");

		scrollCausa = new JScrollPane(txtCausa);

		txtHora = factoriaDeTextos.crearJtextFieldEnmascarado("##:##");
		comboHoras = factoriaDeCombos.crearCombo();
		comboHoras.addItem("AM");
		comboHoras.addItem("PM");

		pnlHora = new JPanel();
		pnlHora.add(txtHora);
		pnlHora.add(comboHoras);
		pnlHora.setLayout(new BoxLayout(pnlHora, BoxLayout.X_AXIS));

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
		}

		pnlPaciente = new JPanel();
		pnlPaciente.add(txtPaciente);
		pnlPaciente.add(btnPaciente);
		pnlPaciente.setLayout(new BoxLayout(pnlPaciente, BoxLayout.X_AXIS));

		pnlMedico = new JPanel();
		pnlMedico.add(txtMedico);
		pnlMedico.add(btnMedico);
		pnlMedico.setLayout(new BoxLayout(pnlMedico, BoxLayout.X_AXIS));

		pnlFormulario = new JPanel(new GridLayout(4, 2));

		pnlFormulario.add(lblPaciente);
		pnlFormulario.add(pnlPaciente);
		pnlFormulario.add(lblMedico);
		pnlFormulario.add(pnlMedico);
		pnlFormulario.add(lblFecha);
		pnlFormulario.add(calendarCombo);
		pnlFormulario.add(lblHora);
		pnlFormulario.add(pnlHora);

		pnlContenedorFormulario = new JPanel();
		pnlContenedorFormulario.add(pnlFormulario);
		pnlContenedorFormulario.add(lblCausa);
		pnlContenedorFormulario.add(scrollCausa);

		btnAgregarCita = factoriaDeBotones
				.crearBoton("         Agregar cita         ");
		btnAgregarCita.setToolTipText("Agregar la cita");
		btnAgregarPaciente = factoriaDeBotones
				.crearBoton("Registrar nuevo paciente");
		btnAgregarPaciente
				.setToolTipText("Clic para agregar un nuevo paciente");
		btnLimpiar = factoriaDeBotones.crearBoton("               Limpiar             ");
		btnLimpiar.setToolTipText("Limpiar todos los campos");

		pnlBotones = new JPanel();
		pnlBotones.add(btnAgregarCita);
		pnlBotones.add(btnAgregarPaciente);
		pnlBotones.add(btnLimpiar);
		pnlBotones.setLayout(new GridBagLayout());

		add(pnlContenedorFormulario);
		add(pnlBotones);
		add(scrollTabla);
		
		/*
		 * IMPORTANTE AQUÍ
		 * 
		 * Si el usuario que está logueado es un médico, entonces 
		 * sólo se toman los datos de ese usuario y no se manda a 
		 * buscar otro médico. Si no es un médico, pues se sigue normal
		 * se manda al usuario a buscar qué médico tiene esa cita.
		 */
		if (UsuarioLogueado.getInstancia().getRol().equals("Médico")) {
			btnMedico.setEnabled(false);
			txtMedico.setText(UsuarioLogueado.getInstancia().getNombre() 
					+ " " + UsuarioLogueado.getInstancia().getApellido());
			medico = new Medico();
			medico.setCodigoEmpleado(UsuarioLogueado.getInstancia().getCodigoDeEmpleado());
		}
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});

		btnPaciente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarPaciente.getInstancia().setEmisor(
						"agregarCita");
			}
		});

		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSeleccionarMedico.getInstancia()
						.setEmisor("agregarCita");
			}
		});

		btnAgregarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarPaciente.getInstancia();
			}
		});

		btnAgregarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int validar = validar();
				if (validar == 0) {
					
					System.out.println(cedulaPaciente + " en PanelAgregarCita");
					boolean noExiste = ModeloCita.getInstancia().agregar(new Cita(
							medico.getCodigoEmpleado(), paciente.getId(), txtMedico.getText(), cedulaMedico, 
							txtPaciente.getText(), cedulaPaciente, new SimpleDateFormat("yyyy-MM-dd")
							.format(calendarCombo.getDate()), txtHora.getText().trim(), 
							txtCausa.getText().trim()));
					
					if(!noExiste) {
						JOptionPane.showMessageDialog(PanelAgregarCita.this, 
								"Este médico tiene una cita con la misma hora y fecha");
					} else {
						limpiar();
					}
					
				} else if (validar == 1) {
					JOptionPane.showMessageDialog(PanelAgregarCita.this,
							"Debe rellenar toda la información",
							"Información incompleta",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(PanelAgregarCita.this, 
							"Debe ingresar una hora correcta.", 
							"Hora mal formulada", 
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		// Para que se presente el nombre el paciente en la UI
		txtPaciente
				.setText(paciente.getNombre() + " " + paciente.getApellido());
		cedulaPaciente = paciente.getCedula();
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
		txtMedico.setText(medico.getNombre() + " " + medico.getApellido());
		cedulaMedico = medico.getCedula();
	}
	
	public void limpiar() {
		txtCausa.setText("");
		txtHora.setText("");
		txtPaciente.setText("");
		// Operador ternario, si el usuario logueado 
		// es un médico se le pone su nombre, de lo contrario se pone vacío.
		txtMedico.setText(!usuarioLogueado.getRol().equals("Médico") 
				? "" : usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());
	}
	
	/**
	 * Para validar la información ingresada.
	 * @return Retorna 0 si los campos están correctos,
	 * 1 si los campos están vacíos y en caso de
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

}
