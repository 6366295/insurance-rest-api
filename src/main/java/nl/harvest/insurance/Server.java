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

    private ServerSocketChannel serverSocket = null;
    private Selector selector = null;

    private ChannelParser channel = new ChannelParser(1024);

    public Server(String host, int port) {

        this.host = host;
        this.port = port;

        socketAddress = new InetSocketAddress(host, port);
    }

    public void start() {

        try {
            bindServer();

            while(true) {
                // Select channels who are ready for IO operations
                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();

                // Iterate over the IO-ready channels
                while(iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // Accept incoming connections
                    if(key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocket.accept();

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        logger.info("Accepted: " + socketChannel);
                    }

                    // Read incoming data
                    if(key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        String request = channel.readChannel(socketChannel);

                        HTTPRequest requestHeader = new HTTPRequest(request);

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

    private void bindServer() throws IOException {

        selector = Selector.open();

        serverSocket = ServerSocketChannel.open();

        serverSocket.bind(socketAddress);
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        logger.info("Started server: " + host + ':' + port);

    }

    // TODO: response based on request
    private void responseMessage(SocketChannel socketChannel) throws IOException {

        String temp = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Type: text/html\r\n\r\nHello World!\n";

        ByteBuffer buffer2 = ByteBuffer.allocate(temp.getBytes().length);

        buffer2.put(temp.getBytes());
        buffer2.flip();

        while(buffer2.hasRemaining()) {
            int temp2 = socketChannel.write(buffer2);
        }

        buffer2.clear();

    }

}
