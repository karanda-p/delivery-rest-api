package com.itfb.fooddeliveryservice.model;

public enum Message {

    SUCCESS(0,"message.success"),
    ENTITY_NOT_FOUND(-1, "error.entityNotFound"),
    CART_IS_EMPTY(-2, "error.cartIsEmpty"),
    USER_NOT_FOUND(-3, "error.userNotFound")
    ;

    private int code;
    private String text;

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

    public static Message valueOf(int code){
        for (Message message : Message.values())
            if (message.getCode() == code)
                return message;
        return null;
    }
}
