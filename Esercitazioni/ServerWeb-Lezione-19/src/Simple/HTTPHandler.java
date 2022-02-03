package Simple;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * @Project ServerWeb-Lezione-19
 * @AUTHOR giulianoranauro on 03/02/22
 */
public class HTTPHandler implements ProtocolHandler {
    private Socket socket;
    public HTTPHandler(Socket connectionSocket) {
        this.socket = connectionSocket;
    }

    @Override
    public void handle() throws IOException {
        socket.setSoTimeout(100);
        System.out.println("Richiesta ricevuta da un browser.\nClient: " + this.socket.getInetAddress());

        InputStream inFromClient = socket.getInputStream();
        Scanner scannerFromClient = new Scanner(new InputStreamReader(inFromClient));

        PrintStream outToClient = new PrintStream(socket.getOutputStream());

        System.out.println(new Date());

        String requestLine = null;
        try {
            requestLine = scannerFromClient.nextLine();
            System.out.println("Request line: " + requestLine);
        } catch (Exception e) {
            System.err.println("Couldn't read from client: " + socket.getInetAddress());
        }

        String line = null;
        do {
            line = scannerFromClient.nextLine();
            System.out.println(line);
        }while (!line.equals(""));

        String test = "hello";
        outToClient.println(test);

        System.out.println("Request ended. \nClosing socket with host " + socket.getInetAddress());
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
