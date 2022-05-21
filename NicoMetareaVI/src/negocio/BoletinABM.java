package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.BoletinDao;

import modelo.Boletin;

public class BoletinABM {				
	BoletinDao dao=new BoletinDao();
	
	public Boletin traerBoletin(int idBoletin) throws Exception{	
		Boletin c= dao.traerBoletin(idBoletin); 
		if (c == null){
			throw new Exception ("Boletin nulo");				
		}
		return c; 
		
		/*¿Las excepciones no deben estar en BoletinDao como excepciones Hibernate? -> El acceso a los datos surge ahí. Hibernate deberia lanzar un error al no encontrar el id en la base de datos.
		 * Poner la excepcion acá luego de traer el Boletin asume que Hibernate puede traer un Boletin = null cuando no encuentra un "id" en la base de datos.
		 */
	}
	
	public Boletin traerBoletin(GregorianCalendar fecha, int horaUTC) throws Exception{
		Boletin c= dao.traerBoletin( fecha,  horaUTC);  
		
		return c;
	 
		//Misma opinion que en el Boletin anterior. ¿El problema de Boletin == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(GregorianCalendar fecha, int horaUTC) throws Exception { 
		
		//Consultar si existe un Boletin con el mismo dni, si existe arrojar la Excepcion:
		
			Boletin cnueva;
			
			cnueva= new Boletin(fecha, horaUTC);
			return dao.agregar(cnueva); 
			
			//A diferencia de los dos Boletins anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(Boletin c) throws Exception {
		// Implementar antes de actualizar que no exista un Boletin con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		Boletin d = dao.traerBoletin(c.getIdBoletin());
		if (d != null){					
			if (d.getIdBoletin() != c.getIdBoletin()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es él mismo. 
				throw new Exception ("Ya existe un Boletin con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... ¿Por qué preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idBoletin) throws Exception {
		
		/* En este caso es una baja física. En gral, no se se aplicaría este caso de uso. 
		 * Si se hiciera, habría que validar que el Boletin no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		Boletin c= dao.traerBoletin(idBoletin); 
		if (c == null){
			throw new Exception ("El Boletin es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<Boletin> traerBoletin(){
		return dao.traerBoletin(); 
	} 
	
	
	
public Boletin traerBoletinYListaWarning(int idBoletin) throws Exception{	
		
		
		Boletin c = dao.traerBoletinYListaWarning(idBoletin);
		
		/*
		if (c.getListaClases().isEmpty()) {	
			throw new Exception ("Este no hay clases asignadas"); 
		}	 */																				  
		return c;															 
	}	
	




	
		
	
}
