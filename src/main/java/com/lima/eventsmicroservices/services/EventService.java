package com.lima.eventsmicroservices.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.eventsmicroservices.domain.Event;
import com.lima.eventsmicroservices.domain.Subscription;
import com.lima.eventsmicroservices.dtos.EmailRequestDTO;
import com.lima.eventsmicroservices.dtos.EventRequestDTO;
import com.lima.eventsmicroservices.exceptions.EventNotFoundException;
import com.lima.eventsmicroservices.repository.EventRepository;
import com.lima.eventsmicroservices.repository.SubscriptionRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public List<Event> getUpcomingEvents(){
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }
    public Event createEvent(EventRequestDTO eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    public void registeredParticipants(String eventId, String participantEmail){
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if(event.getRegisteredParticipants() < event.getMaxParticipants()){

             Subscription subscription = new Subscription(event, participantEmail);
             subscriptionRepository.save(subscription);

             event.setRegisteredParticipants(event.getRegisteredParticipants() + 1 );

        EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, eventId, participantEmail);


        emailServiceClient.sendEmail(emailRequest);
        }
    }

}
