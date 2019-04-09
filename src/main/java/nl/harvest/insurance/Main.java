package nl.harvest.insurance;

import nl.harvest.insurance.web.Server;

import org.hibernate.Session;
import org.hibernate.Transaction;

import nl.harvest.insurance.data.Customer;
import nl.harvest.insurance.data.Product;
import nl.harvest.insurance.data.Application;
import nl.harvest.insurance.data.HibernateUtil;

public class Main {

    public static void main(String[] args) {

        HibernateUtil.createSessionFactory();

        Session session;
        Transaction tx;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Session created");

        tx = session.beginTransaction();

        Customer customer = new Customer();

        Product product1 = new Product();
        Product product2 = new Product();

        Application application = new Application(customer, product1);


        session.save(customer);
        session.save(product1);
        session.save(product2);

        session.save(application);

        tx.commit();

        Server server = new Server("localhost", 8080);

        server.start();

    }

}
