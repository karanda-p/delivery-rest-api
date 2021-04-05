package com.itfb.fooddeliveryservice.model.domain.order;

public enum OrderStatus {

    DONE("Выполнен"),
    CANCELED("Отменен"),
    IN_PROGRESS("Выполняется");

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
