#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>

#define PROTOPORT 5193
#define BUFFER_SIZE 256
int main(int argc, char const *argv[])
{
    char buf[BUFFER_SIZE];
    int serverSocket;
    socklen_t clientAddrLen;

    int visits = 0;

    struct sockaddr_in serverAddr;
    struct sockaddr_in clientAddr;

    serverSocket = socket(PF_INET, SOCK_DGRAM, 0);

    memset(&serverAddr, 0, sizeof(serverAddr));

    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(PROTOPORT);
    //serverAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    serverAddr.sin_addr.s_addr = inet_addr("192.168.1.45");

    bind(serverSocket, (struct sockaddr*) &serverAddr, sizeof(serverAddr));
    clientAddrLen = sizeof(clientAddr);

    
    while(1){
        // fase di ricezione

        char name[256];
        memset(name, 0, sizeof(name));

        recvfrom(serverSocket, name, sizeof(name), 0, (struct sockaddr*) &clientAddr, &clientAddrLen);
        visits++;

        memset(buf, 0, sizeof(buf));
        //sprintf(buf, "Questo server è stato visitato %ld volte", prod);
        sprintf(buf, "%s, Sei il %d° utente del server!\n", name, visits);

        



        sendto(serverSocket, buf, sizeof(buf), 0, (struct sockaddr*) &clientAddr, clientAddrLen);
    }



    return 0;
}
