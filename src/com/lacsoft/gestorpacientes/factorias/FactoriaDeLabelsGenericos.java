package com.lacsoft.gestorpacientes.factorias;

import java.awt.Font;

import javax.swing.JLabel;

public class FactoriaDeLabelsGenericos implements FactoriaDeLabels {

	@Override
	public JLabel crearLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(new Font("Calibri", Font.PLAIN, 14));
		return label;
	}

}
