package controladores;
import java.io.File;
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
import modelo.Cese;
import modelo.Mail;
import modelo.Pronostico;
import modelo.Warning;
import negocio.BoletinABM;
import negocio.CeseABM;
import negocio.WarningABM;





public class ControladorGenerarBoletines extends HttpServlet {
	
	
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
				
				
				GregorianCalendar f = new GregorianCalendar();
				
				int mes = funciones.FuncionesNico.traerMes(f);
				int dia = funciones.FuncionesNico.traerDia(f);
				int anio = funciones.FuncionesNico.traerAnio(f);
				boolean turno = funciones.Funciones.esDiurno();
				
				String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
				
				
				//URL ruta = this.getServletContext().getResource("");
				
				/*
				String nuevaDireccion = ruta.toString().replace("file:/", "");
				
				System.out.println("----------------La ruta es: " +ruta);
				
				System.out.println("----------------La nueva direccion: " +nuevaDireccion);
						
				nuevaDireccion = nuevaDireccion.replace("InformesMetareaVI/" , "InformesMetareaVI");
				
				nuevaDireccion = nuevaDireccion.replace("/", "\\");
				
				String direccionAPP = nuevaDireccion; 
				
				
				System.out.println("----------La direcctionAPP: " +direccionAPP);
				
*/
				//String direccionAPP = ruta.toString().replace("file:", "");
				
				
				String direccionAPP = "/entrada/";
				
				
				

				Funciones.escribirYGuardarNavtex("COSTA MAR DEL PLATA",direccionAPP);
				Funciones.escribirYGuardarNavtex("COSTA BAHIA BLANCA",direccionAPP);
				Funciones.escribirYGuardarNavtex("GOLFO SAN JORGE SUR",direccionAPP);
				Funciones.escribirYGuardarNavtex("COSTA GALLEGOS",direccionAPP);
				Funciones.escribirYGuardarNavtex("COSTA RIO GRANDE",direccionAPP);
				Funciones.escribirYGuardarNavtex("DESEMBOCADURA RIO DE LA PLATA",direccionAPP);
				
				
			
				
		
	        
				
				
				//genero primero los warning activos
				WarningABM warningABM = new WarningABM();
				
				////////////////////////////////////////////////////////////////////////////////////
				/////////////GENERO LOS ARCHIVOS DE WARNING ACTIVOS iNGLES//////////////////////////
				////////////////////////////////////////////////////////////////////////////////////
				
				
				for (Warning w : warningABM.traerWarningActivos()) {
					
					
					
					//Genero el archivo con el warning
					//escribo el archivo
					
				        
				        FileWriter ficheroViejo = null;
				        PrintWriter pwViejo = null;
				        
				        
				        try
				        {
				        	
				        	String textoDominio = w.getDominio().replaceAll(" ", "_");
				           
				            ficheroViejo = new FileWriter("/entrada/boletines/InformesMetareaVI/WarningViejos/warning"+textoDominio +"-" +w.getIdWarning()+paraElArchivo +".txt");
				            
				       
				            
				          
				            pwViejo = new PrintWriter(ficheroViejo);

				            
				            String texto = w.getTexto().replaceAll("XXX", String.valueOf(w.getIdWarning()));
				                
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
				        
				        
				        
				        
				        FileWriter fichero = null;
				        PrintWriter pw = null;
				        
				        try
				        {
				        	
				        	String textoDominio = w.getDominio().replaceAll(" ", "_");
				            fichero = new FileWriter("/entrada/boletines/InformesMetareaVI/Warning/warning"+textoDominio +"-" +w.getIdWarning()+paraElArchivo +".txt");

				            
				       
				            
				            pw = new PrintWriter(fichero);
				    

				            
				            String texto = w.getTexto().replaceAll("XXX", String.valueOf(w.getIdWarning()));
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

				}//cierra el for
				
				
				
				
			
				////////////////////////////////////////////////////////////////////////////////////
				/////////////GENERO LOS ARCHIVOS DE WARNING TODOS JUNTOS EN INGLES//////////////////////////
				////////////////////////////////////////////////////////////////////////////////////
				
			String textoFinal = "";	

			
					for (Warning w : warningABM.traerWarningActivos()) {
						
					
					if (w.getDominio().equals("HIGH SEA")||w.getDominio().equals("OFF SHORE"))	{
						
						
						textoFinal = textoFinal +w.getTexto().replaceAll("XXX", String.valueOf(w.getIdWarning())) +"\r\n"; 
						
						
						
					}
				
						
					}
					
					
					
					
					CeseABM cABM = new CeseABM();
					
					
					for (Cese c:cABM.traerCesesActivos()) {
						
						
						
						textoFinal = textoFinal +c.getTexto()  +"\r\n\r\n"; 
					}
					
					
					
					
					textoFinal = textoFinal.replace("NNNN=", "NNNN=\r\n\r\n");
					
					
					
					//Genero el archivo con el warning
					//escribo el archivo
					
					
					
					
				        
				     FileWriter ficheroViejo = null;
				     PrintWriter pwViejo = null;
				        
				        
				        try
				        {
				        	
				        	
				        	
				        
				            ficheroViejo = new FileWriter("/entrada/boletines/InformesMetareaVI/WarningViejos/warningTodos"+paraElArchivo+".txt");
				            
				            
				            
				          
				            pwViejo = new PrintWriter(ficheroViejo);

				            
				          
				          
				              
				                pwViejo.printf(textoFinal);
				                
				                
				                
				                

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
				        
				        
				        
				        
				        FileWriter fichero2 = null;
					    PrintWriter pw2 = null;
				        try
				        {
				        	
				        	
				        	
				        
				            fichero2 = new FileWriter("/entrada/boletines/InformesMetareaVI/Warning/warningTodos.txt");
				     
				            
				            
				            
				            pw2 = new PrintWriter(fichero2);
				       

				            
				          
				                pw2.printf(textoFinal);
				              
				                
				                
				                
				                

				        } catch (Exception e2) {
				            e2.printStackTrace();
				        } finally {
				           try {
				           // Nuevamente aprovechamos el finally para 
				           // asegurarnos que se cierra el fichero.
				           if (null != fichero2)
				              fichero2.close();
				           } catch (Exception e2) {
				              e2.printStackTrace();
				           }
				        }
				        
				        
				        
				        
				        

					      //escribo el archivo
							 FileWriter fichero3 = null;
						        PrintWriter pw3 = null;
						        try
						        {
						            fichero3 = new FileWriter("/entrada/TODOS.txt");
						            pw3 = new PrintWriter(fichero3);

						            
						                pw3.printf(textoFinal);
						                
						                
						                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

						        } catch (Exception e) {
						            e.printStackTrace();
						        } finally {
						           try {
						           // Nuevamente aprovechamos el finally para 
						           // asegurarnos que se cierra el fichero.
						           if (null != fichero3)
						              fichero3.close();
						           } catch (Exception e3) {
						              e3.printStackTrace();
						           }
						        }
						        
						        
						        
						        
						        
						     
							        
							        
							        
							        
							        //escribo el archivo
									 FileWriter fichero5 = null;
								        PrintWriter pw5 = null;
								        try
								        {
								            fichero5 = new FileWriter("/entrada/TODOSE.txt");
								            pw5 = new PrintWriter(fichero5);

								            
								                pw5.printf(Funciones.traducirBoletin(textoFinal));
								                
								                
								                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

								        } catch (Exception e) {
								            e.printStackTrace();
								        } finally {
								           try {
								           // Nuevamente aprovechamos el finally para 
								           // asegurarnos que se cierra el fichero.
								           if (null != fichero5)
								              fichero5.close();
								           } catch (Exception e5) {
								              e5.printStackTrace();
								           }
								        }
								        
								        
								        
								        
								    
							        
							        
							        
							        
				        
				        
				        


				//cierra el for
				/////TERMINE DE CREAR EL ARCHIVO D ETODOS LOS WARNING JUNTOS
				
				
				
				
					
		        ////////////////////////////////////////////////////////////////////////////////////
				/////////////GENERO LOS TRES BOLETINES//////////////////////////
				////////////////////////////////////////////////////////////////////////////////////        
				        
				
				//Genero los boletines de los 3 tipos
				Funciones.escribirBoletinHS(direccionAPP);
				Funciones.escribirBoletinOS(direccionAPP);
				Funciones.escribirBoletinCoastal(direccionAPP);
				
				
				
				
				////////////////////////////////////////////////////////////////////////////////////
				/////////////GENERO LOS DOS BOLETINES EN ESPA�OL//////////////////////////
				////////////////////////////////////////////////////////////////////////////////////  
				Funciones.escribirBoletinHSEspa(direccionAPP);
				Funciones.escribirBoletinOSEspa(direccionAPP);
				
				
				
			////////////////////////////////////////////////////////////////////////////////////
			/////////////MANDO POR MAIL//////////////////////////
			////////////////////////////////////////////////////////////////////////////////////
				//mando el mail
				
				/*No mando el mail por ahora
				Mail mail = new Mail();
				
				mail.mandarTXTDeDirectorio(direccionAPP);
	
	            System.out.println("Cual elimino: " +direccionAPP);
	            
	            */
				
				
				
				
				Funciones.eliminarLosArchivosEnviador(direccionAPP);
				
				
				
				
				
				
				
			
				
				
				for (Cese c:cABM.traerCesesActivos()) {
					
					c.setActivo(0);
					
					cABM.modificar(c);
				}
				
				
				
				
				
				request.setAttribute("direccionAPP", direccionAPP);
			
				
				

				//request.setAttribute("w", w);
				request.getRequestDispatcher("/indexOK.jsp").forward(request , response);
			
				
				
				
				
				
				
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/indexERROR.jsp").forward(request , response);
		
			}
			}
			
	
	
	

}