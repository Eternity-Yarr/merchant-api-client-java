package com.ll.wikimart;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class QueryTest {
    @Test
    public void testSign() throws Exception {
        String hmac = Query.getHmac("foo");
        System.out.println(" Comparing with " + hmac);
        assertTrue(hmac.equalsIgnoreCase("007f5df338c4743f126606636c9448ff08522c2d"));
    }
}
