#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>


#define PROTOPORT 5193
#define QLEN 6    


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

/*
    while (1) {
        sd2 = accept(sd, (struct sockaddr*) &cad, &alen);
        visits++;


        read(sd2, &buf, sizeof(buf));
        
        
        sprintf(buf + strlen(buf), ", sei il %d° utente del server!\n", visits);

        printf("%s", buf);

        close (sd2);
    }
    */

    sd2 = accept(sd, (struct sockaddr*) &cad, &alen);
    int actualLen = 0;
    int n = 0;
    do{
        read(sd2, &actualLen, sizeof(actualLen));
        printf("Actual string len: %d\n", actualLen);
        
        read(sd2, &buf, sizeof(buf));
        printf("%s", buf);
        printf("\n");
        
    }while((buf[strlen(buf)-1]) != '.');

    close(sd); // non ci arrivo mai
}
 