package com.lacsoft.gestorpacientes.entidades;

public class ResultadoDeLaboratorio {

	private String idResultado;
	

	private String codigoPrueba;
	private String fecha;
	private String nombrePrueba;
	private String hora;
	private String estado;
	private String resultado;
	private String idPaciente;
	private String nombrePaciente;
	private String cedulaPaciente;

	public ResultadoDeLaboratorio(String codigoPrueba, String fecha,
			String hora, String estado, String resultado, String idPaciente) {
		super();
		this.codigoPrueba = codigoPrueba;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.resultado = resultado;
		this.idPaciente = idPaciente;
	}

	public ResultadoDeLaboratorio(String fecha, String nombrePrueba,
			String hora, String estado, String resultado,
			String nombrePaciente, String cedulaPaciente,String id) {
		super();
		this.fecha = fecha;
		this.nombrePrueba = nombrePrueba;
		this.hora = hora;
		this.estado = estado;
		this.resultado = resultado;
		this.nombrePaciente = nombrePaciente;
		this.cedulaPaciente = cedulaPaciente;
		this.idResultado = id;
	}
	
	public String getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(String idResultado) {
		this.idResultado = idResultado;
	}

	public String getCodigoPrueba() {
		return codigoPrueba;
	}

	public void setCodigoPrueba(String codigoPrueba) {
		this.codigoPrueba = codigoPrueba;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombrePrueba() {
		return nombrePrueba;
	}

	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
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

}
