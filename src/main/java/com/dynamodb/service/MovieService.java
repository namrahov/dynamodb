package com.dynamodb.service;

import com.dynamodb.model.Application;
import com.dynamodb.model.Employee;
import com.dynamodb.model.Movie;
import com.dynamodb.repository.AppRepository;
import com.dynamodb.repository.EmployeeRepository;
import com.dynamodb.repository.MovieRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private EmployeeRepository employeeRepository;
    private AppRepository appRepository;

    //  private DynamoDBConfig client;


    public MovieService(MovieRepository movieRepository, EmployeeRepository employeeRepository, AppRepository appRepository) {
        this.movieRepository = movieRepository;
        this.employeeRepository = employeeRepository;
        this.appRepository = appRepository;
    }

    public List<Movie> getAllMovies() {

       /*List<String> titles = movieRepository.findTitle();
        System.out.println(titles);*/

        return StreamSupport
                .stream(movieRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }

    public Employee saveEmployee(Employee employee) {

        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {

        return StreamSupport
                .stream(employeeRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public Application saveApplication(Application application) {
        return appRepository.save(application);
    }


    public List<Employee> findByBalance(String balance) {
        List<Employee> employeeList = StreamSupport
                .stream(employeeRepository.findAll().spliterator(), true).toList()
                .stream().filter(p -> Objects.equals(p.getAccount().getBalance(), balance))
                .toList();

         List<Employee> eList = employeeRepository.findByCreatedAtBetween(
                LocalDateTime.now().minusDays(4),
                LocalDateTime.now().plusDays(3)
        );

       // List<Employee> eList = employeeRepository.findTop2ByOrderByCreatedAtDesc();


        return eList;
    }


}
