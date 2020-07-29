package com.epam.rd.java.basic.practice5;

public class Part1 {

    private static final double TIME = (double) 1/3;

    public static void main(String[] args) {

        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        Thread myThread2 = new Thread(new MyThread2());
        myThread2.start();

        try {

            myThread1.join();
            myThread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class MyThread1 extends Thread {

        @Override
        public void run() {

            double timePassed = 0;
            String threadName = this.getName();

            while (timePassed <= 2.0){
                System.out.println(threadName);
                timePassed += TIME;
            }

        }
    }

    private static class MyThread2 implements Runnable {

        @Override
        public void run() {

            double timePassed = 0;
            String threadName = Thread.currentThread().getName();

            while (timePassed <= 2.0){
                System.out.println(threadName);
                timePassed += TIME;
            }

        }
    }

}
