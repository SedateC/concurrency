package part2.chaper7;

import java.util.Random;

/*
* 线程保险箱
* 不同线程数据独立
*实现原理当前线程作为key值 自己实现类
* */
public class ThreadLocalSimulatorTest {
    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<>();
    private final static Random random = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            threadLocal.set("thread t1 ");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" getLocal "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1");


        Thread t2 = new Thread(()->{
            threadLocal.set("thread t2 ");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" getLocal "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("===============");
        System.out.println(Thread.currentThread().getName()+" main "+threadLocal.get());
        /*
        t1 getLocal thread t1
        t2 getLocal thread t2
        ===============
        main main null
        */
    }
}
