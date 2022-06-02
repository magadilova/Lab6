import manager.CommandManager;
import module.CommandWorkerModule;
import module.ConnectionModule;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    ConnectionModule connectionModule;
    private ExecutorService threadPool;
    private CommandManager commandManager;

    public Server(int port, CommandManager commandManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.threadPool = Executors.newFixedThreadPool(1);
    }

    public boolean start() {
        try {
            this.connectionModule = new ConnectionModule(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started on PORT: "  + port );
        return true;
    }

    public void connect() {
        while (true) {
            try {
                connectionModule.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadPool.execute(new CommandWorkerModule(connectionModule.getClientSocketChannel(), commandManager));
        }
    }
//

}