package com.learnjava.completablefutures;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CompletableFutureExceptionHandlingTest {

    @Mock
    private HelloWorldService helloWorldService;

    @InjectMocks
    private CompletableFutureExceptionHandling cfEH;

    @Test
    void thenCombineExampleWithThreeAsyncCallsHandleException() {
        // Given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod(); // Cool Stuff!

        // When
        String result = cfEH.thenCombineExampleWithThreeAsyncCallsHandleException();

        // Then
        assertEquals(" WORLD! HI COMPLETABLE FUTURE!", result);
    }
}