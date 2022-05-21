package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dao.BoletinDao;
import negocio.BoletinABM;
import negocio.WarningABM;
import modelo.Boletin;
import modelo.Warning;
import java.util.GregorianCalendar;



public class TestGenerarWarningTXTdesdeBD {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
	
		BoletinABM b = new BoletinABM(); 
		
		
	Boletin boletin = b.traerBoletin(new GregorianCalendar(),12); 
		
		if (boletin==null) {
           b.agregar(new GregorianCalendar(), 12);
           boletin = b.traerBoletin(new GregorianCalendar(),12);
		} 
		
		else {
		
	    Boletin boletinConWarning = b.traerBoletinYListaWarning(boletin.getIdBoletin());
	    
	    */
		
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
		            fichero = new FileWriter("Z:\\MetareaVI\\Warning\\warning"+textoDominio +"-" +w.getIdWarning() +".txt");
		            ficheroViejo = new FileWriter("Z:\\MetareaVI\\WarningViejos\\warning"+textoDominio +"-" +w.getIdWarning() +".txt");
		            
		            
		            
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
		
		
		
		
		
		
		


	}//cierra el main
	}//cierra la clase