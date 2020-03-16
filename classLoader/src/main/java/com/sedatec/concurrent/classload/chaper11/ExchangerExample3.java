package com.sedatec.concurrent.classload.chaper11;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 **/
public class ExchangerExample3 {
    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference<Integer>(1);
                System.out.println(Thread.currentThread().getName() + "Start ..");
                try {
                 while (true){
                     Object result =  exchanger.exchange(exchanger.exchange(value.get()));
                     System.out.println(Thread.currentThread().getName() + " " +result+"get from B");
                     TimeUnit.SECONDS.sleep(1);
                 }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"=A=").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                AtomicReference<Integer> value = new AtomicReference<Integer>(2);
                System.out.println(Thread.currentThread().getName() + "Start ..");
                try {
                    while (true){
                        Object result =  exchanger.exchange(exchanger.exchange(value.get()));
                        System.out.println(Thread.currentThread().getName() + " " +result+"get from A");
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"=B=").start();
    }

}
