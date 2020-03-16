package com.sedatec.concurrent.classload.chaper9;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * 计数器门阀
 * 并行执行过程 线程之间相互通讯工具类
 **/
public class CountDownLatchExample3 {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Thread main = Thread.currentThread();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               latch.countDown(); //【1】 【2】
               // main.interrupt();【1】
                System.out.println("----------");
            }
        }.start();


       // latch.await();//如果count是 0 直接 结束执行下面 或者执行线程被打断 【1】
        //System.out.println("+++++++");


        latch.await(1000, TimeUnit.MILLISECONDS); //如果超过时间就不等了【2】
        System.out.println("=========");
    }
}
