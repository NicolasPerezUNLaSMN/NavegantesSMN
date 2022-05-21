package dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import funciones.FuncionesNico;
import modelo.Fenomeno;

public class FenomenoDao {
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
	
	public int agregar(Fenomeno objeto) {		
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

	public void actualizar(Fenomeno objeto) throws HibernateException {
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
	
	public void eliminar(Fenomeno objeto) throws HibernateException {
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
	
	public Fenomeno traerFenomeno(int idFenomeno) throws HibernateException {
		Fenomeno objeto = null;
		try {
			iniciaOperacion();
			objeto = (Fenomeno) session.get(Fenomeno.class, idFenomeno);		
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;*/  //Estos "catch" comentareados estan puestos en base a una duda explicada en FenomenoABM. 
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	
	public Fenomeno traerFenomeno(GregorianCalendar fecha, int horaUTC) throws HibernateException {
		Fenomeno objeto = null;
		try {
			iniciaOperacion();
			
			objeto = (Fenomeno) session.createQuery("from Fenomeno c where c.fecha = " +FuncionesNico.traerFechaCortaParaHibernate(fecha)  +"and c.horaUTC = "   +horaUTC ).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
		}
		return objeto;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Fenomeno> traerFenomeno() throws HibernateException {
		List<Fenomeno> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Fenomeno").list();
	} finally {
		session.close();
	}
	return lista;	
	}
	
	
	
	
	
	

	
		
		
} 
