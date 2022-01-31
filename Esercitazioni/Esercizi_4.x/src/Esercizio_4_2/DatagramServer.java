package Esercizio_4_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 * Scrivere la stessa applicazione del problema 4.2 usando la comunicazione orientata ai datagram.
 */
public class DatagramServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(1200);

        byte[] receivedData = new byte[1024];
        byte[] manipulatedData;

        while (true){
            DatagramPacket packetToReceive = new DatagramPacket(receivedData, receivedData.length);

            serverSocket.receive(packetToReceive);

            // ora il pacchetto è all'interno di packetToReceive (oggetto), mentre i dati all'interno di receivedData

            String buff = new String(packetToReceive.getData(), 0, packetToReceive.getLength());
            String capitalized = buff.toUpperCase();
            manipulatedData = capitalized.getBytes();

            InetAddress clientAddress = packetToReceive.getAddress();
            int port = packetToReceive.getPort();

            DatagramPacket packetToSend = new DatagramPacket(manipulatedData, manipulatedData.length, clientAddress, port);
            serverSocket.send(packetToSend);

            Arrays.fill(receivedData, (byte) 0);    // pulisco il buffer
        }
    }
}
