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

        int accountId = 1;
        for (Account a: employee.getAccount()) {
            a.setAccountId(accountId++);
        }

        employee.setCreatedAt(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {

        return StreamSupport
                .stream(employeeRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }

    public App saveApplication(App app) {
        return appRepository.save(app);
    }


    public List<Employee> findByBalance(String balance) {
         List<Employee> eList = employeeRepository.findByCreatedAtBetween(
                LocalDateTime.now().minusDays(4),
                LocalDateTime.now().plusDays(3)
        );

        String name = "Nurlan";
         String matchSchoolName = "name = :name";
        Map<String, AttributeValue> schoolNames = new HashMap<>();
        schoolNames.put(":name", new AttributeValue().withS(name));
        schoolNames.put(":balance", new AttributeValue().withS("1000man"));


        DynamoDBQueryExpression<Employee> queryExpression = new DynamoDBQueryExpression<Employee>()
                //.withHashKeyValues(name)
                //.withIndexName("schoolName-index")
                 .withKeyConditionExpression(matchSchoolName)
                .withFilterExpression("Account.balance = :balance")
                .withExpressionAttributeValues(schoolNames)
                .withConsistentRead(false)
                .withLimit(3);

        DynamoDBMapperConfig mapperConfig
                = new DynamoDBMapperConfig.Builder().withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("employee")).build();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client.amazonDynamoDB(), mapperConfig);


        List<Employee> fetchedMembers = dynamoDBMapper.query(Employee.class, queryExpression);


        //List<Books> scanResult = mapper.scan(Books.class, scanExpression);
        return fetchedMembers;

    }


}
