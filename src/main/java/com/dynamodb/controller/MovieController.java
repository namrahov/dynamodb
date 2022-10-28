package com.dynamodb.controller;


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
}
