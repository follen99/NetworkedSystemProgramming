package Esercizio_6_2;

import java.io.IOException;
import java.net.Socket;

public class ChatFullDuplexStreamClient {
    private Socket clientSocket;
    public static void main(String[] args) {
        try {
            ChatFullDuplexStreamClient client = new ChatFullDuplexStreamClient("127.0.0.1", 6789);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("couldn't start client.");
        }
    }

    public ChatFullDuplexStreamClient(String address, int port) throws IOException {
        this.clientSocket = new Socket(address, port);
    }

    public void start() throws IOException {
        ProtocolHandler handler = new CommonChatFullDuplexHandler(clientSocket);    // usiamo lo stesso handler per client e server!
        handler.handle();
    }
}
