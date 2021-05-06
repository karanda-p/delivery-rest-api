package com.itfb.fooddeliveryservice.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    private boolean emailEnabled;
    private String phone;
    private boolean phoneEnabled;
    private Map<String, String> attributes;
    private List<Attachment> attachments;

    public void addAttributes(String name, String value) {
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
