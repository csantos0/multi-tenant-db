package br.com.synchro.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author cvs
 * @create Jan 16, 2015
 * 
 *         Default Hibernate utility class to instantiate a SessionFactory object and provide a Session object
 */
public class HibernateUtil {

    private static SessionFactory factory;

    /**
     * Hibernate setup
     */
    static {
	final Configuration configuration = new Configuration().configure();
	final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration
		.getProperties());
	factory = configuration.buildSessionFactory(builder.build());
    }

    /**
     * Open a new session based on the SessionFactory object
     * 
     * @return session opened
     */
    public static Session getSession() {
	return factory.openSession();
    }

}
