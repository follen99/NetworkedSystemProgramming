package Esercizio_6_3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<PrintStream> list = new ArrayList<>(); // contiene tutti i prinstream dei vari client
    public Room(){

    }
    public synchronized void broadCast(String s) {
        for (PrintStream ps : this.list)
            ps.println(s);  // invio il messaggio (stringa) ad ogni stream associato ad un client
    }

    public synchronized void add(Socket chatSocket) throws IOException {
        // estraggo lo stream di output di questa socket ed aggiungo il printstream alla lista delle socket.
        this.list.add(new PrintStream(chatSocket.getOutputStream()));
    }

    /**
     * Questo metodo dovrebbe essere usato nel momento in cui
     * un client chiude la connessione, è inutile inviare messaggi
     * ad un client che non li riceverà
     * */
    public synchronized void remove(Socket socket){
        // da implementare
    }
}
