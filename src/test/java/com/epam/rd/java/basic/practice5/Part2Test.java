package com.epam.rd.java.basic.practice5;

import org.junit.Assert;
import org.junit.Test;

public class Part2Test {

    @Test
    public void Part2MainTest() {

        Part2 part2 = new Part2();

        Assert.assertNotNull(part2);

    }

    @Test
    public void Part2MyInputStreamTest() {

        Part2.MyInputStream myInputStream = new Part2.MyInputStream();

        Assert.assertNotNull(myInputStream);

    }

}