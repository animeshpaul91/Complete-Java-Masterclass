package com.learnjava.apiclient;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MovieClientTest {
    private final MoviesClient moviesClient;

    public MovieClientTest() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/movies")
                .build();
        this.moviesClient = new MoviesClient(webClient);
    }

    @Test
    public void testRetrieveMovie() {
        String movieInfoId = "1";
        var movie = moviesClient.retrieveMovie(movieInfoId);
        System.out.println("Movie: " + movie);
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
    }

}