package com.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBTable(tableName = "hesen")
public class Movie {

    String filmId;
    String title;
    String releaseYear;
    String rated;
    String released;
    String runtime;
    List<String> genres;
    List<String> actors;
    String director;
    String pilot;
    String language;
    String country;
    String awards;
    String imdbRating;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    @DynamoDBAttribute(attributeName = "FilmId")
    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    @DynamoDBAttribute(attributeName = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "ReleaseYear")
    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @DynamoDBAttribute(attributeName = "Rated")
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    @DynamoDBAttribute(attributeName = "Released")
    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    @DynamoDBAttribute(attributeName = "Runtime")
    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @DynamoDBAttribute(attributeName = "Genres")
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @DynamoDBAttribute(attributeName = "Actors")
    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @DynamoDBAttribute(attributeName = "Director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @DynamoDBAttribute(attributeName = "Pilot")
    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    @DynamoDBAttribute(attributeName = "Language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @DynamoDBAttribute(attributeName = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @DynamoDBAttribute(attributeName = "Awards")
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @DynamoDBAttribute(attributeName = "imdbRating")
    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }
}