package com.dynamodb.controller;


import com.dynamodb.model.App;
import com.dynamodb.model.Employee;
import com.dynamodb.model.Movie;
import com.dynamodb.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> AllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
         return movieService.saveMovie(movie);
    }

    @DeleteMapping
    public void deleteAllMovies() {
        movieService.deleteAllMovies();
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return movieService.saveEmployee(employee);
    }

    @GetMapping("/employee")
    public List<Employee> AllEmployees() {
        return movieService.getAllEmployees();
    }

    @PostMapping("/application")
    public App saveEmployee(@RequestBody App app) {
        return movieService.saveApplication(app);
    }

    @GetMapping("/{balance}")
    public List<Employee> findByBalance(@PathVariable String balance) {
        return movieService.findByBalance(balance);
    }
}
