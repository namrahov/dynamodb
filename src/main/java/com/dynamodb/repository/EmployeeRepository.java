package com.dynamodb.repository;

import com.dynamodb.model.Employee;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@EnableScan
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    List<Employee> findByName(String name);

    List<Employee> findByCreatedAtLessThan(LocalDateTime createdAt);
    List<Employee> findByCreatedAtGreaterThan(LocalDateTime createdAt);
    List<Employee> findByCreatedAtBetween(LocalDateTime begin, LocalDateTime end);
    List<Employee> findTop2ByOrderByCreatedAtDesc();
}
