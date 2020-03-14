package com.sedatec.concurrent.classload.chaper3;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicIntergerDetailTest {
    @Test
    public void testCreate(){
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());

        atomicInteger = new AtomicInteger(12);
        atomicInteger.set(12);
        System.out.println(atomicInteger.get());
        atomicInteger.lazySet(13);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testGetAndSet(){
        AtomicInteger getAndSet = new AtomicInteger(10);
        int result = getAndSet.getAndAdd(10); //返回原来的值 在加10
        System.out.println(result);
        System.out.println(getAndSet);
        /*
        * 10
        * 20
        * */
    }
    /*
    *
    *  compareAndSet 关键方法 无锁
    * */
    @Test
    public void testGetAndSet2(){
        AtomicInteger getAndSet = new AtomicInteger(10);
        boolean flag = getAndSet.compareAndSet(12,20);// expect 与原来的值一样才成功 否则失败
        System.out.println(flag);
        System.out.println(getAndSet);
        boolean flag2 = getAndSet.compareAndSet(10,20);// expect 与原来的值一样才成功 否则失败
        System.out.println(flag);

        System.out.println(getAndSet);
        /*
         * 10
         * 20
         * */
    }
}
