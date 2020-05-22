package assignment8.client;

import assignment8.ConstantUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Read thread is used to read data from server to and write to console.
 */
public class ReadThread extends Thread {
    private transient BufferedReader reader;
    private transient Socket socket;

    /**
     * Instantiates a new Read thread.
     *
     * @param socket the socket
     */
    public ReadThread(final Socket socket) {
        this.socket = socket;

        try {
            final InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println(ConstantUtils.INPUT_STREAM_ERROR_MESSAGE + ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     * "response" is read from the server and broken down to produce desired output.
     * an example of "response" -> for connection response -> 20 6 andrew
     * These inputs are further parsed and printed on the output stream (console)
     */
    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                final String response = reader.readLine();
                if (response != null && response.length()>ConstantUtils.ZERO) {
                    if (response.startsWith(ConstantUtils.TWENTY)) {
                        final String[] connectionResponse = response.split(ConstantUtils.SPACE);
                        final Boolean isAdded = Boolean.valueOf(connectionResponse[ConstantUtils.ONE]);
                        if (isAdded) {
                            final String serverMessage = ConstantUtils.CONNECT_SUCCESS + connectionResponse[connectionResponse.length-1];
                            System.out.println(serverMessage);
                        } else {
                            final String serverMessage = ConstantUtils.CONNECT_FAIL+ConstantUtils.CHATROOM_SIZE+ConstantUtils.NEXT_STEP;
                            System.out.println(serverMessage);
                            WriteThread.valid=Boolean.FALSE;
                            socket.close();
                            break;
                        }
                    } else if (response.startsWith(ConstantUtils.TWENTY_SIX)) {
                        System.out.println(response.split(ConstantUtils.SPACE,ConstantUtils.THREE)[ConstantUtils.TWO]);
                    } else if (response.startsWith(ConstantUtils.TWENTY_EIGHT)) {
                        System.out.println(response.split(ConstantUtils.SPACE,ConstantUtils.THREE)[ConstantUtils.TWO]);
                        socket.close();
                        break;
                    }else if (response.startsWith(ConstantUtils.TWENTY_THREE)) {
                        final String[] names =response.split(ConstantUtils.SPACE);
                        final StringBuilder builder = new StringBuilder(ConstantUtils.CONNECTED_USERS);
                        for(int i = ConstantUtils.THREE; i<names.length; i += ConstantUtils.TWO) {
                            builder.append(names[i]).append(ConstantUtils.COMMA);
                        }
                        System.out.println(builder.substring(ConstantUtils.ZERO,builder.length()-ConstantUtils.ONE));
                    }

                    else {
                        System.out.println(response);
                    }
                }
            } catch (IOException ex) {
                System.out.println(ConstantUtils.SERVER_READING_ERROR + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
