package com.future.threads;

import java.time.Instant;
import java.util.concurrent.*;

import static java.lang.Thread.*;


public class Adder implements Callable <Integer> {
    private int number1;
    private int number2;

    public Adder (Integer number1, Integer number2){
        this.number1 = number1;
        this.number2 = number2;
    }


    @Override
    public Integer call() throws InterruptedException {
        sleep(500);
        int sum = 0;
        for(number1 = number1; number1 <= number2; number1++) {
            sum = sum + number1;
        }
        return  sum;
    }

}