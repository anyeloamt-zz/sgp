package com.lacsoft.gestorpacientes.factorias;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public interface FactoriaDeBotones {
	JButton crearBoton(String texto);
	JButton crearBoton(String texto, ImageIcon icono);
	JButton crearBotonConBorde(String texto, ImageIcon icono);
}
