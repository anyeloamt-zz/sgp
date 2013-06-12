package com.lacsoft.gestorpacientes.generadoresdecodigos;

import java.util.ArrayList;


public class GeneradorDeCodigoPrueba implements GeneradorDeCodigos {
	
	public String generarCodigo() {
		ArrayList<String> codigos = new ArrayList<>();
		String prefijo = "PLB-";
		String retorno = "";
		while (true) {
			int numeroGenerado = (int) ((Math.random() * (99999 - 10000)) + 10000);
			retorno = (prefijo + Integer.toString(numeroGenerado));
			for (String codigo : codigos) {
				if (codigo.equalsIgnoreCase(retorno)) {
					continue;
				} else {
					break;
				}
			}
			break;

		}
		return retorno;
	}
}

