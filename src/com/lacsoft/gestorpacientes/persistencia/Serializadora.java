package com.lacsoft.gestorpacientes.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializadora {

	private ObjectOutputStream escritor;
	private ObjectInputStream lector;

	public void escribir(Object objeto) {

		try {
			escritor = new ObjectOutputStream(new FileOutputStream(new File(
					"usuario.dat")));
			escritor.writeObject(objeto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object leer(String nombreArchivo) {
		Object retorno = null;

		try {
			lector = new ObjectInputStream(new FileInputStream(new File(nombreArchivo)));
			retorno = lector.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return retorno;
	}

}
