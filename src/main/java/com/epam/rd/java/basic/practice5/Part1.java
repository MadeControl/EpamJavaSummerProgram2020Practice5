package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {

    private static final double TIME = (double) 1/2;
    private static final Logger LOGGER = Logger.getLogger(Part1.class.getName());
    private static final String MESSAGE_INTERRUPTED_EXCEPTION = "Interrupted exception";

    public static void main(String[] args) {

        Thread myThread1 = new MyThread1();
        myThread1.start();

        try {
            myThread1.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
            Thread.currentThread().interrupt();
        }

        Thread myThread2 = new Thread(new MyThread2());
        myThread2.start();

        try {
            myThread2.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
            Thread.currentThread().interrupt();
        }

    }

    private static void myRun(){

        double timePassed = TIME;
        long timeEvery = (long) (TIME * 1000);
        String threadName = Thread.currentThread().getName();

        while (timePassed <= 2.0) {

            timePassed += TIME;

            System.out.println(threadName);

            try {
                Thread.sleep(timeEvery);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
                Thread.currentThread().interrupt();
            }

        }

    }

    public static class MyThread1 extends Thread {

        @Override
        public void run() {

            myRun();

        }
    }

    public static class MyThread2 implements Runnable {

        @Override
        public void run() {

            myRun();

        }
    }

}
