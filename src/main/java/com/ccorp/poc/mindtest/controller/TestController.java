package com.ccorp.poc.mindtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chuck on 9/18/15.
 */
@Controller
public class TestController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<String> test(){
        this.simpMessagingTemplate.convertAndSend("/topic/price", "15.12");
        return new HttpEntity<String>("test success");
    }

    @RequestMapping(value = "/chat",method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity<String> test1(@RequestBody String message){
        this.simpMessagingTemplate.convertAndSend("/topic/price", message);
        return new HttpEntity<String>("test success");
    }
}
