package com.ccorp.poc.mindtest.model.domain;

import com.ccorp.poc.mindtest.service.WebSocketService;

/**
 * Created by ssge on 2015/11/11.
 */
public class WebSocketBroadcaster {

    public static void broadcast(WebSocketService webSocketService, String dest, String message){
        webSocketService.notify(dest, message);
    }

}
