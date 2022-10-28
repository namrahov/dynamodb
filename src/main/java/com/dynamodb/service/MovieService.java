package com.dynamodb.service;

import com.dynamodb.model.Movie;
import com.dynamodb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
}
