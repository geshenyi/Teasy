package com.ccorp.poc.mindtest.repository;

import com.ccorp.poc.mindtest.model.domain.Flow;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ssge on 2015/11/24.
 */
public interface FlowRepository extends PagingAndSortingRepository<Flow,ObjectId>{
    public Flow findByName(String name);
}
