package com.domrey.ecommerce.service;

import com.domrey.ecommerce.entity.MyEvent;
import com.domrey.ecommerce.repository.EventRepository;
import com.domrey.ecommerce.service.telegram.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasks {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    NotificationService notificationService;

    @Scheduled(cron = "0 * * * * *")  // runs every minute
    public void morningTask() {
//        MyEvent myEvent = new MyEvent();
//        myEvent.setTitle("Hello");
//        myEvent.setEndTime(LocalDateTime.now());
//        myEvent.setStartTime(LocalDateTime.now().plusHours(1));
//        myEvent.setLocation("Takeo, Pot Sor");
//
//        eventRepository.save(myEvent);
        //notificationService.notifyAdmin("🛎 Something happened in the system");
        System.out.println("🛎 Something happened in the system!");
    }
}
