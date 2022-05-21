package test;

import funciones.Funciones;
import modelo.Area;
import modelo.Pronostico;

public class ConseguirAreas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		 Pronostico p = funciones.Funciones.cargarXML("C:\\Users\\nperez\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\NicoMetareaVI\\");
		 
		 
		 for (Area a: p.getAreas()) {
			 if(Funciones.areaCorreta(a)) {
				
			 System.out.println("<option value=\""+a.getNombre()+"\">" +a.getNombre()+"</option>") ;}
				 
			 
		 }

	}

}
