package com.sedatec.concurrent.classload.chaper9;

import java.util.Random;
import java.util.concurrent.*;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * 计数器门阀
 * 并行执行过程 线程之间相互通讯工具类
 **/
public class CountDownLatchExample {
    private static Random random = new Random(System.currentTimeMillis());
    private static ExecutorService executor = Executors.newFixedThreadPool(2);
    private static final CountDownLatch latch = new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {
        //[1]
        int[] data  = query();
        //[2]
        for (int i = 0 ; i < data.length; i++ ){
            executor.execute(new SimpleRunnable(data,i, latch));
        }
        //[3]
        //executor.shutdown();//异步结束
        //executor.awaitTermination(1, TimeUnit.MINUTES); 实现方法1
        latch.await();//什么时候过 解决方法3
        System.out.println("all task finish done");
        executor.shutdown();
    }

    static class SimpleRunnable implements Runnable{
        private final int[] data;
        private final int index ;
        private final CountDownLatch latch;

        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if (value % 2 == 0 ){
                data[index]  = value * 2;
            }else {
                data[index]  = value * 10;
            }
            System.out.println(Thread.currentThread().getName() +" task finish done");
            latch.countDown();
        }
    }

    private static int[] query() {
        return  new int[] {1,2,3,4,5,6,7,8,9,10};
    }
}
