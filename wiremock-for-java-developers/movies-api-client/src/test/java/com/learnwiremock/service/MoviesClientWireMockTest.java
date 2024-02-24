package com.learnwiremock.service;

import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.learnwiremock.dto.Movie;
import com.learnwiremock.exceptions.MovieErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Month;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.learnwiremock.constants.MoviesAppConstants.GET_ALL_MOVIES_V1;
import static com.learnwiremock.constants.MoviesAppConstants.GET_MOVIE_BY_NAME;
import static com.learnwiremock.constants.MoviesAppConstants.GET_MOVIE_BY_YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(WireMockExtension.class)
class MoviesClientWireMockTest {

    private MoviesClient moviesClient;

    @InjectServer
    private WireMockServer wireMockServer;

    @ConfigureWireMock
    private final Options options = wireMockConfig()
            .port(8088)
            .notifier(new ConsoleNotifier(true))
            .extensions(new ResponseTemplateTransformer(true));

    @BeforeEach
    void setUp() {
        int port = wireMockServer.port();
        final String baseUrl = String.format("http://localhost:%s", port);
        WebClient webClient = WebClient.create(baseUrl);
        moviesClient = new MoviesClient(webClient);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRetrieveAllMovies() {
        // given
        stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("all-movies.json")));

        // when
        final var moviesList = moviesClient.retrieveAllMovies();
        System.out.println(moviesList);

        // then
        assertFalse(moviesList.isEmpty());
    }

    @Test
    void testRetrieveAllMoviesMatchesURLPattern() {
        // given
        stubFor(get(urlPathEqualTo(GET_ALL_MOVIES_V1))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("all-movies.json")));

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
        stubFor(get(urlMatching("/movieservice/v1/movie/[0-9]"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie.json")));

        // when
        final var movie = moviesClient.retrieveMovieById(movieId);
        System.out.println(movie);

        // then
        assertNotNull(movie);
        assertNotNull(movie.getMovie_id());
    }

    @Test
    void testGetMovieByIdValidMovieResponseTemplating() {
        // given
        stubFor(get(urlMatching("/movieservice/v1/movie/[0-9]"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-template.json")));

        // when
        final Integer movieId = 7;
        final var movie = moviesClient.retrieveMovieById(movieId);
        System.out.println(movie);

        // then
        assertNotNull(movie);
        assertEquals(movieId, movie.getMovie_id().intValue());
    }

    @Test
    void testGetMovieByIdInvalidMovie() {
        // given
        final Integer movieId = 100;
        stubFor(get(urlMatching("/movieservice/v1/movie/[0-9]+"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-404.json")));

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveMovieById(movieId));
    }

    @Test
    void testGetMovieByNameValidMovie() {
        // given
        final String movieName = "Avengers";
        final String urlPattern = GET_MOVIE_BY_NAME + "?movie_name=" + movieName;
        stubFor(get(urlEqualTo(urlPattern))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("avengers.json")));

        // when
        final var moviesList = moviesClient.retrieveMovieByName(movieName);
        System.out.println(moviesList);

        // then
        assertFalse(moviesList.isEmpty());
        assertEquals(4, moviesList.size());
    }

    @Test
    void testGetMovieByNameValidMovieApproach2() {
        // given
        final String movieName = "Avengers";
        stubFor(get(urlPathEqualTo(GET_MOVIE_BY_NAME))
                .withQueryParam("movie_name", equalTo(movieName))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("avengers.json")));

        // when
        final var moviesList = moviesClient.retrieveMovieByName(movieName);
        System.out.println(moviesList);

        // then
        assertFalse(moviesList.isEmpty());
        assertEquals(4, moviesList.size());
    }

    @Test
    void testGetMovieByNameValidMovieResponseTemplating() {
        // given
        final String movieName = "Avengers";
        final String urlPattern = GET_MOVIE_BY_NAME + "?movie_name=" + movieName;
        stubFor(get(urlEqualTo(urlPattern))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-by-name.json")));

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
        final String urlPattern = GET_MOVIE_BY_NAME + "?movie_name=" + movieName;
        stubFor(get(urlEqualTo(urlPattern))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-404.json")));

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveMovieByName(movieName));
    }

    @Test
    void testGetMovieByYearValidMovie() {
        // given
        final int movieYear = 2012;
        final String urlPattern = GET_MOVIE_BY_YEAR + "?year=" + movieYear;
        stubFor(get(urlEqualTo(urlPattern))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-by-year.json")));

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
        final String urlPattern = GET_MOVIE_BY_YEAR + "?year=" + movieYear;
        stubFor(get(urlEqualTo(urlPattern))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("movie-by-year-404.json")));

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveMovieByYear(movieYear));
    }

    @Test
    void testCreateMovieValidMovie() {
        // given
        final LocalDate releaseDate = LocalDate.of(1993, Month.DECEMBER, 15);
        final Movie movie = new Movie(null, "Schindler's List", "Liam Neeson, Ben Kingsley", 1993, releaseDate);

        // when
        final var createdMovie = moviesClient.createMovie(movie);
        System.out.println(createdMovie);

        // then
        assertNotNull(createdMovie.getMovie_id());
    }

    @Test
    void testCreateMovieBadMovie() {
        // given
        final LocalDate releaseDate = LocalDate.of(1993, Month.DECEMBER, 15);
        final Movie movie = new Movie(null, null, "Liam Neeson, Ben Kingsley", 1993, releaseDate);

        // when
        final String expectedMessage = "Please pass all input fields";
        assertThrows(MovieErrorResponse.class, () -> moviesClient.createMovie(movie), expectedMessage);
    }

    @Test
    void testUpdateWithValidMovie() {
        // given
        final Integer movieId = 3;
        final String cast = "abc";
        final Movie movie = new Movie();
        movie.setCast(cast);

        // when
        final var updatedMovie = moviesClient.updateMovie(movieId, movie);

        // then
        assertTrue(updatedMovie.getCast().contains(cast));
    }

    @Test
    void testUpdateWithInvalidMovie() {
        // given
        final Integer movieId = 100;
        final String cast = "abc";
        final Movie movie = new Movie();
        movie.setCast(cast);

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.updateMovie(movieId, movie));
    }

    @Test
    void testDeleteValidMovie() {
        // given
        // first create movie
        final LocalDate releaseDate = LocalDate.of(1993, Month.DECEMBER, 15);
        final Movie movie = new Movie(null, "New Movie to be deleted", "Liam Neeson, Ben Kingsley", 1993, releaseDate);
        final var createdMovie = moviesClient.createMovie(movie);

        // when
        // then delete
        final String expectedMessage = "Movie Deleted Successfully";
        final String actualMessage = moviesClient.deleteMovie(createdMovie.getMovie_id().intValue());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testDeleteInvalidMovie() {
        // given
        final Integer movieId = 100;

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.deleteMovie(movieId));
    }
}
