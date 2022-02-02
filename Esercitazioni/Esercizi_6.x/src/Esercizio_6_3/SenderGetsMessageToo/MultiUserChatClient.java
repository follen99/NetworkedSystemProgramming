package Esercizio_6_3.SenderGetsMessageToo;

import Esercizio_6_2.ProtocolHandler;

import java.io.IOException;
import java.net.Socket;

public class MultiUserChatClient {
    private Socket clientSocket;

    public MultiUserChatClient(String s, int i) throws IOException {
        this.clientSocket = new Socket(s, i);

    }

    public static void main(String[] args) {
        try {
            MultiUserChatClient client = new MultiUserChatClient("127.0.0.1", 6789);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't create main socket.");
        }
    }

    private void start() throws IOException {
        // l'handler è lo stesso dell'esercizio 6.2
        ProtocolHandler handler = new MultiUserChatClientHandler(this.clientSocket);
        handler.handle();
    }

}
