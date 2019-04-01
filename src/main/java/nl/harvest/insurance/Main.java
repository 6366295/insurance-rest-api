package nl.harvest.insurance;

import java.nio.channels.Selector;

public class Main {

    public static void main(String[] args) {

        Server server = new Server("localhost", 8080);

        server.start();
    }

}
