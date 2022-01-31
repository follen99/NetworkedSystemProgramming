package Esercizio_4_3;

import java.io.IOException;
import java.net.Socket;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ClientStream {
    private Socket clientSocket;
    public static void main(String[] args) throws IOException {
        ClientStream cStream = new ClientStream("127.0.0.1", 6789);
        cStream.start();
    }

    public ClientStream(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
    }

    private void start() throws IOException {
        ProtocolHandler handler = new ClientHandler(clientSocket);
        handler.handle();
    }
}
