package nl.harvest.insurance.api;

/**
 * Response for API regarding customer data
 */

 import org.hibernate.Session;
 import org.hibernate.Transaction;

 import nl.harvest.insurance.database.Customer;
 import nl.harvest.insurance.database.Product;
 import nl.harvest.insurance.database.Application;
 import nl.harvest.insurance.database.HibernateUtil;
import nl.harvest.insurance.web.HTTPResponse;
import nl.harvest.insurance.web.HTTPRequest;

public class CustomersResponder implements Responder {

    @Override
    public HTTPResponse getMethod(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();

        httpResponse.setBody("Hello Customers!");

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

        return httpResponse;
    }

    @Override
    public HTTPResponse postMethod(HTTPRequest httpRequest) {
        return new HTTPResponse();
    }

}
