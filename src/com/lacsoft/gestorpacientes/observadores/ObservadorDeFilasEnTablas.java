package com.lacsoft.gestorpacientes.observadores;

import java.util.ArrayList;

import com.lacsoft.gestorpacientes.vista.EditorSecundario;

public class ObservadorDeFilasEnTablas implements Observador {

	@Override
	public void actualizar(ArrayList<String> datos) {
		if (datos.size() <= 5) {
			new EditorSecundario(null, datos.get(0), datos.get(1),
					datos.get(2), Integer.parseInt(datos.get(3)),
					Integer.parseInt(datos.get(4)));
		} else {
			new EditorSecundario(null, datos.get(0), datos.get(1),
					datos.get(2), Integer.parseInt(datos.get(3)),
					Integer.parseInt(datos.get(4)), Boolean.valueOf(datos
							.get(5)));
		}
	}

}
