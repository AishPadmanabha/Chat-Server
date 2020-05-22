package assignment8.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SendInsultTest {

    @Test
    public void process() {
        SendInsult message = new SendInsult();
        Assert.assertEquals("25 4 tony 6 u twat",message.process("25","tony","u twat"));
    }
}