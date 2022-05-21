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





public class ControladorGenerarWarningActivos extends HttpServlet {
	
	
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
				
				
				
				WarningABM warningABM = new WarningABM();
				
				for (Warning w : warningABM.traerWarningActivos()) {
					
					
					
					//Genero el archivo con el warning
					//escribo el archivo
					 FileWriter fichero = null;
				        PrintWriter pw = null;
				        
				        FileWriter ficheroViejo = null;
				        PrintWriter pwViejo = null;
				        
				        
				        try
				        {
				        	
				        	String textoDominio = w.getDominio().replaceAll(" ", "_");
				            fichero = new FileWriter("C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Warning\\warning"+textoDominio +"-" +w.getIdWarning() +".txt");
				            ficheroViejo = new FileWriter("C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\WarningViejos\\warning"+textoDominio +"-" +w.getIdWarning() +".txt");
				            
				            
				            
				            pw = new PrintWriter(fichero);
				            pwViejo = new PrintWriter(ficheroViejo);

				            
				            String texto = w.getTexto().replaceAll("XXX", String.valueOf(w.getIdWarning()));
				                pw.printf(texto);
				                pwViejo.printf(texto);
				                
				                
				                
				                

				        } catch (Exception e) {
				            e.printStackTrace();
				        } finally {
				           try {
				           // Nuevamente aprovechamos el finally para 
				           // asegurarnos que se cierra el fichero.
				           if (null != fichero)
				              fichero.close();
				           } catch (Exception e2) {
				              e2.printStackTrace();
				           }
				        }
					
				        
				        
				        
				        
					
					
					

			}//cierra el for
				
				
				request.getRequestDispatcher("/index.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/index.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}