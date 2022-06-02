package modules;

import client.Client;
import interaction.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

public class RequestWriterModule {
    ObjectOutputStream writer;


    public RequestWriterModule(ObjectOutputStream outputStream) {
        this.writer = outputStream;
    }

    public void sendRequest(Request<?> request) throws IOException {
        try {
            writer.writeObject(request);
            writer.flush();
        }catch (SocketException e){
            Client.waitConnection();
            sendRequest(request);
        }
        catch (IOException e) {
//            Client.waitConnection();
//            sendRequest(request);
        }
    }

    public void sendObject(Object object) throws IOException {
        writer.writeObject(object);
        writer.flush();
    }

    public void sendUTF(String message) throws IOException {
        writer.writeUTF(message);
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }

    public void setWriter(OutputStream outputStream) throws IOException {
        this.writer = new ObjectOutputStream(outputStream);
    }
}

