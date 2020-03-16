package com.sedatec.concurrent.classload.chaper10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 **/
public class CyclicBarrierExample2 {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    cyclicBarrier.await();
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
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        TimeUnit.MILLISECONDS.sleep(100);

        /*rest 等价于 ==  initial   == finish
        * 重新创建了一个cyclicBarrier
        * */
        cyclicBarrier.reset();// BrokenBarrierException  实际上是打断了     cyclicBarrier.await(); 但是await 没有启动。则报错
    }
}
