package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.FenomenoDao;

import modelo.Fenomeno;

public class FenomenoABM {				
	FenomenoDao dao=new FenomenoDao();
	
	public Fenomeno traerFenomeno(int idFenomeno) throws Exception{	
		Fenomeno c= dao.traerFenomeno(idFenomeno); 
		if (c == null){
			throw new Exception ("Fenomeno nulo");				
		}
		return c; 
		
		/*¿Las excepciones no deben estar en FenomenoDao como excepciones Hibernate? -> El acceso a los datos surge ahí. Hibernate deberia lanzar un error al no encontrar el id en la base de datos.
		 * Poner la excepcion acá luego de traer el Fenomeno asume que Hibernate puede traer un Fenomeno = null cuando no encuentra un "id" en la base de datos.
		 */
	}
	
	public Fenomeno traerFenomeno(GregorianCalendar fecha, int horaUTC) throws Exception{
		Fenomeno c= dao.traerFenomeno( fecha,  horaUTC);  
		
		return c;
	 
		//Misma opinion que en el Fenomeno anterior. ¿El problema de Fenomeno == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(String texto, int incluir) throws Exception { 
		
		//Consultar si existe un Fenomeno con el mismo dni, si existe arrojar la Excepcion:
		
			Fenomeno cnueva;
			
			cnueva= new Fenomeno(texto, incluir);
			return dao.agregar(cnueva); 
			
			//A diferencia de los dos Fenomenos anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(Fenomeno c) throws Exception {
		// Implementar antes de actualizar que no exista un Fenomeno con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		Fenomeno d = dao.traerFenomeno(c.getIdFenomeno());
		if (d != null){					
			if (d.getIdFenomeno() != c.getIdFenomeno()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es él mismo. 
				throw new Exception ("Ya existe un Fenomeno con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... ¿Por qué preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idFenomeno) throws Exception {
		
		/* En este caso es una baja física. En gral, no se se aplicaría este caso de uso. 
		 * Si se hiciera, habría que validar que el Fenomeno no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		Fenomeno c= dao.traerFenomeno(idFenomeno); 
		if (c == null){
			throw new Exception ("El Fenomeno es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<Fenomeno> traerFenomeno(){
		return dao.traerFenomeno(); 
	} 
	
	
	





	
		
	
}
