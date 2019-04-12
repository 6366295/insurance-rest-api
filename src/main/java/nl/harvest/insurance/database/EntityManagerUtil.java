package nl.harvest.insurance.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory efactory;

    private EntityManagerUtil() {
    }

    public static EntityManager getEntityManager() {

        return efactory.createEntityManager();

    }

    public static void createEntityManagerFactory() {

        if (efactory == null) {
            efactory = Persistence.createEntityManagerFactory("nl.harvest.insurance.database.entitymanager");

        }

    }

}
