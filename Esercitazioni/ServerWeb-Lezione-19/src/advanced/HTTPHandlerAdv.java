package advanced;

import Simple.ProtocolHandler;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @Project ServerWeb-Lezione-19
 * @AUTHOR giulianoranauro on 03/02/22
 */
public class HTTPHandlerAdv implements ProtocolHandler {
    private static final String CRLF = "\r\n";
    private Socket socket;
    private String root;
    public HTTPHandlerAdv(Socket connectionSocket, String root) {
        this.socket = connectionSocket;
        this.root = root;
    }

    @Override
    public void handle() throws IOException {
        System.out.println("Reciving a request from a browser.\nHost: " + socket.getInetAddress());
        socket.setSoTimeout(500);

        InputStream inFromClient = socket.getInputStream();
        DataOutputStream outToClient = new DataOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));

        Scanner inputFromClientScanner = new Scanner(inFromClient);
        System.out.println("Current date: " + new Date());

        String requestLine = null;
        try {
            requestLine = inputFromClientScanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("couldn't read request from client: " + socket.getInetAddress());
        }


        // fino a questo punto non c'è molto di diverso.

        // ########################## PREPARAZIONE DELLE INFORMAZIONI DI RICHIESTA ##########################
        // con un tokenizer possiamo estrarre i vari "pezzi della richiesta"
        assert requestLine != null;
        StringTokenizer tokenizer = new StringTokenizer(requestLine);

        String method = tokenizer.nextToken();      // GET, POST, PUT ...
        String resource = tokenizer.nextToken();    // contiene il percorso fino al nome del file richiesto
        String httpVersion = "";                           // token contenente la versione del protocollo HTTP

        // stampo la richiesta (interamente)
        do {
            System.out.println(inputFromClientScanner.nextLine());
        }while (inputFromClientScanner.hasNextLine());

        // ########################## PREPARAZIONE DEL MESSAGGIO DI RISPOSTA ##########################

        if (resource.equals(File.separator))
            resource = File.separator + "index.html";

        // anteponiamo /root al nome della risorsa
        resource = root + resource;

        // prepariamo il messaggio di risposta


        String statusLine = "";
        String dateLine = "";

        String connectionLine = "Connection: close" + CRLF;
        String contentTypeLine = "";
        String lastModifiedLine = "";


        String errorBody = "";
        File file = new File(resource);

        System.out.println("Il client ha richiesto la risorsa: " + resource);

        // se esiste prendiamo delle informazioni
        if (file.exists()){
            statusLine = "HTTP/1.1 200 OK" + CRLF;  // diciamo che è tutto apposto
            contentTypeLine = "Content type: " + contentType(resource) + CRLF;
            lastModifiedLine = "Last-Modified: " + new Date(file.lastModified()) + CRLF;


        }else {
            statusLine = "HTTP/1.1 404 Not Found" + CRLF;
            contentTypeLine = "Content type: text/html" + CRLF;

            errorBody = "<HTML>" +
                    "<HEAD> <TITLE>Not Found</TITLE> </HEAD>" +
                    "<BODY>Mi dispiace amico, non ho trovato il tuo file</BODY> </HTML>";
        }

        dateLine = "Date: " + new Date() + CRLF;

        // scriviamo la status line
        outToClient.writeBytes(statusLine);

        // scriviamo l'header
        outToClient.writeBytes(connectionLine);
        outToClient.writeBytes(dateLine);
        if (file.exists()){
            outToClient.writeBytes(lastModifiedLine);   // ultima volta che abbiamo modificato il file
        }
        outToClient.writeBytes(contentTypeLine);
        // fine linee di intestazione

        // diciamo che le linee di intestazione sono finite - scriviamo una linea vuota
        outToClient.writeBytes(CRLF);

        // scriviamo il body

        if (file.exists()){
            try {
                sendFile(file, outToClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else outToClient.writeBytes(errorBody); // nel caso il file non è stato trovato inviamo un file di errore

        if (connectionLine.equals("Connection: close" + CRLF)){
            outToClient.close();
            socket.close();
            System.out.println("Connessione chiusa.");
        }

    }

    private void sendFile(File file, DataOutputStream outToClient) throws IOException {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];

            int bytes = 0;

            /**
             * vengono aperti due stram, uno in lettura e l'altro aperto è di scrittura.
             * con questo ciclo leggiamo dallo stream di input (associato al file)
             * un blocco di byte che deve essere grande al massimo 1024 bytes (per via di buff)
             * riversiamo nel buffer un numero di byte al più di 1024. Il numero di byte effettivamente letti
             * è salvato nella variabile bytes.
             * quando si incontra -1 abbiamo incontrato il fine stream*/
            while ((bytes = fileInputStream.read(buffer)) != -1)
                outToClient.write(buffer, 0, bytes);    // scriviamo in out specificando l'array contenente i byte ed il numero di bytes.

        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream !=null)
                fileInputStream.close();
        }
    }

    // deduciamo il formato MIME a partire dall'estensione del file richiesto
    private String contentType(String fileName) {
        // Il corpo di questo metodo puo' essere esteso per gestire altri formati MIME
        String type ="application/octet-stream";
        if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
            type = "text/html";
        if(fileName.endsWith(".gif"))
            type = "image/gif";
        if(fileName.endsWith(".jpg") || (fileName.endsWith(".jpeg")))
            type = "image/jpeg";

        try {
            type = Files.probeContentType(new File(fileName).toPath());
        } catch (IOException e) {	}
        return type;
    }
}
