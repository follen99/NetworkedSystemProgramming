package Esercizio_5_3.SingleThread;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamConcurrentHandler implements ProtocolHandler {
    private Socket connectionSocket;
    Scanner fromUser;
    public FactorialStreamConcurrentHandler(Socket clientSocket) {
        this.connectionSocket = clientSocket;
    }

    @Override
    public void handle() throws IOException {
        try {
            DataOutputStream outToServer = new DataOutputStream(connectionSocket.getOutputStream());
            DataInputStream inFromServer = new DataInputStream(connectionSocket.getInputStream());
            fromUser = new Scanner(System.in);

            int n = 0;
            do {
                System.out.print("Inserisci un numero intero: "); n = fromUser.nextInt();
                //spedisco al server
                outToServer.writeInt(n);
                // il server mi risponde con il calcolo del fattoriale
                int fact = inFromServer.readInt();
                System.out.println("Fattoriale di " + n + ": " + fact);
            }while (n > 0);

        } catch (IOException e) {
            System.err.println("something went wrong lol");
        } finally {
            if (connectionSocket != null) connectionSocket.close();
            if (fromUser != null) fromUser.close(); // come siamo precisi
        }


    }
}
