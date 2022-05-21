package dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;	

public class HibernateUtil2{
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		
	
	
	sessionFactory = null;
		
	try{
		if (sessionFactory == null) {
			
			
			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
			
			
			


		}
	} catch (HibernateException he) { System.err.println("ERROR en la inicializacion de la SessionFactory: " + he);
	  throw new ExceptionInInitializerError(he);
	}
	
	
	return sessionFactory;
  }	
}
  