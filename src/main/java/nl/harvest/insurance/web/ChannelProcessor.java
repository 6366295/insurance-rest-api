package nl.harvest.insurance.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import nl.harvest.insurance.api.Responder;
import nl.harvest.insurance.api.ResponderMap;

public class ChannelProcessor {

    ResponderMap responderMap = new ResponderMap();

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

        // Respond depending on the request path
        Responder response = responderMap.get(httpRequest.getPath());

        // System.out.println(httpRequest.getPath());

        // Respond depending on request method
        if (response != null && httpRequest.getMethod().equals("GET")) {
            return response.getMethod(httpRequest);
        } else if (response != null && httpRequest.getMethod().equals("POST")) {
            return response.postMethod(httpRequest);
        } else {
            HTTPResponse notFound = new HTTPResponse();

            notFound.setCode(404);
            notFound.setBody("{\"errors\": [{\"code\" : \"404\", \"message\" : \"Not Found\"}]}");

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

        while (writeBuffer.hasRemaining()) {
            this.socketChannel.write(writeBuffer);
        }

        writeBuffer.clear();

    }

}
