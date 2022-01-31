package Esercizio_4_3;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ServerHandler implements ProtocolHandler{
    private Socket connectionSocket;
    public ServerHandler(Socket socket) {
        this.connectionSocket = socket;
    }

    @Override
    public void handle() throws IOException {
        DataInputStream inputFromClient = new DataInputStream(this.connectionSocket.getInputStream());
        DataOutput outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());

        outToClient.writeLong((long) inputFromClient.readInt() * inputFromClient.readInt());
    }
}
