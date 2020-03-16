package com.sedatec.concurrent.classload.chaper9;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * 计数器门阀
 * 并行执行过程 线程之间相互通讯工具类
 **/
public class CountDownLatchExample2 {
    private static Random random = new Random(System.currentTimeMillis());
    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    private static final CountDownLatch latch = new CountDownLatch(1);//设置为总共一个任务countdown
    /*
    * do some initial working.
    * asyn some prepare working.
    * data prepare  for done.
    * do other  working.
    *
    * */
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                System.out.println("do some initial working.");
                try {
                    Thread.sleep(random.nextInt(2000));
                    latch.await();
                    System.out.println("do other  working.");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    latch.await();// 等待其他latch.countDown
                    System.out.println("release");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println("asyn some prepare working.");
                try {
                    Thread.sleep(random.nextInt(3000));
                    System.out.println("data prepare  for done.");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        }.start();
        Thread.currentThread().join();
    }
}
