#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>


#define PROTOPORT 5193
#define QLEN 6    

#define YOU 1
#define PEER 0
#define EXIT -1

int main(int argc, char *argv[]) {
    struct  sockaddr_in sad; // struct contenente l'indirizzo di trasporto del server
    struct  sockaddr_in cad; // struct contenente l'indirizzo di trasporto del client

    int     sd, sd2;
    int     port;
    socklen_t     alen;
    char    buf[256];
    int     visits = 0;

    sd = socket(PF_INET, SOCK_STREAM, 0);
    if (sd < 0) {
      fprintf(stderr, "socket creation failed\n");
      exit(1); 
    }
   

    memset((char *)&sad, 0, sizeof(sad));
   
    sad.sin_family = AF_INET;
    sad.sin_addr.s_addr = htonl(INADDR_ANY);
    if (argc > 1) {
        port = atoi(argv[1]);
    } else {
        port = PROTOPORT;
    }

    sad.sin_port = htons((u_short)port);

  
    if (bind(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
      fprintf(stderr,"bind failed\n");
      exit(1); 
    }
  
    if (listen(sd, QLEN) < 0) {
        fprintf(stderr,"listen failed\n");
        exit(1); 
    }
    alen = sizeof(cad);


    sd2 = accept(sd, (struct sockaddr*) &cad, &alen);
    

    // do stuff here
    int state = PEER;
    int actualLen = 0;

    while(state != EXIT){
        switch (state){
        case YOU:
            actualLen = 0;
            do{
                memset(buf, 0, sizeof(buf));
                printf("Messaggio: "); scanf("\n%[^\n]", buf);
                actualLen = strlen(buf);
                write(sd2, &actualLen, sizeof(actualLen));
                write(sd2, &buf, actualLen);

                if((buf[strlen(buf)-1]) == '-') state = PEER;
                if((buf[strlen(buf)-1]) == '.') state = EXIT;

            }while(state == YOU);

            break;
        case PEER:
            actualLen = 0;
            int n = 0;
            do{
                memset(buf, 0, sizeof(buf));
                read(sd2, &actualLen, sizeof(actualLen));
                
                read(sd2, &buf, sizeof(buf));
                printf("Risposta: %s\n", buf);

                if((buf[strlen(buf)-1]) == '-') state = YOU;
                if((buf[strlen(buf)-1]) == '.') state = EXIT;
                
            }while(state == PEER);

            break;
        case EXIT:
            close(sd2); // chiudo la socket
            exit(0);
            break;
        
        default:
            perror("something wend wrong lol");
            break;
        }
    }

    printf("\nLa connessione è stata chiusa.");
    close(sd); // non ci arrivo mai
    return 0;
}
 