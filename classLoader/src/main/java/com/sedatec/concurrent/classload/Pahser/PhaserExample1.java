package com.sedatec.concurrent.classload.Pahser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 * 可以动态添加
 **/
public class PhaserExample1 {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        IntStream.rangeClosed(1,5).boxed().map(i -> phaser).forEach(Task :: new);
        phaser.register();
        phaser.arriveAndAwaitAdvance(); //到达等待前行
        System.out.println("all of work finish work");
    }

    static class Task extends Thread{
        private final Phaser phaser;
        Task(Phaser phaser){
            this.phaser = phaser;
            phaser.register(); //把自己加进管理器
            start();
        }
        @Override
        public void run() {
            System.out.println("this worker ["+getName()+"]" + "isWorking");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance(); //到达等待前行
        }
    }
}
