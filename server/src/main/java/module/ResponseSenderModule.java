package module;

import interaction.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ResponseSenderModule {
    private final ObjectOutputStream writer;

    public ResponseSenderModule(OutputStream writer) throws IOException {
        this.writer = new ObjectOutputStream(writer);
    }

    public void sendResponse(Response<?> response) throws IOException {
        writer.writeObject(response);
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
