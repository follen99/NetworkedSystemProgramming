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

    printf("Inserisci il tuo nome (max 255 char): "); scanf("%s", &buf);

    sendto(clientSocket, buf, sizeof(buf), 0, (struct sockaddr*) &remoteAddr, remoteAddrLen); // il numero di byte che voglaimo invare corrisponde a 2*sizeof(int)

    memset(buf, 0, sizeof(buf));

    recvfrom(clientSocket, buf, sizeof(buf), 0, (struct sockaddr*) &remoteAddr, &remoteAddrLen);

    printf("%s", buf);    //stampo il contenuto del buffer
    close(clientSocket);
    return 0;
}
