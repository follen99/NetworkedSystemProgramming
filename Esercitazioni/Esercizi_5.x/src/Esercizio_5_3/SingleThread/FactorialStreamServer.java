package Esercizio_5_3.SingleThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamServer {
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        FactorialStreamServer server = new FactorialStreamServer(6789);
        server.start();
    }

    public FactorialStreamServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true){
            Socket acceptSocket = this.serverSocket.accept();
            ProtocolHandler handler = new FactorialStreamConcurrentServer(acceptSocket);
            handler.handle();
        }
    }
}
