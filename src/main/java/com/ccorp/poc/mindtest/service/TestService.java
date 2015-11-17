package com.ccorp.poc.mindtest.service;

import com.ccorp.poc.mindtest.model.domain.Test;
import com.ccorp.poc.mindtest.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ssge on 2015/11/13.
 */
@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public Iterable<Test> fetchTestCases(){
        return testRepository.findAll();
    }

    public Test createTest(Test test){
        Test savedTest = testRepository.save(test);
        return savedTest;
    }

    public Test updateTest(Test test){
        Test updatedTest = testRepository.save(test);
        return updatedTest;
    }
}
