package com.ll.wikimart;

import com.ll.wikimart.json.OrdersList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;


public class QueryTest {
    @Test
    public void testSign() throws Exception {
        String hmac = Query.getHmac("foo");
        System.out.println(" Comparing with " + hmac);
        assertTrue(hmac.equalsIgnoreCase("007f5df338c4743f126606636c9448ff08522c2d"));
    }

    @Test
    public void testgetOpenedOrdersList() throws Exception {
        OrdersList ol = Query.getOpenedOrdersList();
        assertTrue(ol != null);
    }
}
