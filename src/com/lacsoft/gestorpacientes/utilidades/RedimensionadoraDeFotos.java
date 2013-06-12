package com.lacsoft.gestorpacientes.utilidades;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RedimensionadoraDeFotos {
	public static ImageIcon redimensionar(ImageIcon icon) {
		ImageIcon retorno = null;
		Image i = icon.getImage();
		Image escala = i.getScaledInstance(252, 210, Image.SCALE_DEFAULT);
		retorno = new ImageIcon(escala);
		return retorno;
	}

}
