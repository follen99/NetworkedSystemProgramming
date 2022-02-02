package Esercizio_6_2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Writer extends Thread {
    private Socket socket;

    public Writer(Socket socket){
        this.socket = socket;
    }

    // porzione di codice da eseguire (corpo del thread)
    public void run(){
        Scanner inFromUser = new Scanner(System.in);
        try {
            PrintStream outToPeer = new PrintStream(this.socket.getOutputStream()); // preparo lo stream di output

            String str = null;
            do {
                System.out.println("Tu: ");
                str = inFromUser.nextLine();
                outToPeer.println(str);
            }while (!str.endsWith("."));    // finchè non si chiude definitivamente la conversazione

            System.out.println("Hai terminato la conversazione.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred in the writer; closing socket.");
        } finally {
            // non possiamo chiudere la socket perchè il reader potrebbe ancora usarla

            try {
                this.socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("couldn't close the socket.");
            }
        }


    }
}
