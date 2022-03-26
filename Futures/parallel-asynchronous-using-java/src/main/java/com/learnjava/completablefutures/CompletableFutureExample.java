package com.learnjava.completablefutures;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureExample {

    private final HelloWorldService helloWorldService;
    private final Supplier<String> helloSupplier;
    private final Supplier<String> worldSupplier;

    public CompletableFutureExample() {
        this.helloWorldService = new HelloWorldService();
        this.helloSupplier = helloWorldService::hello;
        this.worldSupplier = helloWorldService::world;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloSupplier) // This will spawn a new Thread from ForkJoin Pool and main thread immediately returns
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorldWithSize() {
        return CompletableFuture.supplyAsync(helloSupplier) // This will spawn a new Thread from ForkJoin Pool and main thread immediately returns
                .thenApply(string -> string.length() + " - " + string.toUpperCase());
    }

    public String thenCombineExample() {
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(helloSupplier);
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(worldSupplier);

        String helloWorld = helloCF.thenCombine(worldCF, (helloString, worldString) -> helloString + worldString)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        stopWatchReset();
        return helloWorld;
    }

    public String thenCombineExampleWithThreeAsyncCalls() {
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(helloSupplier);
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(worldSupplier);
        CompletableFuture<String> hiCF = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future!";
        });

        String helloWorld = helloCF.thenCombine(worldCF, (helloString, worldString) -> helloString + worldString)
                .thenCombine(hiCF, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        stopWatchReset();
        return helloWorld;
    }

    public String thenCombineExampleWithThreeAsyncCallsLog() {
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(helloSupplier);
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(worldSupplier);
        CompletableFuture<String> hiCF = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future!";
        });

        String helloWorld = helloCF.thenCombine(worldCF, (helloString, worldString) -> {
                    log("Inside helloCF thenCombine");
                    return helloString + worldString;
                })
                .thenCombine(hiCF, (previous, current) -> {
                    log("Inside hiCF thenCombine");
                    return previous + current;
                })
                .thenApply(string -> {
                    log("Inside thenApply");
                    return string.toUpperCase();
                })
                .join();

        timeTaken();
        stopWatchReset();
        return helloWorld;
    }

    public String thenCombineExampleWithFourAsyncCalls() {
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(helloSupplier);
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(worldSupplier);
        CompletableFuture<String> hiCF = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi Completable Future!";
        });
        CompletableFuture<String> udemyCF = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " This course is great!";
        });

        String helloWorld = helloCF.thenCombine(worldCF, (helloString, worldString) -> helloString + worldString)
                .thenCombine(hiCF, (previous, current) -> previous + current)
                .thenCombine(udemyCF, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        stopWatchReset();
        return helloWorld;
    }

    public CompletableFuture<String> helloWorldThenCompose() {
        log("inside helloWorldThenCompose method");
        return CompletableFuture.supplyAsync(helloSupplier)
                .thenCompose(helloWorldService::worldFuture)
                // thenCompose is a dependent task. It waits for the hello task to complete and because of this the same thread that executes the hello task also executes the world task
                .thenApply(String::toUpperCase); // thenApply is also a dependent task. thenCombine is not
    }
}
