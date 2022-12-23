package com.dynamodb.repository;

import com.dynamodb.entity.ApplicationEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, String> {
}
