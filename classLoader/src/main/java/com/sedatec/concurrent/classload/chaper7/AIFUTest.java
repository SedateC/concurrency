package com.sedatec.concurrent.classload.chaper7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AIFUTest {

    AtomicInteger I = new AtomicInteger();
    private volatile int i;
    /* 使用原因
      1,想增加原子性

    * 2 替换synchronized 乐观锁  无锁速度快
    *
    * 为什么不用AtomicInteger
    * 3，AtomicStampedReference 封装 多10000 * 32 字节
    *  用AtomicIntegerFieldUpdater 抽出来 就不多那么多
    *
    * */
    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class,"i");
    public void update(int newValue){
        updater.set(this,newValue);
    }

    public  int getI() {
        return i;
    }

    public static void main(String[] args) {
        AIFUTest aifuTest = new AIFUTest();
        aifuTest.update(10);
        System.out.println(aifuTest.getI());

    }
}
