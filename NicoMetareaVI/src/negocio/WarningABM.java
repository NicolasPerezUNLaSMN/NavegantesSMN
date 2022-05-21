package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.WarningDao;				
import modelo.Warning;
import modelo.Boletin;

public class WarningABM {				
	WarningDao dao=new WarningDao();
	

	
	public Warning traerWarning(int dni) throws Exception{
		Warning c= dao.traerWarning(dni);  
		
		return c;
	 
		//Misma opinion que en el Boletin anterior. �El problema de Warning == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(int numeroWarning, int actualizacion, GregorianCalendar fecha, String areas, String fenomeno, String texto, String tipo, String dominio, int activo, Boletin boletin) throws Exception { 
		
		//Consultar si existe un Warning con el mismo dni, si existe arrojar la Excepcion:
		
			Warning c;
			
			c= new Warning(numeroWarning, actualizacion,fecha,  areas,  fenomeno,  texto, tipo, dominio, activo, boletin);
			return dao.agregar(c); 
			
			//A diferencia de los dos Boletins anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(Warning c) throws Exception {
		// Implementar antes de actualizar que no exista un Warning con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		Warning d = dao.traerWarning(c.getIdWarning());
		if (d != null){					
			if (d.getIdWarning() != c.getIdWarning()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es �l mismo. 
				throw new Exception ("Ya existe un Warning con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... �Por qu� preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idWarning) throws Exception {
		
		/* En este caso es una baja f�sica. En gral, no se se aplicar�a este caso de uso. 
		 * Si se hiciera, habr�a que validar que el Warning no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		Warning c= dao.traerWarning(idWarning); 
		if (c == null){
			throw new Exception ("El Warning es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<Warning> traerWarning(){
		return dao.traerWarning(); 
	} 
	
	
	public List<Warning> traerWarningActivos(){
		return dao.traerWarningActivos(); 
	} 
	
	public List<Warning> traerWarningTodos(){
		return dao.traerWarningTodos(); 
	} 
	
	public int maximoNumeroWarning() {
		return dao.maximoNumeroWarning();
	}
	
	
		
	
}
