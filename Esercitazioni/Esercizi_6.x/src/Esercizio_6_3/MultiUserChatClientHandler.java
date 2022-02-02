package Esercizio_6_3;

import Esercizio_6_2.ProtocolHandler;
import Esercizio_6_2.Reader;
import Esercizio_6_2.Writer;

import java.io.IOException;
import java.net.Socket;

public class MultiUserChatClientHandler implements ProtocolHandler {
    private Socket communicationSocket;

    public MultiUserChatClientHandler(Socket clientSocket) {
        this.communicationSocket = clientSocket;
    }

    @Override
    public void handle() throws IOException {


        // riusiamo le classi writer e reader già usate nell'esercizio 6.2, il client è praticamente lo stesso!
        Writer writer = new Writer(this.communicationSocket);
        Reader reader = new Reader(this.communicationSocket);

        writer.start();
        reader.start();
    }
}
