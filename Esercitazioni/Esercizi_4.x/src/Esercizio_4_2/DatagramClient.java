package Esercizio_4_2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class DatagramClient {
    public static void main(String[] args) throws IOException {
        byte[] inFromUser;
        byte[] infFromServer = new byte[1024];

        Scanner scannerFromUser = new Scanner(System.in);
        inFromUser = scannerFromUser.nextLine().getBytes();
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();

        DatagramPacket outToServerPacket = new DatagramPacket(inFromUser, inFromUser.length, address, 1200 );

        clientSocket.send(outToServerPacket);

        // leggo i dati
        DatagramPacket inFromServerPacket = new DatagramPacket(infFromServer, infFromServer.length);
        clientSocket.receive(inFromServerPacket);

        String serverString = new String(inFromServerPacket.getData(), 0, inFromServerPacket.getLength());

        System.out.println("From server: " + serverString);
    }
}
