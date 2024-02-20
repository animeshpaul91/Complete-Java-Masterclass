package com.learnwiremock.service;

import com.learnwiremock.dto.Movie;
import com.learnwiremock.exceptions.MovieErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MoviesClientTest {

    private MoviesClient moviesClient;

    @BeforeEach
    void setUp() {
        final String baseUrl = "http://localhost:8081";
        WebClient webClient = WebClient.create(baseUrl);
        moviesClient = new MoviesClient(webClient);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRetrieveAllMovies() {
        // when
        final var moviesList = moviesClient.retrieveAllMovies();
        System.out.println(moviesList);

        // then
        assertFalse(moviesList.isEmpty());
    }

    @Test
    void testGetMovieByIdValidMovie() {
        // given
        final Integer movieId = 1;

        // when
        final var movie = moviesClient.retrieveMovieById(movieId);
        System.out.println(movie);

        // then
        assertNotNull(movie);
        assertNotNull(movie.getMovie_id());
    }

    @Test
    void testGetMovieByIdInvalidMovie() {
        // given
        final Integer movieId = 100;

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveMovieById(movieId));
    }

    @Test
    void testGetMovieByNameValidMovie() {
        // given
        final String movieName = "Avengers";

        // when
        final var moviesList = moviesClient.retrieveMovieByName(movieName);
        System.out.println(moviesList);

        // then
        assertFalse(moviesList.isEmpty());
        assertEquals(4, moviesList.size());
    }

    @Test
    void testGetMovieByNameInvalidMovie() {
        // given
        final String movieName = "bogus";

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveMovieByName(movieName));
    }

    @Test
    void testGetMovieByYearValidMovie() {
        // given
        final int movieYear = 2012;

        // when
        final var moviesList = moviesClient.retrieveMovieByYear(movieYear);
        System.out.println(moviesList);

        // then
        assertFalse(moviesList.isEmpty());
        assertEquals(2, moviesList.size());
    }

    @Test
    void testGetMovieByYearInvalidMovie() {
        // given
        final int movieYear = 0;

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveMovieByYear(movieYear));
    }

    @Test
    void testCreateMovie() {
        // given
        final LocalDate releaseDate = LocalDate.of(1993, Month.DECEMBER, 15);
        final Movie movie = new Movie(null, "Schindler's List", "Liam Neeson, Ben Kingsley", 1993, releaseDate);

        // when
        final var createdMovie = moviesClient.createMovie(movie);
        System.out.println(createdMovie);

        // then
         assertNotNull(createdMovie.getMovie_id());
    }
}