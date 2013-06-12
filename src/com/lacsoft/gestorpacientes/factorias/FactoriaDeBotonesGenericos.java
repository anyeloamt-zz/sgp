package com.lacsoft.gestorpacientes.factorias;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FactoriaDeBotonesGenericos implements FactoriaDeBotones {
	private JButton boton;
	
	@Override
	public JButton crearBoton(String texto) {
		boton = new JButton(texto);
		boton.setFont(new Font("Calibri", Font.PLAIN, 14));
		return boton;
	}

	@Override
	public JButton crearBoton(String texto, ImageIcon icono) {
		boton = new JButton(texto, icono);
		boton.setFont(new Font("Calibri", Font.PLAIN, 14));
		return boton;
	}

	@Override
	public JButton crearBotonConBorde(String texto, ImageIcon icono) {
		boton = new JButton(texto, icono);
		boton.setFont(new Font("Calibri", Font.PLAIN, 14));
		boton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		return boton;
	}

}
