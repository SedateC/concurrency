package com.sedatec.concurrent.classload.chaper12;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * * A counting semaphore.  Conceptually, a semaphore maintains a set of
 * permits.  Each {@link #acquire} blocks if necessary until a permit is
 * available, and then takes it.  Each {@link #release} adds a permit,
 * potentially releasing a blocking acquirer.
 * However, no actual permit objects are used; the {@code Semaphore} just
 * keeps a count of the number available and acts accordingly.
 *
 **/
public class SemaphoreExample {
    public static void main(String[] args) {
        final SemaphoreLock Lock = new SemaphoreLock();
        for (int i = 0;i < 2; i++  ){
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +"is running");
                    try {
                        Lock.lock();
                        System.out.println(Thread.currentThread().getName() +" get the lock");
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            Lock.unlock();
                            System.out.println(Thread.currentThread().getName() +" unlock the lock");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
    static class SemaphoreLock {
        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException{
            semaphore.acquire();
        }

        public void unlock() throws InterruptedException{
            semaphore.release();
            System.out.println(Thread.currentThread().getName() +" release");
        }
    }

}
