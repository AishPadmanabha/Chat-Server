package assignment8.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectMessageTest {

    @Test
    public void process() {
        DirectMessage message = new DirectMessage();
        Assert.assertEquals("25 4 tony 5 steve 5 hi yo",message.process("25","tony","steve","hi yo"));
    }
}