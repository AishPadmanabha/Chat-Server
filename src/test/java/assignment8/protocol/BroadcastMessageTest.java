package assignment8.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BroadcastMessageTest {

    @Test
    public void process() {
        BroadcastMessage message = new BroadcastMessage();
        Assert.assertEquals("25 4 tony 6 hi all",message.process("25","tony","hi all"));
    }
}