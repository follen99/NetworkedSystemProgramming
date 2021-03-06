#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define PROTOPORT  5193
#define LOCALHOST "127.0.0.1" // costante indirizzo loopback

#define YOU 1
#define PEER 0
#define EXIT -1

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


    
    // do stuff here

    int state = YOU;
    int actualLen = 0;
    char otherUser[256];

    // leggo ed invio il mio username
    printf("Inserisci il tuo username: "); scanf("%s",buf);
    write(sd, &buf, strlen(buf));

    read(sd, &otherUser, sizeof(otherUser));
    printf("Stai parlando con: %s\n", otherUser);

    while(state != EXIT){
        switch (state){
        case YOU:
            actualLen = 0;
            do{
                memset(buf, 0, sizeof(buf));
                printf("Messaggio: "); scanf("\n%[^\n]", buf);
                actualLen = strlen(buf);
                write(sd, &actualLen, sizeof(actualLen));
                write(sd, &buf, actualLen);

                if((buf[strlen(buf)-1]) == '-') state = PEER;
                if((buf[strlen(buf)-1]) == '.') state = EXIT;

            }while(state == YOU);

            break;
        case PEER:
            actualLen = 0;
            int n = 0;
            do{
                memset(buf, 0, sizeof(buf));
                read(sd, &actualLen, sizeof(actualLen));
                
                read(sd, &buf, sizeof(buf));
                printf("%s: %s\n",otherUser, buf);

                if((buf[strlen(buf)-1]) == '-') state = YOU;
                if((buf[strlen(buf)-1]) == '.') state = EXIT;
                
            }while(state == PEER);
            break;
        case EXIT:
            close(sd); // chiudo la socket
            exit(0);
            break;
        
        default:
            perror("something wend wrong lol");
            break;
        }
    }
    printf("\nLa connessione è stata chiusa.");
    return 0;
}

