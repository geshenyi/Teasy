package com.ccorp.poc.mindtest.controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chuck on 9/18/15.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<String> test(){
        return new HttpEntity<String>("test success");
    }
}
