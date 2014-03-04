package com.ll.wikimart.json;

import java.util.List;

final public class OrdersList
{
    int pageSize;
    int numPages;
    int total;
    String uri;
    String firstPageUri;
    String prePageUri;
    String nextPageUri;
    String lastPageUri;
    List<Order> orders;
}
