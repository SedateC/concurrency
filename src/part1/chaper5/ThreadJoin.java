package part1.chaper5;

import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            IntStream.range(1,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));
        });
        Thread t2 = new Thread(()->{
            IntStream.range(1,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));
        });
        t1.start();
        t2.start();
        /*
          必须先start
        * Join 当前线程等待子线程执行完毕
        * */
        //加join 子线程按顺序执行
        t1.join();
        t2.join();
        IntStream.range(1,1000).forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));
    }
}
