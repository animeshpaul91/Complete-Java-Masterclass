package com.future.threads;

import java.time.Instant;
import java.util.concurrent.*;

import static java.lang.Thread.*;


public class MyThread extends Thread {

    public void run(){
        for (int i = 0; i<100;i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread running");
        }
    }
}