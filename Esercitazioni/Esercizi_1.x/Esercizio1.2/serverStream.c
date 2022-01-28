
/*#include <sys/types.h>*/
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define PROTOPORT 5193 /* Default port number */
#define QLEN 6    /* Size of connection requests queue   */
int main(int argc, char *argv[]) {
  struct  sockaddr_in sad; /* Struct to store the transport address of
the server socket */
  struct  sockaddr_in cad; /* Struct to store the transport address of
the client socket */
  int     sd, sd2;
  int     port;
  socklen_t     alen;
  char    buf[1000];
  int     visits = 0;
/* Socket descriptors */
    /* Port number */
/* Lenght of the client transport address
/* Buffer for sending and receiving data */
                           /* Status variable to count the number of
connections from clients */
  /* Create a socket for stream oriented communication */
  sd = socket(PF_INET, SOCK_STREAM, 0);
  if (sd < 0) {
      fprintf(stderr, "socket creation failed\n");
exit(1); }
   /* Clean the memory area that will store the transport address of
the local socket (server) */
   memset((char *)&sad, 0, sizeof(sad));
   /* Set the transport address of the local socket (server) */
   sad.sin_family = AF_INET;
   sad.sin_addr.s_addr = htonl(INADDR_ANY);
if (argc > 1) {
        port = atoi(argv[1]);
   } else {
        port = PROTOPORT;
   }
   sad.sin_port = htons((u_short)port);

  /* Bind a transport address (sad) to the local socket (sd) */
  if (bind(sd, (struct sockaddr *)&sad, sizeof(sad)) < 0) {
      fprintf(stderr,"bind failed\n");
exit(1); }
  /* Characterize the socket as a welcome socket (server) */
  if (listen(sd, QLEN) < 0) {
      fprintf(stderr,"listen failed\n");
exit(1); }
  alen = sizeof(cad);
  while (1) {
      /* Extract a connection request from the queue and create a new
socket */
      if ( (sd2=accept(sd, (struct sockaddr *)&cad, &alen)) < 0) {
        fprintf(stderr, "accept failed\n");
exit(1); }
      /* It is not necessary to invoke read() since the request
message is empty */
      /* accpet() is sufficient since a connection request can be
considered as a request message*/
      /* The request message is processed */
      visits++;
      sprintf(buf,"This server has been contacted %d time%s\n",
visits, visits==1 ? "." : "s.");
      /* A response message has been produced */
      /* Write the response message in the network channel */
      write(sd2, buf, strlen(buf));
      /* Close the connection socket created by accept() */
      close (sd2);
  }
}
 