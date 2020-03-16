package com.sedatec.concurrent.classload.chaper10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 *
 * * A synchronization aid that allows a set of threads to all wait for
 * each other to reach a common barrier point.  CyclicBarriers are
 * useful in programs involving a fixed sized party of threads that
 * must occasionally wait for each other. The barrier is called
 * <em>cyclic</em> because it can be re-used after the waiting threads
 *   等待部分进程完成
 *     不需要裁判员
 *   t2 finish .
    t1 finish .
    t1 other finish .
    t2 other finish too .
 **/
public class CyclicBarrierExample1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有完成后 回调 finish task . ");
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(12);
                    System.out.println("t1 finish . ");
                    cyclicBarrier.await();
                    System.out.println("t1 other finish . ");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("t2 finish . ");
                    cyclicBarrier.await();
                    System.out.println("t2 other finish too . ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

       /* cyclicBarrier.await();
        System.out.println("all finish task . ");*/

        while (true){
            System.out.println(cyclicBarrier.getNumberWaiting());
            System.out.println(cyclicBarrier.getParties());
            TimeUnit.SECONDS.sleep(2);
        }


    }

}
