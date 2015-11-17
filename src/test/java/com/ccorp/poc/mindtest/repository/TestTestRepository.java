package com.ccorp.poc.mindtest.repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


/**
 * Created by ssge on 2015/11/13.
 */
@Test
@ContextConfiguration(locations = { "classpath: **/spring-test-config.xml" })
public class TestTestRepository extends AbstractTestNGSpringContextTests {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TestRepository testRepository;

    @Test
    private void testTestRepository(){
//        testRepository.insert
        testRepository.save(new com.ccorp.poc.mindtest.model.domain.Test(ObjectId.get().toString(),"", "chuck", "Sign in"));
    }

}
