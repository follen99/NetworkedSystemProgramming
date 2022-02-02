package Esercizio_6_3.SenderGetsMessageToo;

import Esercizio_6_2.ProtocolHandler;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiUserChatServerHandler extends Thread implements ProtocolHandler {
    private Socket chatSocket;
    private Room room;
    public MultiUserChatServerHandler(Socket connectionSocket, Room room) throws IOException {
        this.chatSocket = connectionSocket;
        this.room = room;
        room.add(chatSocket);   // Registro la socket di connessione all'interno di room
    }


    @Override
    public void handle() throws IOException {
        start();
    }

    @Override
    public void run() {
        try {
            Scanner inpuStream = new Scanner(this.chatSocket.getInputStream());
            PrintStream outStream = new PrintStream(this.chatSocket.getOutputStream());
            room.showRoomToClients();

            boolean end = false; // flag
            do {
                String line = inpuStream.nextLine();


                line = chatSocket.getInetAddress() + " > " + line;  // costruisco il messaggio identificando il client

                end = line.endsWith(".");

                // con questa istruzione viene inviata la linea a tutti i client connessi
                // se è presente un punto, invio la stringa senza il punto finale, questo perchè farebbe terminare tutti i client
                room.broadCast(!end ? line : line.substring(0, line.lastIndexOf(".")));
            }while (!end);
            /**
             * l'unico a ricevere il punto sarà il client che lo ha inviato;
             * questo serve a far sì che il thread di lettura del client
             * venga terminato.*/
            outStream.println("."); //
            System.out.println("Chiudo la connessione...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.err.println("Errore generico in MultiUserChatServerHandler");
        } finally {
            try {
                this.chatSocket.close();
            } catch (IOException e) {
                System.err.println("errore nella chiusura della socket. Classe: " + getName());
            }
        }
    }
}
