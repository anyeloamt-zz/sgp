package com.lacsoft.gestorpacientes.entidades;

public class PacienteRecetas {

	private String nombrePaciente;
	private String cedulaPaciente;
	private String nombrePadecimientos;
	private String mediciamentoPacientes;
	private String fechaReceta;

	
	public PacienteRecetas(String nombrePaciente, String cedulaPaciente,
			String nombrePadecimientos, String mediciamentoPacientes,
			String fechaReceta) {
		this.nombrePaciente = nombrePaciente;
		this.cedulaPaciente = cedulaPaciente;
		this.nombrePadecimientos = nombrePadecimientos;
		this.mediciamentoPacientes = mediciamentoPacientes;
		this.fechaReceta = fechaReceta;
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

	public String getNombrePadecimientos() {
		return nombrePadecimientos;
	}

	public void setNombrePadecimientos(String nombrePadecimientos) {
		this.nombrePadecimientos = nombrePadecimientos;
	}

	public String getMediciamentoPacientes() {
		return mediciamentoPacientes;
	}

	public void setMediciamentoPacientes(String mediciamentoPacientes) {
		this.mediciamentoPacientes = mediciamentoPacientes;
	}

	public String getFechaReceta() {
		return fechaReceta;
	}

	public void setFechaReceta(String fechaReceta) {
		this.fechaReceta = fechaReceta;
	}

}
