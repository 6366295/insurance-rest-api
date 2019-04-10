package nl.harvest.insurance;

import nl.harvest.insurance.database.HibernateUtil;
import nl.harvest.insurance.web.Server;

public class Main {

    public static void main(String[] args) {

        // Create singleton SessionFactory
        HibernateUtil.createSessionFactory();

        Server server = new Server("localhost", 8080);

        server.start();

    }

}
