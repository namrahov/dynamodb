package com.dynamodb.repository;

import com.dynamodb.entity.StatusHistoryEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface StatusHistoryRepository extends CrudRepository<StatusHistoryEntity, String> {
}
