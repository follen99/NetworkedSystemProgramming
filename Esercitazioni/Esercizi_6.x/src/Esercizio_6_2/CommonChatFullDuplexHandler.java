package Esercizio_6_2;

import java.io.IOException;
import java.net.Socket;
/**
 * nel momento in cui istanziamo l'handler invochiamo handle(), ma ci aspettiamo che all'atto di invocazione di handle vengano invocati i due thread reader e writer.*/
public class CommonChatFullDuplexHandler implements ProtocolHandler {
    private Socket commonSocket;

    public CommonChatFullDuplexHandler(Socket commonSocket) {
        this.commonSocket = commonSocket;

    }

    @Override
    public void handle() throws IOException {
        Reader reader = new Reader(this.commonSocket);
        Writer writer = new Writer(this.commonSocket);

        reader.start();
        writer.start();
    }
}
