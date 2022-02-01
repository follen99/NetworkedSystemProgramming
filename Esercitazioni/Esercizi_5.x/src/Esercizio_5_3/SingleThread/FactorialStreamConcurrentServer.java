package Esercizio_5_3.SingleThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamConcurrentServer implements ProtocolHandler {
    private Socket connectionSocket;
    public FactorialStreamConcurrentServer(Socket acceptSocket) {
        this.connectionSocket = acceptSocket;
    }

    @Override
    public void handle() throws IOException {
        try {
            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            int n = 0;
            int f = 0;

            do {
                n = inFromClient.readInt();
                f = fact(n);

                outToClient.writeInt(f);
            }while (n > 0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          if (connectionSocket != null) connectionSocket.close();

        }
    }

    private int fact(int n) {
        if (n <= 2) return 2;
        return n * fact(n - 1);
    }
}
