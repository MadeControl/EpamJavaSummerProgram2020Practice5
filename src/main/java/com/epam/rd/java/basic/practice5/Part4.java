package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part4 {

    private static final String FILE_NAME = "part4.txt";
    private static final Logger LOGGER = Logger.getLogger(Part4.class.getName());

    public static void main(final String[] args) {

        System.out.println(executeWithSingleThreading());
        System.out.println(executeWithMultiThreading());

    }

    private static String executeWithMultiThreading(){

        long startTime = System.currentTimeMillis();

        String matrixDigitsInString = readFromFile();
        String[] stringsMatrix = matrixDigitsInString.split(System.lineSeparator());
        int[] arrayMaxValuesOfEachStringMatrix = new int[stringsMatrix.length];

        for (int i = 0; i < stringsMatrix.length; i++){

            MyThread thread = new MyThread(stringsMatrix[i]);
            thread.start();

            pauseTenMilliSeconds();

            arrayMaxValuesOfEachStringMatrix[i] = thread.maxValue;

        }

        int maxValue = Arrays
                        .stream(arrayMaxValuesOfEachStringMatrix)
                        .max()
                        .getAsInt();

        long endTime = System.currentTimeMillis();

        return maxValue + System.lineSeparator() + (endTime - startTime);
    }

    private static String executeWithSingleThreading(){

        long startTime = System.currentTimeMillis();

        String matrixDigitsInString = readFromFile();
        String stringsMatrix = matrixDigitsInString.replace(System.lineSeparator(), " ");

        MyThread singleThread = new MyThread(stringsMatrix);
        singleThread.start();

        pauseTenMilliSeconds();

        int maxValue = singleThread.maxValue;

        long endTime = System.currentTimeMillis();

        return maxValue + System.lineSeparator() + (endTime - startTime);

    }

    private static void pauseTenMilliSeconds(){

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Interrupted exception", e);
            Thread.currentThread().interrupt();
        }

    }

    private static String readFromFile() {

        StringBuilder stringBuilder = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))){

            String string;

            while ((string = bufferedReader.readLine()) != null){
                stringBuilder.append(string).append(System.lineSeparator());
            }

        } catch (IOException ex){
            LOGGER.log(Level.SEVERE, "IOException", ex);
        }

        return stringBuilder
                .deleteCharAt(stringBuilder.length()-1)
                .toString();
    }

    private static class MyThread extends Thread {

        private int maxValue = 0;
        private final String stringOfDigits;

        public MyThread(String stringOfDigits) {
            this.stringOfDigits = stringOfDigits;
        }

        @Override
        public void run() {

            String[] arrayOfDigitsInString = stringOfDigits.split(" ");

            for(String digitInString : arrayOfDigitsInString){

                int digit = Integer.parseInt(digitInString.trim());

                if(maxValue < digit){
                    maxValue = digit;
                }

            }

        }

    }

}
