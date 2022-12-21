package com.dynamodb.service;

import com.dynamodb.model.Employee;
import com.dynamodb.model.Movie;
import com.dynamodb.repository.EmployeeRepository;
import com.dynamodb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private EmployeeRepository employeeRepository;

    public MovieService(MovieRepository movieRepository, EmployeeRepository employeeRepository) {
        this.movieRepository = movieRepository;
        this.employeeRepository = employeeRepository;
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
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return StreamSupport
                .stream(employeeRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
    }
}
