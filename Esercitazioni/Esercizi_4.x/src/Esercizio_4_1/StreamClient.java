package Esercizio_4_1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class StreamClient {
    public static void main(String[] args) throws IOException {
        String localString;
        String stringFromServer;

        Scanner inFromUser = new Scanner(System.in);

        Socket clientSocket = new Socket("127.0.0.1", 6789);

        PrintStream outToServer = new PrintStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());

        System.out.print("Enter a string: ");
        localString = inFromUser.nextLine();
        outToServer.println(localString);

        stringFromServer = inFromServer.nextLine();

        System.out.println(stringFromServer);

        clientSocket.close();
    }
}
