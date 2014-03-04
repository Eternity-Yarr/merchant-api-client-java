package com.ll.wikimart.json;

import java.util.Date;
import java.util.List;

final public class Order
{
    int id;
    String code;
    Date createTime;
    OrderStatus status;
    Date statusTime;
    Delivery delivery;
    Payment payment;
    List<Product> positions;
    Buyer buyer;
    Discount discount;
    Appeal appeal;
}
