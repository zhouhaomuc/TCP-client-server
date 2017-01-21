# TCP-client-server
Socket programming. 
Implement a client and a server that communicate with each other over the network. 
The client sends a zip code to the server and server replies back a string contating the city and the state corresponding to the zip code. 
Below are some additional operational details about the client and server:
As the command line arguments, the client should accept as input either a host name or an IP address with a port number. 
Using this information, it creates a TCP connection with the server, which should be already running at this time. 
Then it accepts an input string containing the zip code from the terminal (ending with an enter),and sends the string to the server
through the TCP connection. When it receives a response, it prints the response and exits. 
As the command line arguments, 
the server accept a port number that it will run at. 
After being started, the server repeatedly accept an input string from any TCP client, extract the zip code, 
perform a table look-up and finally send back the corresponding city and state. 
The server can maintain a database by reading a file with the required information.
