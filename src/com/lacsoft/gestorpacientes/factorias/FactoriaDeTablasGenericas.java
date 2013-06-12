package com.lacsoft.gestorpacientes.factorias;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;

public class FactoriaDeTablasGenericas implements FactoriaDeTablas {

	@Override
	public JTable crearTabla() {
		JTable tabla = new JTable();
		//tabla.setSelectionBackground(Color.BLACK);
		tabla.setSelectionForeground(Color.WHITE);
		tabla.setFont(new Font("Calibri", Font.PLAIN, 14));
		tabla.getTableHeader().setReorderingAllowed(false);
		return tabla;
	}

}
