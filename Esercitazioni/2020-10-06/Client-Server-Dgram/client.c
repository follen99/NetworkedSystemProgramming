#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>

#include <stdio.h>
#include <string.h>

#include <fcntl.h> // for open
#include <unistd.h> // for close

int main(int argc, char* argv[]){
  char buf[256];
  int clientSocket;
  
  struct sockaddr_in remoteAddr;
  int remoteAddrLen;
  
  clientSocket = socket(PF_INET, SOCK_DGRAM, 0);
  
  memset(&remoteAddr, 0, sizeof(remoteAddr));
  remoteAddr.sin_family = AF_INET;
  remoteAddr.sin_port = htons(5193);
  remoteAddr.sin_addr.s_addr = inet_addr(argv[1]); // passiamo l'indirizzo del server come argomento così da poter usare il client con diversi server.
  
  remoteAddrLen = sizeof(remoteAddr);
  
  memset(buf, 0, sizeof(buf));
  sendto(clientSocket, buf, sizeof(buf), 0, (struct sockaddr*) &remoteAddr, remoteAddrLen);
  
  memset(buf, 0, sizeof(buf));
  recvfrom(clientSocket, buf, sizeof(buf), 0, (struct sockaddr*) &remoteAddr, &remoteAddrLen);
  
  printf("%s\n", buf);
  
  close(clientSocket);
}