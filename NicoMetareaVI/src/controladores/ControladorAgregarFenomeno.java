package controladores;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import negocio.FenomenoABM;
import negocio.WarningABM;





public class ControladorAgregarFenomeno extends HttpServlet {
	
	
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
				
				
				
		
				String fenomeno = request.getParameter("fenomeno");
				String valorCentral = request.getParameter("valorCentral");
				String mov = request.getParameter("mov");
				String evo = request.getParameter("evo");
				
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
				
				
				String navtex = request.getParameter("navtex");
		
				
				//System.out.println(navtex);
				
				
				String fecha1 = "";
				String fecha2 = "";
				String hora1 = "";
				String hora2 = "";
				
				
				String textoInicial = " ";
				String textoFinal = " ";
			
				
				String datetimeLocal1 =  request.getParameter("fechaInicial");
				String datetimeLocal2 =  request.getParameter("fechaFinal");
				
				
				
				if(datetimeLocal1.contains("T")) {
				
				String[] dateTime1 = datetimeLocal1.split("T");
				fecha1 = dateTime1[0].substring(dateTime1[0].length()-2,dateTime1[0].length());
				hora1 = dateTime1[1].substring(0,2);
				
				textoInicial = " AT " +fecha1 +"/" +hora1;
				
				
				System.out.println("------------------->>>>>" +textoInicial);
				
				}
				
			
				
				
				if(datetimeLocal2.contains("T")) {
					  
				String[] dateTime2 = datetimeLocal2.split("T");
				fecha2 = dateTime2[0].substring(dateTime2[0].length()-2,dateTime2[0].length());
				hora2 = dateTime2[1].substring(0,2);
				 textoFinal = " UNTIL " +fecha2 +"/" +hora2;
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
				
				
				String textoFenomeno = "";
			
				
			
				String valorEnHPA = ""; //Si es necesario
				if(valorCentral==null) {valorEnHPA = " ";}else {valorEnHPA = valorCentral.toString().toUpperCase() +"HPA";}
				
				
				switch(fenomeno) {
				case  "DEPRESION": textoFenomeno = "LOW " +valorEnHPA; break;
				case "ANTICICLON": textoFenomeno = "HIGH " +valorEnHPA;break;
				case "FRENTE": textoFenomeno = "FRONT";break;
				case "FRENTE FRIO": textoFenomeno = "CFNT";break;
				case "FRENTE CALIENTE": textoFenomeno = "WFNT";break;
				case "FRENTE OCLUIDO": textoFenomeno = "OCCLUDED";break;
				case "CICLOGENESIS": textoFenomeno  = "CYCLOGENESIS";break;
				case "FUERTE GRADIENTE BARICO": textoFenomeno= "STRONG GRADIENT";break;
				case "EJE DE CUÑA": textoFenomeno = "RIDGE"; break;
				case "EJE DE VAGUADA": textoFenomeno = "TROUGH"; break;
				}
				
				//agrego las posiciones
				if(!posicionInicial.equals(" ")) {
				textoFenomeno = textoFenomeno +" IN " +posicionInicial +textoInicial;
				}
				
				if(!mov.equals("SIN MOV")) {
					textoFenomeno = textoFenomeno +" MOV " +mov;
				}
				
				if(!evo.equals("SIN EVO")) {
					textoFenomeno = textoFenomeno + " " +evo;
				}
				
				
				if(!posicionFinal.equals(" ")) {
					textoFenomeno = textoFenomeno + " EXP " + posicionFinal +textoFinal;
					
				}
				
				
				
				
				
				
				int navtexInt =0; 
				if(navtex.equals("Incluir")) {
					navtexInt = 1;
				}
				
			
				
				//System.out.println(textoFenomeno);
				
				
				FenomenoABM fABM = new FenomenoABM();
				fABM.agregar(textoFenomeno, navtexInt);
				
				
				

				//request.setAttribute("w", w);
				request.getRequestDispatcher("/indexSituacion.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/indexSituacion.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}