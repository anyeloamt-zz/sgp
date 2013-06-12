package com.lacsoft.gestorpacientes.factorias;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FactoriaDeTextosGenericos implements FactoriaDeTextos {

	private JTextField txt;

	@Override
	public JTextField crearJTextField() {
		txt = new JTextField();
		txt.setFont(new Font("Calibri", Font.PLAIN, 14));
		return txt;
	}

	@Override
	public JTextField crearJTextField(int tamanio) {
		txt = new JTextField(tamanio);
		txt.setFont(new Font("Calibri", Font.PLAIN, 14));
		return txt;
	}

	@Override
	public JTextField crearJtextFieldEnmascarado(String mascara) {
		try {
			txt = new JFormattedTextField(new MaskFormatter(mascara));
			txt.setFont(new Font("Calibri", Font.PLAIN, 14));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return txt;
	}

	@Override
	public JTextField crearJTextFieldEstiloClave() {
		txt = new JPasswordField();
		txt.setFont(new Font("Calibri", Font.PLAIN, 14));
		return txt;
	}

}
