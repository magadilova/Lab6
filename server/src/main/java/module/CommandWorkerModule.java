package module;

import interaction.Request;
import interaction.Response;
import manager.CommandManager;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class CommandWorkerModule implements Runnable {
    private static RequestReaderModule reader;
    private static ResponseSenderModule writer;
    private static SocketChannel clientSocket;
    private CommandManager commandManager;

    public CommandWorkerModule(SocketChannel clientSocket, CommandManager commandManager) {
        CommandWorkerModule.clientSocket = clientSocket;
        this.commandManager = commandManager;
    }

    public void setup() {
        try {
            writer = new ResponseSenderModule(clientSocket.socket().getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            reader = new RequestReaderModule(clientSocket.socket().getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleRequest() {
        Request request = null;
        try {
            request = reader.readRequest();
        } catch (IOException | ClassNotFoundException e) {
            close();
        }
        if (request == null) {
            close();
            return;
        }
        try {
            if (request.getCommand().equals("exit")) {
                close();
                System.out.println("Client closed");
                return;
            }
            Response<?> res = commandManager.executeCommand(request);
            writer.sendResponse(res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setup();
        while (!clientSocket.socket().isClosed()) {
            handleRequest();
        }
        close();
    }
}