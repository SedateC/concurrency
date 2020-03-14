package com.sedatec.concurrent.classload.chaper4;

import java.util.concurrent.atomic.AtomicBoolean;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicBooleanFlag {
    private final static AtomicBoolean  flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                while (flag.get()){
                    try {
                        Thread.sleep(1_000);
                        System.out.println("i am working...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("i am finish...");
            }
        }.start();

        Thread.sleep(5_000);
        flag.set(false);
    }
}
