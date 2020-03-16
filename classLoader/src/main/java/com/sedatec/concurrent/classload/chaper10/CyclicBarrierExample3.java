package com.sedatec.concurrent.classload.chaper10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 **/
public class CyclicBarrierExample3 {


    static class MyCountDownLatch extends CountDownLatch{
        private final Runnable runnable;
        public MyCountDownLatch(int count,Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0 ){
                this.runnable.run();
            }
        }
    }

    public static void main(String[] args) {
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all task finish ..");
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"finished countdown");
                    myCountDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"finished countdown");
                    myCountDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
