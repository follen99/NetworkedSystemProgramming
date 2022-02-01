package Esercizio_5_3.SingleThread;

import java.io.IOException;
import java.net.Socket;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamClient {
    private Socket clientSocket;

    public static void main(String[] args) throws IOException {
        FactorialStreamClient client = new FactorialStreamClient("127.0.0.1", 6789);
        client.start();
    }

    public FactorialStreamClient(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
    }

    private void start() throws IOException {
        ProtocolHandler handler = new FactorialStreamConcurrentHandler(clientSocket);
        handler.handle();
    }
}
