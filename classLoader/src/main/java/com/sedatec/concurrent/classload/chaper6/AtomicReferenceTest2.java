package com.sedatec.concurrent.classload.chaper6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicReferenceTest2 {
    /*
    * 参照数据库乐观锁
    * 解决A B A 问题
    * */
    private static AtomicStampedReference<Integer> integerAR = new AtomicStampedReference<>(100,0);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    boolean flag = integerAR.compareAndSet(100,101,integerAR.getStamp(),integerAR.getStamp() + 1);
                    System.out.println("t1-1"+ flag);
                    flag=  integerAR.compareAndSet(101,100,integerAR.getStamp(),integerAR.getStamp() + 1);
                    System.out.println("t1-2"+ flag);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int stamp = integerAR.getStamp();
                    TimeUnit.SECONDS.sleep(2);
                    boolean flag = integerAR.compareAndSet(100,101,stamp,stamp + 1);
                    System.out.println("t2 "+ flag);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
