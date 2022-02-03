package advanced;

import Simple.ProtocolHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Project ServerWeb-Lezione-19
 * @AUTHOR giulianoranauro on 03/02/22
 */
public class WebServerAdv {
    // non final perche il file .conf potrebbe cambiarli
    private String root = "root";
    private int port = 8000;
    private ServerSocket welcomeSocket;

    public static void main(String[] args) {
        try {
            WebServerAdv server = new WebServerAdv();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebServerAdv() throws IOException {
        configure();
        welcomeSocket = new ServerSocket(port);
    }

    private void configure() {
        File conf = new File("webserver.conf");
        if (conf.exists()){
            // se il file esiste
            FileInputStream inputStream = null ;
            try {
                inputStream = new FileInputStream(conf);
                Scanner inputScanner = new Scanner(inputStream);

                while (inputScanner.hasNextLine()){
                    String confLine = inputScanner.nextLine();
                    if (confLine.startsWith("port")){
                        this.port = Integer.parseInt(confLine.substring(4).trim());
                    }

                    if (confLine.startsWith("root")){
                        this.root = confLine.substring(4).trim();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("Conf file not found.\nUsing default settings.");
            } finally {
                if (inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        System.err.println("couldn't close .conf file.");
                    }
                }
            }
        }
    }

    private void start() throws IOException {
        while (true){
            Socket connectionSocket = this.welcomeSocket.accept();

            try {
                ProtocolHandler handler = new HTTPHandlerAdv(connectionSocket, root);
                handler.handle();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
