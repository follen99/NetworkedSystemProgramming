package Esercizio_4_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ServerStream {
    private ServerSocket welcomeSocket;
    public static void main(String[] args) throws IOException {
        ServerStream serverSocket = new ServerStream(6789);
        serverSocket.start();
    }

    public ServerStream(int port) throws IOException {
        this.welcomeSocket = new ServerSocket(port);
    }
    
    private void start() throws IOException {
        while (true){
            Socket serverSocket = this.welcomeSocket.accept();
            ProtocolHandler handler = new ServerHandler(serverSocket);

            handler.handle();

            serverSocket.close();
        }
    }
}
