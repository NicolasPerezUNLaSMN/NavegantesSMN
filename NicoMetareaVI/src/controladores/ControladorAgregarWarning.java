package controladores;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import funciones.Funciones;
import funciones.FuncionesNico;
import modelo.Boletin;
import modelo.Pronostico;
import modelo.Warning;
import negocio.BoletinABM;
import negocio.WarningABM;





public class ControladorAgregarWarning extends HttpServlet {
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException {
procesarPeticion(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException {
procesarPeticion(request, response);
}



@SuppressWarnings("null")
private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			
			
			Logger.getLogger("org.hibernate").setLevel(Level.OFF);
			
			
			try {
				
				
				
				
				
			
				
				String direccionAPP = "/entrada";
				
				
				
				WarningABM warningABM = new WarningABM();
				
				
				//Numero de warning DESPUES VEO COMO GENERARLO
				int numeroWarning = warningABM.maximoNumeroWarning()+1;
				int actWarning = 1;
				
		
			
				String tipo = request.getParameter("tipo");
				String fenomeno = request.getParameter("fenomeno");
				String valorCentral = request.getParameter("valorCentral");
				
				String latitud1 = request.getParameter("latitud1");
				String latitud2 = request.getParameter("latitud2");
				String latitud3 = request.getParameter("latitud3");
				String latitud4 = request.getParameter("latitud4");
				
				String longitud1 = request.getParameter("longitud1");
				String longitud2 = request.getParameter("longitud2");
				String longitud3 = request.getParameter("longitud3");
				String longitud4 = request.getParameter("longitud4");
				
				String latitudF1 = request.getParameter("latitudF1");
				String latitudF2 = request.getParameter("latitudF2");
				String latitudF3 = request.getParameter("latitudF3");
				String latitudF4 = request.getParameter("latitudF4");
				
				String longitudF1 = request.getParameter("longitudF1");
				String longitudF2 = request.getParameter("longitudF2");
				String longitudF3 = request.getParameter("longitudF3");
				String longitudF4 = request.getParameter("longitudF4");
				
				
				String queHace = request.getParameter("queHace");
				String areas[] =   request.getParameterValues("areas");
			
				
				String fecha1 = "";
				String fecha2 = "";
				String hora1 = "";
				String hora2 = "";
				
				String datetimeLocal1 =  request.getParameter("fechaInicial");
				
				if ( datetimeLocal1.contains("T")) {
				String[] dateTime1 = datetimeLocal1.split("T");
				 fecha1 = dateTime1[0];
				 hora1 = dateTime1[1];
				 
				 System.out.print("---------------->fecha: " +fecha1 +"hora: " +hora1);
				}
				
				
				String datetimeLocal2 =  request.getParameter("fechaFinal");
		
				if ( datetimeLocal2.contains("T")) {
				String[] dateTime2 = datetimeLocal2.split("T");
				fecha2 = dateTime2[0];
				 hora2 = dateTime2[1];
				 
				 
				 System.out.print("---------------->fecha: " +fecha2 +"hora: " +hora2);
				}
				
				
				/*
				String datetimeLocal3 =  request.getParameter("fechaFinalCoordenadas");
				String[] dateTime3 = datetimeLocal3.split("T");
				String fecha3 = dateTime3[0];
				String hora3 = dateTime3[1];*/
				
				
			
				
				
				String posicionInicial = " ";
				if(!latitud1.equals("")&&!longitud1.equals("")) {
				posicionInicial = posicionInicial +latitud1+"S " +longitud1 + "W ";}
				if(!latitud2.equals("")&&!longitud2.equals("")) {
					posicionInicial = posicionInicial +latitud2+"S " +longitud2 + "W ";}
				if(!latitud3.equals("")&&!longitud3.equals("")) {
					posicionInicial = posicionInicial +latitud3+"S " +longitud3 + "W ";}
				if(!latitud4.equals("")&&!longitud4.equals("")) {
					posicionInicial = posicionInicial +latitud4+"S " +longitud4 + "W ";}
				
				
				String posicionFinal = " ";
				if(!latitudF1.equals("")&&!longitudF1.equals("")) {
					posicionFinal = posicionFinal +latitudF1+"S " +longitudF1 + "W ";}
				if(!latitudF2.equals("")&&!longitudF2.equals("")) {
					posicionFinal = posicionFinal +latitudF2+"S " +longitudF2 + "W ";}
				if(!latitudF3.equals("")&&!longitudF3.equals("")) {
					posicionFinal = posicionFinal +latitudF3+"S " +longitudF3 + "W ";}
				if(!latitudF4.equals("")&&!longitudF4.equals("")) {
					posicionFinal = posicionFinal +latitudF4+"S " +longitudF4 + "W ";}
				
				
				
				//System.out.println(queHace);
			
				
				String areasAfectadas ="";
				for (String area: areas) {
					//System.out.println(area);
					areasAfectadas = areasAfectadas  +area.toUpperCase() +" ";
				}
			
						
				
				
				//System.out.println("Dia inicial: " +fecha1 +" hora: " +hora1);
				//System.out.println("Dia final: " +fecha2 +" hora: " +hora2);
			
				
			
				//System.out.println("-------------------------");
				
				
				
				Pronostico p = funciones.Funciones.cargarXML(direccionAPP);
					
				//String retorno = "\n";
					
					
					
				//retorno = retorno +Funciones.generarPronosticosParaWarning(p, areas);
					
					
					
				//System.out.println(retorno.toUpperCase());
				
				
				//System.out.println("-----Genero el warning--------");
				
				
				GregorianCalendar hoy = new GregorianCalendar();
				
				int dia = hoy.get(Calendar.DAY_OF_MONTH);
				int hora = hoy.get(Calendar.HOUR_OF_DAY)+3;
				int mes = hoy.get(Calendar.MONTH)+1;
				int anio = hoy.get(Calendar.YEAR);
				
					String horaString = "";
				
				if(hora<10){
					horaString = "0" +String.valueOf(hora);
				}else {horaString = String.valueOf(hora);}
				
				String horaUTCSalida = "";
				
				if (hora<19&&hora>3){
					horaUTCSalida = "12";
				}else {horaUTCSalida = "00"; hoy.add(Calendar.DAY_OF_MONTH,1); dia = hoy.get(Calendar.DAY_OF_MONTH);}
				
				
				
				
				
				String diaString = "";
				
				if(dia<10){
					diaString = "0" +String.valueOf(dia);
				}else {diaString = String.valueOf(dia);}
				
				
				String mesString = "";
				
				if(mes<10){
					mesString = "0" +String.valueOf(mes);
				}else {mesString = String.valueOf(mes);}
				
				
				hoy.add(Calendar.DAY_OF_MONTH,1);
				int diaSiguiente = hoy.get(Calendar.DAY_OF_MONTH);
				String diaSiguienteString = String.valueOf(diaSiguiente);
				
				
				String warning = "WWST02  SABM  " +diaString +horaUTCSalida +"00" +"\r\n1:31:06:01:00 \r\nSECURITE\r\nHIGH SEAS WARNING FOR METAREA VI ISSUED BY SMN ARGENTINA " +diaString +" " +FuncionesNico.traerMesEnLetrasIngles(hoy) +anio +"/" +horaUTCSalida +"00"+".\r\n";
				
				String fuerzaViento = " ";
				switch (tipo) {
				case "GALE FORCE - 8 o 9 BEAUFORT - 34 a 47 kt": warning = warning + "\r\nGALE WARNING NRO "+numeroWarning +" "; fuerzaViento ="8-9"; break;
				case "STORM FORCE - 10 o 11 BEAUFORT - 48 a 63 kt": warning = warning + "\r\nSTORM WARNING NRO "+numeroWarning +" "; fuerzaViento="10-11"; break;
				case "HURRICANE FORCE - +11 BEAUFORT - +63 kt": warning = warning + "\r\nHURRICANE WARNING NRO "+numeroWarning +" "; fuerzaViento="12"; break;
				}
				
				//agrego las areas afectadas
				warning = warning + areasAfectadas;
				//agrego las notas
				warning = warning + "\r\n\r\nGENERAL SYNOPSIS\r\n";
				
				String valorEnHPA = ""; //Si es necesario
				if(valorCentral==null) {valorEnHPA = " ";}else {valorEnHPA = valorCentral.toString().toUpperCase() +"HPA";}
				
				
				switch(fenomeno) {
				case  "DEPRESION": warning = warning +"LOW " +valorEnHPA; break;
				case "ANTICICLON": warning = warning +"HIGH " +valorEnHPA;break;
				case "FRENTE": warning = warning +"FRONT";break;
				case "FRENTE FRIO": warning = warning +"CFNT";break;
				case "FRENTE CALIENTE": warning = warning +"WFNT";break;
				case "FRENTE OCLUIDO": warning = warning +"OCCLUDED";break;
				case "FRENTE ESTACIONARIO": warning = warning +"STATIONARY FRONT";break;
				case "CICLOGENESIS": warning = warning +"CYCLOGENESIS";break;
				case "FUERTE GRADIENTE BARICO": warning = warning +"STRONG GRADIENT";break;
				}
				
				//agrego las posiciones
				if(!posicionInicial.equals(" ")) {
				warning = warning +" AT " +posicionInicial;
				}
				
				if(!posicionFinal.equals(" ")) {
					warning = warning + " EXPECTED " + posicionFinal;
				}
				
				
				switch (queHace) {
				case "Provocara": warning = warning +"\r\nPROVOKES WINDS FORCE " +fuerzaViento;break;
				case "Provoca": warning = warning +" \r\nPROVOKE WINDS FORCE " +fuerzaViento;break;
				}
				
				
				//agrego las areas que afecta
				//warning = warning +" IN " +areasAfectadas +" " +"\r\nFROM " +fecha1+"/"+hora1 +" UNTIL " +fecha2+"/"+hora2 +".\r\n\r\n";
				if(!fecha1.equals("")&&!fecha2.equals("")) {
					//agrego las areas que afecta
					warning = warning +" IN " +areasAfectadas +" " +"\r\nFROM " +FuncionesNico.stringAFechaBoletin(fecha1)+" "+hora1.substring(0, 2) +"00" +" UNTIL " +FuncionesNico.stringAFechaBoletin(fecha2)+" "+hora2.substring(0, 2) +"00 UTC" +".\r\n";
					//warning = warning +" IN " +areasAfectadas +" " +"\r\nFROM " +fecha1 +" "+hora1 +"00" +" UNTIL " +fecha2+" "+hora2 +"00 UTC" +".\r\n";
					
				}
				
				if(!fecha1.equals("")&&fecha2.equals("")) {
					//agrego las areas que afecta
					warning = warning +" IN " +areasAfectadas +" " +"\r\nFROM " +FuncionesNico.stringAFechaBoletin(fecha1)+" "+hora1.substring(0, 2) +"00 UTC" +".\r\n";
					
				}
				
				if(fecha1.equals("")&&!fecha2.equals("")) {
					//agrego las areas que afecta
					warning = warning +" IN " +areasAfectadas +" "  +" UNTIL " +FuncionesNico.stringAFechaBoletin(fecha2)+" "+hora2.substring(0, 2) +"00 UTC" +".\r\n";
					
				}
				
				if(fecha1.equals("")&&fecha2.equals("")) {
					warning = warning +" IN " +areasAfectadas +".\r\n";
				}
				
				System.out.println(Funciones.generarPronosticos(p));
				
				
				//arego el pronostico de las areas
				warning = warning +Funciones.generarPronosticosParaWarning(p, areas);
				
				warning = warning +"\r\n\r\nnnnn= ";
				
				//Genero el Warning para guardarlo
				Warning w = new Warning();
				w.setActualizacion(actWarning);
				w.setFecha(new GregorianCalendar());
				w.setNumeroWarning(numeroWarning);
				//int id = 100*numeroWarning+actWarning;
				//w.setIdWarning(id);
				w.setTexto(warning.toUpperCase());
				w.setAreas(areasAfectadas);
				w.setFenomeno(fenomeno);
				w.setDominio("HIGH SEA");
				w.setTipo(tipo);
				
				System.out.println("---------------");
				System.out.print(w.getTexto());
				
				
				BoletinABM b = new BoletinABM();
				//b.agregar(new GregorianCalendar(), 20);
				
			
				Boletin boletin = b.traerBoletin(new GregorianCalendar(),12);
				
				
				//System.out.println(boletin.getIdBoletin());
				
				WarningABM wABM = new WarningABM();
				wABM.agregar(w.getNumeroWarning(), w.getActualizacion(), w.getFecha(), w.getAreas(), w.getFenomeno(), w.getTexto(), w.getTipo(), w.getDominio(), 1, boletin);
				
				
			
				
				
				//System.out.println(warning.toUpperCase());
				
				
				
				
				
				

				//request.setAttribute("w", w);
				request.getRequestDispatcher("/index.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/indexERROR.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}