package com.sedatec.concurrent.classload.chaper7;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 *
 *
 **/
public class AtomicIntegerFieldUpdateTest2 {

    /*
    * 不能改私有的变量
    *
    *  volatile
    * */
    @Test (expected = RuntimeException.class)
    public void test(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"y");
        TestMe testMe = new TestMe();
        updater.compareAndSet(testMe,0,1);
    }

    @Test
    public void test2(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"y");
        TestMe testMe = new TestMe();
        updater.compareAndSet(testMe,0,1);
    }

    /*
    * 类型不一样 castError
    * */
    @Test
    public void test3(){
        AtomicReferenceFieldUpdater<TestMe,Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class,Integer.class,"y");
        TestMe testMe = new TestMe();
        updater.compareAndSet(testMe,0,1);

    }
    /*
    * Must be volatile type
    * */
    @Test
    public void test4(){
        AtomicReferenceFieldUpdater<TestMe,Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class,Integer.class,"z");
        TestMe testMe = new TestMe();
        updater.compareAndSet(testMe,null,1);

    }
}
