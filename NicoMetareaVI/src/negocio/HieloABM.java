package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.HieloDao;

import modelo.Hielo;

public class HieloABM {				
	HieloDao dao=new HieloDao();
	
	public Hielo traerHielo(int idHielo) throws Exception{	
		Hielo c= dao.traerHielo(idHielo); 
		if (c == null){
			throw new Exception ("Hielo nulo");				
		}
		return c; 
		
		/*¿Las excepciones no deben estar en HieloDao como excepciones Hibernate? -> El acceso a los datos surge ahí. Hibernate deberia lanzar un error al no encontrar el id en la base de datos.
		 * Poner la excepcion acá luego de traer el Hielo asume que Hibernate puede traer un Hielo = null cuando no encuentra un "id" en la base de datos.
		 */
	}
	
	public Hielo traerHielo(GregorianCalendar fecha, int horaUTC) throws Exception{
		Hielo c= dao.traerHielo( fecha,  horaUTC);  
		
		return c;
	 
		//Misma opinion que en el Hielo anterior. ¿El problema de Hielo == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(String espaniol, String ingles) throws Exception { 
		
		//Consultar si existe un Hielo con el mismo dni, si existe arrojar la Excepcion:
		
			Hielo cnueva;
			
			cnueva= new Hielo(espaniol, ingles);
			return dao.agregar(cnueva); 
			
			//A diferencia de los dos Hielos anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(Hielo c) throws Exception {
		// Implementar antes de actualizar que no exista un Hielo con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		Hielo d = dao.traerHielo(c.getIdHielo());
		if (d != null){					
			if (d.getIdHielo() != c.getIdHielo()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es él mismo. 
				throw new Exception ("Ya existe un Hielo con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... ¿Por qué preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idHielo) throws Exception {
		
		/* En este caso es una baja física. En gral, no se se aplicaría este caso de uso. 
		 * Si se hiciera, habría que validar que el Hielo no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		Hielo c= dao.traerHielo(idHielo); 
		if (c == null){
			throw new Exception ("El Hielo es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<Hielo> traerHielo(){
		return dao.traerHielo(); 
	} 
	
	
	





	
		
	
}
