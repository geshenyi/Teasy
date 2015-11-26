package com.ccorp.poc.mindtest.service;

import com.ccorp.poc.mindtest.model.domain.Flow;
import com.ccorp.poc.mindtest.repository.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ssge on 2015/11/24.
 */
@Service
public class FlowService {
    @Autowired
    private FlowRepository flowRepository;

    public Iterable<Flow> fetchFlows(){
        return flowRepository.findAll();
    }

    public Flow createFlow(Flow flow){
        return flowRepository.save(flow);
    }

    public Flow fetchFlowByName(String name){
        return flowRepository.findByName(name);
    }

}
