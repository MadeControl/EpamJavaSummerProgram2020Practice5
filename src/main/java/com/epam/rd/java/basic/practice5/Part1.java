package com.epam.rd.java.basic.practice5;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {

    private static final double TIME = (double) 1/2;
    private static final Logger LOGGER = Logger.getLogger(Part1.class.getName());

    public static void main(String[] args) {

        Thread myThread1 = new MyThread1();
        myThread1.start();

//        try {
//            TimeUnit.MILLISECONDS.sleep(2000);
//        } catch (InterruptedException e) {
//            LOGGER.log(Level.SEVERE, "Interrupted exception", e);
//            Thread.currentThread().interrupt();
//        }
//
//        Thread myThread2 = new Thread(new MyThread2());
//        myThread2.start();

    }

    private static void myRun(){

        double timePassed = TIME;
        long timeEvery = (long) (TIME * 1000);
        String threadName = Thread.currentThread().getName();

        while (timePassed <= 2.0) {

            System.out.println(threadName);
            timePassed += TIME;

            try {
                Thread.sleep(timeEvery);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Interrupted exception", e);
                Thread.currentThread().interrupt();
            }

        }

    }

    private static class MyThread1 extends Thread {

        @Override
        public void run() {

            myRun();
            Thread myThread2 = new Thread(new MyThread2());
            myThread2.start();

        }
    }

    private static class MyThread2 implements Runnable {

        @Override
        public void run() {

            myRun();

        }
    }

}
