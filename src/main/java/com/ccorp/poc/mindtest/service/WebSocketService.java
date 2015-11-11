package com.ccorp.poc.mindtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ssge on 2015/11/11.
 */

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void notify(String topic, String message){
        this.simpMessagingTemplate.convertAndSend(topic, message);
    }
}
