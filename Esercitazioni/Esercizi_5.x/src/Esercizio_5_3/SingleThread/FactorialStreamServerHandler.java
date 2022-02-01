package Esercizio_5_3.SingleThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamServerHandler implements ProtocolHandler {
    private Socket connectionSocket;
    public FactorialStreamServerHandler(Socket acceptSocket) {
        this.connectionSocket = acceptSocket;
    }

    @Override
    public void handle(){
        try {
            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            int n = 0;
            int f = 0;

            do {
                n = inFromClient.readInt();
                f = fact(n);

                outToClient.writeInt(f);
                System.out.println("Serving client " + connectionSocket.getInetAddress() + ":" + connectionSocket.getLocalPort() + ".\n Response: " + f);
            }while (n > 0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          try {
              if (connectionSocket != null) connectionSocket.close();
          } catch (IOException e) {
              System.err.println("couldn't close the socket!");
          }

        }
    }

    private int fact(int n) {
        if (n <= 2) return n;
        return n * fact(n - 1);
    }
}
