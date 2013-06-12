package com.lacsoft.gestorpacientes.entidades;

public class PacienteVisitas {

	private String nombrePaciente;
	private String cedulaPaciente;
	private String fechaCita;
	private String horaCita;
	private String causaCita;

	public PacienteVisitas() {

	}

	public PacienteVisitas(String nombrePaciente, String cedulaPaciente,
			String fechaCita, String horaCita, String causaCita) {
		this.nombrePaciente = nombrePaciente;
		this.cedulaPaciente = cedulaPaciente;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.causaCita = causaCita;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public String getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(String fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

	public String getCausaCita() {
		return causaCita;
	}

	public void setCausaCita(String causaCita) {
		this.causaCita = causaCita;
	}

}
