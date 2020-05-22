package assignment8.client;

import assignment8.server.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ClientTest {
    private Client client;
    private String IPAddress = "127.0.0.1";
    private Integer port = 6754;

    @Before
    public void setUp() {
        this.client = new Client(IPAddress, port);

    }

    @Test
    public void getUserName() {
        this.client.setUserName("ABC");
        Assert.assertEquals("ABC", this.client.getUserName());
    }

    @Test
    public void setUserName() {
        this.client.setUserName("ABC");
        Assert.assertEquals("ABC", this.client.getUserName());
    }

    @Test
    public void run() {
        this.client.run();
    }

    @Test
    public void main() {
        final String[] arguments = {IPAddress, String.valueOf(port)};
        Client.main(arguments);
    }

    @Test
    public void testEquals() {
        Client client2 = new Client(IPAddress, port);
        Client client3 = new Client(IPAddress, port);

        assertEquals(client, client);
        assertEquals(client3, client2);
    }

    @Test
    public void testClientNotEquals() {
        Client client2 = new Client(IPAddress, port);
        Client client3 = new Client(IPAddress, port);
        client2.setUserName("abc");
        assertNotEquals(client2, client3);
    }

    @Test
    public void testNullEquals() {
        Client client2 = new Client(IPAddress, port);
        assertNotEquals(null, client2);
    }

    @Test
    public void testDifferentObjectEquals() {
        Client client2 = new Client(IPAddress, port);
        String test = "test";
        assertNotEquals(client2, test);
    }

    @Test
    public void testHashCode() {
        Client client2 = new Client(IPAddress, port);
        Client client3 = new Client(IPAddress, port);
        Assert.assertEquals(client2.hashCode(), client3.hashCode());
    }

    @Test
    public void testToString() {
        this.client.setUserName("ABC");
        Assert.assertEquals("Client{" +
                "IPAddress='127.0.0.1" + '\'' +
                ", port="+port +
                ", userName='ABC" + '\'' +
                '}', this.client.toString());
    }

}