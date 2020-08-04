package com.epam.rd.java.basic.practice5;

import org.junit.Assert;
import org.junit.Test;

public class Part5Test {

    @Test
    public void Part5MainTest() {

        Part5 part5 = new Part5();

        Assert.assertNotNull(part5);

    }

    @Test
    public void Part5MyThreadTest() {

        Part5.MyThread myThread = new Part5.MyThread();

        Assert.assertNotNull(myThread);

    }

}