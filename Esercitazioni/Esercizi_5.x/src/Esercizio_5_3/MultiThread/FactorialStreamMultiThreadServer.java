package Esercizio_5_3.MultiThread;

import Esercizio_5_3.SingleThread.ProtocolHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamMultiThreadServer {
    ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        FactorialStreamMultiThreadServer server = new FactorialStreamMultiThreadServer(6789);
        server.start();
    }

    public FactorialStreamMultiThreadServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    private void start() throws IOException {
        while (true){
            Socket acceptSocket = this.serverSocket.accept();
            ProtocolHandler handler = new FactorialStreamMultiThreadServerHandler(acceptSocket);
            handler.handle();
        }
    }

}
