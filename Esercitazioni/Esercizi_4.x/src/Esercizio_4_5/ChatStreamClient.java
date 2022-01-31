package Esercizio_4_5;

import java.io.IOException;
import java.net.Socket;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ChatStreamClient {
    private Socket clientSocket;
    public static void main(String[] args) throws IOException {
        ChatStreamClient streamClient = new ChatStreamClient("127.0.01", 6789);
        streamClient.start();
    }

    public ChatStreamClient(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
    }

    private void start() throws IOException{
        ProtocolHandler handler = new ChatstreamClientHandler(clientSocket);
        handler.handle();
    }
}
