package com.epam.rd.java.basic.practice5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Part3 {

    private int counter;
    private int counter2;
    private final int quantityThreads = 10;

    public static void main(final String[] args) {

        Part3 part3 = new Part3();
        part3.compare();
        part3.compareSync();

    }

    public void compare() {

        ExecutorService executorService = Executors.newFixedThreadPool(quantityThreads);

        for(int i = 0; i < quantityThreads; i++){
            Thread thread = new Thread(new MyThread());
            executorService.execute(thread);
            
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

    }

    public void compareSync() {

        ExecutorService executorService = Executors.newFixedThreadPool(quantityThreads);

        for(int i = 0; i < quantityThreads; i++){

            synchronized (this) {

                Thread thread = new Thread(new MyThread());
                executorService.execute(thread);

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        executorService.shutdown();

    }

    private class MyThread implements Runnable {

        @Override
        public void run() {

            boolean result = counter == counter2;

            System.out.println(result);

            counter++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter2++;

        }
    }
}
