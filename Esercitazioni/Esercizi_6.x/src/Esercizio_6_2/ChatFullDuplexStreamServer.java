package Esercizio_6_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatFullDuplexStreamServer {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            ChatFullDuplexStreamServer server = new ChatFullDuplexStreamServer(6789);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't start server");
        }
    }

    public ChatFullDuplexStreamServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    private void start() throws IOException {
        Socket connectionSocket = serverSocket.accept();// non dimenticare di accettare la connessione dalla welcome socket!

        ProtocolHandler handler = new CommonChatFullDuplexHandler(connectionSocket);    // usiamo lo stesso handler per client e server!
        handler.handle();
    }
}
