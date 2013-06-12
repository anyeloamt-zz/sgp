package com.lacsoft.gestorpacientes.factorias;

import java.awt.Font;

import javax.swing.JTabbedPane;

public class FactoriaDeJTabbedPaneGenericos implements FactoriaDeJTabbedPane {

	@Override
	public JTabbedPane crearJTabbedPane() {
		JTabbedPane tab = new JTabbedPane(JTabbedPane.NORTH,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.setFont(new Font("Calibri", Font.PLAIN, 14));
		return tab;
	}

}
