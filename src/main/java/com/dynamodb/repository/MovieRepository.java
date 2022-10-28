package com.dynamodb.repository;

import com.dynamodb.model.Movie;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
}
