package assignment8.protocol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryResponseTest {

    @Test
    public void process() {
        QueryResponse message = new QueryResponse();
        Assert.assertEquals("25 2tony,steve",message.process("25","2","tony,steve"));
    }
}