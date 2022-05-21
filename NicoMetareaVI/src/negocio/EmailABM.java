package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.EmailDao;

import modelo.Email;

public class EmailABM {				
	EmailDao dao=new EmailDao();
	
	public Email traerEmail(int idEmail) throws Exception{	
		Email c= dao.traerEmail(idEmail); 
		if (c == null){
			throw new Exception ("Email nulo");				
		}
		return c; 
		
		/*¿Las excepciones no deben estar en EmailDao como excepciones Hibernate? -> El acceso a los datos surge ahí. Hibernate deberia lanzar un error al no encontrar el id en la base de datos.
		 * Poner la excepcion acá luego de traer el Email asume que Hibernate puede traer un Email = null cuando no encuentra un "id" en la base de datos.
		 */
	}
	
	public Email traerEmail(GregorianCalendar fecha, int horaUTC) throws Exception{
		Email c= dao.traerEmail( fecha,  horaUTC);  
		
		return c;
	 
		//Misma opinion que en el Email anterior. ¿El problema de Email == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(String email) throws Exception { 
		
		//Consultar si existe un Email con el mismo dni, si existe arrojar la Excepcion:
		
			Email cnueva;
			
			cnueva= new Email(email);
			return dao.agregar(cnueva); 
			
			//A diferencia de los dos Emails anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(Email c) throws Exception {
		// Implementar antes de actualizar que no exista un Email con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		Email d = dao.traerEmail(c.getIdEmail());
		if (d != null){					
			if (d.getIdEmail() != c.getIdEmail()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es él mismo. 
				throw new Exception ("Ya existe un Email con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... ¿Por qué preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idEmail) throws Exception {
		
		/* En este caso es una baja física. En gral, no se se aplicaría este caso de uso. 
		 * Si se hiciera, habría que validar que el Email no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		Email c= dao.traerEmail(idEmail); 
		if (c == null){
			throw new Exception ("El Email es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<Email> traerEmail(){
		return dao.traerEmail(); 
	} 
	
	
	





	
		
	
}
