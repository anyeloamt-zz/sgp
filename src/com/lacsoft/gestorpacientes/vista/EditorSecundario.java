package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeCombosGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloEspecialidad;
import com.lacsoft.gestorpacientes.modelos.ModeloPaciente;
import com.lacsoft.gestorpacientes.modelos.ModeloUsuario;
import com.toedter.calendar.JDateChooser;

public class EditorSecundario extends JDialog implements ActionListener,
		KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FactoriaDeBotones factoriaDeBotones = new FactoriaDeBotonesGenericos();
	private FactoriaDeTextos factoriaDeTextos = new FactoriaDeTextosGenericos();
	private FactoriaDeLabels factoriaDeLabels = new FactoriaDeLabelsGenericos();
	private FactoriaDeCombos factoriaDeCombos = new FactoriaDeCombosGenericos();
	
	private JDateChooser dateChooser = new JDateChooser(new Date());

	private JLabel lblValor, lblIcon;

	private JTextField txtValor;
	private JButton btnAceptar, btnCancelar;

	private JPanel pnlValor, pnlBotones, pnlPrincipal;
	private String modelo;
	private int fila;
	private int columna;
	private String mascara;
	private boolean esFecha = false;
	
	private JComboBox<String> comboEspecialidades;

	public EditorSecundario(Frame frame, String titulo, String mascara,
			String modelo, int fila, int columna) {
		super(frame, titulo);
		this.modelo = modelo;
		this.fila = fila;
		this.columna = columna;
		this.mascara = mascara;
		init();
	}

	public EditorSecundario(Frame frame, String titulo, String mascara,
			String modelo, int fila, int columna, boolean esFecha) {
		super(frame, titulo);
		this.modelo = modelo;
		this.fila = fila;
		this.columna = columna;
		this.esFecha = esFecha;
		this.mascara = mascara;
		init();
	}

	public void init() {

		btnAceptar = factoriaDeBotones.crearBoton("Aceptar");
		btnCancelar = factoriaDeBotones.crearBoton("Cancelar");

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		if (mascara.equals("combo especialidad")) {
			comboEspecialidades = factoriaDeCombos.crearCombo();
			ArrayList<String> especialidades = ModeloEspecialidad.getInstancia()
					.listarEspecialidades();
			
			for (String e : especialidades) {
				comboEspecialidades.addItem(e);
			}
			
		} else {
			txtValor = factoriaDeTextos.crearJtextFieldEnmascarado(mascara);
			txtValor.addKeyListener(this);
		}

		lblValor = factoriaDeLabels.crearLabel("Valor");
		lblIcon = new JLabel(new ImageIcon("Imagenes/dialogo.PNG"));

		pnlValor = new JPanel(new BorderLayout());

		pnlValor.add(lblValor);
		
		if (mascara.equals("combo especialidad")) {
			pnlValor.add(comboEspecialidades);
		} else {
			if (esFecha == true) {
				pnlValor.add(dateChooser, BorderLayout.EAST);
			} else {
				pnlValor.add(txtValor, BorderLayout.EAST);
			}
		}
		pnlBotones = new JPanel();
		pnlBotones.add(btnAceptar);
		pnlBotones.add(btnCancelar);

		pnlPrincipal = new JPanel();
		pnlPrincipal.add(lblIcon);
		pnlPrincipal.add(pnlValor);
		pnlPrincipal.add(pnlBotones);

		add(pnlPrincipal);

		setModal(true);
		setSize(400, 200);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAceptar)) {
			String valor = "";
			if (mascara.equals("combo especialidad")) {
				valor = (String) comboEspecialidades.getSelectedItem();
			} else {
				valor = txtValor.getText();
			}
			System.out.println(valor);
			if (valor.equals("(   )-   -    ")
					|| valor.equals("   -       - ")) {
				
				JOptionPane.showMessageDialog(null,
						"Debe llenar la informaci�n", "Falta informaci�n",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				if (modelo.equals("usuario")) {
					ModeloUsuario.getInstancia().setValueAt(valor, columna,
							fila);
				} else {
					if (esFecha == true) {
						String fecha = new SimpleDateFormat("yyyy-MM-dd")
								.format(dateChooser.getDate());
						ModeloPaciente.getInstancia().setValueAt(fecha, fila,
								columna);
					} else {
						ModeloPaciente.getInstancia().setValueAt(valor, fila,
								columna);
					}
				}
				EditorSecundario.this.dispose();
			}
		}

		else {
			EditorSecundario.this.dispose();
		}
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			btnAceptar.doClick();
		}
	}
}
