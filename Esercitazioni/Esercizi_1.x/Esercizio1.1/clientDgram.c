
/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#define PROTOPORT  5193
#define LOCALHOST  "127.0.0.1"  /* Default server address */
/* Default server port number */
int main(int argc, char *argv[]) {
  char buf[256];
  int clientSocket;
  struct sockaddr_in remoteAddr;
  socklen_t remoteAddrLen;
char *host;
  /* Check for the existence of an IP address on the command line */
  if (argc > 1) {
          host = argv[1]; /* if host argument specified */
} else {
}
host = LOCALHOST;
  /* Create a socket for datagram oriented communication */
  clientSocket = socket(PF_INET, SOCK_DGRAM, 0);
  /* Clean the memory area that will store the transport address of
the remote socket (server) */
  memset(&remoteAddr, 0, sizeof(remoteAddr));
  /* Set the transport address of the remote socket (server) */
  remoteAddr.sin_family = AF_INET;
  remoteAddr.sin_port = htons(PROTOPORT);
  remoteAddr.sin_addr.s_addr= inet_addr(host);
  remoteAddrLen = sizeof(remoteAddr);
  /* Clean the memory area that will store the data to send */
  memset(buf, 0, sizeof(buf));
  /* Send the data (no useful data is present in the message) */
  sendto(clientSocket, buf, sizeof(buf), 0, (struct sockaddr
*)&remoteAddr, remoteAddrLen);
memset(buf, 0, sizeof(buf));

  /* Receive the data from the server; we assume that the data are
encapsulated in a single message */
  recvfrom(clientSocket, buf, sizeof(buf), 0, (struct sockaddr
*)&remoteAddr, &remoteAddrLen);
  /* Show the data on the stdout */
  printf("%s\n", buf);
  /* Close the socket and release the related resources */
  close(clientSocket);
  return 0;
}
 