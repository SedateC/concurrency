package com.sedatec.concurrent.classload.StampedLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/18
 *
 *  ReentrantLock 比 synchronized
 *
 *  StampedLockExample 出现原因， 如果read 的线程比较多 write线程比较少 出现写饥饿 解决这个问题
 **/
public class StampedLockExample2 {
    private static final StampedLock LOCK = new StampedLock();

    private static final List<Long> DATA = new ArrayList<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (;;){
                    read();
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                while (true)
                    write();
            }
        };
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(writeTask);
    }

    private static void read(){

 /*       try {
            stamp = LOCK.readLock();// 悲观读
            System.out.print(Thread.currentThread().getName()+"read ");
            Optional.of(
            DATA.stream().map(String::valueOf).collect(Collectors.joining("#","R-",""))
            ).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlockRead(stamp);
        }*/

        long stamp =  LOCK.tryOptimisticRead();
        if (LOCK.validate(stamp)){//检查你发出去的乐观锁有没有人在使用
            try {
                stamp = LOCK.readLock();
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#","R-",""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlockRead(stamp);
            }
        }
    }

    private static void write(){
        long stamp = -1;
        try {
            stamp = LOCK.writeLock();
            DATA.add(System.currentTimeMillis());
            System.out.print(Thread.currentThread().getName()+"write ");
            Optional.of(
                    DATA.stream().map(String::valueOf).collect(Collectors.joining("#","W-",""))
            ).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlockWrite(stamp);
        }
    }
}
