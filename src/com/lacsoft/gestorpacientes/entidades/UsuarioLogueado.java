package com.lacsoft.gestorpacientes.entidades;

import javax.swing.ImageIcon;
	/**
	 * Para guardar el usuario que se 
	 * acaba de loguear en el Sistema, cuenta con un Singleton
	 * así es lógico que sólo un usuario está logueado a la vez.
	 * 
	 * Está hecha para utilizar los datos del usuario que se logueó
	 * en distintas partes de la Aplicación.
	 * 
	 * @author Anyelo
	 * @author Leonardo
	 * @author Cristhian
	 *
	 */
public final class UsuarioLogueado {

	/**
	 * 
	 */
	private String rol;
	private String clave;
	private String nombre;
	private String apellido;
	private String direccion;
	private String cedula;
	private String telefono1;
	private String telefono2;
	private String celular;
	private String codigoDeEmpleado;
	private String especialidad;
	private String fotoString;
	private ImageIcon fotoImagen;
	private String correo;

	private static UsuarioLogueado instancia;
	
	public static synchronized UsuarioLogueado getInstancia() {
		return instancia = (instancia == null) ? new UsuarioLogueado() : instancia;
	}
	
	private UsuarioLogueado() {}	

	public void crear(String rol, String clave, String nombre,
			String apellido, String direccion, String cedula, String telefono1,
			String telefono2, String celular, String codigoDeEmpleado,
			String especialidad, String fotoString, ImageIcon fotoImagen,
			String correo) {
		this.rol = rol;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.cedula = cedula;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.celular = celular;
		this.codigoDeEmpleado = codigoDeEmpleado;
		this.especialidad = especialidad;
		this.fotoString = fotoString;
		this.fotoImagen = fotoImagen;
		this.correo = correo;
		System.out.println("Usuario Logueado cargado");
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCodigoDeEmpleado() {
		return codigoDeEmpleado;
	}

	public void setCodigoDeEmpleado(String codigoDeEmpleado) {
		this.codigoDeEmpleado = codigoDeEmpleado;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	
	public ImageIcon getFotoImagen() {
		return fotoImagen;
	}
	
	public void setFotoImagen(ImageIcon fotoImagen) {
		this.fotoImagen = fotoImagen;
	}
	
	public String getFotoString() {
		return fotoString;
	}
	
	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
