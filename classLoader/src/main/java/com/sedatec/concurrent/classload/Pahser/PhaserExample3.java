package com.sedatec.concurrent.classload.Pahser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 * 可以循环使用 可以销户
 **/
public class PhaserExample3 {
    /*
    * running
    *
    * bicycle
    *
    * long jump
    *
    *
    *
    * */
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(6);
        IntStream.rangeClosed(1,5).forEach(
                (i)->{
                    new Athletes(i,phaser).start();
                }
        );
        new InjureAthletes(6,phaser).start();
    }

    static class Athletes extends Thread{
        private final int  no;
        private final Phaser  phaser;
        Athletes(int no ,Phaser phaser){
            this.no = no;
            this.phaser = phaser;
        }
        @Override
        public void run() {

            try {
                Sport("running", " end running");

                Sport(" start bicycle", " end bicycle");

                Sport(" start long jump", " end long jump");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance(); //到达等待前行
        }

        private void Sport(String running, String s) throws InterruptedException {
            System.out.println("this no [" + no + "]" + running);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("this no [" + no + "]" + s);
            phaser.arriveAndAwaitAdvance();
        }
    }


    static class InjureAthletes extends Thread{
        private final int  no;
        private final Phaser  phaser;
        InjureAthletes(int no ,Phaser phaser){
            this.no = no;
            this.phaser = phaser;
        }
        @Override
        public void run() {

            try {
                Sport("running", " end running");
                Sport(" start bicycle", " end bicycle");
                System.out.println("this no [" + no + "]" + ("oh shit i am injure"));
                phaser.arriveAndDeregister();//出现异常的状况

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void Sport(String running, String s) throws InterruptedException {
            System.out.println("this no [" + no + "]" + running);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("this no [" + no + "]" + s);
            phaser.arriveAndAwaitAdvance();
        }
    }
}
