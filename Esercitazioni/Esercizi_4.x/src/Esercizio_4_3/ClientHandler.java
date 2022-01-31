package Esercizio_4_3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ClientHandler implements ProtocolHandler {
    private Socket connectionSocket;
    Scanner inFromUser;

    public ClientHandler(Socket socket) {
        this.connectionSocket = socket;
    }

    @Override
    public void handle() throws IOException {
        DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
        DataInputStream inFromServer = new DataInputStream(connectionSocket.getInputStream());
        inFromUser = new Scanner(System.in);

        System.out.print("Inserisci il primo numero da moltiplicare: ");
        outToServer.writeInt(inFromUser.nextInt());

        System.out.print("Inserisci il secondo numero da moltiplicare: ");
        outToServer.writeInt(inFromUser.nextInt());

        System.out.println("\nRisultato dal server: " + inFromServer.readLong());

        connectionSocket.close();
    }
}
