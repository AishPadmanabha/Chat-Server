package assignment8.client;

import assignment8.ConstantUtils;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;


/**
 * Creates an object of Client, opens socket connection, starts
 * read(from server to console) and write(from console to server) threads.
 */
public class Client {

    private transient final String ipAddress;
    private transient final int port;
    private String userName;

    /**
     * Instantiates a new Client.
     *
     * @param ipAddress the ip address
     * @param port      the port
     */
    public Client(final String ipAddress, final int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Gets client user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets client user name.
     *
     * @param userName the user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * - Open socket connection
     * - Start read thread
     * - Start write thread
     */
    public void run() {
        try {
            final Socket socket = new Socket(ipAddress, port);
            new ReadThread(socket).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println(ConstantUtils.SERVER_NOT_FOUND_MESSAGE + ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ConstantUtils.IO_MESSAGE + ex.getMessage());
        }
    }

    /**
     * Client starts here.
     *
     * @param args -> ip address, port
     */
    public static void main(final String... args) {
        final String ipAddress = args[ConstantUtils.ZERO];
        final int port = Integer.parseInt(args[ConstantUtils.ONE]);

        final Client client = new Client(ipAddress, port);
        client.run();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Client client = (Client) o;
        return port == client.port &&
                Objects.equals(ipAddress, client.ipAddress) &&
                Objects.equals(getUserName(), client.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, port, getUserName());
    }

    @Override
    public String toString() {
        return "Client{" +
                "IPAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                '}';
    }
}
