package negocio;

import java.util.GregorianCalendar;
import java.util.List;	
import dao.BoletinEmitidoDao;				
import modelo.BoletinEmitido;
import modelo.Boletin;

public class BoletinEmitidoABM {				
	BoletinEmitidoDao dao=new BoletinEmitidoDao();
	

	
	public BoletinEmitido traerBoletinEmitido(int dni) throws Exception{
		BoletinEmitido c= dao.traerBoletinEmitido(dni);  
		
		return c;
	 
		//Misma opinion que en el Boletin anterior. El problema de BoletinEmitido == null no deberia identificarse en la capa de acceso a datos? 
	} 
	
	public int agregar(  String texto, String tipo, Boletin boletin) throws Exception { 
		
		//Consultar si existe un BoletinEmitido con el mismo dni, si existe arrojar la Excepcion:
		
			BoletinEmitido c;
			
			c= new BoletinEmitido(  texto, tipo, boletin);
			return dao.agregar(c); 
			
			//A diferencia de los dos Boletins anteriores, Hibernate en este caso podria NO ARROJAR una excepcion si el DNI se repitiese debido a que el DNI no es nuestra clave primaria. 
			//No hay una restriccion impuesta en la base de datos como sucede con el id. Aqui si necesitariamos declarar una excepcion. 
	} 
	
	
	public void modificar(BoletinEmitido c) throws Exception {
		// Implementar antes de actualizar que no exista un BoletinEmitido con el mismo documento a modificar y con el mismo id, lanzar la excepcion:
		
		BoletinEmitido d = dao.traerBoletinEmitido(c.getIdBoletinEmitido());
		if (d != null){					
			if (d.getIdBoletinEmitido() != c.getIdBoletinEmitido()){ //Si son distintos id quiere decir que son distintos registros. Por ende, otro registro ya tiene ese DNI. Si son el mismo id, existe un registro con el mismo DNI pero es �l mismo. 
				throw new Exception ("Ya existe un BoletinEmitido con este DNI");
			}
		}
		//Que podamos modificar un DNI es valido pero... �Por qu� preguntar si se va a repetir el ID? -> El id de un objeto no puede modificarse. Hay una restriccion.
		//Nunca tendria que poder llegar a modificarse desde memoria principal (desde la aplicacion). 
		dao.actualizar(c);
			
	}	
	
	public void eliminar(int idBoletinEmitido) throws Exception {
		
		/* En este caso es una baja f�sica. En gral, no se se aplicar�a este caso de uso. 
		 * Si se hiciera, habr�a que validar que el BoletinEmitido no tenga dependencias*/
		
		//Implementar que si es null arroje una excepcion 
		BoletinEmitido c= dao.traerBoletinEmitido(idBoletinEmitido); 
		if (c == null){
			throw new Exception ("El BoletinEmitido es nulo");		
		}
		
		
		dao.eliminar(c); 			
	}
	
	public List<BoletinEmitido> traerBoletinEmitido(){
		return dao.traerBoletinEmitido(); 
	} 
	
	


	
	
		
	
}
