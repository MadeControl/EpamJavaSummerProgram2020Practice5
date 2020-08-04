package com.epam.rd.java.basic.practice5;

import org.junit.Assert;
import org.junit.Test;

public class Part1Test {


    @Test
    public void MyThread1Test(){

        Thread thread = new Part1.MyThread1();

        Assert.assertNotNull(thread);

    }

    @Test
    public void MyThread2Test(){

        Thread thread = new Thread(new Part1.MyThread2());

        Assert.assertNotNull(thread);

    }

}