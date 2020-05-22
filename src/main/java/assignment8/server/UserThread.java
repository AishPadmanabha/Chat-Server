package assignment8.server;//package assignment8.server;

import assignment8.ByteStringParserUtils;
import assignment8.ConstantUtils;

import java.io.*;
import java.net.Socket;

/**
 * The User thread takes in data from Client (write thread) and sends data to client (read thread).
 */
public class UserThread extends Thread {

    private transient Socket socket;
    private transient Server server;
    private transient PrintWriter writer;

    /**
     * Instantiates a new User thread.
     *
     * @param socket the socket
     * @param server the server
     */
    UserThread(final Socket socket, final Server server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * Take client array as input, generate server response array based on protocol
     */
    @Override
    public void run() {
        try {
            final InputStream input = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            final OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String clientMessage = reader.readLine();
            final String userName = clientMessage.split(ConstantUtils.SPACE)[ConstantUtils.TWO];
            final String serverMessage = ConstantUtils.NEW_CLIENT_MESSAGE + userName;
            server.broadcast(serverMessage, this);

            byte[] serverResponse = server.protocolResponse(ByteStringParserUtils.stringToByte(clientMessage), this);
            server.peerToPeer(ByteStringParserUtils.byteToString(serverResponse), this);

            while (!ByteStringParserUtils.byteToString(serverResponse).contains(ConstantUtils.TWENTY_EIGHT)) {
                clientMessage = reader.readLine();
                serverResponse = server.protocolResponse(ByteStringParserUtils.stringToByte(clientMessage), this);
                outputMessage(ByteStringParserUtils.byteToString(serverResponse));
            }
            System.out.println(ConstantUtils.SERVER_CLOSE_MESSAGE + userName);
            socket.close();

        } catch (IOException ex) {
            System.out.println(ConstantUtils.USER_THREAD_ERROR + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Output message to the client.
     *
     * @param message the message
     */
    void outputMessage(String message) {
        writer.println(message);
    }
}