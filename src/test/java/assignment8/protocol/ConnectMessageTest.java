package assignment8.protocol;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ConnectMessageTest {
  @Test
  public void process() {
    ConnectMessage message = new ConnectMessage();
    Assert.assertEquals("25 4 tony",message.process("25","tony"));
  }
}