package com.lacsoft.gestorpacientes.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
import com.lacsoft.gestorpacientes.modelos.ModeloEspecialidad;

public class PanelEditarOEliminarEspecialidad extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblBuscar;
	private JComboBox<String> comboBuscar;
	private JTextField txtBuscar;
	private JButton btnBuscar, btnEliminar;
	private JPanel pnlBuscar;
	private JTable tablaEspecialidades;
	private JScrollPane scrollTabla;
	
	private FactoriaDeLabels factoriaDeLabels;
	private FactoriaDeTextos factoriaDeTextos;
	private FactoriaDeTablas factoriaDeTablas;
	private FactoriaDeBotones factoriaDeBotones;
	private FactoriaDeCombos factoriaDeCombos;
	
	public PanelEditarOEliminarEspecialidad() {
		factoriaDeBotones = new FactoriaDeBotonesGenericos();
		factoriaDeLabels = new FactoriaDeLabelsGenericos();
		factoriaDeTablas = new FactoriaDeTablasGenericas();
		factoriaDeTextos = new FactoriaDeTextosGenericos();
		factoriaDeCombos = new FactoriaDeCombosGenericos();
		
		lblBuscar = factoriaDeLabels.crearLabel("Buscar por");
		comboBuscar = factoriaDeCombos.crearCombo();
		comboBuscar.addItem("Nombre");
		comboBuscar.addItem("Código");
		
		txtBuscar = factoriaDeTextos.crearJTextField(15);
		btnBuscar = factoriaDeBotones.crearBoton("Buscar");
		btnEliminar = factoriaDeBotones.crearBoton("Eliminar");
		
		pnlBuscar = new JPanel();
		pnlBuscar.add(lblBuscar);
		pnlBuscar.add(comboBuscar);
		pnlBuscar.add(txtBuscar);
		pnlBuscar.add(btnBuscar);
		pnlBuscar.add(btnEliminar);
		
		tablaEspecialidades = factoriaDeTablas.crearTabla();
		tablaEspecialidades.setModel(ModeloEspecialidad.getInstancia());
		scrollTabla = new JScrollPane(tablaEspecialidades);
		
		add(pnlBuscar);
		add(scrollTabla);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar("btn", txtBuscar.getText().trim(),
						(String) comboBuscar.getSelectedItem());

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
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaEspecialidades.getSelectedRow();
				
				if (fila >= 0) {
					
					int confirmar = JOptionPane.showConfirmDialog(
							PanelEditarOEliminarEspecialidad.this,
							"¿Desea eliminar esta especialidad?",
							"Confirmar borrado", JOptionPane.YES_NO_OPTION);
					
					if (confirmar == JOptionPane.YES_OPTION) {
						ModeloEspecialidad.getInstancia().eliminar(fila);
					}
					
				} else {
					JOptionPane.showMessageDialog(
							PanelEditarOEliminarEspecialidad.this,
							"Debe seleccionar una especialidad",
							"Especialidad no seleccionada",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	public void buscar(String emisor, String texto, String item){
		int columna = 1;
		
		if (item.equals("Código"))
			columna = 0;
			
			if (emisor.equals("txt")){
				for (int i = 0; i < tablaEspecialidades.getRowCount(); i++) {
					if(((String) tablaEspecialidades.getValueAt(i, columna)).contains(texto))
						tablaEspecialidades.changeSelection(i, columna, false, false);
				}
			} else {
			for (int i = 0; i < tablaEspecialidades.getRowCount(); i++) {
				if(((String) tablaEspecialidades.getValueAt(i, columna)).equalsIgnoreCase(texto))
					tablaEspecialidades.changeSelection(i, columna, false, false);
			}
		}
		
	}
}
