package nl.harvest.insurance.database;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    public static SessionFactory factory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {

        if (factory == null) {
            createSessionFactory();

        }

        return factory;

    }

    public static void createSessionFactory() {

        if (factory == null) {
            try {
                // Loads configuration from default path: resources/hibernate.cfg.xml
                factory = new Configuration().configure().buildSessionFactory();

            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);

                throw new ExceptionInInitializerError(e);

            }

        }

    }

}
