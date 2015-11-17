package com.ccorp.poc.mindtest.repository;

import com.ccorp.poc.mindtest.model.domain.Test;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ssge on 2015/11/13.
 */
public interface TestRepository extends PagingAndSortingRepository<Test, ObjectId> {
}
