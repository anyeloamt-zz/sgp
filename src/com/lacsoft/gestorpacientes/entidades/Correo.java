package com.lacsoft.gestorpacientes.entidades;

public class Correo {
	private String direccion, clave, servidor, puerto;
	
	public Correo() {

	}

	public Correo(String direccion, String clave, String servidor, String puerto) {
		super();
		this.direccion = direccion;
		this.clave = clave;
		this.servidor = servidor;
		this.puerto = puerto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	
}
