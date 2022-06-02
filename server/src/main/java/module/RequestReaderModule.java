package module;

import interaction.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class RequestReaderModule {
    private final ObjectInputStream reader;

    public RequestReaderModule(InputStream input) throws IOException {
        this.reader = new ObjectInputStream(input);
    }

    public Request<?> readRequest() throws IOException, ClassNotFoundException {
        return (Request<?>) reader.readObject();
    }

    public void close() throws IOException {
        reader.close();
    }
}
