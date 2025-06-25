package com.domrey.ecommerce.controller;

import com.domrey.ecommerce.entity.MyEvent;
import com.domrey.ecommerce.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public List<MyEvent> listEvents() {
        return eventRepository.findAll();
    }
}
