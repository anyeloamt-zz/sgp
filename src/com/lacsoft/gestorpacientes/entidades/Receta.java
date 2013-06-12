package com.lacsoft.gestorpacientes.entidades;

public class Receta {
	
	private String id;
	private String fecha;
	private String nombrePaciente;
	private String idPaciente;
	private String padecimiento;
	private String codigoPadecimiento;
	private String medicamentos;
	
	public Receta(String id, String fecha, String nombrePaciente, String idPaciente, 
			String padecimiento, String codigoPadecimiento, String medicamentos) {
		this.id = id;
		this.fecha = fecha;
		this.nombrePaciente = nombrePaciente;
		this.padecimiento = padecimiento;
		this.medicamentos = medicamentos;
		this.codigoPadecimiento = codigoPadecimiento;
		this.idPaciente = idPaciente;
		
	}

	public Receta(String fecha, String nombrePaciente, String idPaciente, 
			String padecimiento, String codigoPadecimiento, String medicamentos) {
		this.fecha = fecha;
		this.nombrePaciente = nombrePaciente;
		this.padecimiento = padecimiento;
		this.medicamentos = medicamentos;
		this.codigoPadecimiento = codigoPadecimiento;
		this.idPaciente = idPaciente;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getCodigoPadecimiento() {
		return codigoPadecimiento;
	}

	public void setCodigoPadecimiento(String codigoPadecimiento) {
		this.codigoPadecimiento = codigoPadecimiento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getPadecimiento() {
		return padecimiento;
	}

	public void setPadecimiento(String padecimiento) {
		this.padecimiento = padecimiento;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}
	
	
}

