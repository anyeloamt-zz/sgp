package com.lacsoft.gestorpacientes.fachadas;

import com.lacsoft.gestorpacientes.generadoresdecodigos.GeneradorDeCodigoAlergia;
import com.lacsoft.gestorpacientes.generadoresdecodigos.GeneradorDeCodigoEmpleado;
import com.lacsoft.gestorpacientes.generadoresdecodigos.GeneradorDeCodigoEspecialidad;
import com.lacsoft.gestorpacientes.generadoresdecodigos.GeneradorDeCodigoPadecimiento;
import com.lacsoft.gestorpacientes.generadoresdecodigos.GeneradorDeCodigoPrueba;
import com.lacsoft.gestorpacientes.generadoresdecodigos.GeneradorDeCodigos;

public class FachadaGestoraDeCodigos {

	public static String generarCodigo(String tipo) {
		String retorno = "";
		GeneradorDeCodigos generador;
		
		if (tipo.equals("Alergia")) {
			generador = new GeneradorDeCodigoAlergia();
			retorno = generador.generarCodigo();
		} else if (tipo.equals("Padecimiento")) {
			generador = new GeneradorDeCodigoPadecimiento();
			retorno = generador.generarCodigo();
		} else if (tipo.equals("Especialidad")) {
			generador = new GeneradorDeCodigoEspecialidad();
			retorno = generador.generarCodigo();
		} else if (tipo.equals("Empleado")) {
			generador = new GeneradorDeCodigoEmpleado();
			retorno = generador.generarCodigo();
		} else if (tipo.equals("Prueba")) {
			generador = new GeneradorDeCodigoPrueba();
			retorno = generador.generarCodigo();
		}
		return retorno;

	}

}
