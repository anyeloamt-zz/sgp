package com.lacsoft.gestorpacientes.entidades;

public class PacienteSeleccionadoEnHistorial {

	public static PacienteSeleccionadoEnHistorial instancia;
	private String pacienteId;

	public static synchronized PacienteSeleccionadoEnHistorial getInstacia() {
		return instancia = (instancia == null) ? instancia = new PacienteSeleccionadoEnHistorial()
				: instancia;

	}

	private PacienteSeleccionadoEnHistorial() {

	}

	public String getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(String pacienteId) {
		this.pacienteId = pacienteId;
	}

}
