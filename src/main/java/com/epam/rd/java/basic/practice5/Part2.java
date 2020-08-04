package com.epam.rd.java.basic.practice5;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {

    private static final Logger LOGGER = Logger.getLogger(Part2.class.getName());
    private static final String MESSAGE_INTERRUPTED_EXCEPTION = "Interrupted exception";
    private static final InputStream YOUR_OWN_INPUT_STREAM = new MyInputStream();
    private static final InputStream CACHED_VALUE_OF_SYSTEM_IN = System.in;

    public static void main(final String[] args) {

        System.setIn (YOUR_OWN_INPUT_STREAM);

        Thread t = new Thread(() -> Spam.main (null));
        t.start ();

        try {
            t.join ();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
            Thread.currentThread().interrupt();
        }

        System.setIn (CACHED_VALUE_OF_SYSTEM_IN);

    }

    public static class MyInputStream extends InputStream {

        private int index;
        int indexN;

        @Override
        public int read() {

            byte[] separatorArray = System.lineSeparator().getBytes();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
                Thread.currentThread().interrupt();
            }

            if(index != 0) {
                return -1;
            }

            index++;
            return separatorArray[0] & 0xFF;

        }

        @Override
        public int read(byte[] b,int in,int itr) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, MESSAGE_INTERRUPTED_EXCEPTION, e);
                Thread.currentThread().interrupt();
            }

            byte [] separatorArr = System.lineSeparator().getBytes();

            if (indexN != 0) {
                return -1;
            }

            indexN++;
            b[0] = (byte) (separatorArr[0] & 0xFF);

            return 1;
        }
    }

}
