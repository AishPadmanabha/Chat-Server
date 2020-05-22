# Chat Application

This assignment aims to read JSON files and output sentences based on the grammars/patterns read from the given JSON file.

**Arguments passed**

Client -> IPAddress, Port

Server -> Port

**To run:**
Two commands should be run from command line to run the server and client files:

`Server [port_numer]`

`Client [IPAddress] [port_number]`

1. Server starts from the 'main' method from class 'Server'

2. Clients starts to run from the 'main' method from class 'Client'



**Packages Used:**

1. json-simple

**Pre - requirements:**

1. insults_json.json file must be present in the directory "Grammars/"

**High Level Overview of Classes**

**1. Server**: handles all

- data manipulation 

- accepting client socket requests 

- creation of user threads

- maintains a list of users and their threads

**2. UserThread:** takes in data from Client (write thread) and sends data to client (read thread)

**3. Client:** opens socket connection, starts read(from server to console) and write(from console to server) threads.

**4. ReadThread:** reads data from server to and write to console

**5. WriteThread:** writes data into the server from the client

Currently limit for chatroom size is 3, this can be changed in constant utils.
