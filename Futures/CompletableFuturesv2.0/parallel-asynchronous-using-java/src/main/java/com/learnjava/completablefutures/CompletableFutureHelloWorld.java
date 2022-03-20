package com.learnjava.completablefutures;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {
    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();
        CompletableFuture.supplyAsync(helloWorldService::hello)
                .thenAccept(result -> log("Result is: " + result)).join();
        log("Done");
    }
}
