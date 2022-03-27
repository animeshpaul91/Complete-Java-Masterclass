package com.learnjava.apiclient;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.web.reactive.function.client.WebClient;

import static com.learnjava.util.CommonUtil.*;
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

    @RepeatedTest(10)
    public void testRetrieveMovie() {
        String movieInfoId = "1";
        startTimer();
        var movie = moviesClient.retrieveMovie(movieInfoId);
        timeTaken();
        System.out.println("Movie: " + movie);
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
        stopWatchReset();
    }

    @RepeatedTest(10)
    public void testRetrieveMovieUsingCF() {
        String movieInfoId = "1";
        startTimer();
        var movie = moviesClient.retrieveMovieCF(movieInfoId).join();
        timeTaken();
        System.out.println("Movie: " + movie);
        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
        stopWatchReset();
    }
}