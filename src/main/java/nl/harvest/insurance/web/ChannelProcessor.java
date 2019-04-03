package nl.harvest.insurance.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;


import nl.harvest.insurance.api.Responder;
import nl.harvest.insurance.api.CustomersResponder;

public class ChannelProcessor {

    private Map<String, Responder> responseMap = Map.of(
        "/customers", new CustomersResponder()
        // "/products", new ProductsResponder(),
        // "/orders", new OrderResponder()
    );

    private SocketChannel socketChannel = null;
    private ByteBuffer readBuffer = null;

    public ChannelProcessor() {

        this.readBuffer = ByteBuffer.allocate(1024);
    }

    public void process(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;

        HTTPRequest httpRequest = readChannel();
        HTTPResponse httpResponse = generateResponse(httpRequest);

        writeChannel(httpResponse.toString());

        this.socketChannel = null;
    }

    private HTTPResponse generateResponse(HTTPRequest httpRequest) {

        Responder response = responseMap.get(httpRequest.getPath());

        System.out.println(httpRequest.getPath());

        if(response != null && httpRequest.getMethod().equals("GET")) {
            return response.getMethod(httpRequest);

        } else if(response != null && httpRequest.getMethod().equals("POST")) {
            return response.postMethod(httpRequest);

        } else {
            HTTPResponse notFound = new HTTPResponse();

            notFound.setProtocol("HTTP/1.1");
            notFound.setCode(404);
            notFound.setBody("API NOT FOUND");

            return notFound;

        }

    }

    private HTTPRequest readChannel() throws IOException {

        String request = null;
        int bytesRead = 0;

        bytesRead = this.socketChannel.read(readBuffer);

        request = new String(readBuffer.array()).substring(0, bytesRead);

        readBuffer.clear();

        return new HTTPRequest(request);

    }

    private void writeChannel(String response) throws IOException {

        ByteBuffer writeBuffer = ByteBuffer.allocate(response.getBytes().length);

        writeBuffer.put(response.getBytes());
        writeBuffer.flip();

        while(writeBuffer.hasRemaining()) {
            this.socketChannel.write(writeBuffer);
        }

        writeBuffer.clear();

    }

}
