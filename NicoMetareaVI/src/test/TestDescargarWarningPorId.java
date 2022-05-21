package test;

import java.io.FileWriter;
import java.io.PrintWriter;

import modelo.Warning;
import negocio.WarningABM;

public class TestDescargarWarningPorId {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		WarningABM wABM = new WarningABM();
		
		int id= 0;
		
		Warning w = wABM.traerWarning(id);
		
		
		//Genero el archivo con el warning
		//escribo el archivo
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	        	
	        	String textoDominio = w.getDominio().replaceAll(" ", "_");
	            fichero = new FileWriter("C:\\Users\\nperez\\Desktop\\warning"+textoDominio+w.getIdWarning() +".txt");
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
		
		

	}

}
