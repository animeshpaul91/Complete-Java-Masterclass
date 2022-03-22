package com.learnjava.completablefutures;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;

public class CompletableFutureExceptionHandling {
    private final HelloWorldService helloWorldService;

    public CompletableFutureExceptionHandling() {
        this.helloWorldService = new HelloWorldService();
    }

    public String thenCombineExampleWithThreeAsyncCallsHandleException() {
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> hiCF = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future!";
        });

        String helloWorld = helloCF
                .handle((previous, exception) -> {
                    // log("Exception Thrown: " + exception);
                    return "";
                })
                .thenCombine(worldCF, (helloString, worldString) -> helloString + worldString)
                .thenCombine(hiCF, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        stopWatchReset();
        return helloWorld;
    }
}
