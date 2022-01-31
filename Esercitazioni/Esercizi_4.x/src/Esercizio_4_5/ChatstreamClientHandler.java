package Esercizio_4_5;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */
public class ChatstreamClientHandler implements ProtocolHandler {
    private static final int YOU = 1;
    private static final int PEER = 0;
    private static final int EXIT = -1;

    private Socket connectionSocket;
    public ChatstreamClientHandler(Socket clientSocket) {
        this.connectionSocket = clientSocket;
    }

    @Override
    public void handle() throws IOException {
        PrintStream outToServer = new PrintStream(connectionSocket.getOutputStream());
        Scanner inFromServer = new Scanner(connectionSocket.getInputStream());
        Scanner inFromUser = new Scanner(System.in);

        int state = YOU;
        while (state != EXIT){
            switch (state){
                case YOU:
                    String sentence = inFromUser.nextLine();
                    outToServer.println(sentence);

                    if ((sentence.charAt(sentence.length()-1) == '-'))
                        state = PEER;
                    if ((sentence.charAt(sentence.length()-1) == '.'))
                        state = EXIT;
                    break;
                case PEER:
                    String sentenceFromClient = inFromServer.nextLine();
                    System.out.println(sentenceFromClient);

                    if (sentenceFromClient.charAt(sentenceFromClient.length() - 1) == '-')
                        state = YOU;
                    if (sentenceFromClient.charAt(sentenceFromClient.length() - 1) == '.')
                        state = EXIT;
                    break;
                case EXIT:
                    connectionSocket.close();
            }
        }

    }
}
