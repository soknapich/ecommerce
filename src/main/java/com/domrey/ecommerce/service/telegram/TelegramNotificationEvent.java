package com.domrey.ecommerce.service.telegram;

import org.springframework.context.ApplicationEvent;

public class TelegramNotificationEvent extends ApplicationEvent {

    private final String message;

    public TelegramNotificationEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
