package com.future.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class CallableSumExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Instant start = Instant.now();
        System.out.println("Main Start time " + start);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Adder adder1 = new Adder(1,10);
        Future <Integer> future1 =  executor.submit(adder1);
        Adder adder2 = new Adder(11,20);
        Future <Integer> future2 =  executor.submit(adder2);
        Adder adder3 = new Adder(21,30);
        Future <Integer> future3 =  executor.submit(adder3);

        //System.out.println(future1.get());
        //System.out.println(future2.get());
        System.out.println(future1.get()+ future2.get() + future3.get());

        Instant end = Instant.now();
        System.out.println("Main End time " + end);
        sleep(300);
        executor.shutdown();
        }

    }

