package com.itfb.fooddeliveryservice.model;

public enum Message {

    SUCCESS(0,"Успешно"),
    ENTITY_NOT_FOUND(-1, "Объект не найден"),
    CART_IS_EMPTY(-2, "Корзина пуста"),
    USER_NOT_FOUND(-3, "Пользователь не найден"),
    RESTAURANT_NOT_FOUND(-4, "Ресторан не найден"),
    PRODUCT_NOT_FOUND(-5, "Продукт не найден");

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
