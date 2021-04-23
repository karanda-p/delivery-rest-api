package com.itfb.fooddeliveryservice.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationMessage {

    public static final String CREATE = "create";
    public static final String DELIVERY = "delivery";
    public static final String PAID = "paid";
    public static final String DONE = "done";

    private String template;
    private Map<String, Object> attributes;
    private List<Attachment> attachments;

    public void addAttributes(String name, Object value) {
        if (this.attributes == null) {
            attributes = new HashMap<>();
        }
        this.attributes.put(name, value);
    }

    public void addAttachment(Attachment attachment) {
        if (this.attachments == null) {
            attachments = new ArrayList<>();
        }
        this.attachments.add(attachment);
    }
}
