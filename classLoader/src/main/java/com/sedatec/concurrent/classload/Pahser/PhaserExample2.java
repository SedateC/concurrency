package com.sedatec.concurrent.classload.Pahser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 * 可以循环使用
 **/
public class PhaserExample2 {
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
        IntStream.rangeClosed(0,5).forEach(
                (i)->{
                    new Athletes(i,phaser).start();
                }
        );
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
                System.out.println("this no ["+no+"]" + "running");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("this no ["+no+"]" + " end running");
                System.out.println("  phaser.getPhase()" + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println("this no ["+no+"]" + " start bicycle");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("this no ["+no+"]" + " end bicycle");
                System.out.println("  phaser.getPhase()" + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println("  phaser.getPhase()" + phaser.getPhase());
                System.out.println("this no ["+no+"]" + " end long jump");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("this no ["+no+"]" + " end long jump");
                System.out.println("  phaser.getPhase()" + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance(); //到达等待前行
        }
    }
}
