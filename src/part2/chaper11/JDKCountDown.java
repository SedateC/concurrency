package part2.chaper11;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/*
* 多线程ContDown 设计模式
* */
public class JDKCountDown {
    private final static Random r = new Random(System.currentTimeMillis());
    final static CountDownLatch latch = new CountDownLatch(5);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("prepare CurrentThreads task");
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +"is working");
                try {
                    Thread.sleep(r.nextInt(1000));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                latch.countDown();
            },String.valueOf(i)).start();
        });
        latch.await();
        System.out.println("Second motion starting...");
        System.out.println("-------");
        System.out.println("finish");
    }
}
