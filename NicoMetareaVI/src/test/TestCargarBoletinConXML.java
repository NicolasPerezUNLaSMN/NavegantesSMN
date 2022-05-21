package test;

import modelo.Area;
import modelo.Hora;
import modelo.Parametro;
import modelo.Pronostico;
import modelo.Valor;

public class TestCargarBoletinConXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pronostico p = funciones.Funciones.cargarXML("/home/nykolaiperez/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/NicoMetareaVI");
		
		System.out.println("////////////////////////////");
		System.out.println("Boletin valido para: " +p.toString());
		System.out.println("////////////////////////////");
		
		
		for (Area a: p.getAreas()){
			System.out.println(a.toString());
			for (Parametro parametro: a.getParametros()){
				System.out.println(parametro.toString());
				for (Hora h: parametro.getHoras()){
					//System.out.println(h.toString());
					for (Valor v: h.getValores()){
						//System.out.println(v.toString());
					}
				}
				
			}
		}

	}//cierra main

}//cierra clase
