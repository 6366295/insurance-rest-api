package nl.harvest.insurance;

import nl.harvest.insurance.web.Server;

public class Main {

    public static void main(String[] args) {

        Server server = new Server("localhost", 8080);

        server.start();
    }

}
