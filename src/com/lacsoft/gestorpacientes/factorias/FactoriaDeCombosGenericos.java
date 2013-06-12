package com.lacsoft.gestorpacientes.factorias;

import java.awt.Font;

import javax.swing.JComboBox;

public class FactoriaDeCombosGenericos implements FactoriaDeCombos {

	@Override
	public JComboBox<String> crearCombo() {
		JComboBox<String> combo = new JComboBox<String>();
		combo.setFont(new Font("Calibri", Font.PLAIN, 14));
		return combo;
	}

}
