package test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;			// |
import org.jdom.Element;			// |\ Librerias
import org.jdom.JDOMException;		// |/ JDOM
import org.jdom.input.SAXBuilder;	// |

public class PruebaLectura {

	public PruebaLectura(){
		cargarXml();
	}
	
	public void cargarXml()
	
	
	
	
	{
		//Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File( "C:\\Users\\nperez\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\NicoMetareaVI\\InformesMetareaVI\\archivo.xml" );
		try
		{
			//Se crea el documento a traves del archivo
			Document document = (Document) builder.build( xmlFile );

			//Se obtiene la raiz 'data :( '
			Element rootNode = document.getRootElement();
			
			
			//Se obtiene la lista de hijos de la raiz 'data'
			List<?> list2 = rootNode.getChildren( "forecast" );
			
			//recorro los forecast que es uno solo
			for ( int fore = 0; fore < list2.size(); fore++){
			
				
				Element forecast = (Element) list2.get(fore);
				

			//Se obtiene la lista de hijos de la raiz 'data'
			List<?> list = forecast.getChildren( "area" );
			List<?> listIssue = forecast.getChildren( "issue" );
			
			for (int issu=0; issu<listIssue.size(); issu++){
				Element issue = (Element) listIssue.get(issu);
				System.out.println("Datos tomados el : " + issue.getChildTextTrim("timestamp"));
			}

			
			//Recorro las areas
			//Se recorre la lista de hijos de 'forecast'
			for ( int i = 0; i < 50; i++ )
			{
				//Se obtiene el elemento 'area'
				Element area = (Element) list.get(i);

				//Se obtiene el atributo 'id' que esta en el tag 'area'
				String idArea = area.getAttributeValue("id");
				String latitud = area.getAttributeValue("latitude");
				String longitud= area.getAttributeValue("longitude");
				String nombre = area.getAttributeValue("description");

				System.out.println( "idArea: " + idArea );
				System.out.println( "Latitud: " +latitud );
				System.out.println( "Longitud: " +longitud );
				System.out.println( "Nombre: " + nombre );
				
				

				
				//Se obtiene la lista de hijos del tag 'tabla'
				//List lista_parametros = area.getChildren("parameter");
				//Se obtiene la lista de hijos de la raiz 'tables'
				List<?> lista_parametros = area.getChildren("parameter");

				//System.out.println( "\tNombre\t\tTipo\t\tValor" );

				
				//recorros los parametros
				//Se recorre la lista de campos
				for ( int j = 0; j < lista_parametros.size(); j++ )
				{
					//Se obtiene el elemento 'campo'
					Element parametro = (Element)lista_parametros.get( j );

					//Se obtienen los valores que estan entre los tags '<campo></campo>'
					//Se obtiene el valor que esta entre los tags '<nombre></nombre>'
					String idParametro = parametro.getAttributeValue("id");
					

					System.out.println("Id del Parametro: " +idParametro);
					
					
					
					
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

						//Se obtienen los valores que estan entre los tags '<campo></campo>'
						//Se obtiene el valor que esta entre los tags '<nombre></nombre>'
						String h = hora.getAttributeValue("h");
						String datetime = hora.getAttributeValue("datetime");
						String unidad1 = hora.getAttributeValue("unit");
						String valor1 = hora.getChildTextTrim("value");

						System.out.println("La hora es: " +h);
						System.out.println("Descripta por: " +datetime);
						//System.out.println("Unidad: " +unidad1);
						//System.out.println("Valor: " +valor1);
						
						
						
						
						List<?> lista_valores = hora.getChildren();

						//System.out.println( "\tNombre\t\tTipo\t\tValor" );

						
						
						//Recorro los valores
						//Se recorre la lista de campos
						for ( int l = 0; l < lista_valores.size(); l++ )
						{
							//Se obtiene el elemento 'campo'
							Element valor = (Element)lista_valores.get( l);

							//Se obtienen los valores que estan entre los tags '<campo></campo>'
							//Se obtiene el valor que esta entre los tags '<nombre></nombre>'
							String unidadValor = valor.getAttributeValue("unit");
							String numeroValor = valor.getValue();
							

							System.out.println("Unidad: " +unidadValor);
							System.out.println("Valor: " +numeroValor);
							
							
						}//Cierro los valores
						
						System.out.println("-----------Fin Horario-----------")	;
							
						}//Cierro el datetime
						
		
					System.out.println("-----------Fin Parametro-----------")	;
					
				} //Cierro Parametro
				
				System.out.println("---------------------------")	;
				System.out.println("-----------Fin Area-----------")	;
				System.out.println("-----------------------------")	;
				
			}
		//Cierro areas
			
			
			}//Cierro el forecast
			
		}catch ( IOException io ) {
			System.out.println( io.getMessage() );
		}catch ( JDOMException jdomex ) {
			System.out.println( jdomex.getMessage() );
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PruebaLectura();
	}

}
