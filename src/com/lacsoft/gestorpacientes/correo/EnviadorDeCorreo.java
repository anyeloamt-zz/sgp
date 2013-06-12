package com.lacsoft.gestorpacientes.correo;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lacsoft.gestorpacientes.entidades.Correo;
import com.lacsoft.gestorpacientes.utilidades.GestorXML;

public class EnviadorDeCorreo {
	
	private final String miCorreo;
	private final String miClave;
	private final String servidorSMTP;
	private final String puertoEnvio;
	
	private GestorXML gestorXML = new GestorXML();
	
	private Properties propiedades;
    private Session session;
    
	public EnviadorDeCorreo() {
		
		Correo correo = gestorXML.listarInformacion();
		miCorreo = correo.getDireccion();
		servidorSMTP = correo.getServidor();
		puertoEnvio = correo.getPuerto();
		miClave = correo.getClave();

		propiedades = new Properties();
		propiedades.put("mail.smtp.user", miCorreo);
		propiedades.put("mail.smtp.host", servidorSMTP);
		propiedades.put("mail.smtp.port", puertoEnvio);
		propiedades.put("mail.smtp.starttls.enable", "true");
		propiedades.put("mail.smtp.auth", "true");
		propiedades.put("mail.smtp.socketFactory.port", puertoEnvio);
		propiedades.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		propiedades.put("mail.smtp.socketFactory.fallback", "false");
		propiedades.put("mail.debug", "true");
	}

	public boolean enviarCorreo(String mailReceptor, String nombreCompleto, String usuario,  String clave) {
		try {
			Authenticator auth = new autentificadorSMTP();
			session = Session.getInstance(propiedades, auth);

			MimeMessage msg = new MimeMessage(session);
			
			String pagina = "";
			
			Scanner scanner = new Scanner(new File("clave.html"));
			while (scanner.hasNext()) {
				pagina += scanner.nextLine();
			}
			
			/*
			 * Reemplazar los el nombre, usuario y clave en el archivo html
			 * Hay que igualarla a lo que devuelve el método replace
			 * que lo devuelve ya reeplazado.
			 */
			pagina = pagina.replace("nombreCompleto000", nombreCompleto);
			pagina = pagina.replace("usuario000", usuario);
			pagina = pagina.replace("clave000", clave);
			scanner.close();
			
			// Mensaje, charset (conjunto de caracteres y tipo)
			msg.setText(pagina,"utf-8", "html");
			msg.setSubject("Información de contraseña Gestor de Pacientes LAC-soft®");
			
			msg.setFrom(new InternetAddress(miCorreo));
			
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			System.out.println("El correo no se pudo enviar, " +
					"aquí se genera una excepción de la cual no imprimo el StackTrace");
			return false;
		}

	}

	private class autentificadorSMTP extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(miCorreo, miClave);
		}
	}

}
