#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>

#define PROTOPORT 5193
int main(int argc, char const *argv[])
{
    char buf[256];
    int serverSocket;
    socklen_t clientAddrLen;

    int visits = 0; // non serve per questo esercizio
    struct sockaddr_in serverAddr;
    struct sockaddr_in clientAddr;

    serverSocket = socket(PF_INET, SOCK_DGRAM, 0);

    memset(&serverAddr, 0, sizeof(serverAddr));

    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = PROTOPORT;
    serverAddr.sin_addr.s_addr = htonl(INADDR_ANY);

    bind(serverSocket, (struct sockaddr*) &serverAddr, sizeof(serverAddr));
    clientAddrLen = sizeof(clientAddr);

    
    while(1){
        //memset(buf, 0, sizeof(buf));

        int a,b;
        recvfrom(serverSocket, &a, sizeof(int), 0, (struct sockaddr*) &clientAddr, &clientAddrLen);
        recvfrom(serverSocket, &b, sizeof(int), 0, (struct sockaddr*) &clientAddr, &clientAddrLen);
        
        long prod = a * (long) b;
        // do something here
        //visits++;

        printf("%ld", prod);
        
        memset(buf, 0, sizeof(buf));
        sprintf(buf, "Questo server è stato visitato %ld volte", prod);

        sendto(serverSocket, buf, sizeof(buf), 0, (struct sockaddr*) &clientAddr, clientAddrLen);
    }



    return 0;
}
