package com.lacsoft.gestorpacientes.entidades;

import java.util.Observable;


public class Especialidad extends Observable {

	private String codigo;
	private String nombre;

	public Especialidad() {
		
	}

	public Especialidad(String nombre) {
		this.nombre = nombre;
	}
	
	public Especialidad(String codigo, String nombre) {
		this.codigo = codigo;
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
