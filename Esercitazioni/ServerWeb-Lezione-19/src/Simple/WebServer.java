package Simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Project ServerWeb-Lezione-19
 * @AUTHOR giulianoranauro on 03/02/22
 */

public class WebServer {
    private static ServerSocket welcomeSocket;

    public static void main(String[] args) {
        try {
            WebServer server = new WebServer();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error during server launch.");
        }
    }
    public WebServer() throws IOException {
        welcomeSocket = new ServerSocket(Constants.PORT);
    }

    public void start() throws IOException {
        while (true){
            Socket connectionSocket = welcomeSocket.accept();   // si comunica in TCP con HTTP
            try {
                ProtocolHandler handler = new HTTPHandler(connectionSocket);
                handler.handle();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("la richiesta del client " + welcomeSocket.getInetAddress() + " non può essere elaborata.");
            }
        }
    }
}
