package com.lacsoft.gestorpacientes.vista;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.freixas.jcalendar.JCalendarCombo;

import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotones;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeBotonesGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabels;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeLabelsGenericos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTablasGenericas;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextos;
import com.lacsoft.gestorpacientes.factorias.FactoriaDeTextosGenericos;
import com.lacsoft.gestorpacientes.modelos.ModeloPacienteVisita;

public class PanelVisitaPacientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtFechaVisita;
	private JTextArea textCausaVisita;
	private JCalendarCombo selectorFecha = new JCalendarCombo();
	private JPanel pnlBuscar;
	private JLabel lblBuscar, lblDatosVisitas,lblFechasVisitas,lblCausas;
	private JButton btnBuscar;

	private FactoriaDeLabels factoriaLabel;
	private FactoriaDeTextos factoriaText;
	private FactoriaDeBotones factoriaBotones;
	private FactoriaDeTablas factoriaTabla;
	private JScrollPane scrollPruebas;
	private JTable tablaVisitasPacientes;
	private JPanel pnlComponentes;
	private JScrollPane scrollCausas;


	public PanelVisitaPacientes() {

		factoriaLabel = new FactoriaDeLabelsGenericos();
		factoriaText = new FactoriaDeTextosGenericos();
        factoriaBotones = new FactoriaDeBotonesGenericos();
		factoriaTabla = new FactoriaDeTablasGenericas();

		lblBuscar = factoriaLabel.crearLabel("Fecha: ");
		btnBuscar = factoriaBotones.crearBoton("Buscar");


		pnlBuscar = new JPanel();
		pnlBuscar.setBounds(300, 11, 600, 33);
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(selectorFecha);
		pnlBuscar.add(btnBuscar);

		pnlComponentes = new JPanel();
		pnlComponentes.setBounds(10, 71, 334, 315);

		pnlComponentes.setLayout(null);

		lblFechasVisitas = factoriaLabel.crearLabel("Fecha de la visita");
		lblFechasVisitas.setBounds(0, 4, 100, 14);
		pnlComponentes.add(lblFechasVisitas);

		lblCausas = factoriaLabel.crearLabel("Causa de la visita");
		lblCausas.setBounds(0, 31, 150, 14);
		pnlComponentes.add(lblCausas);

		txtFechaVisita = factoriaText.crearJTextField(15);
		txtFechaVisita.setBounds(120, 1, 200, 23);
		pnlComponentes.add(txtFechaVisita);
		txtFechaVisita.setColumns(10);
		txtFechaVisita.setEditable(false);
		
		scrollCausas = new JScrollPane();
		scrollCausas.setBounds(10, 69, 314, 176);
		pnlComponentes.add(scrollCausas);

		textCausaVisita = new JTextArea(5,10);
		textCausaVisita.setWrapStyleWord(true);
		textCausaVisita.setLineWrap(true);
		scrollCausas.setViewportView(textCausaVisita);
		textCausaVisita.setEditable(false);

		tablaVisitasPacientes = factoriaTabla.crearTabla();
		tablaVisitasPacientes.setModel(ModeloPacienteVisita.getInstancia());

		scrollPruebas = new JScrollPane(tablaVisitasPacientes);
		scrollPruebas.setBounds(354, 71, 555, 315);

		lblDatosVisitas = factoriaLabel.crearLabel("Datos de la visitas");
		lblDatosVisitas.setBounds(10, 46, 120, 14);

		setLayout(null);

		add(pnlComponentes);
		add(lblDatosVisitas);
		add(pnlBuscar);
		add(scrollPruebas);
		
		tablaVisitasPacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int fila = tablaVisitasPacientes.getSelectedRow();
				if(fila >= 0){
				 txtFechaVisita.setText(String.valueOf(ModeloPacienteVisita.getInstancia().getValueAt(fila, 2)));
				 textCausaVisita.setText(String.valueOf(ModeloPacienteVisita.getInstancia().getValueAt(fila, 4)));
				}
				
			}
		});

	}
}
