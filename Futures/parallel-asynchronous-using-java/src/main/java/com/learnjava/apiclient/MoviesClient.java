package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MoviesClient {

    private final WebClient webClient;

    public MoviesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(String movieInfoId) {
        // Need MovieInfo
        var movieInfo = invokeMovieInfoService(movieInfoId);
        // Need Review Data
        var reviews = invokeReviewsService(movieInfoId);
        return new Movie(movieInfo, reviews);
    }

    public CompletableFuture<Movie> retrieveMovieCF(String movieInfoId) {
        // Need MovieInfo
        var movieInfoCF = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));
        // Need Review Data
        var reviewsCF = CompletableFuture.supplyAsync(() -> invokeReviewsService(movieInfoId));
        return movieInfoCF.thenCombine(reviewsCF, Movie::new);
    }

    private MovieInfo invokeMovieInfoService(String movieInfoId) {
        var moviesInfoUrlPath = "/v1/movie_infos/{movieInfoId}";

        return webClient
                .get()
                .uri(moviesInfoUrlPath, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)// used for single objects
                .block();
    }

    private List<Review> invokeReviewsService(String movieInfoId) {
        var reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId", movieInfoId)
                .buildAndExpand()
                .toString();

        return webClient
                .get()
                .uri(reviewUri)
                .retrieve()
                .bodyToFlux(Review.class)// used for collections
                .collectList()
                .block();
    }
}
