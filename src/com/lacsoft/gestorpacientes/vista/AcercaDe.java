package com.lacsoft.gestorpacientes.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AcercaDe(Frame frame) {
		super(frame, "Sistema Gestor de Pacientes LAC-soft", true);
		setLayout(new BorderLayout());
		String acercaDe = "Sistema Gestor de Pacientes (LAC-soft�) " +
				"es una aplicaci�n de escritorio que sirve para llevar" +
				" a cabo, entre otros, la gesti�n y el control de visitas " +
				"de pacientes a un hospital. " +
				"\n\nProyecto final Programaci�n II, profesor: Ing. Raydelto Hern�ndez.\n" +
				"Integrantes: " +
				"\n\tAnyelo Alm�nzar Mercedes" +
				"\n\tCristhian Cuevas" +
				"\n\tLeonardo Tav�rez." +
				"\n\n\t\t\t\tAbril 2013.";
		
		JTextArea txtAcercaDe = new JTextArea(acercaDe);
		txtAcercaDe.setFont(new Font("Calibr", Font.PLAIN, 13));
		txtAcercaDe.setEditable(false);
		txtAcercaDe.setLineWrap(true);
		txtAcercaDe.setWrapStyleWord(true);
		
		add(new JLabel(new ImageIcon("Imagenes/mantenimientosBanner2.JPG")), BorderLayout.NORTH);
		add(new JScrollPane(txtAcercaDe));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(455, 315);
		setVisible(true);
	}

}
