package dao;

import java.util.GregorianCalendar;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import funciones.FuncionesNico;
import modelo.Email;

public class EmailDao {
	private static Session session = null;
	private Transaction tx= null;
	
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession(); 
		//Crear una sesion 
		tx = session.beginTransaction(); //Crea una transaccion 
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();                                       
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Email objeto) {		
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

	public void actualizar(Email objeto) throws HibernateException {
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
	
	public void eliminar(Email objeto) throws HibernateException {
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
	
	public Email traerEmail(int idEmail) throws HibernateException {
		Email objeto = null;
		try {
			iniciaOperacion();
			objeto = (Email) session.get(Email.class, idEmail);		
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;*/  //Estos "catch" comentareados estan puestos en base a una duda explicada en EmailABM. 
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	
	public Email traerEmail(GregorianCalendar fecha, int horaUTC) throws HibernateException {
		Email objeto = null;
		try {
			iniciaOperacion();
			
			objeto = (Email) session.createQuery("from Email c where c.fecha = " +FuncionesNico.traerFechaCortaParaHibernate(fecha)  +"and c.horaUTC = "   +horaUTC ).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
		}
		return objeto;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Email> traerEmail() throws HibernateException {
		List<Email> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Email").list();
	} finally {
		session.close();
	}
	return lista;	
	}
	
	
	
	
	
	

	
		
		
} 
