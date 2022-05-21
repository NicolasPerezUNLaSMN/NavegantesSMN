package funciones;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
//import org.omg.CORBA.portable.InputStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import modelo.Area;
import modelo.Boletin;
import modelo.Cese;
import modelo.Fenomeno;
import modelo.Hielo;
import modelo.Hora;
import modelo.Parametro;
import modelo.Pronostico;
import modelo.Valor;
import modelo.Warning;
import negocio.BoletinABM;
import negocio.BoletinEmitidoABM;
import negocio.CeseABM;
import negocio.FenomenoABM;
import negocio.HieloABM;
import negocio.WarningABM;






public class Funciones {
	
	
	
	//Direcion WEB CONTEXT de mi app
	//static String direccionAPP = "C:\\Users\\nperez\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\NicoMetareaVI";
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Transforma los codigos a la descripcion del fenomeno EN INGLES
	
	
	public static String codigoAFenomeno(String codigo){
		
	
		
		String retorno = "WORSENING"; //DesMejorando
		
		if (codigo.equals("74")){retorno = "SHOWERS";}
			if (codigo.equals("73")) {retorno = "RAIN";} 
		
				if (codigo.equals("83")) {retorno = "HEAVY RAIN";}
					
					if (codigo.equals("85")){retorno = "HEAVY SNOW";}
						if (codigo.equals("71")){retorno = "DRIZZLE";}
							if (codigo.equals("77")){retorno = "RAIN AND SNOW";}
							if (codigo.equals("79")){retorno = "OCNL SNOW";}
							
							if (codigo.equals("81")){retorno = "RAIN AND THUNDERSTORM";}
								if (codigo.equals("84")){retorno = "HEAVY THUNDERSTORM";}
										
										if (codigo.equals("72")){retorno = "OCNL RAIN";}
											if (codigo.equals("76")){retorno = "OCNL STORM";}
											
											if (codigo.equals("51")){retorno = "SPRAY";}
											if (codigo.equals("69")){retorno = "FREEZING FOG";}
											
											if (codigo.equals("67")){retorno = "FOG";}
											if (codigo.equals("61")){retorno = "MIST";}
											
											if (codigo.equals("94")){retorno = "BLIZZARD";}
											if (codigo.equals("96")){retorno = "DRIFTING SNOW";}
											if (codigo.equals("92")){retorno = "BLOWING SNOW";}
		
		
			

		return retorno; 
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//consigo la visibilidad en ingles

	
	public static String transformarNumeroAVisibilidad (String visi){
		
		String retorno = "CODIGOMALO"; 
		
		if ( visi.equals("500")){
			retorno = "VERY POOR";
			
		}
		
		if ( visi.equals("2000")){
			retorno = "POOR";
		}

		if ( visi.equals("5000")){
			retorno = "MODERATE";
	
		}

		if ( visi.equals("10000")){
			retorno = "GOOD";
	
		}
		
		
		return retorno; 
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//trnsforma los nudos a BEAUFORT
	
	
	public static String nudosABeaufort(String stringNudos){
		
		Float.parseFloat(stringNudos);
		float nudos = Float.parseFloat(stringNudos);
		
		int retorno = 1; 
		
		if (nudos> 3){
			retorno = 2;
		}
		
		if (nudos> 6){
			retorno = 3;
		}
		
		if (nudos> 10){
			retorno = 4;
		}
		if (nudos> 16){
			retorno = 5;
		}
		if (nudos> 21){
			retorno = 6;
		}
		if (nudos> 27){
			retorno = 7;
		}
		
		if (nudos> 33){
			retorno = 8;
		}
		if (nudos> 40){
			retorno = 9;
		}
		if (nudos> 47){
			retorno = 10;
		}
		if (nudos> 55){
			retorno = 11;
		}
		if (nudos> 63){
			retorno = 12;
		}
		
		String retornoString = String.valueOf(retorno);
		
		
		
		return retornoString;
		
	}
	

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Lee el archivo  xml, cambiar el directorio cuando se lo instale totalmente 
	//Retorna un pronostico, el mismo tiene las areas
	
	public static Pronostico cargarXML(String direccionAPP){
		
		Pronostico p = new Pronostico();
		
		
		
		
		//Arrancamos la lectura del xml
		
		//Se crea un SAXBuilder para poder parsear el archivo
				SAXBuilder builder = new SAXBuilder();
				
				
				//PARA CASA --->File xmlFile = new File( direccionAPP +"/InformesMetareaVI/archivo.xml" );
				
				//PARA TRABAJO
				
				File xmlFile = new File( direccionAPP +"/archivo.xml" );
				
				
				System.out.println("donde busco el archivo: \n" + xmlFile.getAbsolutePath());
				try
				{
					//Se crea el documento a traves del archivo
					Document document = (Document) builder.build( xmlFile );

					//Se obtiene la raiz 'data :( '
					Element rootNode = document.getRootElement();
					
					
					//Se obtiene la lista de hijos de la raiz 'data',o sea forecast
					List<?> list2 = rootNode.getChildren( "forecast" );
					
					//recorro los forecast que es uno solo
					for ( int fore = 0; fore < list2.size(); fore++){
					
						
						Element forecast = (Element) list2.get(fore);
						

					//Se obtiene la lista de hijos de la raiz 'data'
					List<?> list = forecast.getChildren( "area" );
					List<?> listIssue = forecast.getChildren( "issue" );
					
					for (int issu=0; issu<listIssue.size(); issu++){
						Element issue = (Element) listIssue.get(issu);
						//System.out.println("Datos tomados el : " + issue.getChildTextTrim("timestamp"));
						//Transformo la etiqueta de mi pronostico en el id double
						p.setIdPronostico(Double.parseDouble(issue.getChildTextTrim("timestamp")));
					}

					
					//Recorro las areas
					//Se recorre la lista de hijos de 'forecast'
					for ( int i = 0; i < list.size(); i++ )
					{
						
						Area areaParaLista = new Area();
						
						//Se obtiene el elemento 'area'
						Element area = (Element) list.get(i);

						//Se obtiene el atributo 'id' que esta en el tag 'area'
						String idArea = area.getAttributeValue("id");
						String latitud = area.getAttributeValue("latitude");
						String longitud= area.getAttributeValue("longitude");
						String nombre = area.getAttributeValue("description");
						String dominio = area.getAttributeValue("domain");

						
						
						areaParaLista.setDominio(dominio);
						areaParaLista.setIdArea(Integer.parseInt(idArea));
						areaParaLista.setLatitud(latitud);
						areaParaLista.setLongitud(longitud);
						areaParaLista.setNombre(nombre);
						
						

						
						//Se obtiene la lista de hijos del tag 'tabla'
						//List lista_parametros = area.getChildren("parameter");
						//Se obtiene la lista de hijos de la raiz 'tables'
						List<?> lista_parametros = area.getChildren("parameter");

						//System.out.println( "\tNombre\t\tTipo\t\tValor" );

						
						//recorros los parametros
						//Se recorre la lista de campos
						for ( int j = 0; j < lista_parametros.size(); j++ )
						{
							
							Parametro parametroParaLista = new Parametro();
							
							//Se obtiene el elemento 'campo'
							Element parametro = (Element)lista_parametros.get( j );

							//Se obtienen los valores que estan entre los tags '<campo></campo>'
							//Se obtiene el valor que esta entre los tags '<nombre></nombre>'
							String idParametro = parametro.getAttributeValue("id");
							

							//System.out.println("Id del Parametro: " +idParametro);
							
							//genero el parametro
							parametroParaLista.setNombre(idParametro);
							
							
							
							
							//Arranco con los timerange
							//Se obtiene la lista de hijos del tag 'tabla'
							//List lista_parametros = area.getChildren("parameter");
							//Se obtiene la lista de hijos de la raiz 'tables'
							List<?> lista_horas = parametro.getChildren();

							//System.out.println( "\tNombre\t\tTipo\t\tValor" );

							
							
							//Recorro los horarios
							//Se recorre la lista de campos
							for ( int k = 0; k < lista_horas.size(); k++ )
							{
								//Se obtiene el elemento 'campo'
								Element hora = (Element)lista_horas.get( k );
								
								
								Hora horaParaLista = new Hora();

								//Se obtienen los valores que estan entre los tags '<campo></campo>'
								//Se obtiene el valor que esta entre los tags '<nombre></nombre>'
								String h = hora.getAttributeValue("h");
								String datetime = hora.getAttributeValue("datetime");
								

								
								//System.out.println("Unidad: " +unidad1);
								//System.out.println("Valor: " +valor1);
								
								horaParaLista.setDescripcion(datetime);
								horaParaLista.setIdHora(Integer.parseInt(h));
								
								
								
								
								List<?> lista_valores = hora.getChildren();

								//System.out.println( "\tNombre\t\tTipo\t\tValor" );

								
								
								//Recorro los valores
								//Se recorre la lista de campos
								for ( int l = 0; l < lista_valores.size(); l++ )
								{
									//Se obtiene el elemento 'campo'
									Element valor = (Element)lista_valores.get( l);
									
									
									Valor valorParaLista = new Valor(); 

									//Se obtienen los valores que estan entre los tags '<campo></campo>'
									//Se obtiene el valor que esta entre los tags '<nombre></nombre>'
									String unidadValor = valor.getAttributeValue("unit");
									String numeroValor = valor.getValue();
									
								
									

									valorParaLista.setUnidad(unidadValor);
									valorParaLista.setValor(numeroValor);
									
								
									
									horaParaLista.getValores().add(valorParaLista);
									
									
									
								}//Cierro los valores
								
								parametroParaLista.getHoras().add(horaParaLista);
								
									
								}//Cierro el datetime
								
				
							areaParaLista.getParametros().add(parametroParaLista);
							
					
							
						} //Cierro Parametro
						
						p.getAreas().add(areaParaLista);
						
					}
				//Cierro areas
					
					
					}//Cierro el forecast
					
				}catch ( IOException io ) {
					System.out.println( io.getMessage() );
					
				}catch ( JDOMException jdomex ) {
					System.out.println( jdomex.getMessage() );
					
				}
		
		
		return p;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Devueklve true cuando las areas son las que queremos en el boletin, descarga el sur de los 60
	
	public static boolean areaCorreta (Area a){

		boolean respuesta = true;
		/*
		if (
		
		
		
		
		!a.getNombre().contains("Weddell-S1")&&
		
		!a.getNombre().contains("Weddell-S2")&&
		
		!a.getNombre().contains("Weddell-S3")&&
		
		!a.getNombre().contains("Weddell-S4")&&
		
		
		
		
		
		!a.getNombre().contains("Zona3-Gerlache")&&
		!a.getNombre().contains("Zona5-Erebus")&&
		
		
		
		!a.getNombre().contains("ZonaMalvinas") )  {
		
		respuesta = true;
		
		}
		*/


return respuesta;
}
	
	
	

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe los avisos, tiene que estar generados en la BD por medio de la interface web

	
	public static String generarParte1HighSea() {
		
		String retorno = "\r\nWARNING\r\n";
		retorno= retorno.toUpperCase();
		
		WarningABM wABM = new WarningABM();
		
		
	
		int contador = 0;
		
		for (Warning w:wABM.traerWarningActivos()) {
			
			if(w.getDominio().equals("HIGH SEA")) {
				contador = contador +1;
				String tipo ="";
				
				if(w.getTipo().equals("GALE FORCE - 8 o 9 BEAUFORT - 34 a 47 kt")) {tipo = "GALE";}
				
					if(w.getTipo().equals("STORM FORCE - 10 o 11 BEAUFORT - 48 a 63 kt")) {tipo = "STORM";}
						
						if(w.getTipo().equals("HURRICANE FORCE - +11 BEAUFORT - +63 kt")) {tipo = "HURRICANE";}
				
				retorno = retorno +tipo +" WARNING NRO " +w.getNumeroWarning() +" " +w.getAreas() +".\r\n"	;			
			}
			
			
		}
		
		if(contador==0) {
			retorno = retorno +"NO WARNING.\r\n";
		}
		
		
		
		CeseABM cABM = new CeseABM();
		
		
		for (Cese c:cABM.traerCesesActivos()) {
			
			
			
			retorno = retorno + c.getTexto().replaceAll("\r\nSECURITE \r\n", " ") +".\r\n";
		}
		
		
		
		
		return retorno;
		
	}
	
	
	

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe los avisos, tiene que estar generados en la BD por medio de la interface web

	
	
	
public static String generarParte1OffShore() {
		
		String retorno = "\r\nWARNING\r\n";
		retorno= retorno.toUpperCase();
		
		WarningABM wABM = new WarningABM();
		
		
	
		int contador = 0;
		
		for (Warning w:wABM.traerWarningActivos()) {
			
			if(w.getDominio().equals("OFF SHORE")) {
				contador = contador +1;
				String tipo ="";
				
				if(w.getTipo().equals("GALE FORCE - 8 o 9 BEAUFORT - 34 a 47 kt")) {tipo = "GALE";}
				
					if(w.getTipo().equals("STORM FORCE - 10 o 11 BEAUFORT - 48 a 63 kt")) {tipo = "STORM";}
						
						if(w.getTipo().equals("HURRICANE FORCE - +11 BEAUFORT - +63 kt")) {tipo = "HURRICANE";}
				
				retorno = retorno  +tipo +" WARNING NRO " +w.getNumeroWarning() +" " +w.getAreas() +".\r\n";
				
			}
			
			
		}
		
		if(contador==0) {
			retorno = retorno +"NO WARNING.\r\n";
		}
		
		
	CeseABM cABM = new CeseABM();
		
		
		for (Cese c:cABM.traerCesesActivos()) {
			
			
			
			retorno = retorno + c.getTexto().replaceAll("\r\nSECURITE \r\n", " ") +".\r\n";
		}
		
		
		
		
		return retorno;
		
	}





////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe los avisos, tiene que estar generados en la BD por medio de la interface web


public static String generarParte1Coastal() {
	
	String retorno = "\r\nAVISOS:\r\n";
	retorno= retorno.toUpperCase();
	
	WarningABM wABM = new WarningABM();
	
	

	int contador = 0;
	
	for (Warning w:wABM.traerWarningActivos()) {
		
		if(w.getDominio().equals("COASTAL")) {
			contador = contador +1;
			String tipo ="";
			
			if(w.getTipo().equals("GALE FORCE - 8 o 9 BEAUFORT - 34 a 47 kt")) {tipo = "TEMPORAL";}
			
				if(w.getTipo().equals("STORM FORCE - 10 o 11 BEAUFORT - 48 a 63 kt")) {tipo = "TEMPESTAD";}
					
					if(w.getTipo().equals("HURRICANE FORCE - +11 BEAUFORT - +63 kt")) {tipo = "HURACAN";}
			
			retorno = retorno +w.getAreas()  +" AVISO DE " +tipo +" NRO " +w.getNumeroWarning() +".\r\n";
			
		}
		
		
	}
	
	if(contador==0) {
		retorno = retorno +"SIN AVISOS.\r\n";
	}
	
	
	
	CeseABM cABM = new CeseABM();
	
	
	for (Cese c:cABM.traerCesesActivos()) {
		
		
		
		retorno = retorno + c.getTexto().replaceAll("\r\nSECURITE \r\n", " ") +".\r\n";
	}
	
	
	
	
	
	
	return retorno;
	
}
	
	
	
	

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera los pronosticos de forma ordenada de norte a sur, los pasa al ingles. 
	
	
	
	
	
	public static String generarPronosticos(Pronostico p){
		
         String retorno = "FORECAST\r\n";
         
         
        String enOrden[] = {"NORTH AREA W 020W",
        		"NORTH AREA W 030W",
        		"NORTH AREA W 040W",
        		"CENTRAL EAST AREA N 45S E 030W",
        		"CENTRAL EAST AREA N 45S W 030W",
        		"CENTRAL EAST AREA S 45S E 030W",
        		"CENTRAL EAST AREA S 45S W 030W",
        		"CENTRAL WEST AREA N 45S E 050W",
        		"CENTRAL WEST AREA N 45S W 050W",
        		"CENTRAL WEST AREA S 45S E 050W",
        		"CENTRAL WEST AREA S 45S W 050W",
        		"SOUTH EAST AREA N 55S E 030W",
        		"SOUTH EAST AREA N 55S W 030W",
        		"SOUTH EAST AREA S 55S E 030W",
        		"SOUTH EAST AREA S 55S W 030W",
        		"SOUTH WEST AREA N 55S E 050W",
        		"SOUTH WEST AREA N 55S W 050W",
        		"SOUTH WEST AREA S 55S W 050W",
        		"SOUTH WEST AREA S 55S E 050W",
        		"ZONAMALVINAS",
        		"DRAKE AREA",
        		"NORTHERN WEDDELL AREA N 68S W 020W",
        		"NORTHERN WEDDELL AREA N 68S W 030W",
        		"NORTHERN WEDDELL AREA N 68S W 040W",
        		"SOUTHERN WEDDELL AREA S 68S W 020W",
        		"SOUTHERN WEDDELL AREA S 68S W 040W",
        		"SOUTHERN WEDDELL AREA S 68S W 050W",
        		"SOUTHERN WEDDELL AREA S 68S W 030W",
        		"DRAKE SOUTH",
        		"MARDELAFLOTA",
        		"GERLACHE STRAIT",
        		"EREBUS AND TERROR GULF"}; 
        
        

        
        
        boolean esDiurno = esDiurno(); 
        
        
        
        if(esDiurno) {
        
        for (int indice = 0 ; indice<32; indice++) {
         
        	
        	
		
		for (Area a: p.getAreas()){
			
			
	
			
			
			if(a.getNombre().toString().toUpperCase().equals(enOrden[indice])||a.getNombre().toString().equals(enOrden[indice])) {
				
			
			
		    //Verifico que sea al norte de 60
			if(funciones.Funciones.areaCorreta(a)){
			
			//solo pronostico de metarea
			if(a.getDominio().equals("Metarea VI")){
				
				retorno = retorno +"\r\n"+a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD12 = "X";
				String vientoD18 = "X";
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				
				
				
				String vientoV12 = "X";
				String vientoV18 = "X";
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 ="X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoD12 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoD18 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					vientoD12 = transformarASectores(vientoD12);
					vientoD18 = transformarASectores(vientoD18);
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoV12 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoV18 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==24){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==36){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==24){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==18){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==24){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==18){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==36){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD12.equals("VARIABLE")){vientoD12 = "VRB";}
				if(vientoD18.equals("VARIABLE")){vientoD18 = "VRB";}
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
						retorno = retorno +"\r\n" +escribirTextoVientoDiurno(vientoV12, vientoV18, vientoV24, vientoV30, vientoV36
								,vientoD12, vientoD18, vientoD24, vientoD30, vientoD36);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n" +escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
						
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								
								//No hago nada
								
								
							}else {//else1
									
						
			        	
								
							//Si falta ALGUNA ola, no hago pronostico de olas
							//Si el modelo no cargo bien no se pronostica en esa region
							//o si el mar esta congelado
							if(altura1=="X"||altura2=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X") {
								//no hago nada
								
							}	
							
							else { //else 2
							
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";;
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";;
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";;}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
								
								
								
								
							}	//cierra el else2
								
							}//cierra el else1
								
								//Termine la visi dejo renglon
								
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
							
							
							
						
							
			
				
			}//Cierra el if de metareaa
			
			
			
			
			
			}//Cierra el if que verifica si es al norte de 60
			
			
			 }//cierro el if de igualdad de area para el orden
			
			
			
		}//Cierro el recorrido de las areas
		
		
       
		
        }//Cierro el for que recorre al arreglo de areas
        
        
        }
        
        
        
        
        
        
        /////Algoritmo turno noche!!!!!!!!!!!!!!!!!!!!
        //CHEACKEAAAAAAAAAAAR
        
        if(!esDiurno) {
            
            for (int indice = 0 ; indice<32; indice++) {
             
            	
            	
    		
    		for (Area a: p.getAreas()){
    			
    			
    	
    			
    			
    			if(a.getNombre().toString().toUpperCase().equals(enOrden[indice])||a.getNombre().toString().equals(enOrden[indice])) {
    			
    		    //Verifico que sea al norte de 60
    			if(funciones.Funciones.areaCorreta(a)){
    			
    			//solo pronostico de metarea
    			if(a.getDominio().equals("Metarea VI")){
    				
    				retorno = retorno +"\r\n" +a.getNombre() +": ";
    				
    				///Variables para el viento
    				String vientoD24 = "X";
    				String vientoD30 = "X";
    				String vientoD36 = "X";
    				String vientoD42 = "X";
    				String vientoD48 = "X";
    				
    				
    				
    				String vientoV24 = "X";
    				String vientoV30 = "X";
    				String vientoV36 = "X";
    				String vientoV42 = "X";
    				String vientoV48 = "X";
    				
    				//Variables fenomeno
    				String fenomeno1 = "X";
    				String fenomeno2 = "X";
    				String fenomeno3 = "X";
    				
    				//variables olas
    				String altura1 = "X";
    				String altura2 = "X";
    				String altura3 = "X";
    				String altura4 = "X";
    				String direccion1 = "X";
    				String direccion2 = "X";
    				
    				//variables visibilidad
    				String visibilidad1 = "X";
    				String visibilidad2 = "X";
    				String visibilidad3 = "X"; 
    				
    				/////////////////////////////////////////////////////////////////
    				/////////////////////////////////////////////////////////////////
    				
    				//traigo las direcciones
    				for (Parametro parametro: a.getParametros()){
    					
    					
    					
    					/////////////////////Busco los vientos /////////////////////////
    					//guardo las direcciones
    					if (parametro.getNombre().equals("wd")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								vientoD24 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==30){
    								
    								vientoD30 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==36){
    								
    								vientoD36 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==42){
    								
    								vientoD42 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							
    							if (hora.getIdHora()==48){
    								
    								vientoD48 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							
    							
    							
    							
    							
    						}//cierro el for hora
    					}//cierro el if parametro
    					
    					
    					
    					vientoD24 = transformarASectores(vientoD24);
    					vientoD30 = transformarASectores(vientoD30);
    					vientoD36 = transformarASectores(vientoD36);
    					vientoD42 = transformarASectores(vientoD42);
    					vientoD48 = transformarASectores(vientoD48);
    					
    					
    					/////Guardo las velocidades
    					if (parametro.getNombre().equals("ws")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==30){
    								
    								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==36){
    								
    								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==42){
    								
    								vientoV42 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    							
    							if (hora.getIdHora()==48){
    								
    								vientoV48 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							
    							
    							
    							
    							
    						}//cierro el for hora
    					}//cierro el if parametro
    					
    					
    					
    					
    				/////Guardo los fenomenos
    					if (parametro.getNombre().equals("ww")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    						
    							
    							if (hora.getIdHora()==36){
    								
    								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							
    							
    							
    							if (hora.getIdHora()==48){
    								
    								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							
    							
    							
    							
    							
    						}//cierro el for hora
    					}//cierro el if parametro
    					
    					
    					
    					
    					
    				/////Guardo las visibilidades
    					if (parametro.getNombre().equals("visibility")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
    							}
    							
    							if (hora.getIdHora()==36){
    								
    								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
    							}
    							
    							if (hora.getIdHora()==48){
    								
    								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
    							}
    							
    							
    						} //cierro el for de hora
    						
    						
    					}//cierro if de visibilidad 
    					
    					
    					
    					
    					
    					
    				/////Guardo las alturas de olas
    					if (parametro.getNombre().equals("waveh")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								altura1 =hora.getValores().get(0).getValor();
    							}
    							
    							if (hora.getIdHora()==30){
    							
    								altura2 =hora.getValores().get(0).getValor();
    							}
    							
    							if (hora.getIdHora()==36){
    								
    								altura3 =hora.getValores().get(0).getValor();
    							}
    							
    							if (hora.getIdHora()==48){
    								
    								altura4 =hora.getValores().get(0).getValor();
    							}
    							
    							
    						} //cierro el for de hora
    						
    						
    					}//cierro if de visibilidad 
    					
    					
    					
    				/////Guardo la direccion de olas
    					if (parametro.getNombre().equals("waved")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==30){
    								
    								direccion1 =hora.getValores().get(1).getValor();
    							}
    							
    							
    							if (hora.getIdHora()==48){
    								
    								direccion2 =hora.getValores().get(1).getValor();
    							}
    							
    							
    						} //cierro el for de hora
    						
    						
    					}//cierro if de visibilidad 
    					
    					
    					
    					
    					
    					
    					direccion1 = transformarOlasA8Cuadrantes(direccion1);
    					
    					direccion2 = transformarOlasA8Cuadrantes(direccion2);
    					
    					
    					
    					
    					
    					
    					
    					
    					
    				}//Cierro el for paramertro
    				
    				
    				
    				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
    				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
    				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
    				if(vientoD42.equals("VARIABLE")){vientoD42 = "VRB";}
    				if(vientoD48.equals("VARIABLE")){vientoD48 = "VRB";}
    				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
    				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
    				
    				
    				
    				
    				/////////////////////////////////////////////////
    				///////////////////////////////////////////////
    				
    				//Aca genero la logica del  viento
    				
    		
    				
    			retorno = retorno +"\r\n"+escribirTextoVientoNocturno(vientoV24, vientoV30, vientoV36
    					,  vientoV42, vientoV48
    					,vientoD24, vientoD30, vientoD36, vientoD42,  vientoD48);
    							
    					/////////////////////////////////////////////////
    							///////////////////////////////////////////////
    							
    							retorno = retorno +"\r\n"+escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
    							
    						/////////////////////////////////////////////////
    								///////////////////////////////////////////////
    								
    								//Aca genero la logica de las olas
    								//Si son distintas hago la transicion
    							
    							
    							
    							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
    									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
    											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
    												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
    													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
    														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
    															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
    								
    								//no hago nada
    								
    								
    							}else {
    							
    							
    							
    							if(altura1=="X"||altura1=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X"){
    							
    								//no hago nada si se cargo mal el modelo
    							}	
    								
    							//Si se cargo bien si hago
    							else {
    								
    							if (altura1.equals("0")){ altura1 = "1";}
    							if (altura2.equals("0")){ altura2 = "1";}
    							if (altura3.equals("0")){ altura3 = "1";}
    							if (altura4.equals("0")){ altura4 = "1";}
    							
    								if (direccion1.equals(direccion2)){
    									
    								
    									
    									
    									if(altura1.equals(altura4)){
    										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";
    									}
    									if(!altura1.equals(altura4)){
    										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";
    									}
    									
    								}//cierro olas iguales
    								
    								
    								if (!direccion1.equals(direccion2)){
    									
    									
    									if (!altura1.equals(altura2)){
    										
    										
    										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";}
    									
    									if (altura1.equals(altura2)){
    										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
    									
    									
    									
    								}//cierro los cambios de olas
    							
    								
    								
    							}
    								
    							}//ceirra el else
    								
    								
    								//Termine la visi dejo renglon
    							
    		
    							
    							
    							
    							
    							
    							
    							
    							
    							
    							
    							/////////////////////////////////////////////////
    							///////////////////////////////////////////////
    							
    							//Aca genero la logica de la visibilidad
    							//Si son distintas hago la transicion
    							if(!visibilidad1.equals(visibilidad3)){
    								
    								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
    							}
    							
    							
    							if(visibilidad1.equals(visibilidad3)){
    								
    								retorno = retorno +"VIS " +visibilidad1;
    							}
    							
    							
    							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
    								retorno = retorno +",OCNL " +visibilidad2;
    							}
    							
    							//Termine la visi dejo renglon
    							retorno  = retorno +".\r\n";
    							
    							
    							
    							
    						
    							
    			
    				
    			}//Cierra el if de metareaa
    			
    			
    			
    			
    			
    			}//Cierra el if que verifica si es al norte de 60
    			
    			
    			 }//cierro el if de igualdad de area para el orden
    			
    			
    			
    		}//Cierro el recorrido de las areas
    		
    		
           
    		
            }//Cierro el for que recorre al arreglo de areas
            
            
            }
        
        
        
        
	
		retorno = retorno.replace("OCCASIONAL OCCASIONAL","OCCASIONAL");
		
		retorno = retorno.toUpperCase();
		retorno= retorno.replace("AREA NORTE","NORTH AREA");
		retorno = retorno.replace("AREA CENTRO ESTE","CENTRAL EAST AREA");
		retorno= retorno.replace("AREA CENTRO OESTE","CENTRAL WEST AREA");
		retorno= retorno.replace("AREA SUDESTE","SOUTH EAST AREA");
		retorno= retorno.replace("AREA SUDOESTE","SOUTH WEST AREA");
		retorno= retorno.replace("ZONADRAKE","DRAKE AREA");
		
		
		
		return retorno;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera los pronosticos para la coleccion de areas elegidas

	
	
	public static String generarPronosticosParaWarning(Pronostico p,String areasElegidas[]){
		
        String retorno = "\r\n\r\nFORECAST:\r\n";
        
        
      
        
       
       if(esDiurno()) {
        
     
        for (String areaElegida:areasElegidas) {
        
		for (Area a: p.getAreas()){
			
			

		
			
		    //Verifico que sea un area de warning
			if(a.getNombre().toUpperCase().equals(areaElegida.toUpperCase())){
				
				
retorno = retorno +"\r\n"+a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD12 = "X";
				String vientoD18 = "X";
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				
				
				
				String vientoV12 = "X";
				String vientoV18 = "X";
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 ="X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoD12 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoD18 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					vientoD12 = transformarASectores(vientoD12);
					vientoD18 = transformarASectores(vientoD18);
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoV12 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoV18 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==24){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==36){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==24){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==18){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==24){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==18){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==36){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD12.equals("VARIABLE")){vientoD12 = "VRB";}
				if(vientoD18.equals("VARIABLE")){vientoD18 = "VRB";}
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
						retorno = retorno +"\r\n" +escribirTextoVientoDiurno(vientoV12, vientoV18, vientoV24, vientoV30, vientoV36
								,vientoD12, vientoD18, vientoD24, vientoD30, vientoD36);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n" +escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
						
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								
								//No hago nada
								
								
							}else {//else1
									
						
			        	
								
							//Si falta ALGUNA ola, no hago pronostico de olas
							//Si el modelo no cargo bien no se pronostica en esa region
							//o si el mar esta congelado
							if(altura1=="X"||altura2=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X") {
								//no hago nada
								
							}	
							
							else { //else 2
							
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";;
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";;
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";;}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
								
								
								
								
							}	//cierra el else2
								
							}//cierra el else1
								
								//Termine la visi dejo renglon
								
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
							
				
				
				
				
				
				
				
				
			}//Cierra el if de area == area
			
			
			}//Cierra el recorrido de areas de pronostico
			
			
			
		}//Cierro el recorrido de las areas[]
	
        
        
       }
        
       
       
       
       if(!esDiurno()) {
           
    	     
           for (String areaElegida:areasElegidas) {
           
   		for (Area a: p.getAreas()){
   			
   			

   			
   			//System.out.println(areaElegida +"---VS-----" +a);
   		
   			
   		    //Verifico que sea un area de warning
   			if(a.getNombre().toUpperCase().equals(areaElegida.toUpperCase())){
   				
   				
   				
   				retorno = retorno +"\r\n" +a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				String vientoD42 = "X";
				String vientoD48 = "X";
				
				
				
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				String vientoV42 = "X";
				String vientoV48 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 = "X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==42){
								
								vientoD42 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==48){
								
								vientoD48 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					vientoD42 = transformarASectores(vientoD42);
					vientoD48 = transformarASectores(vientoD48);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==42){
								
								vientoV42 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==48){
								
								vientoV48 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==36){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==48){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==48){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==30){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==48){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==30){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==48){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(vientoD42.equals("VARIABLE")){vientoD42 = "VRB";}
				if(vientoD48.equals("VARIABLE")){vientoD48 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
		
				
			retorno = retorno +"\r\n"+escribirTextoVientoNocturno(vientoV24, vientoV30, vientoV36
					,  vientoV42, vientoV48
					,vientoD24, vientoD30, vientoD36, vientoD42,  vientoD48);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n"+escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
							
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								//no hago nada
								
								
							}else {
							
							
							
							if(altura1=="X"||altura1=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X"){
							
								//no hago nada si se cargo mal el modelo
							}	
								
							//Si se cargo bien si hago
							else {
								
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
							
								
								
							}
								
							}//ceirra el else
								
								
								//Termine la visi dejo renglon
							
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
   				
   				
   				
   				
   				
   			}//Cierra el if de area == area
   			
   			
   			}//Cierra el recorrido de areas de pronostico
   			
   			
   			
   		}//Cierro el recorrido de las areas[]
   	
           
           
          }
           
       
       
       
       
       
        
        
        
		retorno = retorno.replace("OFFSHORE ","");
		return retorno;
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera la cabecera en inglees para los hs

	
	public static String generoCabecera(){
		String retorno = "";
		
		
		GregorianCalendar hoy = new GregorianCalendar();
		
		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		int hora = hoy.get(Calendar.HOUR_OF_DAY)+3;
		int mes = hoy.get(Calendar.MONTH)+1;
		int anio = hoy.get(Calendar.YEAR);
		
			String horaString = "";
		
		if(hora<10){
			horaString = "0" +String.valueOf(hora);
		}else {horaString = String.valueOf(hora);}
		
		String horaUTCSalida = "";
		
		if (hora<19&&hora>3){
			horaUTCSalida = "12";
		}else {horaUTCSalida = "00"; hoy.add(Calendar.DAY_OF_MONTH,1); dia = hoy.get(Calendar.DAY_OF_MONTH);}
		
		
		
		
		
		String diaString = "";
		
		if(dia<10){
			diaString = "0" +String.valueOf(dia);
		}else {diaString = String.valueOf(dia);}
		
		
		String mesString = "";
		
		if(mes<10){
			mesString = "0" +String.valueOf(mes);
		}else {mesString = String.valueOf(mes);}
		
		
		hoy.add(Calendar.DAY_OF_MONTH,1);
		int diaSiguiente = hoy.get(Calendar.DAY_OF_MONTH);
		String diaSiguienteString = String.valueOf(diaSiguiente);
		
		retorno = "FQST02 SABM " +diaString +horaUTCSalida +"00\r\n1:31:06:01:00  \r\nSECURITE \r\nHIGH SEAS BULLETIN FOR METAREA VI ISSUED "+" BY SMN,ARGENTINA,FOR THE PERIOD " +horaUTCSalida +"UTC ON " +diaString +" " +funciones.FuncionesNico.traerMesEnLetrasIngles(hoy) +" UNTIL " +horaUTCSalida +"UTC ON " +diaSiguienteString +" " +funciones.FuncionesNico.traerMesEnLetrasIngles(hoy) +" " +anio+". \r\nPLEASE BE AWARE WIND GUSTS CAN BE A FURTHER 40 PERCENT STRONGER THAN THE AVERAGES GIVEN HERE,AND MAXIMUM WAVES IN METERS  MAY BE UP TO TWICE THE HEIGHT. SEA ICE AND ICEBERGS ISSUED BY SHN,PRESSURE HPA AND BEAUFORT SCALE WINDS.\r\n\r\n";

		
		
		
		return retorno;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera la cabecera en inglees para el offshore
	
	public static String generoCabeceraOFFSHORE(){
		String retorno = "";
		
		
		GregorianCalendar hoy = new GregorianCalendar();
		
		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		int hora = hoy.get(Calendar.HOUR_OF_DAY)+3;
		int mes = hoy.get(Calendar.MONTH)+1;
		int anio = hoy.get(Calendar.YEAR);
		
		
		
		
		String horaString = "";
		
		if(hora<10){
			horaString = "0" +String.valueOf(hora);
		}else {horaString = String.valueOf(hora);}
		
		String horaUTCSalida = "";
		
		if (hora<19&&hora>3){
			horaUTCSalida = "12";
		}else {horaUTCSalida = "00"; hoy.add(Calendar.DAY_OF_MONTH,1); dia = hoy.get(Calendar.DAY_OF_MONTH);}
		
		
		
		
		
		String diaString = "";
		
		if(dia<10){
			diaString = "0" +String.valueOf(dia);
		}else {diaString = String.valueOf(dia);}
		
		
		String mesString = "";
		
		if(mes<10){
			mesString = "0" +String.valueOf(mes);
		}else {mesString = String.valueOf(mes);}
		
		

		hoy.add(Calendar.DAY_OF_MONTH,1);
		int diaSiguiente = hoy.get(Calendar.DAY_OF_MONTH);
		String diaSiguienteString = String.valueOf(diaSiguiente);
		
		retorno = "FQST04 SABM " +diaString +horaUTCSalida +"00\r\n1:31:06:01:00  \r\nSECURITE \r\nOFFSHORE WEATHER FOR METAREA VI ISSUED SMN,ARGENTINA, " +"FOR THE PERIOD " +horaUTCSalida +"UTC ON " +diaString +" " +funciones.FuncionesNico.traerMesEnLetrasIngles(hoy) +" UNTIL " +horaUTCSalida +"UTC ON " +diaSiguienteString +" " +funciones.FuncionesNico.traerMesEnLetrasIngles(hoy) +" "+anio+". \r\nPLEASE BE AWARE WIND GUSTS CAN BE A FURTHER 40 PERCENT STRONGER THAN THE AVERAGES GIVEN HERE,AND MAXIMUM WAVES IN METERS  MAY BE UP TO TWICE THE HEIGHT. SEA ICE AND ICEBERGS ISSUED BY SHN,PRESSURE HPA AND BEAUFORT SCALE WINDS.\r\n\r\n";

		
		
		
		return retorno;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera la cabecera en inglees para la costa
	
	
	public static String generoCabeceraCoastal(){
		String retorno = "";
		
		
		GregorianCalendar hoy = new GregorianCalendar();
		
		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		int hora = hoy.get(Calendar.HOUR_OF_DAY)+3;
		int mes = hoy.get(Calendar.MONTH)+1;
		int anio = hoy.get(Calendar.YEAR);
		
			String horaString = "";
		
		if(hora<10){
			horaString = "0" +String.valueOf(hora);
		}else {horaString = String.valueOf(hora);}
		
		String horaUTCSalida = "";
		
		if (hora<19&&hora>3){
			horaUTCSalida = "12";
		}else {horaUTCSalida = "00"; hoy.add(Calendar.DAY_OF_MONTH,1); dia = hoy.get(Calendar.DAY_OF_MONTH);}
		
		
		
		String diaString = "";
		
		if(dia<10){
			diaString = "0" +String.valueOf(dia);
		}else {diaString = String.valueOf(dia);}
		
		
		String mesString = "";
		
		if(mes<10){
			mesString = "0" +String.valueOf(mes);
		}else {mesString = String.valueOf(mes);}
		
		
		
		hoy.add(Calendar.DAY_OF_MONTH,1);
		int diaSiguiente = hoy.get(Calendar.DAY_OF_MONTH);
		String diaSiguienteString = String.valueOf(diaSiguiente);

		
		retorno = "XXXXX XXXX " +diaString +horaUTCSalida +"00\r\n1:31:06:01:00  \r\nSEGURIDAD \r\nBOLETIN PARA AGUAS COSTERAS HASTA LAS 50 MILLAS NAUTICAS HACIA EL MAR. EMITIDO POR EL SERVICIO METEOROLOGICO NACIONAL A LAS "+horaUTCSalida +"00 UTC DEL  " +diaString +"/" +mesString +"/" +String.valueOf(anio)    +". \r\nVIENTO EN NUDOS,ALTURA DE OLAS EN METROS,LAS VELOCIDADES DEL VIENTO SON LAS VELOCIDADES PROMEDIO ESPERADAS. LAS RAFAGAS INDIVIDUALES PUEDEN EXCEDER ESTOS VALORES. LA ALTURAS DE OLAS SIGNIFICATIVAS PUEDEN ESTAR ACOMPA�ADAS POR ONDAS INDIVIDUALES 1.5 A 2.0 VECES MAS ALTAS.\r\n\r\nPRONOSTICO VALIDO DESDE " +diaString +horaUTCSalida +"00 HASTA " +diaSiguienteString +horaUTCSalida +"00 UTC:\r\n\r\n";

		
		
		
		return retorno;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera la cabecera en inglees para el navtex
	
	public static String generoCabeceraNavTex(){
		String retorno = "";
		
		
		GregorianCalendar hoy = new GregorianCalendar();
		
		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		int hora = hoy.get(Calendar.HOUR_OF_DAY)+3;
		int mes = hoy.get(Calendar.MONTH)+1;
		int anio = hoy.get(Calendar.YEAR);
		
		
		String diaString = "";
		
		if(dia<10){
			diaString = "0" +String.valueOf(dia);
		}else {diaString = String.valueOf(dia);}
		
		
		String mesString = "";
		
		if(mes<10){
			mesString = "0" +String.valueOf(mes);
		}else {mesString = String.valueOf(mes);}
		
		
		String horaString = "";
		
		if(hora<10){
			horaString = "0" +String.valueOf(hora);
		}else {horaString = String.valueOf(hora);}
		
		String horaUTCSalida = "";
		
		if (hora<15&&hora>3){
			horaUTCSalida = "12";
		}else {horaUTCSalida = "00";}
		
		retorno = "WWST03 SABM "+diaString+horaUTCSalida  +"00\r\nWEATHER BULLETIN FOR NAVTEX STATIONS - METAREA VI -\r\n"  +FuncionesNico.traerMesEnLetrasIngles(hoy) +" "+diaString +" " +horaUTCSalida +":00UTC"  +"\r\nNATIONAL WEATHER SERVICE\r\nPRESSURE HPA\r\nBEAUFORT SCALE WINDS.";

		
		
		
		return retorno;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera los pronosticos de forma ordenada de norte a sur, los pasa al ingles. 

	
	
	public static String generarPronosticosOFFSHORE(Pronostico p){
		
        String retorno = "FORECAST\r\n";
        
        
        
        
        String enOrden[] = {"DESEMBOCADURA RIO DE LA PLATA",
        		"OFFSHORE RIO DE LA PLATA",
        		"OFFSHORE MAR DEL PLATA",
        		"OFFSHORE BAHIA BLANCA",
        		"OFFSHORE VALDES",
        		"OFFSHORE SAN JORGE",
        		"OFFSHORE PATAGONIA SUR",
        		"OFFSHORE FIN DEL MUNDO"}; 
        
        
        boolean esDiurno = esDiurno();
        
        
        if(esDiurno) {
        
        for (int indice = 0 ; indice<8; indice++) {
        
		
		for (Area a: p.getAreas()){
			
			
			if(a.getNombre().toString().toUpperCase().equals(enOrden[indice])) {
				
			
		    //Verifico que sea al norte de 60
			if(a.getNombre().contains("OFFSHORE")||a.getNombre().contains("DESEMBOCADURA RIO DE LA PLATA")){
			
			//solo pronostico de metarea
			if(a.getDominio().equals("Costas")){
				
				
				
retorno = retorno +"\r\n"+a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD12 = "X";
				String vientoD18 = "X";
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				
				
				
				String vientoV12 = "X";
				String vientoV18 = "X";
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 ="X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoD12 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoD18 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					vientoD12 = transformarASectores(vientoD12);
					vientoD18 = transformarASectores(vientoD18);
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoV12 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoV18 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==24){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==36){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==24){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==18){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==24){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==18){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==36){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD12.equals("VARIABLE")){vientoD12 = "VRB";}
				if(vientoD18.equals("VARIABLE")){vientoD18 = "VRB";}
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
						retorno = retorno +"\r\n" +escribirTextoVientoDiurno(vientoV12, vientoV18, vientoV24, vientoV30, vientoV36
								,vientoD12, vientoD18, vientoD24, vientoD30, vientoD36);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n" +escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
						
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								
								//No hago nada
								
								
							}else {//else1
									
						
			        	
								
							//Si falta ALGUNA ola, no hago pronostico de olas
							//Si el modelo no cargo bien no se pronostica en esa region
							//o si el mar esta congelado
							if(altura1=="X"||altura2=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X") {
								//no hago nada
								
							}	
							
							else { //else 2
							
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";;
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";;
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";;}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
								
								
								
								
							}	//cierra el else2
								
							}//cierra el else1
								
								//Termine la visi dejo renglon
								
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
							
							
				
				
				
				
				
				
				
				
				
				
			}//Cierra el if de metareaa
			
			
			}//Cierra el if que verifica si es al norte de 60
			
			
			}//Cierra el if del orden
			
			
		}//Cierro el recorrido de las areas
		
		
        }//Cierro el indice del orden
        
        
        }
        
        
        
        
        
        if(!esDiurno) {
        	
        	
        	
        	for (int indice = 0 ; indice<8; indice++) {
                
        		
        		for (Area a: p.getAreas()){
        			
        			
        			if(a.getNombre().toString().toUpperCase().equals(enOrden[indice])) {
        				
        			
        		    //Verifico que sea al norte de 60
        			if(a.getNombre().contains("OFFSHORE")||a.getNombre().contains("DESEMBOCADURA RIO DE LA PLATA")){
        			
        			//solo pronostico de metarea
        			if(a.getDominio().equals("Costas")){
        				
        				
        				
        				retorno = retorno +"\r\n" +a.getNombre() +": ";
        				
        				///Variables para el viento
        				String vientoD24 = "X";
        				String vientoD30 = "X";
        				String vientoD36 = "X";
        				String vientoD42 = "X";
        				String vientoD48 = "X";
        				
        				
        				
        				String vientoV24 = "X";
        				String vientoV30 = "X";
        				String vientoV36 = "X";
        				String vientoV42 = "X";
        				String vientoV48 = "X";
        				
        				//Variables fenomeno
        				String fenomeno1 = "X";
        				String fenomeno2 = "X";
        				String fenomeno3 = "X";
        				
        				//variables olas
        				String altura1 = "X";
        				String altura2 = "X";
        				String altura3 = "X";
        				String altura4 = "X";
        				String direccion1 = "X";
        				String direccion2 = "X";
        				
        				//variables visibilidad
        				String visibilidad1 = "X";
        				String visibilidad2 = "X";
        				String visibilidad3 = "X"; 
        				
        				/////////////////////////////////////////////////////////////////
        				/////////////////////////////////////////////////////////////////
        				
        				//traigo las direcciones
        				for (Parametro parametro: a.getParametros()){
        					
        					
        					
        					/////////////////////Busco los vientos /////////////////////////
        					//guardo las direcciones
        					if (parametro.getNombre().equals("wd")){
        						
        						for (Hora hora: parametro.getHoras()){
        							
        							if (hora.getIdHora()==24){
        								
        								vientoD24 = hora.getValores().get(1).getValor();
        							}//cierro el if hora
        							
        							if (hora.getIdHora()==30){
        								
        								vientoD30 = hora.getValores().get(1).getValor();
        							}//cierro el if hora
        							
        							if (hora.getIdHora()==36){
        								
        								vientoD36 = hora.getValores().get(1).getValor();
        							}//cierro el if hora
        							
        							if (hora.getIdHora()==42){
        								
        								vientoD42 = hora.getValores().get(1).getValor();
        							}//cierro el if hora
        							
        							
        							if (hora.getIdHora()==48){
        								
        								vientoD48 = hora.getValores().get(1).getValor();
        							}//cierro el if hora
        							
        							
        							
        							
        							
        							
        						}//cierro el for hora
        					}//cierro el if parametro
        					
        					
        					
        					vientoD24 = transformarASectores(vientoD24);
        					vientoD30 = transformarASectores(vientoD30);
        					vientoD36 = transformarASectores(vientoD36);
        					vientoD42 = transformarASectores(vientoD42);
        					vientoD48 = transformarASectores(vientoD48);
        					
        					
        					/////Guardo las velocidades
        					if (parametro.getNombre().equals("ws")){
        						
        						for (Hora hora: parametro.getHoras()){
        							
        							if (hora.getIdHora()==24){
        								
        								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
        								
        								
        							}//cierro el if hora
        							
        							if (hora.getIdHora()==30){
        								
        								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
        								
        								
        							}//cierro el if hora
        							
        							if (hora.getIdHora()==36){
        								
        								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
        								
        							}//cierro el if hora
        							
        							if (hora.getIdHora()==42){
        								
        								vientoV42 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
        								
        								
        							}//cierro el if hora
        							
        							
        							if (hora.getIdHora()==48){
        								
        								vientoV48 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
        								
        							}//cierro el if hora
        							
        							
        							
        							
        							
        							
        						}//cierro el for hora
        					}//cierro el if parametro
        					
        					
        					
        					
        				/////Guardo los fenomenos
        					if (parametro.getNombre().equals("ww")){
        						
        						for (Hora hora: parametro.getHoras()){
        							
        							if (hora.getIdHora()==24){
        								
        								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
        								
        								
        							}//cierro el if hora
        							
        						
        							
        							if (hora.getIdHora()==36){
        								
        								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
        								
        							}//cierro el if hora
        							
        							
        							
        							
        							if (hora.getIdHora()==48){
        								
        								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
        								
        							}//cierro el if hora
        							
        							
        							
        							
        							
        							
        						}//cierro el for hora
        					}//cierro el if parametro
        					
        					
        					
        					
        					
        				/////Guardo las visibilidades
        					if (parametro.getNombre().equals("visibility")){
        						
        						for (Hora hora: parametro.getHoras()){
        							
        							if (hora.getIdHora()==24){
        								
        								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
        							}
        							
        							if (hora.getIdHora()==36){
        								
        								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
        							}
        							
        							if (hora.getIdHora()==48){
        								
        								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
        							}
        							
        							
        						} //cierro el for de hora
        						
        						
        					}//cierro if de visibilidad 
        					
        					
        					
        					
        					
        					
        				/////Guardo las alturas de olas
        					if (parametro.getNombre().equals("waveh")){
        						
        						for (Hora hora: parametro.getHoras()){
        							
        							if (hora.getIdHora()==24){
        								
        								altura1 =hora.getValores().get(0).getValor();
        							}
        							
        							if (hora.getIdHora()==30){
        							
        								altura2 =hora.getValores().get(0).getValor();
        							}
        							
        							if (hora.getIdHora()==36){
        								
        								altura3 =hora.getValores().get(0).getValor();
        							}
        							
        							if (hora.getIdHora()==48){
        								
        								altura4 =hora.getValores().get(0).getValor();
        							}
        							
        							
        						} //cierro el for de hora
        						
        						
        					}//cierro if de visibilidad 
        					
        					
        					
        				/////Guardo la direccion de olas
        					if (parametro.getNombre().equals("waved")){
        						
        						for (Hora hora: parametro.getHoras()){
        							
        							if (hora.getIdHora()==30){
        								
        								direccion1 =hora.getValores().get(1).getValor();
        							}
        							
        							
        							if (hora.getIdHora()==48){
        								
        								direccion2 =hora.getValores().get(1).getValor();
        							}
        							
        							
        						} //cierro el for de hora
        						
        						
        					}//cierro if de visibilidad 
        					
        					
        					
        					
        					
        					
        					direccion1 = transformarOlasA8Cuadrantes(direccion1);
        					
        					direccion2 = transformarOlasA8Cuadrantes(direccion2);
        					
        					
        					
        					
        					
        					
        					
        					
        					
        				}//Cierro el for paramertro
        				
        				
        				
        				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
        				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
        				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
        				if(vientoD42.equals("VARIABLE")){vientoD42 = "VRB";}
        				if(vientoD48.equals("VARIABLE")){vientoD48 = "VRB";}
        				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
        				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
        				
        				
        				
        				
        				/////////////////////////////////////////////////
        				///////////////////////////////////////////////
        				
        				//Aca genero la logica del  viento
        				
        		
        				
        			retorno = retorno +"\r\n"+escribirTextoVientoNocturno(vientoV24, vientoV30, vientoV36
        					,  vientoV42, vientoV48
        					,vientoD24, vientoD30, vientoD36, vientoD42,  vientoD48);
        							
        					/////////////////////////////////////////////////
        							///////////////////////////////////////////////
        							
        							retorno = retorno +"\r\n"+escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
        							
        						/////////////////////////////////////////////////
        								///////////////////////////////////////////////
        								
        								//Aca genero la logica de las olas
        								//Si son distintas hago la transicion
        							
        							
        							
        							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
        									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
        											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
        												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
        													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
        														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
        															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
        								
        								//no hago nada
        								
        								
        							}else {
        							
        							
        							
        							if(altura1=="X"||altura1=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X"){
        							
        								//no hago nada si se cargo mal el modelo
        							}	
        								
        							//Si se cargo bien si hago
        							else {
        								
        							if (altura1.equals("0")){ altura1 = "1";}
        							if (altura2.equals("0")){ altura2 = "1";}
        							if (altura3.equals("0")){ altura3 = "1";}
        							if (altura4.equals("0")){ altura4 = "1";}
        							
        								if (direccion1.equals(direccion2)){
        									
        								
        									
        									
        									if(altura1.equals(altura4)){
        										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";
        									}
        									if(!altura1.equals(altura4)){
        										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";
        									}
        									
        								}//cierro olas iguales
        								
        								
        								if (!direccion1.equals(direccion2)){
        									
        									
        									if (!altura1.equals(altura2)){
        										
        										
        										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";}
        									
        									if (altura1.equals(altura2)){
        										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
        									
        									
        									
        								}//cierro los cambios de olas
        							
        								
        								
        							}
        								
        							}//ceirra el else
        								
        								
        								//Termine la visi dejo renglon
        							
        		
        							
        							
        							
        							
        							
        							
        							
        							
        							
        							
        							/////////////////////////////////////////////////
        							///////////////////////////////////////////////
        							
        							//Aca genero la logica de la visibilidad
        							//Si son distintas hago la transicion
        							if(!visibilidad1.equals(visibilidad3)){
        								
        								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
        							}
        							
        							
        							if(visibilidad1.equals(visibilidad3)){
        								
        								retorno = retorno +"VIS " +visibilidad1;
        							}
        							
        							
        							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
        								retorno = retorno +",OCNL " +visibilidad2;
        							}
        							
        							//Termine la visi dejo renglon
        							retorno  = retorno +".\r\n";
        							
        							
        							
        				
        				
        				
        				
        				
        			}//Cierra el if de metareaa
        			
        			
        			}//Cierra el if que verifica si es al norte de 60
        			
        			
        			}//Cierra el if del orden
        			
        			
        		}//Cierro el recorrido de las areas
        		
        		
                }//Cierro el indice del orden
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
        
        
	
		
		retorno = retorno.replace("OCCASIONAL OCCASIONAL","OCCASIONAL");
		
		retorno = retorno.replace("OFFSHORE ","");
		
		
		return retorno;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera los pronosticos de forma ordenada de norte a sur, los pasa al ingles. 

	
	
	
	public static String generarPronosticosCoastal(Pronostico p){
		
        String retorno = "PRONOSTICO:\r\n";
        
        
        String enOrden[] = { "RIO DE LA PLATA EXTERIOR",
        		"RIO DE LA PLATA INTERIOR",
        		"RIO DE LA PLATA INTERMEDIO",
        		"COSTA BAHIA BLANCA",
        		 "COSTA CARMEN PATAGONES",
        		"COSTA DEL TUYU",
        		"COSTA DESEADO SUR",
        		 "COSTA GALLEGOS",
        		 "COSTA GESELL",
        		 "COSTA ISLA DE LOS ESTADOS",
        		"COSTA MAR DEL PLATA",
        		"COSTA NECOCHEA",
        		 "COSTA RAWSON",
        		"COSTA RIO GRANDE",
        		 "COSTA SAN JULIAN",
        		"COSTA SANTA CRUZ",
        		"COSTA VALDES",
        		 "COSTA VIEDMA",
        		 "GOLFO NUEVO",
        		"GOLFO SAN JORGE NORTE",
        		 "GOLFO SAN JORGE SUR",
        		"GOLFO SAN JOSE",
        		 "GOLFO SAN MATIAS"}; 
        
        
        
        
        boolean esDiurno = esDiurno(); 
        
        
        
        if(esDiurno) {
        
      
         
 
        
        for (int indice = 0 ; indice<23; indice++) {
        
        
		
		for (Area a: p.getAreas()){
			
			
			
			
			if(a.getNombre().toString().toUpperCase().equals(enOrden[indice])) {
			
		    //Verifico que sea al norte de 60
			if(!a.getNombre().contains("OFFSHORE")){
			
			//solo pronostico de metarea
			if(a.getDominio().equals("Costas")){
				
				
				
				

				
				retorno = retorno +"\r\n"+a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD12 = "X";
				String vientoD18 = "X";
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				
				
				
				String vientoV12 = "X";
				String vientoV18 = "X";
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 ="X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoD12 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoD18 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					vientoD12 = transformarASectores(vientoD12);
					vientoD18 = transformarASectores(vientoD18);
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoV12 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoV18 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==24){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==36){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==24){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==18){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==24){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==18){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==36){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD12.equals("VARIABLE")){vientoD12 = "VRB";}
				if(vientoD18.equals("VARIABLE")){vientoD18 = "VRB";}
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
						retorno = retorno +"\r\n" +escribirTextoVientoDiurno(vientoV12, vientoV18, vientoV24, vientoV30, vientoV36
								,vientoD12, vientoD18, vientoD24, vientoD30, vientoD36);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n" +escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
						
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								
								//No hago nada
								
								
							}else {//else1
									
						
			        	
								
							//Si falta ALGUNA ola, no hago pronostico de olas
							//Si el modelo no cargo bien no se pronostica en esa region
							//o si el mar esta congelado
							if(altura1=="X"||altura2=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X") {
								//no hago nada
								
							}	
							
							else { //else 2
							
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";;
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";;
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";;}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
								
								
								
								
							}	//cierra el else2
								
							}//cierra el else1
								
								//Termine la visi dejo renglon
								
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
							
							
							
						
							
			
				
			
			}//Cierra el if de metareaa
			
			
			}//Cierra el if que verifica si es al norte de 60
			
			
			}//cierro el ordenmiento
			
		}//Cierro el recorrido de las areas
			
		}//Cierro el recorrido del indice
        
        
        }
        
        
        
        
        
        if(!esDiurno) {
        
        for (int indice = 0 ; indice<23; indice++) {
        
        
		
		for (Area a: p.getAreas()){
			
			
			
			
			if(a.getNombre().toString().toUpperCase().equals(enOrden[indice])) {
			
		    //Verifico que sea al norte de 48
			if(!a.getNombre().contains("OFFSHORE")){
			
			//solo pronostico de metarea
			if(a.getDominio().equals("Costas")){
				
				retorno = retorno +"\r\n" +a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				String vientoD42 = "X";
				String vientoD48 = "X";
				
				
				
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				String vientoV42 = "X";
				String vientoV48 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 = "X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==42){
								
								vientoD42 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==48){
								
								vientoD48 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					vientoD42 = transformarASectores(vientoD42);
					vientoD48 = transformarASectores(vientoD48);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==42){
								
								vientoV42 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==48){
								
								vientoV48 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==36){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==48){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==48){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==24){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==30){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==48){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==30){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==48){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(vientoD42.equals("VARIABLE")){vientoD42 = "VRB";}
				if(vientoD48.equals("VARIABLE")){vientoD48 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
		
				
			retorno = retorno +"\r\n"+escribirTextoVientoNocturno(vientoV24, vientoV30, vientoV36
					,  vientoV42, vientoV48
					,vientoD24, vientoD30, vientoD36, vientoD42,  vientoD48);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n"+escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
							
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								//no hago nada
								
								
							}else {
							
							
							
							if(altura1=="X"||altura1=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X"){
							
								//no hago nada si se cargo mal el modelo
							}	
								
							//Si se cargo bien si hago
							else {
								
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
							
								
								
							}
								
							}//ceirra el else
								
								
								//Termine la visi dejo renglon
							
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
							
							
							
						
							
			
				
			}//Cierra el if de metareaa
			
			
			}//Cierra el if que verifica si es al norte de 48
			
			
			}//cierro el ordenmiento
			
		}//Cierro el recorrido de las areas
			
		}//Cierro el recorrido del indice
        
        
        }
        
        
        
	
		
		retorno = retorno.replace("OCCASIONAL OCCASIONAL","OCCASIONAL");
		
		//retorno = Funciones.traducirBoletin(retorno);
		
		return retorno;
	}
	
	
	
	

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe la situacion sinoptica, tiene que estar generados en la BD por medio de la interface web

	
	public static String generarParte2() {
		String retorno = "\r\nGENERAL SYNOPSIS\r\n";
		
		
		FenomenoABM fABM = new FenomenoABM();
		
		for(Fenomeno f: fABM.traerFenomeno()) {
			
			//System.out.println(f.getTexto());
			
			
			retorno = retorno +f.getTexto() +"\r\n";
		}
		
		
		
	
		
		return retorno;
	}
	
	
	

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe los hielos, tiene que estar generados en la BD por medio de la interface web

	
	public static String generarParte2Hielos() {
		String retorno = "\r\n";
		
		
		
		HieloABM hABM = new HieloABM();
		
		for(Hielo h: hABM.traerHielo()) {
			
			retorno = retorno +h.getIngles() +"\r\n\r\n";
		}
		
		
		
		return retorno;
	}
	
	
	
	
	

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe la situacion para el navtex, tiene que estar generados en la BD por medio de la interface web

	
	public static String generarParte2Navtex() {
		String retorno = "GENERAL SYNOPSIS\r\n";
		
		
		FenomenoABM fABM = new FenomenoABM();
		
		for(Fenomeno f: fABM.traerFenomeno()) {
			
			if (f.getIncluir()==1) {
			//System.out.println(f.getTexto());
			
			
			retorno = retorno +f.getTexto() +"\r\n";
			}
		}
		
		
		
		return retorno;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Escribe el boletin de HS y genera el respectivo archivo
	
	public static void escribirBoletinHS(String direccionAPP) {
		
		  Pronostico p = funciones.Funciones.cargarXML(direccionAPP);
			
			String retorno = Funciones.generoCabecera();
			
			retorno = retorno +Funciones.generarParte1HighSea();
			
			retorno = retorno +Funciones.generarParte2();
			
			retorno = retorno +Funciones.generarParte2Hielos();
			
			
			
			retorno = retorno +Funciones.generarPronosticos(p);
			
			retorno = retorno.replace(" .", ".");
			
			
			retorno = retorno +"\r\n\r\n\r\nNNNN=";
			
			
			
			
			//escribo el archivo
			 FileWriter fichero = null;
			 
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/Boletines/highseas.txt");
		           
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"HIGH SEA");

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
		        
		        
		        
		        
			        
				      //escribo el archivo
						 FileWriter fichero3 = null;
					        PrintWriter pw3 = null;
					        try
					        {
					            fichero3 = new FileWriter(direccionAPP +"/highseas.txt");
					            pw3 = new PrintWriter(fichero3);

					            
					                pw3.printf(retorno.toUpperCase());
					                
					                
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
					        
					        
					        
					        /*
					        
						      //escribo el archivo
								 FileWriter fichero4 = null;
							        PrintWriter pw4 = null;
							        try
							        {
							            fichero4 = new FileWriter("C:\\Users\\nperez\\Desktop\\workspace\\NicoMetareaVI\\WebContent\\highseas.txt");
							            pw4 = new PrintWriter(fichero4);

							            
							                pw4.printf(retorno.toUpperCase());
							                
							            
							                
							                
							                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

							        } catch (Exception e) {
							            e.printStackTrace();
							        } finally {
							           try {
							           // Nuevamente aprovechamos el finally para 
							           // asegurarnos que se cierra el fichero.
							           if (null != fichero4)
							              fichero4.close();
							           } catch (Exception e4) {
							              e4.printStackTrace();
							           }
							        }
							               
					        
					        
					        
					        */
					        
					        
					        GregorianCalendar f = new GregorianCalendar();
							
							int mes = funciones.FuncionesNico.traerMes(f);
							int dia = funciones.FuncionesNico.traerDia(f);
							int anio = funciones.FuncionesNico.traerAnio(f);
							boolean turno = funciones.Funciones.esDiurno();
							
							String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
					        
					     
					        
						      //escribo el archivo
								 FileWriter fichero2 = null;
							        PrintWriter pw2 = null;
							        try
							        {
							            fichero2 = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/BoletinesViejos/highseas"+paraElArchivo +".txt");
							            pw2 = new PrintWriter(fichero2);

							            
							                pw2.printf(retorno.toUpperCase());
							                
							                
							                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

							        } catch (Exception e) {
							            e.printStackTrace();
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
							        
					        
		        
		        
		        
		        
		
		
	}
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Traduce, escribe el boletin HS
	
	
	
	public static void escribirBoletinHSEspa(String direccionAPP) {
		
		  Pronostico p = funciones.Funciones.cargarXML(direccionAPP);
			
			String retorno = Funciones.generoCabecera();
			
			retorno = retorno +Funciones.generarParte1HighSea();
			
			retorno = retorno +Funciones.generarParte2();
			
			retorno = retorno +Funciones.generarParte2Hielos();
			
			
			
			retorno = retorno +Funciones.generarPronosticos(p);
			
			
			retorno = Funciones.traducirBoletin(retorno);
			
			
			retorno = retorno +"\r\n\r\n\r\nNNNN=";
			
	        GregorianCalendar f = new GregorianCalendar();
			
								int mes = funciones.FuncionesNico.traerMes(f);
								int dia = funciones.FuncionesNico.traerDia(f);
								int anio = funciones.FuncionesNico.traerAnio(f);
								boolean turno = funciones.Funciones.esDiurno();
								
								String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
			
			
			//escribo el archivo
			 FileWriter fichero = null;
			 
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/Boletines/highseas-Espa.txt");
		           
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"HIGH SEA");

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
		        
		        
		        

		        
		        
		      //escribo el archivo
				 FileWriter fichero2 = null;
				 
			        PrintWriter pw2 = null;
			        try
			        {
			            fichero2 = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/BoletinesViejos/highseas-Espa"+paraElArchivo+".txt");
			           
			            pw2 = new PrintWriter(fichero2);

			            
			                pw2.printf(retorno.toUpperCase());
			                
			                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"HIGH SEA");

			        } catch (Exception e) {
			            e.printStackTrace();
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
				            fichero3 = new FileWriter(direccionAPP +"highseas-Espa.txt");
				           
				            pw3 = new PrintWriter(fichero3);

				            
				                pw3.printf(retorno.toUpperCase());
				                
				                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"HIGH SEA");

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
						 FileWriter fichero4 = null;
						 
					        PrintWriter pw4 = null;
					        try
					        {
					            fichero4 = new FileWriter("highseas-Espa.txt");
					           
					            pw4 = new PrintWriter(fichero4);

					            
					                pw4.printf(retorno.toUpperCase());
					                
					                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"HIGH SEA");

					        } catch (Exception e) {
					            e.printStackTrace();
					        } finally {
					           try {
					           // Nuevamente aprovechamos el finally para 
					           // asegurarnos que se cierra el fichero.
					           if (null != fichero4)
					              fichero4.close();
					           } catch (Exception e4) {
					              e4.printStackTrace();
					           }
					        }
			          
		        
		
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Escribe el boletin OOFF y genera el txt, ademas de imparctar en la BD
	
	
	public static void escribirBoletinOS(String direccionAPP) {
		
		  Pronostico p = funciones.Funciones.cargarXML(direccionAPP);
			
			String retorno = Funciones.generoCabeceraOFFSHORE();
			
			retorno = retorno +Funciones.generarParte1OffShore();
			
			retorno = retorno +Funciones.generarParte2();
			
			retorno = retorno +"THE LOCATION OF ICE IS IN THE BULLETIN HIGH SEA.\n\r\n\r" ;
			
			
			
			
			retorno = retorno +Funciones.generarPronosticosOFFSHORE(p);
			
			
			retorno = retorno +"\r\n\r\n\r\nNNNN=";
			
			
	        GregorianCalendar f = new GregorianCalendar();
			
								int mes = funciones.FuncionesNico.traerMes(f);
								int dia = funciones.FuncionesNico.traerDia(f);
								int anio = funciones.FuncionesNico.traerAnio(f);
								boolean turno = funciones.Funciones.esDiurno();
								
								String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
			
			
			
			//escribo el archivo
			 FileWriter fichero = null;
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/Boletines/offshore.txt");
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

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
		        
		        
		        
		        

		        GregorianCalendar fecha= new GregorianCalendar();
		        String fechaString = FuncionesNico.traerFechaCorta(fecha);

		        fechaString = fechaString.replace("\\", "-");
		        
		      //escribo el archivo
				 FileWriter fichero2 = null;
			        PrintWriter pw2 = null;
			        try
			        {
			            fichero2 = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/BoletinesViejos/offshore"+paraElArchivo+".txt");
			            pw2 = new PrintWriter(fichero2);

			            
			                pw2.printf(retorno.toUpperCase());
			                
			                
			                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

			        } catch (Exception e) {
			            e.printStackTrace();
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
					            fichero3 = new FileWriter(direccionAPP +"/offshore.txt");
					            pw3 = new PrintWriter(fichero3);

					            
					                pw3.printf(retorno.toUpperCase());
					                
					                
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
					        
					        
					        
					        
/*
						      //escribo el archivo
								 FileWriter fichero4 = null;
							        PrintWriter pw4 = null;
							        try
							        {
							            fichero4 = new FileWriter("C:\\Users\\nperez\\Desktop\\workspace\\NicoMetareaVI\\WebContent\\offshore.txt");
							            pw4 = new PrintWriter(fichero4);

							            
							                pw4.printf(retorno.toUpperCase());
							                
							                
							                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

							        } catch (Exception e) {
							            e.printStackTrace();
							        } finally {
							           try {
							           // Nuevamente aprovechamos el finally para 
							           // asegurarnos que se cierra el fichero.
							           if (null != fichero4)
							              fichero4.close();
							           } catch (Exception e4) {
							              e4.printStackTrace();
							           }
							        }  
			        
			        */
			        
			    
			        
		
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Transforma al espa�ol el boletin OFFSHORE
	
	
	
	public static void escribirBoletinOSEspa(String direccionAPP) {
		
		  Pronostico p = funciones.Funciones.cargarXML(direccionAPP);
			
			String retorno = Funciones.generoCabeceraOFFSHORE();
			
			retorno = retorno +Funciones.generarParte1OffShore();
			
			retorno = retorno +Funciones.generarParte2();
			
			retorno = retorno +Funciones.generarParte2Hielos();
			
			
			
			retorno = retorno +Funciones.generarPronosticosOFFSHORE(p);
			
			
			retorno = funciones.Funciones.traducirBoletin(retorno);
			
			
			retorno = retorno +"\r\n\r\n\r\nNNNN=";
			
	        GregorianCalendar f = new GregorianCalendar();
			
								int mes = funciones.FuncionesNico.traerMes(f);
								int dia = funciones.FuncionesNico.traerDia(f);
								int anio = funciones.FuncionesNico.traerAnio(f);
								boolean turno = funciones.Funciones.esDiurno();
								
								String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
			
			//escribo el archivo
			 FileWriter fichero = null;
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/Boletines/offshoreEspa.txt");
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

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
		        
		        
		        

		        GregorianCalendar fecha= new GregorianCalendar();
		        String fechaString = FuncionesNico.traerFechaCorta(fecha);

		        fechaString = fechaString.replace("\\", "-");
		        
		        
		        
		      //escribo el archivo
				 FileWriter fichero2 = null;
			        PrintWriter pw2 = null;
			        try
			        {
			            fichero2 = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/BoletinesViejos/offshoreEspa"+paraElArchivo+".txt");
			            pw2 = new PrintWriter(fichero2);

			            
			                pw2.printf(retorno.toUpperCase());
			                
			                
			                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

			        } catch (Exception e) {
			            e.printStackTrace();
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
			        
			        
			        FileWriter fichero3 = null;
			        PrintWriter pw3 = null;
			        try
			        {
			            fichero3 = new FileWriter(direccionAPP +"/offshoreEspa.txt");
			            pw3 = new PrintWriter(fichero3);

			            
			                pw3.printf(retorno.toUpperCase());
			                
			                
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
			        
			        
			        
			        
			        FileWriter fichero4 = null;
			        PrintWriter pw4 = null;
			        try
			        {
			            fichero4 = new FileWriter("offshoreEspa.txt");
			            pw4 = new PrintWriter(fichero4);

			            
			                pw4.printf(retorno.toUpperCase());
			                
			                
			                //Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"OFF SHORE");

			        } catch (Exception e) {
			            e.printStackTrace();
			        } finally {
			           try {
			           // Nuevamente aprovechamos el finally para 
			           // asegurarnos que se cierra el fichero.
			           if (null != fichero4)
			              fichero4.close();
			           } catch (Exception e4) {
			              e4.printStackTrace();
			           }
			        }
			        
			       
			        
			        
			        
		
		
	}
	

	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Escribe el boletin costero y genera el respectivo archivo, tambien lo guarda en la BD
	
	
	public static void escribirBoletinCoastal(String direccionAPP) {
		
		  Pronostico p = funciones.Funciones.cargarXML(direccionAPP);
			
			String retorno = Funciones.generoCabeceraCoastal();
			
			retorno = retorno +Funciones.generarParte1Coastal();
			
			retorno = retorno +Funciones.generarParte2();
			retorno = retorno.replace("GENERAL SYNOPSIS","SINOPSIS GENERAL");
			
			
			
			
			 GregorianCalendar f = new GregorianCalendar();
				
				int mes = funciones.FuncionesNico.traerMes(f);
				int dia = funciones.FuncionesNico.traerDia(f);
				int anio = funciones.FuncionesNico.traerAnio(f);
				boolean turno = funciones.Funciones.esDiurno();
				
				String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
			
			
			
			
			
			
			
			retorno = retorno +Funciones.generarPronosticosCoastal(p);
			
			
			retorno = retorno +"\r\n\r\n\r\nNNNN=";
			

			
			
			//retorno = Funciones.traducirBoletin(retorno);
			
			//escribo el archivo
			 FileWriter fichero = null;
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/Boletines/coastal.txt");
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                
		                System.out.println("Esto me genera error al escribir en l BAD");
		                System.out.println("-------------------------------------");
		                
		                System.out.println(retorno.toUpperCase());
		                
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"COASTAL");

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
		        
		        
		        
		        
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/BoletinesViejos/coastal"+paraElArchivo+".txt");
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                
		                System.out.println("Esto me genera error al escribir en l BAD");
		                System.out.println("-------------------------------------");
		                
		                System.out.println(retorno.toUpperCase());
		                
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"COASTAL");

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
		        
		        
		        
		        
		        
		        try
		        {
		            fichero = new FileWriter(direccionAPP +"coastal.txt");
		            pw = new PrintWriter(fichero);

		            
		                pw.printf(retorno.toUpperCase());
		                
		                
		                System.out.println("Esto me genera error al escribir en l BAD");
		                System.out.println("-------------------------------------");
		                
		                System.out.println(retorno.toUpperCase());
		                
		                
		                Funciones.escribirBoletinEmitidoEnBD(retorno.toUpperCase(),"COASTAL");

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
	

	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//En desuso
	
	public static void escribirBoletinEmitidoEnBD(String texto,String tipo) throws Exception {
		
		BoletinEmitidoABM beABM = new BoletinEmitidoABM();
		BoletinABM bABM = new BoletinABM();
		
		beABM.agregar(texto,tipo,bABM.traerBoletin(Funciones.ultimoBoletin()));
		
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//devuelve la direccion de todos los ficheros a enviar por mail
	
	public static ArrayList<String> listaDeUrls(String direccionAPP){
		
		
		
		File dir = new File(direccionAPP +"/InformesMetareaVI/Warning");
		String[] ficheros = dir.list();
		
		
		File dir2 = new File(direccionAPP +"/InformesMetareaVI/Boletines");
		String[] ficheros2 = dir2.list();
		
		ArrayList<String> lista =new ArrayList<String>();
		
		if (ficheros == null)
			  System.out.println("No hay ficheros en el directorio especificado");
			else { 
			  for (int x=0;x<ficheros.length;x++) {
			    //System.out.println(ficheros[x]);
			  
			 
			  lista.add(direccionAPP +"/InformesMetareaVI/Warning/" +ficheros[x]);
			  }
			}
		
		
		if (ficheros2 == null)
			  System.out.println("No hay ficheros en el directorio especificado");
			else { 
			  for (int x=0;x<ficheros2.length;x++) {
			    //System.out.println(ficheros2[x]);
			  lista.add(direccionAPP +"/InformesMetareaVI/Boletines/" +ficheros2[x]);
			  }
			}

	return lista;}
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Genera los pronosticos de forma ordenada de norte a sur, los pasa al ingles. 

	
public static String generarPronosticosParaNavtex(Pronostico p,String areaElegida){
		
        String retorno = "FORECAST:\r\n";
		
   
        
 
        

        
        
        boolean esDiurno = esDiurno(); 
        
        
        
        if(esDiurno) {
        
     
         
        	
        	
		
		for (Area a: p.getAreas()){
			
			
			
			if(a.toString().toUpperCase().contains(areaElegida.toUpperCase())) {
				
				

				
				retorno = retorno +"\r\n"+a.getNombre() +": ";
				
				///Variables para el viento
				String vientoD12 = "X";
				String vientoD18 = "X";
				String vientoD24 = "X";
				String vientoD30 = "X";
				String vientoD36 = "X";
				
				
				
				String vientoV12 = "X";
				String vientoV18 = "X";
				String vientoV24 = "X";
				String vientoV30 = "X";
				String vientoV36 = "X";
				
				//Variables fenomeno
				String fenomeno1 = "X";
				String fenomeno2 = "X";
				String fenomeno3 = "X";
				
				//variables olas
				String altura1 = "X";
				String altura2 = "X";
				String altura3 = "X";
				String altura4 = "X";
				String direccion1 = "X";
				String direccion2 = "X";
				
				//variables visibilidad
				String visibilidad1 = "X";
				String visibilidad2 ="X";
				String visibilidad3 = "X"; 
				
				/////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////
				
				//traigo las direcciones
				for (Parametro parametro: a.getParametros()){
					
					
					
					/////////////////////Busco los vientos /////////////////////////
					//guardo las direcciones
					if (parametro.getNombre().equals("wd")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoD12 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoD18 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoD24 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoD30 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoD36 = hora.getValores().get(1).getValor();
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					vientoD12 = transformarASectores(vientoD12);
					vientoD18 = transformarASectores(vientoD18);
					vientoD24 = transformarASectores(vientoD24);
					vientoD30 = transformarASectores(vientoD30);
					vientoD36 = transformarASectores(vientoD36);
					
					
					/////Guardo las velocidades
					if (parametro.getNombre().equals("ws")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								vientoV12 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==18){
								
								vientoV18 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							if (hora.getIdHora()==24){
								
								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							if (hora.getIdHora()==30){
								
								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
							
							if (hora.getIdHora()==36){
								
								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
				/////Guardo los fenomenos
					if (parametro.getNombre().equals("ww")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
								
							}//cierro el if hora
							
						
							
							if (hora.getIdHora()==24){
								
								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							if (hora.getIdHora()==36){
								
								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
								
							}//cierro el if hora
							
							
							
							
							
							
						}//cierro el for hora
					}//cierro el if parametro
					
					
					
					
					
				/////Guardo las visibilidades
					if (parametro.getNombre().equals("visibility")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==24){
								
								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							if (hora.getIdHora()==36){
								
								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					
				/////Guardo las alturas de olas
					if (parametro.getNombre().equals("waveh")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==12){
								
								altura1 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==18){
							
								altura2 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==24){
								
								altura3 =hora.getValores().get(0).getValor();
							}
							
							if (hora.getIdHora()==36){
								
								altura4 =hora.getValores().get(0).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
				/////Guardo la direccion de olas
					if (parametro.getNombre().equals("waved")){
						
						for (Hora hora: parametro.getHoras()){
							
							if (hora.getIdHora()==18){
								
								direccion1 =hora.getValores().get(1).getValor();
							}
							
							
							if (hora.getIdHora()==36){
								
								direccion2 =hora.getValores().get(1).getValor();
							}
							
							
						} //cierro el for de hora
						
						
					}//cierro if de visibilidad 
					
					
					
					
					
					direccion1 = transformarOlasA8Cuadrantes(direccion1);
					
					direccion2 = transformarOlasA8Cuadrantes(direccion2);
					
					
					
					
					
					
					
					
				}//Cierro el for paramertro
				
				
				
				if(vientoD12.equals("VARIABLE")){vientoD12 = "VRB";}
				if(vientoD18.equals("VARIABLE")){vientoD18 = "VRB";}
				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
				
				
				
				
				/////////////////////////////////////////////////
				///////////////////////////////////////////////
				
				//Aca genero la logica del  viento
				
						retorno = retorno +"\r\n" +escribirTextoVientoDiurno(vientoV12, vientoV18, vientoV24, vientoV30, vientoV36
								,vientoD12, vientoD18, vientoD24, vientoD30, vientoD36);
							
					/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							retorno = retorno +"\r\n" +escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
							
							
						/////////////////////////////////////////////////
								///////////////////////////////////////////////
								
								//Aca genero la logica de las olas
								//Si son distintas hago la transicion
							
						
							
							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
								
								
								//No hago nada
								
								
							}else {//else1
									
						
			        	
								
							//Si falta ALGUNA ola, no hago pronostico de olas
							//Si el modelo no cargo bien no se pronostica en esa region
							//o si el mar esta congelado
							if(altura1=="X"||altura2=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X") {
								//no hago nada
								
							}	
							
							else { //else 2
							
							if (altura1.equals("0")){ altura1 = "1";}
							if (altura2.equals("0")){ altura2 = "1";}
							if (altura3.equals("0")){ altura3 = "1";}
							if (altura4.equals("0")){ altura4 = "1";}
							
								if (direccion1.equals(direccion2)){
									
								
									
									
									if(altura1.equals(altura4)){
										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";;
									}
									if(!altura1.equals(altura4)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";;
									}
									
								}//cierro olas iguales
								
								
								if (!direccion1.equals(direccion2)){
									
									
									if (!altura1.equals(altura2)){
										
										
										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";;}
									
									if (altura1.equals(altura2)){
										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
									
									
									
								}//cierro los cambios de olas
								
								
								
								
							}	//cierra el else2
								
							}//cierra el else1
								
								//Termine la visi dejo renglon
								
		
							
							
							
							
							
							
							
							
							
							
							/////////////////////////////////////////////////
							///////////////////////////////////////////////
							
							//Aca genero la logica de la visibilidad
							//Si son distintas hago la transicion
							if(!visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
							}
							
							
							if(visibilidad1.equals(visibilidad3)){
								
								retorno = retorno +"VIS " +visibilidad1;
							}
							
							
							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
								retorno = retorno +",OCNL " +visibilidad2;
							}
							
							//Termine la visi dejo renglon
							retorno  = retorno +".\r\n";
							
							
							
							
						
							
			
				
			
			}
			
			
		}//Cierro el recorrido de las areas
		
		
		
       
		
   
        
        
        }
        
        
        
        
        
        
        /////Algoritmo turno noche!!!!!!!!!!!!!!!!!!!!
        //CHEACKEAAAAAAAAAAAR
        
        if(!esDiurno) {
            
          
             
            	
            	
    		
    		for (Area a: p.getAreas()){
    			
    			
    			if(a.toString().toUpperCase().contains(areaElegida.toUpperCase())) {
    				
    				retorno = retorno +"\r\n" +a.getNombre() +": ";
    				
    				///Variables para el viento
    				String vientoD24 = "X";
    				String vientoD30 = "X";
    				String vientoD36 = "X";
    				String vientoD42 = "X";
    				String vientoD48 = "X";
    				
    				
    				
    				String vientoV24 = "X";
    				String vientoV30 = "X";
    				String vientoV36 = "X";
    				String vientoV42 = "X";
    				String vientoV48 = "X";
    				
    				//Variables fenomeno
    				String fenomeno1 = "X";
    				String fenomeno2 = "X";
    				String fenomeno3 = "X";
    				
    				//variables olas
    				String altura1 = "X";
    				String altura2 = "X";
    				String altura3 = "X";
    				String altura4 = "X";
    				String direccion1 = "X";
    				String direccion2 = "X";
    				
    				//variables visibilidad
    				String visibilidad1 = "X";
    				String visibilidad2 = "X";
    				String visibilidad3 = "X"; 
    				
    				/////////////////////////////////////////////////////////////////
    				/////////////////////////////////////////////////////////////////
    				
    				//traigo las direcciones
    				for (Parametro parametro: a.getParametros()){
    					
    					
    					
    					/////////////////////Busco los vientos /////////////////////////
    					//guardo las direcciones
    					if (parametro.getNombre().equals("wd")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								vientoD24 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==30){
    								
    								vientoD30 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==36){
    								
    								vientoD36 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==42){
    								
    								vientoD42 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							
    							if (hora.getIdHora()==48){
    								
    								vientoD48 = hora.getValores().get(1).getValor();
    							}//cierro el if hora
    							
    							
    							
    							
    							
    							
    						}//cierro el for hora
    					}//cierro el if parametro
    					
    					
    					
    					vientoD24 = transformarASectores(vientoD24);
    					vientoD30 = transformarASectores(vientoD30);
    					vientoD36 = transformarASectores(vientoD36);
    					vientoD42 = transformarASectores(vientoD42);
    					vientoD48 = transformarASectores(vientoD48);
    					
    					
    					/////Guardo las velocidades
    					if (parametro.getNombre().equals("ws")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								vientoV24 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==30){
    								
    								vientoV30 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==36){
    								
    								vientoV36 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							if (hora.getIdHora()==42){
    								
    								vientoV42 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    							
    							if (hora.getIdHora()==48){
    								
    								vientoV48 = Funciones.nudosABeaufort(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							
    							
    							
    							
    							
    						}//cierro el for hora
    					}//cierro el if parametro
    					
    					
    					
    					
    				/////Guardo los fenomenos
    					if (parametro.getNombre().equals("ww")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								fenomeno1 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
    								
    								
    							}//cierro el if hora
    							
    						
    							
    							if (hora.getIdHora()==36){
    								
    								fenomeno2 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							
    							
    							
    							if (hora.getIdHora()==48){
    								
    								fenomeno3 = funciones.Funciones.codigoAFenomeno(hora.getValores().get(0).getValor());
    								
    							}//cierro el if hora
    							
    							
    							
    							
    							
    							
    						}//cierro el for hora
    					}//cierro el if parametro
    					
    					
    					
    					
    					
    				/////Guardo las visibilidades
    					if (parametro.getNombre().equals("visibility")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								visibilidad1 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
    							}
    							
    							if (hora.getIdHora()==36){
    								
    								visibilidad2 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
    							}
    							
    							if (hora.getIdHora()==48){
    								
    								visibilidad3 = Funciones.transformarNumeroAVisibilidad(hora.getValores().get(0).getValor());
    							}
    							
    							
    						} //cierro el for de hora
    						
    						
    					}//cierro if de visibilidad 
    					
    					
    					
    					
    					
    					
    				/////Guardo las alturas de olas
    					if (parametro.getNombre().equals("waveh")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==24){
    								
    								altura1 =hora.getValores().get(0).getValor();
    							}
    							
    							if (hora.getIdHora()==30){
    							
    								altura2 =hora.getValores().get(0).getValor();
    							}
    							
    							if (hora.getIdHora()==36){
    								
    								altura3 =hora.getValores().get(0).getValor();
    							}
    							
    							if (hora.getIdHora()==48){
    								
    								altura4 =hora.getValores().get(0).getValor();
    							}
    							
    							
    						} //cierro el for de hora
    						
    						
    					}//cierro if de visibilidad 
    					
    					
    					
    				/////Guardo la direccion de olas
    					if (parametro.getNombre().equals("waved")){
    						
    						for (Hora hora: parametro.getHoras()){
    							
    							if (hora.getIdHora()==30){
    								
    								direccion1 =hora.getValores().get(1).getValor();
    							}
    							
    							
    							if (hora.getIdHora()==48){
    								
    								direccion2 =hora.getValores().get(1).getValor();
    							}
    							
    							
    						} //cierro el for de hora
    						
    						
    					}//cierro if de visibilidad 
    					
    					
    					
    					
    					
    					
    					direccion1 = transformarOlasA8Cuadrantes(direccion1);
    					
    					direccion2 = transformarOlasA8Cuadrantes(direccion2);
    					
    					
    					
    					
    					
    					
    					
    					
    					
    				}//Cierro el for paramertro
    				
    				
    				
    				if(vientoD24.equals("VARIABLE")){vientoD24 = "VRB";}
    				if(vientoD30.equals("VARIABLE")){vientoD30 = "VRB";}
    				if(vientoD36.equals("VARIABLE")){vientoD36 = "VRB";}
    				if(vientoD42.equals("VARIABLE")){vientoD42 = "VRB";}
    				if(vientoD48.equals("VARIABLE")){vientoD48 = "VRB";}
    				if(direccion1.equals("VARIABLE")){direccion1 = "VRB";}
    				if(direccion2.equals("VARIABLE")){direccion2 = "VRB";}
    				
    				
    				
    				
    				/////////////////////////////////////////////////
    				///////////////////////////////////////////////
    				
    				//Aca genero la logica del  viento
    				
    		
    				
    			retorno = retorno +"\r\n"+escribirTextoVientoNocturno(vientoV24, vientoV30, vientoV36
    					,  vientoV42, vientoV48
    					,vientoD24, vientoD30, vientoD36, vientoD42,  vientoD48);
    							
    					/////////////////////////////////////////////////
    							///////////////////////////////////////////////
    							
    							retorno = retorno +"\r\n"+escribirTextoFenomenos(fenomeno1, fenomeno2, fenomeno3);
    							
    						/////////////////////////////////////////////////
    								///////////////////////////////////////////////
    								
    								//Aca genero la logica de las olas
    								//Si son distintas hago la transicion
    							
    							
    							
    							if(a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 020W")||
    									a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 040W")||
    											a.getNombre().toUpperCase().contains(	"SOUTHERN WEDDELL AREA S 68S W 050W")||
    												a.getNombre().toUpperCase().contains("SOUTHERN WEDDELL AREA S 68S W 030W")||
    													a.getNombre().toUpperCase().contains("MARDELAFLOTA")||
    														a.getNombre().toUpperCase().contains("GERLACHE STRAIT")||
    															a.getNombre().toUpperCase().contains("EREBUS AND TERROR GULF")){
    								
    								//no hago nada
    								
    								
    							}else {
    							
    							
    							
    							if(altura1=="X"||altura1=="X"||altura3=="X"||altura4=="X"||direccion1=="X"||direccion2=="X"){
    							
    								//no hago nada si se cargo mal el modelo
    							}	
    								
    							//Si se cargo bien si hago
    							else {
    								
    							if (altura1.equals("0")){ altura1 = "1";}
    							if (altura2.equals("0")){ altura2 = "1";}
    							if (altura3.equals("0")){ altura3 = "1";}
    							if (altura4.equals("0")){ altura4 = "1";}
    							
    								if (direccion1.equals(direccion2)){
    									
    								
    									
    									
    									if(altura1.equals(altura4)){
    										retorno = retorno +"WAVES "  +" "+direccion1  +" "+altura1+".\r\n";
    									}
    									if(!altura1.equals(altura4)){
    										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +"/" +altura4+".\r\n";
    									}
    									
    								}//cierro olas iguales
    								
    								
    								if (!direccion1.equals(direccion2)){
    									
    									
    									if (!altura1.equals(altura2)){
    										
    										
    										retorno = retorno +"WAVES " +direccion1  +" "+altura1  +"/"+altura2 +" LATER " +direccion2 +" " +altura4+".\r\n";}
    									
    									if (altura1.equals(altura2)){
    										retorno = retorno +"WAVES " +direccion1  +" "+altura1 +" LATER " +direccion2 +" " +altura4+".\r\n";}
    									
    									
    									
    								}//cierro los cambios de olas
    							
    								
    								
    							}
    								
    							}//ceirra el else
    								
    								
    								//Termine la visi dejo renglon
    							
    		
    							
    							
    							
    							
    							
    							
    							
    							
    							
    							
    							/////////////////////////////////////////////////
    							///////////////////////////////////////////////
    							
    							//Aca genero la logica de la visibilidad
    							//Si son distintas hago la transicion
    							if(!visibilidad1.equals(visibilidad3)){
    								
    								retorno = retorno +"VIS " +visibilidad1 +" TO " +visibilidad3;
    							}
    							
    							
    							if(visibilidad1.equals(visibilidad3)){
    								
    								retorno = retorno +"VIS " +visibilidad1;
    							}
    							
    							
    							if((!visibilidad1.equals(visibilidad2))&&(!visibilidad3.equals(visibilidad2))){
    								retorno = retorno +",OCNL " +visibilidad2;
    							}
    							
    							//Termine la visi dejo renglon
    							retorno  = retorno +".\r\n";
    							
    							
    							
    							
    						
    							
    			
    				
    			}

    			
    			
    			
    		}//Cierro el recorrido de las areas
    		
    		
           
    		
          
            
            
            }
        
        
        
        
	
		retorno = retorno.replace("OCCASIONAL OCCASIONAL","OCCASIONAL");
		
		retorno = retorno.toUpperCase();
		retorno= retorno.replace("AREA NORTE","NORTH AREA");
		retorno = retorno.replace("AREA CENTRO ESTE","CENTRAL EAST AREA");
		retorno= retorno.replace("AREA CENTRO OESTE","CENTRAL WEST AREA");
		retorno= retorno.replace("AREA SUDESTE","SOUTH EAST AREA");
		retorno= retorno.replace("AREA SUDOESTE","SOUTH WEST AREA");
		retorno= retorno.replace("ZONADRAKE","DRAKE AREA");
		
		
		
		return retorno;
	}
	


////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Escribe y guarda el navtex


public static void escribirYGuardarNavtex(String localidadNavtex, String direccionAPP) {
	
	String localidad = localidadNavtex;
	
	
	
	
	Pronostico p = Funciones.cargarXML(direccionAPP);
	
						String retorno = Funciones.generoCabeceraNavTex();
						
						retorno = retorno  +Funciones.generarParte1Coastal();
						
						retorno = retorno  +Funciones.generarParte2Navtex();
							
						retorno = retorno   +Funciones.generarPronosticosParaNavtex(p,localidad);
						
						System.out.print("ppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
						System.out.println("Localidad: "+localidad);
						System.out.print(retorno);
						
						GregorianCalendar f = new GregorianCalendar();
						
						int mes = funciones.FuncionesNico.traerMes(f);
						int dia = funciones.FuncionesNico.traerDia(f);
						int anio = funciones.FuncionesNico.traerAnio(f);
						boolean turno = funciones.Funciones.esDiurno();
						
						String paraElArchivo = "-"+anio+"-"+mes+"-"+dia+"-"+turno;
							
						//Genero el archivo con el navtex
						//escribo el archivo
						 FileWriter fichero = null;
						    PrintWriter pw = null;
						    
						    FileWriter fichero3 = null;
						    PrintWriter pw3 = null;
						    
						    FileWriter ficheroViejo = null;
						    PrintWriter pwViejo = null;
						    
						 
						    localidad = localidad.replace(" ", "_");
						
						    
						    
						    try
						    {
						   
						    	
						        fichero = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/Navtex/" +localidad +".txt");
						  
						        
						        
						        
						        pw = new PrintWriter(fichero);
						   
						
						        
						        //String texto = w.getTexto().replaceAll("XXX",String.valueOf(w.getIdWarning()));
						            pw.printf(retorno);
						          
						            
						            
						            
						            
						
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
						    
						    
						    
						    try
						    {
						    	
						    	
						 
						        ficheroViejo = new FileWriter(direccionAPP +"/boletines/InformesMetareaVI/NavtexViejos/" +localidad +paraElArchivo +".txt");
						        
						        
						        
						  
						        pwViejo = new PrintWriter(ficheroViejo);
						
						        
						        //String texto = w.getTexto().replaceAll("XXX",String.valueOf(w.getIdWarning()));
						  
						            pwViejo.printf(retorno);
						            
						            
						            
						            
						
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
						    
						    
						    try
						    {
						    	
						    	
						 
						        fichero3 = new FileWriter(direccionAPP  +"/"+localidad +".txt");
						        
						        
						        
						  
						        pw3 = new PrintWriter(fichero3);
						
						        
						        //String texto = w.getTexto().replaceAll("XXX",String.valueOf(w.getIdWarning()));
						  
						            pw3.printf(retorno);
						            
						            
						            
						            
						
						    } catch (Exception e3) {
						        e3.printStackTrace();
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
						    
						   


	
}//Cierro escribir y guardr navtex



////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//devuelve el id del ultimo boletin creado. 


public static int ultimoBoletin() {
	

	int maximo = 0; 
	BoletinABM bABM = new BoletinABM();
	
	for (Boletin b : bABM.traerBoletin()) {
		
		if(b.getIdBoletin()>=maximo) {
			maximo = b.getIdBoletin();
		}
	}
	return maximo;
}



////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//Una vez enviados los archivos por mail los elimina del directorio, quedan guardados
//en otro directorio y en la BD

		public static void eliminarLosArchivosEnviador(String direccionAPP) {
	
	

	   File dir = new File(direccionAPP +"/boletines/InformesMetareaVI/Boletines");
	   String cabeceraDir = direccionAPP +"/boletines/InformesMetareaVI/Boletines/";
	   String[] ficheros = dir.list();
	 
	   File dir2 = new File(direccionAPP +"/boletines/InformesMetareaVI/Warning");
	   String cabeceraDir2 = direccionAPP +"/boletines/InformesMetareaVI/Warning/";
	   String[] ficheros2 = dir2.list();
	   
	  
	   File dir3 = new File(direccionAPP +"/boletines/InformesMetareaVI/Navtex");
	   String cabeceraDir3 = direccionAPP +"/boletines/InformesMetareaVI/Navtex/";
	   String[] ficheros3 = dir3.list();
	

	   
					   for (String s: ficheros) {
					   
				 try{
				
				     //File archivo = new File("C:\\carpeta1\\ejemplo1.txt");
				
				 	File archivo = new File(cabeceraDir +s);
				 
				     boolean estatus = archivo.delete();
				
				     if (!estatus) {
				
				         System.out.println("Error no se ha podido eliminar el  archivo");
				         System.out.println("Cual??: "  +s);
				
				    }else{
				    	
				    	
				    	
				
				         System.out.println("Se ha eliminado el archivo exitosamente");
				         System.out.println("Cual??: "  +s);
				         
				
				    }
				
				 }catch(Exception e){
				
				    System.out.println(e);
				
				 }
				 
					   } //Cierro el for
					   
					   
					   
					   
					   for (String s: ficheros2) {
					   
				 try{
				
				     //File archivo = new File("C:\\carpeta1\\ejemplo1.txt");
				
				 	File archivo = new File(cabeceraDir2 +s);
				 
				     boolean estatus = archivo.delete();;
				
				     if (!estatus) {
				
				         System.out.println("Error no se ha podido eliminar el  archivo");
				         System.out.println("Cual??: "  +s);
				
				    }else{
				
				         System.out.println("Se ha eliminado el archivo exitosamente");
				         System.out.println("Cual??: "  +s);
				
				    }
				
				 }catch(Exception e){
				
				    System.out.println(e);
				
				 }
				 
					   } //Cierro el for
					   
					   
					   
					
					   
					   for (String s: ficheros3) {
					   
				 try{
				
				     //File archivo = new File("C:\\carpeta1\\ejemplo1.txt");
				
				 	File archivo = new File(cabeceraDir3 +s);
				 
				     boolean estatus = archivo.delete();;
				
				     if (!estatus) {
				
				         System.out.println("Error no se ha podido eliminar el  archivo");
				         System.out.println("Cual??: "  +s);
				
				    }else{
				
				         System.out.println("Se ha eliminado el archivo exitosamente");
				         System.out.println("Cual??: "  +s);
				
				    }
				
				 }catch(Exception e){
				
				    System.out.println(e);
				
				 }
				 
					   } //Cierro el for
					   
				
				}
		
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

//traduce del ingles al espa�ol
		
		public static String traducirBoletin(String textoIngles) {
			
			String retorno = "";
			
			
		
		
			retorno = textoIngles.replace("WARNING","AVISO");
			
			
			
			retorno = retorno.replace("FQST02","FQST01");
			retorno = retorno.replace("FQST04","FQST03");
			retorno = retorno.replace("HIGH SEAS BULLETIN FOR METAREA VI ISSUED  BY NWS,ARGENTINA,FOR THE PERIOD ", "BOLETIN DE ALTA MAR PARA LA METAREA VI CREADO POR EL SMN, ARGENTINA, PARA EL PERIODO ");
			
			retorno = retorno.replace("PLEASE BE AWARE WIND GUSTS CAN BE A FURTHER 40 PERCENT STRONGER THAN THE AVERAGES GIVEN HERE,AND MAXIMUM WAVES IN METERS  MAY BE UP TO TWICE THE HEIGHT. SEA ICE AND ICEBERGS ISSUED BY SHN,PRESSURE HPA AND BEAUFORT SCALE WINDS", 
					"TENGA EN CUENTA QUE LAS RAFAGAS PUEDEN SER UN 40 POR CIENTO MAS FUERTES DE LO PRONOSTICADO AQUI,Y LAS ALTURAS MAXIMAS PUEDEN LLEGAR A SER HASTA EL DOBLE. EL LIMITE DE HIELO Y LOS TEMPANOS SON EMITIDOS POR EL SHN. PRESION DADA EN HPA Y VIENTOS EN ESCALA BEAUFORT");
			
			
			retorno = retorno.replace("OFFSHORE WEATHER FOR METAREA VI ISSUED NWS,ARGENTINA, FOR THE PERIOD ", "PRONOSTICO OFFSHORE PARA LA METAREA VI CREADOR POR EL SMN, ARGENTINA, PARA EL PERIODO ");
			
			retorno = retorno.replace("UTC ON", "UTC DEL DIA");
			
			
			
			
			retorno = retorno.replace(" IN "," EN ");
			retorno = retorno.replace(" EXP "," ESPEDADO EN ");
			retorno = retorno.replace(" UNTIL "," HASTA EL ");
			retorno = retorno.replace("LOW","DEPRESION");
			retorno = retorno.replace(" AT "," EN ");
			retorno = retorno.replace(" MOV "," MOVIENDOSE AL ");
			retorno = retorno.replace("DPN","DEBILITANDOSE");
			retorno = retorno.replace("EXPECTED","ESPERADO EN");
			retorno = retorno.replace(" FROM "," DESDE EL ");
			retorno = retorno.replace(" BY "," HASTA EL ");
			retorno = retorno.replace("HIGH 1","ANTICICLON 1");
			
	
			retorno = retorno.replace(" BACK "," CAMBIANDO ");
			

			retorno = retorno.replace("AUMTD","AUMENTANDO A");
			retorno = retorno.replace("WAVES "," OLAS ");
			retorno = retorno.replace(" VRB "," VARIABLES ");
			retorno = retorno.replace("SECURITE","SEGURIDAD");
			retorno = retorno.replace("INTSF","INTENSIFICANDOSE");
			retorno = retorno.replace("DISM","DISMINUYENDO A");
			retorno = retorno.replace("WORSENING","DESMEJORANDO");
			retorno = retorno.replace("LATER","LUEGO");
			retorno = retorno.replace("ICE PELLETS","GRANULOS DE NIEVE");
			retorno = retorno.replace("TOWARDS THE END OF THE PERIOD","HACIA EL FINAL DEL PERIODO");
			retorno = retorno.replace("GOOD","BUENA");
			retorno = retorno.replace(" AND "," Y ");
			retorno = retorno.replace("OCCASIONAL ","OCASIONAL ");
			retorno = retorno.replace("HEAVY SNOW","NEVADAS FUERTES");
			retorno = retorno.replace("SNOW","NEVADAS");
			retorno = retorno.replace("MODERATE","REGULAR");
			retorno = retorno.replace("POOR","MALA");
			retorno = retorno.replace("VERY POOR","MUY MALA");
			retorno = retorno.replace("VIS ","VISIBILIDAD ");
			retorno = retorno.replace(" TO "," A ");
			retorno = retorno.replace("VIS ","VISIBILIDAD ");
			retorno = retorno.replace("OCNL ","OCASIONAL ");
			retorno = retorno.replace("OCASIONAL PROBABLES LLUVIAS","PROBABILIDAD DE OCASINALES LLUVIAS");
			retorno = retorno.replaceAll(" GOLFO SAN MATIAS:","GOLFO SAN MATIAS:");
			retorno = retorno.replace("OCNL NIEBLAS","OCASIONALES NIEBLAS");
			retorno = retorno.replace("STORM AVISO","AVISO POR TEMPORAL FUERTE");
			retorno = retorno.replace("GALE AVISO","AVISO POR TEMPORAL");
			retorno = retorno.replace("HURRICANE AVISO","AVISO POR TEMPORAL MUY FUERTE");
			retorno = retorno.replace("FORECAST","PRONOSTICO");
			retorno = retorno.replace("PROB ","PROBABILIDAD");
			retorno = retorno.replace("DRIZZLE","LLOVIZNAS");
			retorno = retorno.replace("IMPR","MEJORANDO");
			retorno = retorno.replace("SHOWERS","CHAPARRONES");
			retorno = retorno.replace("HEAVY RAIN","LLUVIAS FUERTES");
			retorno = retorno.replace("RAIN","LLUVIAS");
			retorno = retorno.replace("HEAVY THUNDERSTORM","TORMENTAS FUERTES");
			retorno = retorno.replace("THUNDERSTORM","TORMENTAS");
			retorno = retorno.replace("OCNL RAIN","LLUVIAS AISLADAS");
			retorno = retorno.replace("OCNL STORM","TORMENTAS AISLADAS");
			retorno = retorno.replace("FREEZING FOG","NIEBLA ENGELANTE");
			retorno = retorno.replace("FOG","NEBLINAS");
			retorno = retorno.replace("MIST","NIEBLA");
			retorno = retorno.replace("SPRAY","BRUMA");
			retorno = retorno.replace("OCASIONAL LLUVIAS","OCASIONALES LLUVIAS");
			retorno = retorno.replace("DESMEJORANDO Y ","DESMEJORANDO CON ");
			retorno = retorno.replace("OCASIONAL LLOVIZNAS","OCASIONALES LLOVIZNAS");
			retorno = retorno.replace("GENERAL SYNOPSIS","SINOPSIS GENERAL");
			retorno = retorno.replace(" SEA ICE LIMIT "," LIMITE DE HIELO MARINO ");
			retorno = retorno.replace(" ICEBERGS GREATER THAN 10"," TEMPANOS MAYORES A 10");
			retorno = retorno.replace(" ICEBERGS SMALLER THAN 10"," TEMPANOS MENORES A 10");
			retorno = retorno.replace(" ICEBERG"," TEMPANO");
			retorno = retorno.replace("REPORTED HASTA EL","REPORTADO POR");
			retorno = retorno.replace("OCASIONAL NEBLINAS","OCASIONALES NEBLINAS");
			
			retorno= retorno.replace("NORTH AREA","AREA NORTE");
			retorno = retorno.replace("CENTRAL EAST AREA","AREA CENTRO ESTE");
			retorno= retorno.replace("CENTRAL WEST AREA","AREA CENTRO OESTE");
			retorno= retorno.replace("SOUTH EAST AREA","AREA SUDESTE");
			retorno= retorno.replace("SOUTH WEST AREA","AREA SUDOESTE");
			retorno= retorno.replace("DRAKE AREA","AREA DRAKE");
			retorno= retorno.replace("DRAKE SOUTH","DRAKE SUR");
			
			retorno= retorno.replace("NORTHERN WEDDELL AREA", "AREA WEDDELL NORTE");
			retorno= retorno.replace("SOUTHERN WEDDELL AREA", "AREA WEDDELL SUR");
			retorno= retorno.replace("MARDELAFLOTA", "MAR DE LA FLOTA");
			
			retorno = retorno.replace("WFNT","FRENTE CALIENTE");
			retorno = retorno.replace("CFNT","FRENTE FRIO");
			retorno = retorno.replace("OCCLUDED","FRENTE OCLUIDO");
			retorno = retorno.replace("CYCLOGENESIS","CICLOGENESIS");
			
			retorno = retorno.replace("STRONG GRADIENT","FUERTE GRADIENTE BARICO");
			retorno = retorno.replace("RIDGE","EJE DE CUÑA");
			retorno = retorno.replace("TROUGH","EJE DE VAGUADA");
			retorno = retorno.replace("FRONT","FRENTE");
			retorno = retorno.replace("OCASIONAL OCASIONALES", "PROBABILIDAD DE OCASIONALES");
			
			
			
			
			
			
			
			
			return retorno; 
		}
		
		//////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////
		
		
		public static boolean esDiurno() {
			
			boolean esDiurno = true;
			
			
			GregorianCalendar fecha = new GregorianCalendar();
			
		
			int hora 	 = 	 fecha.get(Calendar.HOUR_OF_DAY);
			
			
			
			if(hora>5 && hora < 13) {
				esDiurno = true;
			}
			
			else {esDiurno = false;}
			
			
			
			System.out.println ("La hora es: "  + hora  +"Entonces: " +esDiurno);
			
			
			
			return esDiurno;
			
		}
		
		
		
		///////////////////////////////////////
		////////////////////////////////////////////
		
		
		public static boolean downloadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {
			try {
			URL url = new URL("ftp://" + user +":" + pass + "@" + server + remotePath + ";type=i");
			URLConnection urlc = url.openConnection();
			//InputStream is = (InputStream) urlc.getInputStream();
			BufferedWriter bw = new BufferedWriter(new FileWriter(localPath));
			int c;
			//while ((c = is.read()) != -1) {
			//bw.write(c);
			//}
			//is.close();
			bw.flush();
			bw.close();
			return true;
			} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return false;
			}
			}
		
		
		public static String escribirTextoFenomenos(String fenomeno1, String fenomeno2, String fenomeno3) {
			
			String retorno = "";
			
			
			if(!(fenomeno1.equals("WORSENING")&&fenomeno2.equals("WORSENING")&&fenomeno3.equals("WORSENING"))) {
				
				
				
				//Aca genero la logica de los fenomenos
				//Si son distintas hago la transicion
				if (fenomeno1.equals(fenomeno2)&& !fenomeno2.equals(fenomeno3)){
					if(fenomeno3.equals("WORSENING")) {fenomeno3="IMPR";}
					retorno = retorno +fenomeno1 +" LATER " +fenomeno3 +".\r\n";
				}
				
				
				if (!fenomeno1.equals(fenomeno2)&& !fenomeno2.equals(fenomeno3)&&!fenomeno1.equals(fenomeno3)){
					if(fenomeno3.equals("WORSENING")) {
						fenomeno3="IMPR";
						retorno = retorno +fenomeno1 +" LATER " +fenomeno2 +" AND LATER " + fenomeno3+".\r\n";
					
					}
					
					if(fenomeno2.equals("WORSENING")) {
						fenomeno2="IMPR";
						retorno = retorno +fenomeno1 +" LATER " +fenomeno3 +", OCNL " + fenomeno2+".\r\n";
					
					}
					
					if(fenomeno1.equals("WORSENING")) {
						
						retorno = retorno +fenomeno1 +", " +fenomeno2 +" LATER " + fenomeno3+".\r\n";
					
					}
			
				}
				
				
				if (fenomeno1.equals(fenomeno3)&& !fenomeno2.equals(fenomeno3)){
					if(fenomeno2.equals("WORSENING")) {fenomeno2="IMPR";
					
					retorno = retorno +fenomeno1 +" AND OCNL " +fenomeno2+".\r\n";							
					}
					
					if(fenomeno1.equals("WORSENING")) {
					retorno = retorno +" OCNL " +fenomeno2+".\r\n";
					}
				}
				
				
				
				
				if (!fenomeno1.equals(fenomeno2)&&fenomeno2.equals(fenomeno3)){
					if(fenomeno2.equals("WORSENING")) {fenomeno2="IMPR";}
					retorno = retorno + fenomeno1 + " LATER " +fenomeno2+".\r\n";
				}
				
		
				boolean dejarRenglon = true;
				if (fenomeno1.equals(fenomeno2)&& fenomeno2.equals(fenomeno3)){
					if (fenomeno1.equals("WORSENING")) {
						
						dejarRenglon = false;
					}
					
					else {retorno = retorno +fenomeno1+".\r\n";}
					
				}
				
				
				
				if(dejarRenglon) {
				//Termine la visi dejo renglon
				//retorno  = retorno +".";
				}
				
				
				}
				
				else {//Si no tenemos fenomeno no decimos nada}
				};
				
			return retorno;
		}
		
		
		public static String escribirTextoVientoDiurno(String vientoV12,String vientoV18,String  vientoV24
				,String  vientoV30,String  vientoV36
				,String vientoD12,String  vientoD18,String  vientoD24, String vientoD30,String  vientoD36) {
			

			
			boolean yaDejeRenglon = false;
			String retorno = "";
			
			
			//si el viento de 12 es distinto al viento de 18
			
				//----------------------->//OJOOOOOO back o veer,hacer luego 
			if(!vientoD12.equals(vientoD18)){
			retorno = retorno +vientoD12 +" " +vientoV12 +" BACK " +vientoD18 +" " +vientoV18 +agregarONoRafagas(vientoV18)+" AT 18Z " ;
			}
			
			//Vientos de misma direccion
			if(vientoD12.equals(vientoD18)){
				//Distinta intensidad 
				if(!vientoV12.equals(vientoV18)){
				retorno = retorno +vientoD12 +" " +vientoV12 +"/" +vientoV18 +agregarONoRafagas(vientoV18)+" " ;}
				//Misma intensidad
				if(vientoV12.equals(vientoV18)){
					retorno = retorno +vientoD12 +" " +vientoV12 +agregarONoRafagas(vientoV12)+" " ;}
				}
			
			
			
			//vamos de 18 a 24 
			
			if(!vientoD18.equals(vientoD24)){
				retorno = retorno +"BACK" +" " +vientoD24 +" " +vientoV24 +agregarONoRafagas(vientoV24) +" AT 00Z " ;
				}
				
				//Vientos de misma direccion
				if(vientoD18.equals(vientoD24)){
					//Distinta intensidad 
					if(!vientoV18.equals(vientoV24)){
						
						String nexo = "INCR";
						
						if(Float.parseFloat(vientoV18)>=Float.parseFloat(vientoV24)){
							nexo = "DISM";
						}
						
					retorno = retorno +nexo +" AT 00Z TO " +vientoV24 +agregarONoRafagas(vientoV24) +" ";}
					//Misma intensidad
					if(vientoV18.equals(vientoV24)){
						//No hago nada
						//retorno = retorno +vientoD12 +" " +vientoV12 +"\r\n" ;}
					}
				
				}
				
				
				//vamos de 24 a 30 
				
				if(!vientoD24.equals(vientoD30)){
					retorno = retorno +"\r\nBACK" +" " +vientoD30 +" " +vientoV30 +agregarONoRafagas(vientoV30)+" AT 06Z " ;
					yaDejeRenglon = true;
					}
					
					//Vientos de misma direccion
					if(vientoD24.equals(vientoD30)){
						//Distinta intensidad 
						if(!vientoV24.equals(vientoV30)){
							
							String nexo = "INCR";
							
							if(Float.parseFloat(vientoV24)>=Float.parseFloat(vientoV30)){
								nexo = "DISM";
							}
							yaDejeRenglon = true;
						retorno = retorno +"\r\n"+nexo +" AT 06Z TO " +vientoV30 +agregarONoRafagas(vientoV30)+" " ;}
						//Misma intensidad
						if(vientoV24.equals(vientoV30)){
							
							//No hago nada
							//retorno = retorno +vientoD12 +" " +vientoV12 +"\r\n" ;}
						}
					
					}
					
					
					
					//vamos de 30 a 36 
					
					if(!vientoD30.equals(vientoD36)){
						
						if(yaDejeRenglon) {
						retorno = retorno +"AND BACK AT 12Z TO " +" " +vientoD36 +" " +vientoV36 +agregarONoRafagas(vientoV36);
						}
						
						if(!yaDejeRenglon) {
							retorno = retorno +"\r\nAND BACK AT 12Z TO " +" " +vientoD36 +" " +vientoV36 +agregarONoRafagas(vientoV36);
						yaDejeRenglon = true;	
						}
					
					}
						
						//Vientos de misma direccion
						if(vientoD30.equals(vientoD36)){
							//Distinta intensidad 
							if(!vientoV30.equals(vientoV36)){
								
								String nexo = "AND INCR";
								
								if(Float.parseFloat(vientoV30)>=Float.parseFloat(vientoV36)){
									nexo = "AND DISM";
								}
								
								if(yaDejeRenglon) {
								
							retorno = retorno +nexo +" AT 12Z TO " +vientoV36 +agregarONoRafagas(vientoV36) +" ";}
								
								
								if(!yaDejeRenglon) {
									
									retorno = retorno +"\r\n" +nexo +" AT 12Z TO " +vientoV36 +agregarONoRafagas(vientoV36) +" ";
								yaDejeRenglon = true;
								}
								
								
							
							}
							
							
							
							//Misma intensidad
							if(vientoV30.equals(vientoV36)){
								//No hago nada
								//retorno = retorno +vientoD12 +" " +vientoV12 +"\r\n" ;}
							}
						
						}
				
			
						//Cambio de area y dejo un renglon
						retorno = retorno +".";	
						
			
			return retorno;
			
		}
		
		
		
		
		public static String escribirTextoVientoNocturno(String vientoV24,String vientoV30,String  vientoV36
				,String  vientoV42,String  vientoV48
				,String vientoD24,String  vientoD30,String  vientoD36, String vientoD42,String  vientoD48) {
			

			String retorno = "";
			boolean yaDejeRenglon = false;
			
			
			//si el viento de 24 es distinto al viento de 30
			
				//----------------------->//OJOOOOOO back o veer,hacer luego 
			if(!vientoD24.equals(vientoD30)){
			retorno = retorno +vientoD24 +" " +vientoV24 +" BACK " +vientoD30 +" " +vientoV30 +agregarONoRafagas(vientoV30)+" AT 06Z " ;
			}
			
			//Vientos de misma direccion
			if(vientoD24.equals(vientoD30)){
				//Distinta intensidad 
				if(!vientoV24.equals(vientoV30)){
				retorno = retorno +vientoD24 +" " +vientoV24 +"/" +vientoV30 +agregarONoRafagas(vientoV30) +" " ;}
				//Misma intensidad
				if(vientoV24.equals(vientoV30)){
					retorno = retorno +vientoD24 +" " +vientoV24 +agregarONoRafagas(vientoV24) ;}
				}
			
			
			
			//vamos de 30 a 36 
			
			if(!vientoD30.equals(vientoD36)){
				retorno = retorno +"BACK" +" " +vientoD36 +" " +vientoV36 +agregarONoRafagas(vientoV36)+" AT 12Z " ;
				}
				
				//Vientos de misma direccion
				if(vientoD30.equals(vientoD36)){
					//Distinta intensidad 
					if(!vientoV30.equals(vientoV36)){
						
						String nexo = "INCR";
						
						if(Float.parseFloat(vientoV30)>=Float.parseFloat(vientoV36)){
							nexo = "DISM";
						}
						
					retorno = retorno +nexo +" AT 12Z TO " +vientoV36 +agregarONoRafagas(vientoV36) +" ";}
					//Misma intensidad
					if(vientoV30.equals(vientoV36)){
						//No hago nada
						//retorno = retorno +vientoD24 +" " +vientoV24 +"\r\n" ;}
					}
				
				}
				
				
				//vamos de 36 a 42 
				
				if(!vientoD36.equals(vientoD42)){
					retorno = retorno +"\r\nBACK" +" " +vientoD42 +" " +vientoV42 +agregarONoRafagas(vientoV42)+" AT 18Z " ;
					yaDejeRenglon = true;
					}
					
					//Vientos de misma direccion
					if(vientoD36.equals(vientoD42)){
						//Distinta intensidad 
						if(!vientoV36.equals(vientoV42)){
							
							String nexo = "INCR";
							
							if(Float.parseFloat(vientoV36)>=Float.parseFloat(vientoV42)){
								nexo = "DISM";
							}
							yaDejeRenglon = true;
						retorno = retorno +"\r\n"+nexo +" AT 18Z TO " +vientoV42 +agregarONoRafagas(vientoV42)+" " ;
						}
						//Misma intensidad
						if(vientoV36.equals(vientoV42)){
							
							//No hago nada
							//retorno = retorno +vientoD24 +" " +vientoV24 +"\r\n" ;}
						}
					
					}
					
					
					
					//vamos de 42 a 48 
					
					if(!vientoD42.equals(vientoD48)){
						
						
						if(yaDejeRenglon) {
						retorno = retorno +"AND BACK AT 00Z TO " +" " +vientoD48 +" " +vientoV48 +agregarONoRafagas(vientoV48);
						
						}
						
						
						if(!yaDejeRenglon) {
	
							retorno = retorno +"\r\nAND BACK AT 00Z TO " +" " +vientoD48 +" " +vientoV48 +agregarONoRafagas(vientoV48);}
							yaDejeRenglon = true;
						}
						
						//Vientos de misma direccion
						if(vientoD42.equals(vientoD48)){
							//Distinta intensidad 
							if(!vientoV42.equals(vientoV48)){
								
								String nexo = "AND INCR";
								
								if(Float.parseFloat(vientoV42)>=Float.parseFloat(vientoV48)){
									nexo = "AND DISM";
								}
								
							if(yaDejeRenglon) {	
							retorno = retorno +nexo +" AT 00Z TO " +vientoV48 +agregarONoRafagas(vientoV48);}
							
							if(!yaDejeRenglon) {
								retorno = retorno +"\r\n "+nexo +" AT 00Z TO " +vientoV48 +agregarONoRafagas(vientoV48);
							}
							
							
							}
							//Misma intensidad
							if(vientoV42.equals(vientoV48)){
								//No hago nada
								//retorno = retorno +vientoD24 +" " +vientoV24 +"\r\n" ;}
							}
						
						}
				
			
						//Cambio de area y dejo un renglon
						retorno = retorno +".";	
						
			
			return retorno;
			
		}
		
		
		
		public static String agregarONoRafagas(String velocidad) {
			
			
			String retorno ="";
			
			//si faltan datos los paso a cero
			if(velocidad=="X"||velocidad=="") {
				velocidad = "0";
			}
			
			
			float nudos = Float.parseFloat(velocidad);
			
			if(nudos >= 7) {
				
				retorno = " WITH GUSTS";
				
			}
			
			
			return retorno;
		}
		
		
		public static String transformarASectores(String vientoD){
			
			
			
			System.out.println("----->Modificar: " +vientoD);
			
			switch (vientoD) {
			
			case "N": vientoD = "SECTOR N"; break;
			case "NNE": vientoD = "SECTOR N"; break;
			case "NNW": vientoD = "SECTOR N";break;
			
			case "S": vientoD = "SECTOR S";break;
			case "SSE": vientoD = "SECTOR S";break;
			case "SSW": vientoD = "SECTOR S";break;
			

			case "W": vientoD = "SECTOR W";break;
			case "WNW": vientoD = "SECTOR W";break;
			case "WSW": vientoD = "SECTOR W";break;
			

			case "E": vientoD = "SECTOR E";break;
			case "ENE": vientoD = "SECTOR E";break;
			case "ESE": vientoD = "SECTOR E";break;
			
			};
			
			System.out.println("----->Así quedo: " +vientoD);
			
			
			
			return vientoD;
		}
		
		
		
		public static String transformarOlasA8Cuadrantes(String olaD){
			
			
			
		
			
			switch (olaD) {
			
			case "N": olaD = "N"; break;
			case "NNE": olaD = "N"; break;
			case "NNW": olaD = "N";break;
			
			case "S": olaD = "S";break;
			case "SSE": olaD = "S";break;
			case "SSW": olaD= "S";break;
			

			case "W": olaD = "W";break;
			case "WNW": olaD = "W";break;
			case "WSW": olaD = "W";break;
			

			case "E": olaD= "E";break;
			case "ENE":olaD = "E";break;
			case "ESE": olaD = "E";break;
			
			};
			
			
			
			
			
			return olaD;
		}
		
		
		
		public static int contarCaracteres(String cadena, char caracter) {
	        int posicion, contador = 0;
	        //se busca la primera vez que aparece
	        posicion = cadena.indexOf(caracter);
	        while (posicion != -1) { //mientras se encuentre el caracter
	            contador++;           //se cuenta
	            //se sigue buscando a partir de la posición siguiente a la encontrada
	            posicion = cadena.indexOf(caracter, posicion + 1);
	        }
	        return contador;
	}
	

}//cierro funciones
