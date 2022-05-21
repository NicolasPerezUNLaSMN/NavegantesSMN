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
import negocio.WarningABM;





public class ControladorVerWarning extends HttpServlet {
	
	
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
				
				
				int idWarning = Integer.parseInt(request.getParameter("idWarning"));
				
				WarningABM wABM = new WarningABM ();
			
				
				Warning w = wABM.traerWarning(idWarning);
				
				String bandera = w.getDominio();
				
				
				
			    if(bandera.equals("HIGH SEA")) {
				

				request.setAttribute("idWarning", idWarning);
				request.getRequestDispatcher("/indexActualizarHigh.jsp").forward(request , response);
				}
			    
			    
			    if(bandera.equals("COASTAL")) {
					

					request.setAttribute("idWarning", idWarning);
					request.getRequestDispatcher("/indexActualizarCoastal.jsp").forward(request , response);
					}
			    
			    
			    	if(bandera.equals("OFF SHORE")) {
					

					request.setAttribute("idWarning", idWarning);
					request.getRequestDispatcher("/indexActualizarOFFShore.jsp").forward(request , response);
					}
			    
			    
			    
			    
			    
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/index.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}