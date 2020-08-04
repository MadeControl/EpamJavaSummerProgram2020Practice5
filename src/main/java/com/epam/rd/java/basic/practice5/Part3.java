package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {

    private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());
    private static final String MESSAGE_INTERRUPTED_EXCEPTION = "Interrupted exception";
    private final Object mutex = new Object();
    private int counter;
    private int counter2;
    private final int numberOfThreads;
    private final int numberOfIterations;

    public Part3(int numberOfThreads, int numberOfIterations) {

        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;

    }

    public static void main(String[] args) {

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

        Thread[] threads = new Thread[numberOfThreads];
        counter = 0;
        counter2 = 0;

        for(int i = 0; i < numberOfThreads; i++) {

            Thread thread = new Thread(new MyThread());
            threads[i] = thread;
            thread.start();

        }

        for(int i = 0; i < numberOfThreads; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
                Thread.currentThread().interrupt();
            }

        }

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

        Thread[] threads = new Thread[numberOfThreads];
        counter = 0;
        counter2 = 0;

        for(int i = 0; i < numberOfThreads; i++) {

            Thread thread = new Thread(new MyThreadSync());
            threads[i] = thread;
            thread.start();

        }

        for(int i = 0; i < numberOfThreads; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
                Thread.currentThread().interrupt();
            }

        }

    }

    private void myRun() {

        for(int i = 0; i < numberOfIterations; i++) {

            System.out.println(counter + " == " + counter2);

            counter++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
                Thread.currentThread().interrupt();
            }

            counter2++;

        }

    }

    public class MyThread implements Runnable {

        @Override
        public void run() {

            myRun();

        }
    }

    public class MyThreadSync implements Runnable {

        @Override
        public void run() {

            synchronized (mutex) {

                myRun();

            }

        }
    }

}