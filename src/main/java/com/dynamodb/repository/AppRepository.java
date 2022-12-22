package com.dynamodb.repository;

import com.dynamodb.model.Application;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface AppRepository extends CrudRepository<Application, String> {

}
