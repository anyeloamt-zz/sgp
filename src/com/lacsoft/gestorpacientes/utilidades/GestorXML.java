package com.lacsoft.gestorpacientes.utilidades;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.lacsoft.gestorpacientes.entidades.Correo;

public class GestorXML {
	private Document documento;
	private Element raiz;
	
	public GestorXML() {
		try {
			documento = new SAXBuilder().build(new File("config/configCorreo.xml"));
			raiz = documento.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Correo listarInformacion() {
		Element correo = raiz.getChild("correo");
		String direccion = correo.getChildText("direccion");
		String clave = correo.getChildText("clave");
		String servidor = correo.getChildText("servidor");
		String puerto = correo.getChildText("puerto");
		
		return new Correo(direccion, clave, servidor, puerto);
	}
}
