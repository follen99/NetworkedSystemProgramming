package Esercizio_6_3.SenderDoesNotGetMessageToo;

import Esercizio_6_2.ProtocolHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiUserChatServer {
    private ServerSocket welcomeSocket;

    public static void main(String[] args) throws IOException {
        MultiUserChatServer server = new MultiUserChatServer(6789);
        server.start();
    }

    private void start(){
        RoomRev1 roomRev1 = new RoomRev1(0); // l'oggetto room viene istanziato solo una volta
        try {
            while (true){
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("New connection accepted: " + connectionSocket.getInetAddress());

                /**
                 * ogni volta che viene creato un handler per servire uno specifico client,
                 * ad ognuno di questi handler viene passato l'oggetto room (condiviso da tutti i client per ora)
                 * */
                ProtocolHandler handler = new MultiUserChatServerHandler(connectionSocket, roomRev1);   //
                handler.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MultiUserChatServer(int port) throws IOException {
        this.welcomeSocket = new ServerSocket(port);
    }
}
