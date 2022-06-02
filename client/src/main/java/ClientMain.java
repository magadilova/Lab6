import client.Client;

public class ClientMain {
    public static void main(String[] args)  {
        Client client = new Client(args[0], Integer.parseInt(args[1]));
        if (client.connect()) {
            Client.setup();
            client.run();
            Client.close();
        }

    }
}
