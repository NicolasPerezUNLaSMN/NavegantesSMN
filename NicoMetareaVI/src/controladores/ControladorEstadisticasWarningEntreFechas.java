package controladores;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
import modelo.Estadisticas;
import modelo.Pronostico;
import modelo.Warning;
import negocio.BoletinABM;
import negocio.WarningABM;





public class ControladorEstadisticasWarningEntreFechas extends HttpServlet {
	
	
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
				
				
				 
				int diaInicial = 0;
				
				int mesInicial = 0;
				
				int anioInicial = 0;
				
				int horaInicial = 0;
						
				int minInicial = 0;
				 
				
				
				int diaFinal = 0;
				
				int mesFinal = 0;
				
				int anioFinal= 0;
				
				int horaFinal = 0;
						
				int minFinal= 0 ;
	
				
				String fecha1 = "";
				String fecha2 = "";
				String hora1 = "";
				String hora2 = "";
				
				String datetimeLocal1 =  request.getParameter("fechaInicial");
				
				if ( datetimeLocal1.contains("T")) {
				String[] dateTime1 = datetimeLocal1.split("T");
				 fecha1 = dateTime1[0];
				 hora1 = dateTime1[1];
				 
				diaInicial = Integer.parseInt(fecha1.substring(8, 10));
				
				 mesInicial = Integer.parseInt(fecha1.substring(5, 7));
				
				anioInicial = Integer.parseInt(fecha1.substring(0, 4));
				
				horaInicial =  Integer.parseInt(hora1.substring(0, 2));
						
				minInicial =  Integer.parseInt(hora1.substring(3, 5));
				 
				 
				 
				 
				}
				
				
				String datetimeLocal2 =  request.getParameter("fechaFinal");
		
				if ( datetimeLocal2.contains("T")) {
				String[] dateTime2 = datetimeLocal2.split("T");
				fecha2 = dateTime2[0];
				 hora2 = dateTime2[1];
				 
					diaFinal = Integer.parseInt(fecha2.substring(8, 10));
					
					 mesFinal = Integer.parseInt(fecha2.substring(5, 7));
					
					anioFinal = Integer.parseInt(fecha2.substring(0, 4));
					
					horaFinal =  Integer.parseInt(hora2.substring(0, 2));
							
					minFinal =  Integer.parseInt(hora2.substring(3, 5));
				}
				
				//por el gregorian
				mesInicial = mesInicial -1;
				mesFinal = mesFinal -1;
				
				GregorianCalendar fechaInicial = new GregorianCalendar(anioInicial, mesInicial, diaInicial, horaInicial, minInicial);
				GregorianCalendar fechaFinal = new GregorianCalendar(anioFinal, mesFinal, diaFinal, horaFinal, minFinal);
				
				System.out.println("De donde hasta donde");
				System.out.println(funciones.FuncionesNico.traerFechaCortaConHora(fechaInicial));
				System.out.println(funciones.FuncionesNico.traerFechaCortaConHora(fechaFinal));
				System.out.println("----------------------------");
				
				
				WarningABM wABM = new WarningABM();
				List<Warning> lista = new ArrayList<Warning>(); 
				
				for (Warning w:wABM.traerWarningTodos()) {
					
					
					System.out.println(funciones.FuncionesNico.traerFechaCortaConHora(w.getFecha()));
					
					if(w.getFecha().after(fechaInicial)&&w.getFecha().before(fechaFinal)) {
						
						lista.add(w);
						
					}
					
					
					
				}
				
				Estadisticas e = new Estadisticas(lista);
				
			
				request.setAttribute("e", e);
				request.getRequestDispatcher("/estadisticasWarningEntreFechas.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/indexERROR.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}