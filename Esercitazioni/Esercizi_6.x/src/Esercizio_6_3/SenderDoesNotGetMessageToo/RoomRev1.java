package Esercizio_6_3.SenderDoesNotGetMessageToo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomRev1 {
    private HashMap<Socket, PrintStream> connectionList = new HashMap<>();

    private int id;
    public RoomRev1(int id){
    }
    public synchronized void broadCast(Socket socket ,String s) {
        for (Socket connection : this.connectionList.keySet())
            if (connection != socket)
                connectionList.get(connection).println(s);
    }

    public synchronized void add(Socket chatSocket) throws IOException {
        // estraggo lo stream di output di questa socket ed aggiungo il printstream alla lista delle socket.
        this.connectionList.put(chatSocket, new PrintStream(chatSocket.getOutputStream()));
    }

    public void showRoomToClients(Socket socket){
        this.broadCast(socket ,"You are in the room: " + this.id);
    }

    /**
     * Questo metodo dovrebbe essere usato nel momento in cui
     * un client chiude la connessione, è inutile inviare messaggi
     * ad un client che non li riceverà
     * */
    public synchronized void remove(Socket socket){
        // da implementare
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
