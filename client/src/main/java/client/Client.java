package client;

import commands.*;
import managers.ClientCommandManager;
import modules.RequestWriterModule;
import modules.ResponseReaderModule;
import workers.ConsoleWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Client {
    private static int port;
    private static String host;
    private static Socket socket;
    private static RequestWriterModule writer;
    private static ResponseReaderModule reader;


    public Client(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }


    public static void setup() {
        try {
            writer = new RequestWriterModule(new ObjectOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader = new ResponseReaderModule(new ObjectInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void waitConnection() {
        int sec = 0;
        socket = new Socket();
        while (socket.isClosed() || !socket.isConnected()) {
            socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(host, port));
                reader.setReader(socket.getInputStream());
                writer.setWriter(socket.getOutputStream());
                ConsoleWorker.println("Reconnection completed successfully. Continuation of execution.");
                return;
            } catch (IOException e) {
            }
            ConsoleWorker.println("\rConnection error. Waiting for reconnect: " + sec + "/60 seconds");
            sec++;
            if (sec > 60) {
                System.exit(0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }

    public void run() {
        while (!socket.isOutputShutdown()) {
            get();
        }
    }


    public void get() {
        ClientCommandManager commandManager = new ClientCommandManager();
        commandManager.addCommands(new AbstractCommand[]{
                new HelpCommand(writer, reader),
                new InfoCommand(writer, reader),
                new AddCommand(writer, reader, commandManager),
                new UpdateCommand(writer, reader, commandManager),
                new ShowCommand(writer, reader),
                new RemoveGCommand(writer, reader, commandManager),
                new RemoveIDCommand(writer, reader),
                new ExecuteCommand(commandManager),
                new ExitCommand(writer, reader),
                new ClearCommand(writer, reader),
                new FilterLCommand(writer, reader),
                new FilterPNCommand(writer, reader),
                new HistoryCommand(writer, reader),
                new RemoveLCommand(writer, reader, commandManager),
                new RemovePNCommand(writer, reader)
        });
        commandManager.startInteractiveMode();
    }
}