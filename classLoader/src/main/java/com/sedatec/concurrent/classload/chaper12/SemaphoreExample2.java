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
public class SemaphoreExample2 {


    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(2); //两个信号量个数
        for (int i = 0;i < 2; i++  ){
            new Thread(){
                @Override
                public void run() {
                    try {
                        semaphore.acquire(2);
                        System.out.println(Thread.currentThread().getName() + " semaphore.acquire();");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release(2);
                    }
                    System.out.println(Thread.currentThread().getName() + "semaphore.release();");
                }
            }.start();
        }

        while (true){
            System.out.println("ap -- availablePermits "+ semaphore.availablePermits() ); //当前可用的凭证
            System.out.println("ql -- availablePermits "+ semaphore.getQueueLength() ); //被等待的队列
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
