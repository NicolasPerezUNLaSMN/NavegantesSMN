package dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import funciones.FuncionesNico;
import modelo.Cese;
import modelo.Warning;

public class CeseDao {
	private static Session session=null;
	private Transaction tx=null;
	
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession(); 
		//Crear una sesion 
		tx = session.beginTransaction(); //Crea una transaccion 
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();                                       
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Cese objeto) {		
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

	
	
	public void actualizar(Cese objeto) throws HibernateException {
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
	
	public void eliminar(Cese objeto) throws HibernateException {
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
	
	public Cese traerCese(int idCese) throws HibernateException {
		Cese objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cese) session.get(Cese.class, idCese);		
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;*/  //Estos "catch" comentareados estan puestos en base a una duda explicada en CeseABM. 
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	
	public Cese traerCese(String texto, int activo) throws HibernateException {
		Cese objeto = null;
		try {
			iniciaOperacion();
			
			objeto = (Cese) session.createQuery("from Cese c where c.fecha = " +texto  +"and c.horaUTC = "   +activo).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
		}
		return objeto;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Cese> traerCese() throws HibernateException {
		List<Cese> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Cese").list();
	} finally {
		session.close();
	}
	return lista;	
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Cese> traerCesesActivos() throws HibernateException {
		List<Cese> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Cese c where c.activo = 1 order by c.texto asc c.idCese asc").list();
	} finally {
		session.close();
	}
	return lista;	
	
	
	}
	
	
	
	
	
	

	
		
		
} 
