package com.future.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.function.Supplier;

import static java.lang.Thread.sleep;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        for (int i = 0; i<20;i++) {
            System.out.println("Main running");
        }
    }
}

