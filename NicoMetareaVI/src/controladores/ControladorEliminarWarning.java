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
import negocio.CeseABM;
import negocio.WarningABM;





public class ControladorEliminarWarning extends HttpServlet {
	
	
	
	
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
				
				
				    URL ruta = this.getServletContext().getResource("/InformesMetareaVI");
					
					
					String nuevaDireccion = ruta.toString().replace("file:/", "");
							
					nuevaDireccion = nuevaDireccion.replace("InformesMetareaVI/" , "InformesMetareaVI");
					
					nuevaDireccion = nuevaDireccion.replace("/", "\\");
					
					String direccionAPP = nuevaDireccion; 
				
				
				int idAEliminar = Integer.parseInt(request.getParameter("idWarning"));
				
				WarningABM w = new WarningABM ();
				
				
				Warning warning = w.traerWarning(idAEliminar);
				warning.setActivo(0);
				w.modificar(warning);
				
				
				String textoInicial = warning.getTexto().substring(0,20);
				
				
				String texto = textoInicial +"\r\nSECURITE \r\nCANCEL WARNING  NRO " +warning.getNumeroWarning();
				
				
				
				
				
				CeseABM cABM = new CeseABM();
				cABM.agregar(texto, 1);
				
				
				//Genero el archivo con el cancel
				//escribo el archivo
				 FileWriter fichero = null;
			        PrintWriter pw = null;
			       
			        try
			        {
			        	
			        	String textoDominio = warning.getDominio().replaceAll(" ", "_");
			            fichero = new FileWriter(direccionAPP+"\\Warning\\warningCancel" +textoDominio +"- "+warning.getNumeroWarning() +".txt");
			           
			            
			            
			            pw = new PrintWriter(fichero);

			            
			                pw.printf(texto);
			                
			               

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
			        
			        
			        
			        
			        
			        
			        
			      //Genero el archivo con el cancel
					//escribo el archivo
				
				        FileWriter ficheroViejo = null;
				        PrintWriter pwViejo = null;
				        try
				        {
				        	
				        	String textoDominio = warning.getDominio().replaceAll(" ", "_");
				          
				            ficheroViejo = new FileWriter(direccionAPP+"\\WarningViejos\\warningCancel" +textoDominio +"- "+warning.getNumeroWarning() +".txt");
				            
				            
				           
				                
				                
				                pwViejo = new PrintWriter(ficheroViejo);

					            
				                pwViejo.printf(texto);

				        } catch (Exception e) {
				            e.printStackTrace();
				        } finally {
				           try {
				           // Nuevamente aprovechamos el finally para 
				           // asegurarnos que se cierra el fichero.
				           if (null != ficheroViejo)
				              ficheroViejo.close();
				           } catch (Exception e2) {
				              e2.printStackTrace();
				           }
				        }
				
				
				
				

				//request.setAttribute("w", w);
				request.getRequestDispatcher("/index.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/index.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
	
	
	

}