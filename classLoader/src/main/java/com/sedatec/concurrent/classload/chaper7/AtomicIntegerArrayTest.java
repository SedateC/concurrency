package com.sedatec.concurrent.classload.chaper7;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.junit.Assert.assertEquals;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicIntegerArrayTest {
    @Test
    public void test(){
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(10);
        assertEquals(10,atomicArray.length());
    }

    @Test
    public void test2(){
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(10);
        assertEquals(10,atomicArray.length());
        assertEquals(0,atomicArray.get(5)); ;
    }

    @Test
    public void testGetAndSet(){
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(10);
         System.out.println(atomicArray.getAndSet(1,2));;
    }
}
