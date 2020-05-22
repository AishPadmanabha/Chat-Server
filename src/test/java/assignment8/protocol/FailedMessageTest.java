package assignment8.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FailedMessageTest {

    @Test
    public void process() {
        FailedMessage message = new FailedMessage();
        Assert.assertEquals("25 4 tony",message.process("25","tony"));
    }
}