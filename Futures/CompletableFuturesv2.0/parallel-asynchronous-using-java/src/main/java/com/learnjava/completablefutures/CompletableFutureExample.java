package com.learnjava.completablefutures;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureExample {

    private final Supplier<String> hwsSupplier;

    public CompletableFutureExample() {
        HelloWorldService helloWorldService = new HelloWorldService();
        this.hwsSupplier = helloWorldService::helloWorld;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(hwsSupplier) // This will spawn a new Thread from ForkJoin Pool and main thread immediately returns
                .thenApply(String::toUpperCase);
    }
}
