package com.lacsoft.gestorpacientes.entidades;

public class Padecimiento {

	private String codigo;
	private String nombre;

	public Padecimiento() {}
	
	public Padecimiento(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Padecimiento(String nombre) {
		this.nombre = nombre;
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
