package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modelo.BoletinEmitido;

public class BoletinEmitidoDao {
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
	
	public int agregar(BoletinEmitido objeto) {		
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

	public void actualizar(BoletinEmitido objeto) throws HibernateException {
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
	
	public void eliminar(BoletinEmitido objeto) throws HibernateException {
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
	

	public BoletinEmitido traerBoletinEmitido(int dni) throws HibernateException {
		BoletinEmitido objeto = null;
		try {
			iniciaOperacion();
			objeto = (BoletinEmitido) session.createQuery("from BoletinEmitido c where c.idBoletinEmitido="+dni).uniqueResult();
		/*} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he; */
		} finally {
			session.close(); 
		}
		return objeto;	
	}
	
	

	public List<BoletinEmitido> traerBoletinEmitido() throws HibernateException {
		List<BoletinEmitido> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from BoletinEmitido c  order by c.tipo asc c.texto asc").list();
	} finally {
		session.close();
	}
	return lista;	
	
	
	}
	
	

	

		
} 
