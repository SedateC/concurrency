package part2.chaper2;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {
/*
* 1， 所有的对象都有自己的wait set  存放调用wait 方法之后变成block状态
* 2， 线程被notify后不一定立即执行 唤醒顺序不是fifo 不清楚顺序
* 3，线程被唤醒后必须重新获取锁 线程计数器会记录上次执行到的代码行 继续往下执行
* */
    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        IntStream.rangeClosed(1,10).forEach(i->{
                    new Thread(String.valueOf(i)){
                        @Override
                        public void run() {
                            synchronized (LOCK){
                                try {
                                    Optional.of(Thread.currentThread().getName() +" thread will come to wait set").ifPresent(System.out::println);
                                    LOCK.wait(); //会把自己放到自己的wait set中 所有的object 都有自己的wait set JVM 中
                                    Optional.of(Thread.currentThread().getName() +" thread will leave to wait set").ifPresent(System.out::println);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
        });
        IntStream.rangeClosed(1,10).forEach(i->{
                   synchronized (LOCK){
                       LOCK.notify();
                       try {
                           Thread.sleep(1000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
        });

    }
}
