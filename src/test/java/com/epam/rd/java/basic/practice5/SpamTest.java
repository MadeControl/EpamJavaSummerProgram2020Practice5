package com.epam.rd.java.basic.practice5;

import org.junit.Assert;
import org.junit.Test;

public class SpamTest {

    @Test
    public void SpamMainTest(){

        final String[] messages = new String[] { "@@@", "bbbbbbb" };
        final int[] times = new int[] { 333, 222 };

        Spam spam = new Spam(messages, times);

        Assert.assertNotNull(spam);

    }

}