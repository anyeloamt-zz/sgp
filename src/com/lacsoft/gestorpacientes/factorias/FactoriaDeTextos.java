package com.lacsoft.gestorpacientes.factorias;

import javax.swing.JTextField;

public interface FactoriaDeTextos {
	JTextField crearJTextField();
	JTextField crearJTextField(int tamanio);
	JTextField crearJtextFieldEnmascarado(String mascara);
	JTextField crearJTextFieldEstiloClave();
}
