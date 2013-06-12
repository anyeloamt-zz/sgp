package com.lacsoft.gestorpacientes.persistencia;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class GestorXML {

	private Document documento;
	private Element raiz;
	private Element configuracion;
	
	protected GestorXML() {
		try {
			documento = new SAXBuilder().build(new File("config/conexion.xml"));
			raiz = documento.getRootElement();
			configuracion = raiz.getChild("configuracion");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String getStringConnection() {
		String retorno = null;

		String api = configuracion.getChildText("api");
		String compania = configuracion.getChildText("compania");
		String host = configuracion.getChildText("host");
		String baseDeDatos = configuracion.getChildText("baseDeDatos");
		String usuario = configuracion.getChildText("usuario");
		String clave = configuracion.getChildText("clave");

		retorno = api + ":" + compania + "://" + host + "/" + baseDeDatos
				+ "?user=" + usuario + "&password=" + clave;

		return retorno;
	}

	public String getNombreDriver() {
		String driver = configuracion.getChildText("driver");
		return driver;
	}
}
