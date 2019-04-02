package nl.harvest.insurance;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import java.io.IOException;

public class ChannelParser {

    private ByteBuffer buffer;

    public ChannelParser(int bufferSize) {

        buffer = ByteBuffer.allocate(bufferSize);

    }

    public String readChannel(SocketChannel socketChannel) throws IOException {

        String message = null;
        int bytesRead = 0;

        bytesRead = socketChannel.read(buffer);

        message = new String(buffer.array()).substring(0, bytesRead);

        buffer.clear();

        return message;

    }

    public String writeChannel(SocketChannel socketChannel) throws IOException {
    }

}
