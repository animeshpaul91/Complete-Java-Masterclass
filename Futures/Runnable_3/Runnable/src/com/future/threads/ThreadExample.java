package com.future.threads;


public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        //Runnable Implemented as Anonymous inner class
        Runnable runnable = () -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("MyRunnable running");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("Main running");
        }
    }
}

