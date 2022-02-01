package Esercizio_5_3.MultiThread;

import Esercizio_5_3.SingleThread.FactorialStreamServerHandler;
import Esercizio_5_3.SingleThread.ProtocolHandler;

import javax.swing.plaf.SeparatorUI;
import java.io.IOException;
import java.net.Socket;

/**
 * @Project Esercizi_5.x
 * @AUTHOR giulianoranauro on 01/02/22
 */
public class FactorialStreamMultiThreadServerHandler extends FactorialStreamServerHandler implements Runnable {
    Thread newThread;
    public FactorialStreamMultiThreadServerHandler(Socket acceptSocket) {
        super(acceptSocket);
        newThread = new Thread(this);
    }

    @Override
    public void handle(){
        newThread.start();
    }

    @Override
    public void run() {
        super.handle();
    }
}
