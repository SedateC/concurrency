package com.sedatec.concurrent.classload.chaper13;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 * condition 生产者消费者模式
 **/
public class ConditionExample {
    private final static ReentrantLock lock = new ReentrantLock(true);

    private final static Condition condition = lock.newCondition();

    private  static int data = 0;

    private static volatile boolean noUse = true;

    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                buildData();
            }
        }).start();

        new Thread(()->{
            while (true){
                buildData();
            }
        }).start();
        new Thread(()->{
            while (true){
                useData();
            }
        }).start();

        new Thread(()->{
            while (true){
                useData();
            }
        }).start();
    }
    private static void buildData(){
        try {
            lock.lock(); //synchronized key word
            while (noUse){
                condition.await(); //monitor.wait();
            }
            data ++;
            Optional.of("P:" +data).ifPresent(System.out::println);
            //TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signalAll();//monitor.notify
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
           lock.unlock();//synchronized end   monitor end
        }

    }

    private static void useData(){
        try{
            lock.lock();
            while (!noUse){
                condition.await();
            }
           TimeUnit.SECONDS.sleep(2);
            Optional.of(Thread.currentThread().getName()+" C:" +data).ifPresent(System.out::println);
            noUse = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
