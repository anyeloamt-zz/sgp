package com.lacsoft.gestorpacientes.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.lacsoft.gestorpacientes.entidades.Prueba;
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
import com.lacsoft.gestorpacientes.modelos.ModeloPrueba;

public class PanelSeleccionarPrueba extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnSeleccionar;
	private JPanel pnlBuscar;
	private JTable tablaPrueba;
	private JScrollPane scrollTabla;
	private static Prueba pruebaSeleccionada;

	private FactoriaDeLabels factoriaDeLabels;
	private FactoriaDeTextos factoriaDeTextos;
	private FactoriaDeTablas factoriaDeTablas;
	private FactoriaDeBotones factoriaDeBotones;
	private FactoriaDeCombos factoriaDeCombos;

	public PanelSeleccionarPrueba() {
		factoriaDeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeLabels = new FactoriaDeLabelsGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		factoriaDeTextos = new FactoriaDeTextosGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();

		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Codigo");
		comboBuscar.addItem("Nombre");

		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnSeleccionar = factoriaDeBotones.crearBoton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaPrueba.getSelectedRow();
				if (fila >= 0) {
					pruebaSeleccionada = ModeloPrueba.getInstancia()
							.getPruebaSeleccionada(fila);
					PanelHacerPruebaLaboratorio.getInstancia()
							.setTxtNombrePrueba(
									pruebaSeleccionada.getNombre());
					VentanaSeleccionarPrueba.getInstancia().cerrarVentana();
				} else {
					JOptionPane.showMessageDialog(
							PanelSeleccionarPrueba.this,
							"Debe Seleccionar una fila", "Gestor Pacientes",
							JOptionPane.WARNING_MESSAGE);

				}
			}
		});

		pnlBuscar = new JPanel();
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnSeleccionar);

		tablaPrueba = factoriaDeTablas.crearTabla();
		tablaPrueba.setModel(ModeloPrueba.getInstancia());
		scrollTabla = new JScrollPane(tablaPrueba);

		add(pnlBuscar);
		add(scrollTabla);

	}
	
	public static Prueba getPruebaSeleccionado() {
		return pruebaSeleccionada;
	}
}