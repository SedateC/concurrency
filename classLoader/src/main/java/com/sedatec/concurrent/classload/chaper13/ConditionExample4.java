package com.sedatec.concurrent.classload.chaper13;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 * 多生产者消费者
 **/
public class ConditionExample4 {
    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition PRODUCE_COND = lock.newCondition();

    private final static Condition CONSUME_COND = lock.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) {
        IntStream.range(0,6).forEach(ConditionExample4::beginProduce);
        IntStream.range(0,10).forEach(ConditionExample4::beginConsume);
        for (;;){
            sleep(5);
            System.out.println("============================");
        }
    }

    private static void beginProduce(int i){
        new Thread(()->{
            for (;;){
                produce();
                sleep(2);
            }
        },"P-"+i).start();
    }

    private static void beginConsume(int i){

        new Thread(()->{
            for(;;){
                consume();
                sleep(2);
            }

        },"C-"+i).start();
    }

    private static void produce(){
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() > MAX_CAPACITY){
                PRODUCE_COND.await();
            }
            Long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            TIMESTAMP_POOL.addLast(value);
            PRODUCE_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume(){
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()){
                CONSUME_COND.await();
            }
            Long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + value);
            CONSUME_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
