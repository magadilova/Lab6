package module;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ConnectionModule {
    SocketChannel clientSocketChannel;
    ServerSocketChannel server;
    int port;

    public ConnectionModule(int port) throws IOException {
        this.port = port;
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(port));
    }

    public SocketChannel getClientSocketChannel() {
        return clientSocketChannel;
    }

    public void connect() throws IOException {
        clientSocketChannel = server.accept();
    }
}
