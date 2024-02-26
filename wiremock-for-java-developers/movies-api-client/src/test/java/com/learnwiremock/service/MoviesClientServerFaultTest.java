package com.learnwiremock.service;

import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.Fault;
import com.learnwiremock.exceptions.MovieErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.serverError;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(WireMockExtension.class)
public class MoviesClientServerFaultTest {
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
    void testRetrieveAllMoviesServerError() {
        // given
        stubFor(get(anyUrl())
                .willReturn(serverError()));

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveAllMovies());
    }

    @Test

    void testRetrieveAllMovies503ServiceUnavailable() {
        // given
        final var errorMessage = "Service Unavailable";
        stubFor(get(anyUrl())
                .willReturn(serverError()
                        .withStatus(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .withBody(errorMessage)));

        // when and then
        final var movieErrorResponse = assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveAllMovies());
        assertEquals(errorMessage, movieErrorResponse.getMessage());
    }

    @Test // simulate an instance of a premature close connection
    void testRetrieveAllMoviesFaultResponse() {
        // given
        final String expectedErrorMessage = "reactor.netty.http.client.PrematureCloseException: Connection prematurely closed BEFORE response";
        stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withFault(Fault.EMPTY_RESPONSE)));

        // when and then
        final var movieErrorResponse = assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveAllMovies());
        assertTrue(movieErrorResponse.getMessage().contains(expectedErrorMessage));
    }

    @Test// simulate an instance of a premature close connection
    void testRetrieveAllMoviesRandomDataAndClose() {
        // given
        stubFor(get(anyUrl())
                .willReturn(aResponse()
                        .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));

        // when and then
        assertThrows(MovieErrorResponse.class, () -> moviesClient.retrieveAllMovies());
    }
}
