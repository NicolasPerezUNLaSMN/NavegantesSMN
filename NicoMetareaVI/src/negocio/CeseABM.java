package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.CeseDao;

import modelo.Cese;
import modelo.Warning;

public class CeseABM {				
	CeseDao dao=new CeseDao();
	
	public Cese traerCese(int idCese) throws Exception{	
		Cese c= dao.traerCese(idCese); 
		if (c == null){
			throw new Exception ("Cese nulo");				
		}
		return c; 
		
		/*¿Las excepciones no deben estar en CeseDao como excepciones Hibernate? -> El acceso a los datos surge ahí. Hibernate deberia lanzar un error al no encontrar el id en la base de datos.
		 * Poner la excepcion acá luego de traer el Cese asume que Hibernate puede traer un Cese = null cuando no encuentra un "id" en la base de datos.
		 */
	}
	
	public Cese traerCese(String texto, int activo) throws Exception{
		Cese c= dao.traerCese( texto,  activo);  
		
		return c;
	 
		//Misma opinion que en el Cese anterior. ¿El problema de Cese == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(String texto, int incluir) throws Exception { 
		
		//Consultar si existe un Cese con el mismo dni, si existe arrojar la Excepcion:
		
			Cese cnueva;
			
			cnueva= new Cese(texto, incluir);
			return dao.agregar(cnueva); 
			
			//A diferencia de los dos Ceses anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(Cese c) throws Exception {
		// Implementar antes de actualizar que no exista un Cese con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		Cese d = dao.traerCese(c.getIdCese());
		if (d != null){					
			if (d.getIdCese() != c.getIdCese()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es él mismo. 
				throw new Exception ("Ya existe un Cese con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... ¿Por qué preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idCese) throws Exception {
		
		/* En este caso es una baja física. En gral, no se se aplicaría este caso de uso. 
		 * Si se hiciera, habría que validar que el Cese no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		Cese c= dao.traerCese(idCese); 
		if (c == null){
			throw new Exception ("El Cese es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<Cese> traerCese(){
		return dao.traerCese(); 
	} 
	
	
	
	
	public List<Cese> traerCesesActivos(){
		return dao.traerCesesActivos(); 
	} 
	
	





	
		
	
}
