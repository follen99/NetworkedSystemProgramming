package Esercizio_4_4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project Esercizi_4.x
 * @AUTHOR giulianoranauro on 31/01/22
 */

public class ChatStreamServer {
    private static final int YOU = 1;
    private static final int PEER = 0;
    private static final int EXIT = -1;


    public static void main(String[] args) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        Socket chatSocket = welcomeSocket.accept();
        Scanner inFromUser = new Scanner(System.in);

        int state = PEER;
        PrintStream outToClient = new PrintStream(chatSocket.getOutputStream());
        Scanner inFromClient = new Scanner(chatSocket.getInputStream());

        while (state != EXIT) {
            switch (state){
                case YOU:
                    String sentence = inFromUser.nextLine();
                    outToClient.println(sentence);

                    if ((sentence.charAt(sentence.length()-1) == '-'))
                        state = PEER;
                    if ((sentence.charAt(sentence.length()-1) == '.'))
                        state = EXIT;
                    break;
                case PEER:
                    String sentenceFromClient = inFromClient.nextLine();
                    System.out.println(sentenceFromClient);

                    if (sentenceFromClient.charAt(sentenceFromClient.length() - 1) == '-')
                        state = YOU;
                    if (sentenceFromClient.charAt(sentenceFromClient.length() - 1) == '.')
                        state = EXIT;
                    break;
                case EXIT:
                    chatSocket.close();
            }
        }

        welcomeSocket.close();
    }
}
