package com.epam.rd.java.basic.practice5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {

    private int counter;
    private int counter2;
    private final int numberOfThreads;
    private final int numberOfIterations;
    private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
    }
    
    public static void main(final String[] args) {

        Part3 part3 = new Part3(5, 10);

        part3.compare();
        part3.compareSync();

    }

    /**
     * Should create {@code numberOfThreads} threads
     * that will {@code numberOfIterations} times
     * print "counter == counter2" and increase counters.
     * Between increasing first and second counter
     * must be delay equals 100 milliseconds.
     *
     * This method should wait until threads will finish their work.
     */

    public void compare() {

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for(int i = 0; i < numberOfIterations; i++){

            executorService.submit(new MyThread());

        }

        executorService.shutdown();

    }

    /**
     * Should create {@code numberOfThreads} threads
     * that will {@code numberOfIterations} times synchronously
     * print "counter == counter2" and increase counters.
     * Between increasing first and second counter
     * must be delay equals 100 milliseconds.
     *
     * This method should wait until threads will finish their work.
     */

    public void compareSync() {

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for(int i = 0; i < numberOfIterations; i++){

            synchronized (this) {

                executorService.submit(new MyThread());

            }
        }

        executorService.shutdown();

    }

    private class MyThread implements Runnable {

        @Override
        public void run() {

            System.out.println(counter == counter2);

            counter++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "InterruptedException", e);
            }

            counter2++;


        }
    }
}
