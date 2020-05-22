package assignment8;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ByteStringParserUtilsTest {




    @Test
    public void testByteConversion() {
        String message ="Hi yall";
        Assert.assertEquals(message,ByteStringParserUtils.byteToString(ByteStringParserUtils.stringToByte(message)));
    }
}