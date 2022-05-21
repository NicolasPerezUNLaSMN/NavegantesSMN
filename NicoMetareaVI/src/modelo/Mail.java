package modelo;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import funciones.Funciones;
import funciones.FuncionesNico;
import negocio.EmailABM;
 
/**
 * @author datojava.blogspot.com
 */
public class Mail {
	
	
	
	
	//static String directorioAPP = "C:\\Users\\nperez\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\NicoMetareaVI";
 
	
	
	
	
 public void mandarCorreo(String envioA, String contrasenia) {
  // El correo gmail de env�o
  String correoEnvia = "nperez@smn.gov.ar";
  String claveCorreo = "Nishikori3";
 
  // La configuraci�n para enviar correo
  Properties properties = new Properties();
  properties.put("mail.smtp.host", "smtp.smn.gov.ar");
  properties.put("mail.smtp.starttls.enable", "true");
  properties.put("mail.smtp.port", "587");
  properties.put("mail.smtp.auth", "true");
  properties.put("mail.user", correoEnvia);
  properties.put("mail.password", claveCorreo);
 
  // Obtener la sesion
  Session session = Session.getInstance(properties, null);
 
  try {
   // Crear el cuerpo del mensaje
   MimeMessage mimeMessage = new MimeMessage(session);
 
   // Agregar quien env�a el correo
   mimeMessage.setFrom(new InternetAddress(correoEnvia, "Dato IMPORTANTE PercepcionUML"));
    
   // Los destinatarios
   InternetAddress[] internetAddresses = {
     new InternetAddress(envioA)};
 
   // Agregar los destinatarios al mensaje
   mimeMessage.setRecipients(Message.RecipientType.TO,
     internetAddresses);
 
   // Agregar el asunto al correo
   mimeMessage.setSubject("Contrase�a para Percepcion UML");
 
   // Creo la parte del mensaje
   MimeBodyPart mimeBodyPart = new MimeBodyPart();
   mimeBodyPart.setText("Usted ha creado un Diagrama de clases, como usted no ha puesto un pass, se le esta asignando uno. \nSolo con esta contrase�a podra ingresar al mismo: \n \n " +contrasenia +"\n \n \n Muchas gracias por utilizar este software. ");
 
   

   
   
   
   // Crear el multipart para agregar la parte del mensaje anterior
   Multipart multipart = new MimeMultipart();
   multipart.addBodyPart(mimeBodyPart);
  
 
   // Agregar el multipart al cuerpo del mensaje
   mimeMessage.setContent(multipart);
 
   // Enviar el mensaje
   Transport transport = session.getTransport("smtp");
   transport.connect(correoEnvia, claveCorreo);
   transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
   transport.close();
 
  } catch (Exception ex) {
   ex.printStackTrace();
  }
  System.out.println("Correo enviado");
 }
 
 
 
 public void mandarConAdjuntos(String enviarA, String direccionAPP) {
	  // El correo gmail de env�o
	  String correoEnvia = "percepcionuml@gmail.com";
	  String claveCorreo = "Nishikori3";
	 
	  // La configuraci�n para enviar correo
	  Properties properties = new Properties();
	  properties.put("mail.smtp.host", "smtp.gmail.com");
	  properties.put("mail.smtp.starttls.enable", "true");
	  properties.put("mail.smtp.port", "587");
	  properties.put("mail.smtp.auth", "true");
	  properties.put("mail.user", correoEnvia);
	  properties.put("mail.password", claveCorreo);
	 
	  // Obtener la sesion
	  Session session = Session.getInstance(properties, null);
	 
	  try {
	   // Crear el cuerpo del mensaje
	   MimeMessage mimeMessage = new MimeMessage(session);
	 
	   // Agregar quien env�a el correo
	   mimeMessage.setFrom(new InternetAddress(correoEnvia, "Se han enviado los boletines"));
	    
	   // Los destinatarios
	   InternetAddress[] internetAddresses = {
	     new InternetAddress(enviarA)};
	 
	   // Agregar los destinatarios al mensaje
	   mimeMessage.setRecipients(Message.RecipientType.TO,
	     internetAddresses);
	 
	   // Agregar el asunto al correo
	   mimeMessage.setSubject("Boletines de navegacion");
	 
	   // Creo la parte del mensaje
	   MimeBodyPart mimeBodyPart = new MimeBodyPart();
	   mimeBodyPart.setText("Se adjuntaron los boletines de navegacion maritima de la metarea VI\n\n");
	 

	   
	   

	   
	   
	   // Crear el multipart para agregar la parte del mensaje anterior
//	   Multipart multipart = new MimeMultipart();
	  // multipart.addBodyPart(mimeBodyPart);
	  
	   
	// Se compone el adjunto con el texto
	   MimeBodyPart adjunto = new MimeBodyPart();
	   //adjunto.setDataHandler(

	   
	   //new DataHandler(new FileDataSource(direccionAPP+"\\InformesMetareaVI\\Boletines\\highseas.txt")));
//	   adjunto.setFileName("highseas.txt");
	   
	   //multipart.addBodyPart(adjunto);
	   
	   
	   /*
	   
	   //agrego los adjuntos
	   for (String url:Funciones.listaDeUrls()) {
	   
	// Se compone el adjunto con el texto
	   MimeBodyPart adjunto = new MimeBodyPart();
	   adjunto.setDataHandler(
	       new DataHandler(new FileDataSource("url")));
	   adjunto.setFileName("url");
	   multipart.addBodyPart(adjunto);
	   }
	 
	   */
	   
	   
	   
	   // Agregar el multipart al cuerpo del mensaje
	   //mimeMessage.setContent(multipart);
	 
	   // Enviar el mensaje
	   Transport transport = session.getTransport("smtp");
	   transport.connect(correoEnvia, claveCorreo);
	   transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	   transport.close();
	 
	  } catch (Exception ex) {
	   ex.printStackTrace();
	  }
	  System.out.println("Correo enviado");
	 }
 
 
 
 public void mandarTXTDeDirectorio(String direccionAPP) {
	 
	  EmailABM eABM = new EmailABM();
	  
	  
	  System.out.print("Entre a TXTDirectorio");
	   
	   
	   //envio a todos los destinatarios
	   for (Email e: eABM.traerEmail())	{
	 
	  // El correo gmail de env�o
	  String correoEnvia = "nykolaiperez@gmail.com";
	  String claveCorreo = "nishikori3141592";
	 
	  // La configuraci�n para enviar correo
	  Properties properties = new Properties();
	  properties.put("mail.smtp.host", "smtp.gmail.com");
	  properties.put("mail.smtp.starttls.enable", "true");
	  properties.put("mail.smtp.port", "587");
	  properties.put("mail.smtp.auth", "false");
	  properties.put("mail.user", correoEnvia);
	  properties.put("mail.password", claveCorreo);
	  
	  
	  String hora= FuncionesNico.traerFechaCortaConHora(new GregorianCalendar());
	 
	  // Obtener la sesion
	  Session session = Session.getInstance(properties, null);
	  
	  
	  System.out.print("Entreo al TRY");
	 
	  try {
	   // Crear el cuerpo del mensaje
	   MimeMessage mimeMessage = new MimeMessage(session);
	 
	   // Agregar quien env�a el correo
	   mimeMessage.setFrom(new InternetAddress(correoEnvia, "Te han enviados los ultimos Boletines de Navegación maritima."));
	    
	   
	
	 
	  
	   // Los destinatarios
	   InternetAddress[] internetAddresses = {new InternetAddress(e.getEmail())};
	   
	 
	   System.out.println("Este es el envio" +internetAddresses.toString());
	 
	   // Agregar los destinatarios al mensaje
	   mimeMessage.setRecipients(Message.RecipientType.TO,
	     internetAddresses);
	 
	   // Agregar el asunto al correo
	   mimeMessage.setSubject(hora +"  -  Ultimos boletines");
	 
	   // Creo la parte del mensaje
	   MimeBodyPart mimeBodyPart = new MimeBodyPart();
	   mimeBodyPart.setText("Se adjuntan los txt correspondientes a los ultimos boletines. \n\n\nServicio Meteorologico Nacional.\n\n\n"
			   +"Dorrego 4019, (C1425GBE)\r\n" + 
			   "Ciudad Autonoma de Buenos Aires, Argentina.\r\n" + 
			   "Tel: (+54 11) 5167-6767\r\n" + 
			   "smn@smn.gov.ar\r\n\r\n\r\n");

	 
	   
	   File dir = new File(direccionAPP+"/InformesMetareaVI/Boletines");
	   String cabeceraDir = direccionAPP+"/InformesMetareaVI/Boletines/";
	String[] ficheros = dir.list();
	

	
	 
	   File dir2 = new File(direccionAPP+"/InformesMetareaVI/Warning");
	   String cabeceraDir2 = direccionAPP+"/InformesMetareaVI/Warning/";
	String[] ficheros2 = dir2.list();
	   
	   
	   File dir3 = new File(direccionAPP+"/InformesMetareaVI/Navtex");
	   String cabeceraDir3 = direccionAPP+"/InformesMetareaVI/Navtex/";
	String[] ficheros3 = dir3.list();
	   
	   
	   
	   
	   
	   
	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(mimeBodyPart);
	   
	   
	  
	   // Crear el multipart para agregar la parte del mensaje anterior
	   for(String s: ficheros) {
		   
		   System.out.println(s);
		   
		   
	  MimeBodyPart adjunto = new MimeBodyPart();
	   //adjunto.setDataHandler(
	       //new DataHandler(new FileDataSource(cabeceraDir +s)));
	   System.out.println(cabeceraDir +s);
	   adjunto.setFileName(s);
	   multipart.addBodyPart(adjunto);
	  }
	   
	   
	
	
	  for(String s: ficheros2) {
		  
		  System.out.println(s);
		  
		  
		  MimeBodyPart adjunto = new MimeBodyPart();
		   //adjunto.setDataHandler(
		     //  new DataHandler(new FileDataSource(cabeceraDir2 +s)));
		   System.out.println(cabeceraDir2 +s);
		   adjunto.setFileName(s);
		   multipart.addBodyPart(adjunto);
		  }
	  
	  
	  
	  for(String s: ficheros3) {
		  
		  System.out.println(s);
		  
		  
		  MimeBodyPart adjunto = new MimeBodyPart();
		  // adjunto.setDataHandler(
		    //   new DataHandler(new FileDataSource(cabeceraDir3 +s)));
		   System.out.println(cabeceraDir3 +s);
		   adjunto.setFileName(s);
		   multipart.addBodyPart(adjunto);
		  }
	   
	   
	   
	   
	   
	   
	   
	   
	
	   
	   
	   
	   
	   
	   
	   //multipart.addBodyPart(adjunto2);
	   //multipart.addBodyPart(adjuntoDescarga);
	 
	   // Agregar el multipart al cuerpo del mensaje
	   mimeMessage.setContent(multipart);
	 
	   // Enviar el mensaje
	   Transport transport = session.getTransport("smtp");
	   transport.connect(correoEnvia, claveCorreo);
	   transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	   transport.close();
	   
	
	  
	 
	   
	   
	 
	   
	   
	  }//cierra el try
	  
	  catch (Exception ex) {
	   ex.printStackTrace();
	  }
	  
	  
	  
	   System.out.println("Correo enviado a " +e.getEmail());
	   }//Cierra el envio
	 
	}
	  
 
	 
 
 
}
 