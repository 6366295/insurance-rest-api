package nl.harvest.insurance.web;

/**
 * Server using non-blocking IO pipelines
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.Iterator;
import java.util.logging.Logger;

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private InetSocketAddress socketAddress = null;
    private String host = null;
    private int port = 0;

    public Server(String host, int port) {

        this.host = host;
        this.port = port;

        socketAddress = new InetSocketAddress(host, port);

    }

    public void start() {

        try (ServerSocketChannel serverSocket = ServerSocketChannel.open();
            Selector selector = Selector.open()) {

            serverSocket.bind(socketAddress);
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            logger.info("Started server: " + host + ':' + port);

            // Keep server running
            while (true) {
                // Select channels who are ready for IO operations
                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();

                // Iterate over the IO-ready channels
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // Accept incoming connection
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocket.accept();

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        logger.info("Accepted: " + socketChannel);
                    }

                    // Read incoming data
                    if (key.isReadable()) {
                        try  (SocketChannel socketChannel = (SocketChannel) key.channel()) {
                            // Process incoming data
                            ChannelProcessor channelProcessor = new ChannelProcessor();

                            channelProcessor.process(socketChannel);

                            // Close connection
                            logger.info("Closing: " + socketChannel);

                            // socketChannel.close();
                        }

                    }

                    // Done with current IO-channel
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            logger.warning(e.toString());
        }

    }

}
