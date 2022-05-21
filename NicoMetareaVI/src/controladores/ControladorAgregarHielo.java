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
import negocio.HieloABM;
import negocio.WarningABM;





public class ControladorAgregarHielo extends HttpServlet {
	
	
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
				
				
				
		
				String espaniol = "";
				String ingles = request.getParameter("ingles");
				
				
				HieloABM hABM = new HieloABM();
				//Agrego los dos en ingles, internamente hago la traduccion. Es mejor que copiar y pegar dos veces
				hABM.agregar(ingles, ingles);
				
				
				

				//request.setAttribute("w", w);
				request.getRequestDispatcher("/indexHielo.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/indexHielo.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}