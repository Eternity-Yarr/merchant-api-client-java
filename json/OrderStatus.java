package com.ll.wikimart.json;

public enum OrderStatus {
    opened("Новый"),
    canceled("Отменен"),
    rejected("Не принят"),
    confirmed("Принят"),
    annuled("Аннулирован"),
    invalid("Ошибка Викимарт"),
    faked("Тестовый");
    public final String desc;
    OrderStatus(String desc)
    {
        this.desc = desc;
    }
}
