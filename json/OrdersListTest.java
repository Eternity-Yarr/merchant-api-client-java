package com.ll.wikimart.json;

import com.google.gson.*;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrdersListTest
{
    @Test
    public void testListDeserialize() throws Exception
    {
        String json = "{\n" +
                "  \"pageSize\":1,\n" +
                "  \"numPages\":30,\n" +
                "  \"total\":30,\n" +
                "  \"uri\":\"\\/api\\/1.0\\/orders?status=confirmed&transitionDateFrom=2012-09-16T08%3A00%3A00%2B04%3A00&transitionDateTo=2012-09-25T08%3A00%3A00%2B04%3A00&transitionStatus=opened&page=1\",\n" +
                "  \"firstPageUri\":\"\\/api\\/1.0\\/orders?status=confirmed&transitionDateFrom=2012-09-16T08%3A00%3A00%2B04%3A00&transitionDateTo=2012-09-25T08%3A00%3A00%2B04%3A00&transitionStatus=opened&pageSize=1&page=1\",\n" +
                "  \"prevPageUri\":null,\n" +
                "  \"nextPageUri\":\"\\/api\\/1.0\\/orders?status=confirmed&transitionDateFrom=2012-09-16T08%3A00%3A00%2B04%3A00&transitionDateTo=2012-09-25T08%3A00%3A00%2B04%3A00&transitionStatus=opened&pageSize=1&page=1\",\n" +
                "  \"lastPageUri\":\"\\/api\\/1.0\\/orders?status=confirmed&transitionDateFrom=2012-09-16T08%3A00%3A00%2B04%3A00&transitionDateTo=2012-09-25T08%3A00%3A00%2B04%3A00&transitionStatus=opened&pageSize=1&page=30\",\n" +
                "  \"orders\":[\n" +
                "    {\n" +
                "      \"id\":787550,\n" +
                "      \"code\":\"\\u2116S-787550\",\n" +
                "      \"createTime\":\"2012-09-16T08:00:00+04:00\",\n" +
                "      \"status\":\"confirmed\",\n" +
                "      \"statusTime\":\"2012-09-25T08:00:00+04:00\",\n" +
                "      \"delivery\":{\n" +
                "        \"type\":\"\\u041a\\u0443\\u0440\\u044c\\u0435\\u0440\\u0441\\u043a\\u0430\\u044f \\u0434\\u043e\\u0441\\u0442\\u0430\\u0432\\u043a\\u0430\",\n" +
                "        \"id\":61131,\n" +
                "        \"name\":null,\n" +
                "        \"alias\":null,\n" +
                "        \"group\":null,\n" +
                "        \"details\":null,\n" +
                "        \"date\":\"2012-09-25T08:00:00+04:00\",\n" +
                "        \"postcode\":null,\n" +
                "        \"region\":\"\\u041c\\u043e\\u0441\\u043a\\u0432\\u0430 \\u0438 \\u041c\\u041e\",\n" +
                "        \"city\":\"\\u041c\\u043e\\u0441\\u043a\\u0432\\u0430\",\n" +
                "        \"address\":\"\\u041c\\u043e\\u0441\\u043a\\u0432\\u0430, \\u0443\\u043b. \\u041b\\u0435\\u043d\\u0438\\u043d\\u0430, \\u0434. 1\",\n" +
                "        \"street\":\"\\u0443\\u043b. \\u041b\\u0435\\u043d\\u0438\\u043d\\u0430\",\n" +
                "        \"house\":\"1\",\n" +
                "        \"building\":\"\",\n" +
                "        \"apartment\":\"\",\n" +
                "        \"porch\":\"\",\n" +
                "        \"floor\":\"\",\n" +
                "        \"intercom\":\"\",\n" +
                "        \"goodsLift\":0,\n" +
                "        \"recipient\":\"\",\n" +
                "        \"cost\":290,\n" +
                "        \"comment\":\"\\u041f\\u043e\\u0437\\u0432\\u043e\\u043d\\u0438\\u0442\\u0435 \\u0441 \\u043f\\u0440\\u043e\\u0445\\u043e\\u0434\\u043d\\u043e\\u0439\"\n" +
                "      },\n" +
                "      \"payment\":{\n" +
                "        \"serviceCode\":\"cash\",\n" +
                "        \"serviceName\":\"\\u041d\\u0430\\u043b\\u0438\\u0447\\u043d\\u044b\\u043c\\u0438\",\n" +
                "        \"status\":\"pending\",\n" +
                "        \"statusTime\":\"2012-09-25T08:00:00+04:00\",\n" +
                "        \"amount\":17290\n" +
                "      },\n" +
                "      \"positions\":[\n" +
                "        {\n" +
                "          \"id\":19747461,\n" +
                "          \"offerId\":\"00-00015040\",\n" +
                "          \"quantity\":1,\n" +
                "          \"price\":17000,\n" +
                "          \"name\":\"\\u041a\\u043e\\u043b\\u044f\\u0441\\u043a\\u0430-\\u043b\\u044e\\u043b\\u044c\\u043a\\u0430 Inglesina Sofia Amalfi, \\u0447\\u0435\\u0440\\u043d\\u043e-\\u0431\\u0435\\u043b\\u0430\\u044f\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"buyer\":{\n" +
                "        \"type\":\"unknown\",\n" +
                "        \"company\":\"\",\n" +
                "        \"inn\":\"\",\n" +
                "        \"kpp\":\"\",\n" +
                "        \"name\":\"\\u0418\\u0432\\u0430\\u043d\\u043e\\u0432 \\u0418\\u0432\\u0430\\u043d\",\n" +
                "        \"phones\":[\n" +
                "          \"+71231231231\"\n" +
                "        ],\n" +
                "        \"email\":\"ivan@somedomain.com\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        Gson g = Serialize.createGson();
        OrdersList ol = g.fromJson(json, OrdersList.class);
        assertTrue(ol != null);
    }
}
