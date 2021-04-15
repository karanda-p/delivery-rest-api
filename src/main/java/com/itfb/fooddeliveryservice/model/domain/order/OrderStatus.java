package com.itfb.fooddeliveryservice.model.domain.order;

public enum OrderStatus {

    DONE("Выполнен"),
    CANCELED("Отменен"),
    CREATED("Выполняется"),
    PAID("Оплачен"),
    IN_PROGRESS("Передан в доставку");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
