package com.lacsoft.gestorpacientes;

import javax.swing.UIManager;

import com.lacsoft.gestorpacientes.vista.VentanaLogin;

public class Sistema {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new VentanaLogin();
		//new VentanaPrueba();
	}
}
