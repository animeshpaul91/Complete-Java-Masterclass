package com.learnwiremock.service;

import com.learnwiremock.dto.Movie;
import com.learnwiremock.exceptions.MovieErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.learnwiremock.constants.MoviesAppConstants.GET_ALL_MOVIES_V1;
import static com.learnwiremock.constants.MoviesAppConstants.GET_MOVIE_BY_ID;
import static com.learnwiremock.constants.MoviesAppConstants.GET_MOVIE_BY_NAME;
import static com.learnwiremock.constants.MoviesAppConstants.GET_MOVIE_BY_YEAR;

@Slf4j
public class MoviesClient {
    private final WebClient webClient;

    public MoviesClient(final WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Movie> retrieveAllMovies() {
        return webClient.get()
                .uri(GET_ALL_MOVIES_V1)
                .retrieve()
                .bodyToFlux(Movie.class)
                .collectList()
                .block();
    }

    public Movie retrieveMovieById(int movieId) {
        try {
            return webClient.get()
                    .uri(GET_MOVIE_BY_ID, movieId)
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
        } catch (final WebClientResponseException ex) {
            log.error("WebClientResponseException when attempting to retrieve movie by ID {} | Status Code {} | Message {}", movieId, ex.getStatusCode(), ex.getResponseBodyAsString());
            throw new MovieErrorResponse(ex.getStatusText(), ex);
        } catch (final Throwable throwable) {
            log.error("Exception when attempting to retrieve movie by ID {}", movieId, throwable);
            throw new MovieErrorResponse(throwable);
        }
    }

    public List<Movie> retrieveMovieByName(final String movieName) {
        final String retrieveByNameUri = UriComponentsBuilder.fromUriString(GET_MOVIE_BY_NAME)
                .queryParam("movie_name", movieName)
                .buildAndExpand()
                .toUriString();

        try {
            return webClient.get()
                    .uri(retrieveByNameUri)
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .collectList()
                    .block();
        } catch (final WebClientResponseException ex) {
            log.error("WebClientResponseException when attempting to retrieve movie by name {} | Status Code {} | Message {}", movieName, ex.getStatusCode(), ex.getResponseBodyAsString());
            throw new MovieErrorResponse(ex.getStatusText(), ex);
        } catch (final Throwable throwable) {
            log.error("Exception when attempting to retrieve movie by name {}", movieName, throwable);
            throw new MovieErrorResponse(throwable);
        }
    }

    public List<Movie> retrieveMovieByYear(final int movieYear) {
        final String retrieveByYearUri = UriComponentsBuilder.fromUriString(GET_MOVIE_BY_YEAR)
                .queryParam("year", movieYear)
                .buildAndExpand()
                .toUriString();

        try {
            return webClient.get()
                    .uri(retrieveByYearUri)
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .collectList()
                    .block();
        } catch (final WebClientResponseException ex) {
            log.error("WebClientResponseException when attempting to retrieve movie by year {} | Status Code {} | Message {}", movieYear, ex.getStatusCode(), ex.getResponseBodyAsString());
            throw new MovieErrorResponse(ex.getStatusText(), ex);
        } catch (final Throwable throwable) {
            log.error("Exception when attempting to retrieve movie by year {}", movieYear, throwable);
            throw new MovieErrorResponse(throwable);
        }
    }
}
