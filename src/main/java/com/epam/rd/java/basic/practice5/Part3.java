package com.epam.rd.java.basic.practice5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {

    private int counter;
    private int counter2;
    private static final int QUANTITY_THREADS = 10;
    private static final Logger LOGGER = Logger.getLogger(Part3.class.getName());

    public Part3(int numberOfThreads, int numberOfIterations) {}
    
    public Part3() {}

    public static void main(final String[] args) {

        Part3 part3 = new Part3();

        part3.compare();
        part3.compareSync();

    }

    public void compare() {

        ExecutorService executorService = Executors.newFixedThreadPool(QUANTITY_THREADS);

        for(int i = 0; i < QUANTITY_THREADS; i++){

            executorService.submit(new MyThread());

        }

        executorService.shutdownNow();

    }

    public void compareSync() {

        ExecutorService executorService = Executors.newFixedThreadPool(QUANTITY_THREADS);

        for(int i = 0; i < QUANTITY_THREADS; i++){

            synchronized (this) {

                executorService.submit(new MyThread());

            }
        }

        executorService.shutdownNow();

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
                LOGGER.log(Level.SEVERE, "InterruptedException", e);
            }

            counter2++;


        }
    }
}
