package com.ccorp.poc.mindtest.controller;

import com.ccorp.poc.mindtest.model.domain.Flow;
import com.ccorp.poc.mindtest.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ssge on 2015/11/24.
 */
@RestController
@RequestMapping(value="/flow")
public class FlowController {

    @Autowired
    private FlowService flowService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Flow> createFlow(@RequestBody Flow flow){
        Flow savedFlow = flowService.createFlow(flow);
        return new ResponseEntity<Flow>(savedFlow, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Flow>> fetchFlows(){
        Iterable<Flow> allFlows = flowService.fetchFlows();
        return new ResponseEntity<Iterable<Flow>>(allFlows, HttpStatus.OK);
    }
}
