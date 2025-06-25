package com.domrey.ecommerce.service.telegram;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelegramEventListener {

    private final String BOT_TOKEN = "1028180699:AAEqnsGDXjCZdcWYKhz-lYy3bDiYsBIoMMc";
    private final String CHAT_ID = "430271166"; // or use from message/event

    @EventListener
    public void handleTelegramNotification(TelegramNotificationEvent event) {
        String message = event.getMessage();
        String url = "https://api.telegram.org/bot" + BOT_TOKEN +
                "/sendMessage?chat_id=" + CHAT_ID +
                "&text=" + message +
                "&parse_mode=HTML";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }
}
