package com.lacsoft.gestorpacientes.entidades;

import javax.swing.ImageIcon;

public class Medico {
	private String codigoEmpleado, nombre, 
	apellido, cedula, direccion, telefono1, telefono2, 
	celular, especialidad;
	private ImageIcon foto; 

	public Medico() {
	}

	public Medico(String codigoEmpleado, String nombre, String apellido, String cedula,
			String direccion, String telefono1, String telefono2, String celular, 
			String especialidad, ImageIcon foto) {
		super();
		this.codigoEmpleado = codigoEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.direccion = direccion;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.celular = celular;
		this.especialidad = especialidad;
		this.foto = foto;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public ImageIcon getFoto() {
		return foto;
	}
	
	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}
	
}
