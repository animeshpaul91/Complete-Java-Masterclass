package com.modernjava.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@Disabled
public class MoviesClientTest {
    private final MoviesClient moviesClient = new MoviesClient();
    @Test
    void getMovieById() {
        final Movie movie = moviesClient.getMovieById();
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.name());
    }
}
