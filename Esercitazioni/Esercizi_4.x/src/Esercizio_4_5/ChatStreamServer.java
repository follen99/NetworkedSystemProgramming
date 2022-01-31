package Esercizio_4_5;

import Esercizio_4_3.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ChatStreamServer {
    private ServerSocket welcomeSocket;
    public static void main(String[] args) throws IOException {
        ChatStreamServer streamServer = new ChatStreamServer(6789);
        streamServer.start();
    }

    public ChatStreamServer(int port) throws IOException {
        this.welcomeSocket = new ServerSocket(port);
    }

    private void start() throws IOException {
        while (true){
            Socket serverSocket = this.welcomeSocket.accept();
            ProtocolHandler handler = new ChatStreamServerHandler(serverSocket);
            handler.handle();

            serverSocket.close();
        }
    }
}
