package com.lacsoft.gestorpacientes.observadores;

import java.util.ArrayList;

import com.lacsoft.gestorpacientes.vista.PanelAgregarUsuario;

public class ObservadorDeEspecialidades implements Observador {

	@Override
	public void actualizar(ArrayList<String> datos) {
		PanelAgregarUsuario.getInstancia().cargarEspecialidades();
	}

}
