package Esercizio_4_1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 *
 * Scrivere un programma java client-server per la moltiplicazione remota di due interi. Usare la comunicazione orientata allo stream.
 */
public class StreamServer {
    public static void main(String[] args) throws IOException {
        String stringFromClient;
        String capitalizedString;

        ServerSocket serverSocket = new ServerSocket(6789);

        while (true){
            Socket welcomeSocket = serverSocket.accept();

            Scanner inFromClient = new Scanner(welcomeSocket.getInputStream());

            stringFromClient = inFromClient.nextLine();
            System.out.println(welcomeSocket.getInetAddress()+" sent: "+stringFromClient);

            capitalizedString = stringFromClient.toUpperCase();
            System.out.println("Replying client with: "+capitalizedString);

            PrintStream outToClient = new PrintStream(welcomeSocket.getOutputStream());

            outToClient.println(capitalizedString);
        }
    }
}
