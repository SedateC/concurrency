package com.sedatec.concurrent.classload.chaper5;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicLongTest {
    @Test
    public void testCreate(){
        AtomicLong atomicLong = new AtomicLong(100L);
        /*
        * int 32
        *
        * long 64
        *
        * height32
        * low 32
        *
        * */
        assertEquals(100L,atomicLong.get());
    }
}
