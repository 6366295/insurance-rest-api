package nl.harvest.insurance;

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

    private Selector selector = null;
    private ServerSocketChannel serverSocket = null;

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public Server(String host, int port) {

        this.host = host;
        this.port = port;

        socketAddress = new InetSocketAddress(host, port);
    }

    public void start() {

        try {
            registerServer();

            while(true) {
                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();

                while(iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // Check for channels looking to connect
                    if(key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocket.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        logger.info("Accepted: " + socketChannel);
                    }

                    // Check for channels that are readable, response immediately
                    if(key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        // TODO: check for possible incomplete messages
                        socketChannel.read(buffer);

                        // TODO: process HTTP request
                        System.out.println(new String(buffer.array()).trim());

                        buffer.clear();
                        buffer.put(new byte[1024]);
                        buffer.clear();

                        // TODO: response based on request
                        responseMessage(socketChannel);

                        logger.info("Closing: " + socketChannel);

                        socketChannel.close();
                    }

                    iterator.remove();
                }
            }
        } catch(IOException e) {
            logger.warning(e.toString());
        }

    }

    private void registerServer() throws IOException {

        selector = Selector.open();

        serverSocket = ServerSocketChannel.open();

        serverSocket.bind(socketAddress);
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        logger.info("Starting server: " + host + ':' + port);
    }

    // TODO: response based on request
    private void responseMessage(SocketChannel socketChannel) throws IOException {

        String temp = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Type: text/html\r\n\r\nHello World!\n";

        buffer.put(temp.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
    }

}
