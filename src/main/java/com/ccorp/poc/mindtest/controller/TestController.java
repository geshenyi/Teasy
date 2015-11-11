package com.ccorp.poc.mindtest.controller;

import com.ccorp.poc.mindtest.command.Commander;
import com.ccorp.poc.mindtest.model.webinput.PlainTestScript;
import com.ccorp.poc.mindtest.service.WebSocketService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.Properties;

/**
 * Created by chuck on 9/18/15.
 */
@Controller
@RequestMapping(value="/test")
public class TestController {

    @Value("#{commandProps}")
    private Properties commandProps;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private Commander commander;

    @Autowired
    private ServletContext context;

    @Value("#{commandProps}")
    private Properties properties;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<String> test(){
        System.out.println(properties);
        this.simpMessagingTemplate.convertAndSend("/topic/price", "15.12");
        return new HttpEntity<String>("test success");
    }

    @RequestMapping(value = "/chat",method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity<String> test1(@RequestBody String message){
        this.simpMessagingTemplate.convertAndSend("/topic/price", message);
        return new HttpEntity<String>("test success");
    }

    @RequestMapping(value="/single/run", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> runSingleTest(@RequestBody PlainTestScript plainScript){

        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        webSocketService.notify("/topic/"+plainScript.getUuid(), "Script received");
        try{
            commander.dictate(plainScript.getSteps(),commandProps, plainScript.getUuid(), context.getRealPath("") + "\\screenshot\\" + plainScript.getUuid());
        }catch(Exception e){
            webSocketService.notify("/topic/"+plainScript.getUuid(), ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            return new ResponseEntity<>("fail", org.springframework.http.HttpStatus.OK);
        }
        return new ResponseEntity<>("success", org.springframework.http.HttpStatus.OK);


    }
}
