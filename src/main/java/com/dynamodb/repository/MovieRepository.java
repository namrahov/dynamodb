package com.dynamodb.repository;

import com.dynamodb.model.Movie;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

}
