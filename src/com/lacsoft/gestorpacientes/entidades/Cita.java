package com.lacsoft.gestorpacientes.entidades;

public class Cita {

	private String idCita;
	private String codigoEmpleado;
	private String idPaciente;
	private String nombreMedico;
	private String cedulaMedico;
	private String nombrePaciente;
	private String cedulaPaciente;
	private String fecha;
	private String hora;
	private String causa;
	
	public Cita() {
	
	}

	public Cita(String codigoEmpleado, String idPaciente, String nombreMedico, 
			String cedulaMedico,String nombrePaciente, String cedulaPaciente, 
			String fecha, String hora, String causa) {
		this.codigoEmpleado = codigoEmpleado;
		this.idPaciente = idPaciente;
		this.nombreMedico = nombreMedico;
		this.cedulaMedico = cedulaMedico;
		this.nombrePaciente = nombrePaciente;
		this.cedulaPaciente = cedulaPaciente;
		this.fecha = fecha;
		this.hora = hora;
		this.causa = causa;
	}

	public Cita(String idCita, String codigoEmpleado, String idPaciente,
			String nombreMedico, String cedulaMedico, String nombrePaciente, 
			String cedulaPaciente, String fecha, String hora, String causa) {
		this.idCita = idCita;
		this.codigoEmpleado = codigoEmpleado;
		this.idPaciente = idPaciente;
		this.nombreMedico = nombreMedico;
		this.cedulaMedico = cedulaMedico;
		this.nombrePaciente = nombrePaciente;
		this.cedulaPaciente = cedulaPaciente;
		this.fecha = fecha;
		this.hora = hora;
		this.causa = causa;
	}
	
	public String getCedulaMedico() {
		return cedulaMedico;
	}

	public void setCedulaMedico(String cedulaMedico) {
		this.cedulaMedico = cedulaMedico;
	}

	public String getCedulaPaciente() {
		return cedulaPaciente;
	}

	public void setCedulaPaciente(String cedulaPaciente) {
		this.cedulaPaciente = cedulaPaciente;
	}

	public String getIdCita() {
		return idCita;
	}

	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

}
	