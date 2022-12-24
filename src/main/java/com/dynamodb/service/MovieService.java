package com.dynamodb.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.dynamodb.config.DynamoDBConfig;
import com.dynamodb.model.Account;
import com.dynamodb.model.App;
import com.dynamodb.model.Employee;
import com.dynamodb.model.Movie;
import com.dynamodb.repository.AppRepository;
import com.dynamodb.repository.EmployeeRepository;
import com.dynamodb.repository.MovieRepository;
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
    private DynamoDBConfig client;


    public MovieService(MovieRepository movieRepository, EmployeeRepository employeeRepository, AppRepository appRepository, DynamoDBConfig client) {
        this.movieRepository = movieRepository;
        this.employeeRepository = employeeRepository;
        this.appRepository = appRepository;
        this.client = client;
    }

    public List<Movie> getAllMovies() {

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

        int accountId = 1;
        for (Account a : employee.getAccount()) {
            a.setAccountId(accountId++);
        }

        employee.setCreatedAt(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {

        return (List<Employee>) employeeRepository.findAll();
    }

    public App saveApplication(App app) {
        return appRepository.save(app);
    }
    public List<Employee> reversedLastItemsInsertedInOneDay() {
        List<Employee> convertedLastFewDaysData = lastItemsInsertedInOneDay(1);
        convertedLastFewDaysData.sort(Comparator.comparing(Employee::getCreatedAt, Comparator.reverseOrder()));

        return convertedLastFewDaysData;
    }
    private List<Employee> lastItemsInsertedInOneDay(int increaseDay) {

        List<Employee> lastFewDaysData = employeeRepository.findByCreatedAtBetween(
                LocalDateTime.now().minusDays(increaseDay),
                LocalDateTime.now()
        );

        List<Employee> convertedLastFewDaysData = new ArrayList<>(lastFewDaysData);

        if (!convertedLastFewDaysData.isEmpty() || increaseDay == 15) {
            return convertedLastFewDaysData;
        }

        return lastItemsInsertedInOneDay(increaseDay + 1);
    }

}
