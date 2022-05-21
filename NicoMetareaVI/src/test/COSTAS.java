package test;

import java.io.FileWriter;
import java.io.PrintWriter;

import modelo.Pronostico;
import funciones.Funciones;

public class COSTAS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
        Pronostico p = funciones.Funciones.cargarXML("C:\\Users\\nperez\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\NicoMetareaVI\\");
		
		String retorno = Funciones.generoCabeceraCoastal();
		
		retorno = retorno +Funciones.generarParte1Coastal()+"\r\n";
		
		retorno = retorno +Funciones.generarParte2();
		
		
		
		
		
		retorno = retorno +Funciones.generarPronosticosCoastal(p);
		
		
		
		
		
		//escribo el archivo
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("C:\\Users\\nperez\\Desktop\\highseas.txt");
	            pw = new PrintWriter(fichero);

	            
	                pw.printf(retorno.toUpperCase());
	                
	                System.out.println(retorno.toUpperCase());

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
