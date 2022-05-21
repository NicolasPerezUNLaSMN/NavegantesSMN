package dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import funciones.FuncionesNico;
import modelo.Hielo;

public class HieloDao {
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
	
	public int agregar(Hielo objeto) {		
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

	public void actualizar(Hielo objeto) throws HibernateException {
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
	
	public void eliminar(Hielo objeto) throws HibernateException {
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
	
	public Hielo traerHielo(int idHielo) throws HibernateException {
		Hielo objeto = null;
		try {
			iniciaOperacion();
			objeto = (Hielo) session.get(Hielo.class, idHielo);		
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;*/  //Estos "catch" comentareados estan puestos en base a una duda explicada en HieloABM. 
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	
	public Hielo traerHielo(GregorianCalendar fecha, int horaUTC) throws HibernateException {
		Hielo objeto = null;
		try {
			iniciaOperacion();
			
			objeto = (Hielo) session.createQuery("from Hielo c where c.fecha = " +FuncionesNico.traerFechaCortaParaHibernate(fecha)  +"and c.horaUTC = "   +horaUTC ).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
		}
		return objeto;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Hielo> traerHielo() throws HibernateException {
		List<Hielo> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Hielo").list();
	} finally {
		session.close();
	}
	return lista;	
	}
	
	
	
	
	
	

	
		
		
} 
