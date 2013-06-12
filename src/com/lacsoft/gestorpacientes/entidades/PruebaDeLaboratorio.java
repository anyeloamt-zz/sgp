package com.lacsoft.gestorpacientes.entidades;

public class PruebaDeLaboratorio {

	private String pruebaId;
	private String codigo;
	private String nombre;

	public PruebaDeLaboratorio(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public String getPruebaId() {
		return pruebaId;
	}

	public void setPruebaId(String pruebaId) {
		this.pruebaId = pruebaId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
