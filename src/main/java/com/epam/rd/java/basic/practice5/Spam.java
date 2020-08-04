package com.epam.rd.java.basic.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spam {

    private static final Logger LOGGER = Logger.getLogger(Spam.class.getName());
    private Thread[] threads;
    private final String[] messages;
    private final int[] times;

    public Spam(final String[] messages, final int[] delays) {
        this.messages = messages;
        this.times = delays;
    }

    public static void main(final String[] args) {

        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };

        Spam spam = new Spam(messages, times);
        spam.threads = new Thread[messages.length];

        spam.start();
        spam.stop();

    }

    public void start() {

        for(int i = 0; i < messages.length; i++) {

            String message = messages[i];
            int time = times[i];

            Thread thread = new Worker(message, time);
            threads[i] = thread;
            thread.start();

        }

        waitPrintEnter();

    }

    public void stop() {

        for(Thread thread : threads) {
            thread.interrupt();
        }

    }

    private void waitPrintEnter() {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {

            while (!((bufferedReader.readLine()).equals("")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Worker extends Thread {

        private String message;
        private int time;

        public Worker(String message, int time) {
            this.message = message;
            this.time = time;
        }

        @Override
        public void run() {

            while (!isInterrupted()) {
                threadAction();
            }
        }

        private void threadAction() {

            System.out.println(message);

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Interrupted exception", e);
                Thread.currentThread().interrupt();
            }
        }
    }

}
