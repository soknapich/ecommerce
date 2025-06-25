package com.domrey.ecommerce.service.telegram;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final ApplicationEventPublisher publisher;

    public NotificationService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void notifyAdmin(String message) {
        publisher.publishEvent(new TelegramNotificationEvent(this, message));
    }
}
