package com.itfb.fooddeliveryservice.model;

public enum Message {

    SUCCESS(0, "message.success"),
    ENTITY_NOT_FOUND(-1, "error.entityNotFound"),
    CART_IS_EMPTY(-2, "error.cartIsEmpty"),
    USER_NOT_FOUND(-3, "error.userNotFound"),
    RESTAURANT_NOT_FOUND(-4, "error.restaurantNotFound"),
    PRODUCT_NOT_FOUND(-5, "error.productNotFound"),
    ORDER_NOT_FOUND(-6, "error.orderNotFound"),
    ORDER_ALREADY_PAID(-7, "error.orderAlreadyPaid"),
    NO_ORDERS_TO_DELIVER(-8, "message.noOrdersToDeliver");

    private final int code;
    private final String text;

    Message(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Message valueOf(int code) {
        for (Message message : Message.values())
            if (message.getCode() == code)
                return message;
        return null;
    }
}
