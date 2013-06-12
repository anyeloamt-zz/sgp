package com.lacsoft.gestorpacientes.entidades;

import javax.swing.ImageIcon;

public class Paciente {

	private String id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String cedula;
	private String fechaNacimiento;
	private String fumador;
	private String alergico;
	private ImageIcon fotoImagen;
	private String fotoString;
	private String direccion;
	
	public Paciente(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Paciente(String nombre, String apellido, String telefono,
			String cedula, String fechaNacimiento, String fumador,
			String alergico, String fotoString,String direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.fumador = fumador;
		this.alergico = alergico;
		this.fotoString = fotoString;
		this.direccion = direccion;
	}

	public Paciente(String nombre, String apellido, String telefono,
			String cedula, String fechaNacimiento, String fumador,
			String alergico, ImageIcon foto, String id,String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.fumador = fumador;
		this.alergico = alergico;
		this.fotoImagen = foto;
		this.id = id;
		this.direccion = direccion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFumador() {
		return fumador;
	}

	public void setFumador(String fumador) {
		this.fumador = fumador;
	}

	public String getAlergico() {
		return alergico;
	}

	public void setAlergico(String alergico) {
		this.alergico = alergico;
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

}
