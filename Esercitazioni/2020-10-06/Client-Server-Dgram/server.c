#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>

#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]){
  char buf[256];
  int serverSocket;
  int clientAddrLen;
  int visits = 0;
  
  struct sockaddr_in serverAddr;
  struct sockaddr_in clientAddr;
  
  serverSocket = socket(PF_INET, SOCK_DGRAM, 0);
  
  memset(&serverAddr, 0, sizeof(serverAddr));
  serverAddr.sin_family = AF_INET;
  serverAddr.sin_port = htons(5193);
  serverAddr.sin_addr.s_addr = htonl(INADDR_ANY);

  bind(serverSocket, (struct sockaddr*) &serverAddr, sizeof(serverAddr));
  clientAddrLen = sizeof(clientAddr);

  while(1){
    memset(buf, 0, sizeof(buf));

    recvfrom(serverSocket, buf, sizeof(buf), 0, (struct sockaddr*) &clientAddr, &clientAddrLen);

    visits++;
    sprintf(buf, "questo server è stato contattato %d volte", visits);

    sendto(serverSocket, buf, sizeof(buf), 0, (struct sockaddr*) &clientAddr, clientAddrLen);
  }
}