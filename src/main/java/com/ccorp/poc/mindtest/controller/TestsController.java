package com.ccorp.poc.mindtest.controller;

import com.ccorp.poc.mindtest.model.domain.Test;
import com.ccorp.poc.mindtest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ssge on 2015/11/13.
 */
@RestController
@RequestMapping(value="/tests")
public class TestsController {

    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Test>> fetchTests(){
        Iterable<Test> tests = null;
        try{
            tests = testService.fetchTestCases();
        }catch(Exception e){
            e.printStackTrace();
//            return new ResponseEntity<>(, HttpStatus.OK);
        }
        return new ResponseEntity<>(tests, HttpStatus.OK);

    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Test> createTest(@RequestBody Test test){
        Test savedTest = testService.createTest(test);
        return new ResponseEntity<Test>(savedTest, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Test> updateTest(@RequestBody Test test){
        Test updatedTest = testService.updateTest(test);
        return new ResponseEntity<Test>(updatedTest, HttpStatus.OK);

    }
}
