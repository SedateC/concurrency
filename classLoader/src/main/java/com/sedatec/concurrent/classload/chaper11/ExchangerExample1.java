package com.sedatec.concurrent.classload.chaper11;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * 两个线程值相互交换
 * 只适用于两个线程使用， 不能加其他的。
 *
 **/
public class ExchangerExample1 {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "Start ..");
                try {
                   String result =  exchanger.exchange("I am come from T - a ");
                    System.out.println(Thread.currentThread().getName()+" get value " + result );
                    System.out.println(Thread.currentThread().getName()+" end ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"=A=").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "Start ..");
                try {
                    TimeUnit.SECONDS.sleep(3);
                    String result =  exchanger.exchange("I am come from T - B ");
                    System.out.println(Thread.currentThread().getName()+" get value " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" end ");
            }
        },"=B=").start();
    }
}
