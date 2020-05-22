package assignment8;

import assignment8.exceptions.InvalidIdentifierException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParserUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getInstanceCM() throws InvalidIdentifierException {
        Assert.assertEquals("19 4 tony",ParserUtils.getInstance("19","tony"));
    }
    @Test
    public void getInstanceCR() throws InvalidIdentifierException {
        Assert.assertEquals("20 true 1061 You have successfully been connected to the chat room. No of users currently present in the chat room are 1",ParserUtils.getInstance("20","true","1"));
    }
    @Test
    public void getInstanceDM() throws InvalidIdentifierException {
        Assert.assertEquals("21 4 tony",ParserUtils.getInstance("21","tony"));
    }
    @Test
    public void getInstanceDR() throws InvalidIdentifierException {
        Assert.assertEquals("28 4 tony",ParserUtils.getInstance("28","tony"));
    }
    @Test
    public void getInstanceQU() throws InvalidIdentifierException {
        Assert.assertEquals("22 4 tony",ParserUtils.getInstance("22","tony"));
    }@Test
    public void getInstanceQR() throws InvalidIdentifierException {
        Assert.assertEquals("23 2tony,steve",ParserUtils.getInstance("23","2","tony,steve"));
    }
    @Test
    public void getInstanceBM() throws InvalidIdentifierException {
        Assert.assertEquals("24 4 tony 6 hi all",ParserUtils.getInstance("24","tony","hi all"));
    }
    @Test
    public void getInstanceDirectM() throws InvalidIdentifierException {
        Assert.assertEquals("25 4 tony 5 steve 5 hi yo",ParserUtils.getInstance("25","tony","steve","hi yo"));
    }
    @Test
    public void getInstanceFM() throws InvalidIdentifierException {
        Assert.assertEquals("26 4 tony",ParserUtils.getInstance("26","tony"));
    }
    @Test
    public void getInstanceSI() throws InvalidIdentifierException {
        Assert.assertEquals("27 4 tony 6 u twat",ParserUtils.getInstance("27","tony","u twat"));
    }
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Test
    public void testException() throws InvalidIdentifierException {
        expectedEx.expect(InvalidIdentifierException.class);
        expectedEx.expectMessage("");
        ParserUtils.getInstance("545","ddd");
    }

}