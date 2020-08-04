package com.epam.rd.java.basic.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

public class Part5 {

    private static final Logger LOGGER = Logger.getLogger(Part5.class.getName());
    private static final String FILE_NAME = "part5.txt";
    private static final String ACCESS_FILE = "rw";
    private static final int[] ARRAY_DIGITS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int TIMES = 20;
    private static RandomAccessFile file;
    private static int seek;
    private static int index;

    public static void main(final String[] args) {

        String content = "";

        try {

            file = new RandomAccessFile(FILE_NAME, ACCESS_FILE);
            seek = 0;
            index = 0;

            for(int i = 0; i < ARRAY_DIGITS.length; i++) {

                Thread thread = new Thread(new MyThread());
                thread.start();
                thread.join();
            }

            file.seek(0);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < ARRAY_DIGITS.length; i++) {
                stringBuilder
                        .append(file.readLine())
                        .append(System.lineSeparator());
            }

            content = stringBuilder.toString();

            file.close();

        } catch (IOException | InterruptedException e) {
            LOGGER.warning(e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.print(content);

    }

    public static class MyThread implements Runnable {

        @Override
        public void run() {

            synchronized (Part5.class) {

                String data = String.valueOf(ARRAY_DIGITS[index]);

                try {

                    for (int i = 0; i < TIMES; i++) {

                        file.seek(seek);
                        file.writeBytes(data);
                        seek++;

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            LOGGER.warning(e.getMessage());
                            Thread.currentThread().interrupt();
                        }

                    }

                    file.seek(seek);
                    file.writeBytes(System.lineSeparator());
                    seek++;

                } catch (IOException e) {
                    LOGGER.warning(e.getMessage());
                }
                index++;

            }

        }
    }



}
