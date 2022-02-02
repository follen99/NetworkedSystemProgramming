package Esercizio_6_2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Reader extends Thread{
    private Socket socket;

    public Reader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner inFromPeer = null;
        PrintStream outToUser = null;
        try {
            inFromPeer = new Scanner(socket.getInputStream());
            outToUser = new PrintStream(System.out);    // non necessario

            String str = null;

            do {
                str = inFromPeer.nextLine();
                outToUser.println("Peer: " + str);
            }while (!str.endsWith("."));

            outToUser.println("Il peer ha terminato la connessione.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Something went wrong with the reader; closing socket");
        } finally {
            try {
                socket.shutdownInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (outToUser!=null) outToUser.close();
        }
    }
}
