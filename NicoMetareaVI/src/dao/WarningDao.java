package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.Warning;

public class WarningDao {
	private static Session session = null;
	private Transaction tx = null;
	
	private void iniciaOperacion() throws HibernateException {
		
		session = HibernateUtil.getSessionFactory().openSession(); 
		
		//Crear una sesion 
		tx = session.beginTransaction(); //Crea una transaccion 
	
		
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();                                       
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Warning objeto) {		
		int id = 0;
		try {
			iniciaOperacion(); //Crea la sesion y la transaccion 
			id = Integer.parseInt(session.save(objeto).toString()); //Agregamos el objeto 
			tx.commit(); //Y lo guardo tambien en la base de datos. 
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close(); //Cerramos la sesion 
			
			
		}
		return id;
	}

	public void actualizar(Warning objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();	
			
	
		}
	}	
	
	public void eliminar(Warning objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto); 
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		
	
		}
	}
	

	public Warning traerWarning(int dni) throws HibernateException {
		Warning objeto = null;
		try {
			iniciaOperacion();
			objeto = (Warning) session.createQuery("from Warning c where c.idWarning="+dni).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
			
			
		}
		return objeto;	
	}
	
	
	public int maximoNumeroWarning() throws HibernateException {
		int maximo = 0;
		for(Warning w: traerWarning()) {
			if(w.getNumeroWarning()>=maximo) {
				maximo = w.getNumeroWarning();
			}
		}
		
		return maximo;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Warning> traerWarning() throws HibernateException {
		List<Warning> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Warning c  order by c.areas asc c.fenomeno asc").list();
	} finally {
		session.close();
		
	
	}
	return lista;	
	
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Warning> traerWarningActivos() throws HibernateException {
		List<Warning> lista=null;
		
		
		
		try {
			iniciaOperacion();
			lista=session.createQuery("from Warning c where c.activo = 1 order by c.areas asc c.fenomeno asc").list();
	} finally {
		session.close();
	

	}
	return lista;	
	
	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Warning> traerWarningTodos() throws HibernateException {
		List<Warning> lista=null;
		
		
		
		try {
			iniciaOperacion();
			lista=session.createQuery("from Warning c order by c.areas asc c.fenomeno asc").list();
	} finally {
		session.close();
	

	}
	return lista;	
	
	
	}
	
	

		
} 
