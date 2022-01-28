#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define PROTOPORT  5193
#define LOCALHOST "127.0.0.1" // costante indirizzo loopback

int main(int argc, char const *argv[]){
    struct sockaddr_in sad;

    int sd;
    int port;
    int n;
    char buf[1000];
    char *host;

    sd = socket(PF_INET, SOCK_STREAM, 0);
    if (sd < 0)
    {
        fprintf(stderr, "Creazione della socket fallita");
        exit(1);
    }

    memset((char*) &sad, 0, sizeof(sad));
    sad.sin_family = AF_INET;
    if (argc > 2){
        sad.sin_port = atoi(argv[2]); // se è presente un argv[2] lo attribuiamo alla porta
    }else{
        port = PROTOPORT;
    }
    
    // controllo la porta
    if(port > 0){
        sad.sin_port = htons((u_short) port);
    }else{
        fprintf(stderr, "Porta errata");
        exit(1);
    }

    if(argc > 1){
        host = argv[1];
    }else{
        host = LOCALHOST;
    }
    
    sad.sin_addr.s_addr = inet_addr(host);

    if(connect(sd, (struct sockaddr* )&sad, sizeof(sad)) < 0){
        fprintf(stderr, "connessione fallita");
        exit(1);
    }



    /*
    prima soluzione
    int a;
    int b;

    printf("Inserisci i due numeri da moltiplicare.\n Inserisci il primo valore: "); scanf("%d", &a);
    printf("Inserisci il secondo valore: "); scanf("%d", &b);
    // inviare dati qui

    
    write(sd, &a, sizeof(int));
    write(sd, &b, sizeof(int));
    */

    int values[2];
    printf("Inserisci i due numeri da moltiplicare.\n Inserisci il primo valore: "); scanf("%d", &values[0]);
    printf("Inserisci il secondo valore: "); scanf("%d", &values[1]);

    write(sd, &values, sizeof(values));

    // leggo la risposta
    long prod;
    read(sd, &prod, sizeof(long)); //salvo il risultato nella variabile prod    

    printf("Il risultato e': %ld\n", prod);
    close(sd); // chiudo la socket
    return 0;
}

