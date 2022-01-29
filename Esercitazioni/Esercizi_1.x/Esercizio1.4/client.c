#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>



#define PROTOPORT  5193
#define LOCALHOST  "127.0.0.1"  

int main(int argc, char *argv[]){
    char buf[256];
    int clientSocket;
    struct sockaddr_in remoteAddr;
    socklen_t remoteAddrLen;

    char *host;
    if(argc > 1){
        host = argv[1];
    }else{
        host = LOCALHOST;
    }

    clientSocket = socket(PF_INET, SOCK_DGRAM, 0);

    memset(&remoteAddr, 0, sizeof(remoteAddr)); // pulisco l'indirizzo

    remoteAddr.sin_family = AF_INET;
    remoteAddr.sin_port = htons(PROTOPORT);
    remoteAddr.sin_addr.s_addr = inet_addr(host);   // setto l'host
    remoteAddrLen = sizeof(remoteAddr);

    memset(&buf, 0, sizeof(buf));   // pulisco il buf

    // fase di invio

    int a,b;
    printf("Inserisci i due numeri da moltiplicare.\n Inserisci il primo valore: "); scanf("%d", &a]);
    printf("Inserisci il secondo valore: "); scanf("%d", &b);

    sendto(clientSocket, &a, sizeof(int), 0, (struct sockaddr*) &remoteAddr, remoteAddrLen);
    sendto(clientSocket, &b, sizeof(int), 0, (struct sockaddr*) &remoteAddr, remoteAddrLen);
    //memset(buf, 0, sizeof(buf));


    // fase di ricezione

    long result;
    recvfrom(clientSocket, result, sizeof(long), 0, (struct sockaddr*) &remoteAddr, &remoteAddrLen);

    printf("%ld\n", result);    //stampo il contenuto del buffer
    close(clientSocket);

    return 0;
}
