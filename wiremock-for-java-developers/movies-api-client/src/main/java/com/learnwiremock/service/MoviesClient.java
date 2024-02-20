package com.learnwiremock.service;

import com.learnwiremock.dto.Movie;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.learnwiremock.constants.MoviesAppConstants.GET_ALL_MOVIES_V1;

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
}
