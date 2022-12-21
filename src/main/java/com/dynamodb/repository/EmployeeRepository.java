package com.dynamodb.repository;

import com.dynamodb.model.Employee;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
