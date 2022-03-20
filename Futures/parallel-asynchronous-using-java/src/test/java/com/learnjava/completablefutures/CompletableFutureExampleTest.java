package com.learnjava.completablefutures;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletableFutureExampleTest {
    private final CompletableFutureExample completableFutureExample;

    public CompletableFutureExampleTest() {
        this.completableFutureExample = new CompletableFutureExample();
    }

    @Test
    public void testHelloWorldCF() {
        CompletableFuture<String> cfh = completableFutureExample.helloWorld();
        cfh.thenAccept(string -> { // without join, the main thread won't even go inside the lambda block
            log("Thread still executes this block of code");
            assertEquals(string.toUpperCase(), string);
            assertEquals("HELLO WORLD", string);
        }).join();
    }

    @Test
    public void testHelloWorldWithSizeCF() {
        CompletableFuture<String> cfh = completableFutureExample.helloWorldWithSize();
        cfh.thenAccept(string -> { // without join, the main thread won't even go inside the lambda block
            log("Thread still executes this block of code");
            assertEquals("11 - HELLO WORLD", string);
        }).join();
    }
}