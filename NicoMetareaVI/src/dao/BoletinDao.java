package dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import funciones.FuncionesNico;
import modelo.Boletin;

public class BoletinDao {
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
	
	public int agregar(Boletin objeto) {		
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

	public void actualizar(Boletin objeto) throws HibernateException {
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
	
	public void eliminar(Boletin objeto) throws HibernateException {
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
	
	public Boletin traerBoletin(int idBoletin) throws HibernateException {
		Boletin objeto = null;
		try {
			iniciaOperacion();
			objeto = (Boletin) session.get(Boletin.class, idBoletin);		
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;*/  //Estos "catch" comentareados estan puestos en base a una duda explicada en BoletinABM. 
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	
	public Boletin traerBoletin(GregorianCalendar fecha, int horaUTC) throws HibernateException {
		Boletin objeto = null;
		try {
			iniciaOperacion();
			
			objeto = (Boletin) session.createQuery("from Boletin c where c.fecha = " +FuncionesNico.traerFechaCortaParaHibernate(fecha)  +"and c.horaUTC = "   +horaUTC ).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
		}
		return objeto;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Boletin> traerBoletin() throws HibernateException {
		List<Boletin> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Boletin").list();
	} finally {
		session.close();
	}
	return lista;	
	}
	
	
	
	
	public Boletin traerBoletinYListaWarning(int idBoletin) throws HibernateException {
		Boletin objeto = null;
		try {
			iniciaOperacion();
			String hql="from Boletin c where c.idBoletin ="+idBoletin;
			objeto=(Boletin) session.createQuery(hql).uniqueResult();
			Hibernate.initialize(objeto.getListaWarning());
		}
		finally {
			session.close();
		}
		return objeto;
	}
	

	
		
		
} 
