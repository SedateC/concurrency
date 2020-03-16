package com.sedatec.concurrent.classload.chaper11;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * 验证相互传递的对象是否为同一个
 * 答案是同一个
 **/
public class ExchangerExample2 {
    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "Start ..");
                try {
                    Object obj = new Object();
                    System.out.println(obj + " OBJ 1");
                    Object result =  exchanger.exchange(obj);
                    System.out.println(result +" obj2 accept");
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
                    Object obj = new Object();
                    System.out.println(obj +" obj2");
                    Object result =  exchanger.exchange(obj);
                    System.out.println(result +" obj1 accept");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" end ");
            }
        },"=B=").start();
    }
}
