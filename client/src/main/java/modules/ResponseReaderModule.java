package modules;

import interaction.Response;

import java.io.*;

public class ResponseReaderModule {
    ObjectInputStream reader;


    public ResponseReaderModule(ObjectInputStream inputStream) {
        this.reader = inputStream;
    }

    public Response<?> readResponse() throws IOException, ClassNotFoundException {
        return (Response<?>) reader.readObject();
    }

    public String readUTF() throws IOException {
        return reader.readUTF();
    }

    public void close() throws IOException {
        reader.close();
    }

    public void setReader(InputStream inputStream) throws IOException {
        this.reader = new ObjectInputStream(inputStream);
    }

}



