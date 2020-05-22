package assignment8.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectResponseTest {

    @Test
    public void process() {
        ConnectResponse message = new ConnectResponse();
        Assert.assertEquals("25 true 1061 You have successfully been connected to the chat room. "
            + "No of users currently present in the chat room are 1",message.process("25","True","1"));
    }
}