package com.learnjava.completablefutures;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static com.learnjava.util.CommonUtil.*;

public class CompletableFutureExample {

    private final Supplier<String> helloSupplier;
    private final Supplier<String> worldSupplier;

    public CompletableFutureExample() {
        HelloWorldService helloWorldService = new HelloWorldService();
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
}
