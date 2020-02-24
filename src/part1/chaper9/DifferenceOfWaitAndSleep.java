package part1.chaper9;

import java.util.stream.Stream;

public class DifferenceOfWaitAndSleep {
    /*
    * 1，sleep 是thread 中的方法  wait 是object中的一个方法
    * 2，wait 直接释放资源等待其他唤醒 sleep 不释放资源
    * 3. wait 需要 加 LOCK 锁
    * 4，wait 需要等待唤醒 sleep 不用
    **/

    private static final Object Lock = new Object();
    public static void main(String[] args) {
        Stream.of("t1","t2").forEach(i->{
             new Thread(){
                @Override
                public void run() {
                    m1(); //sleep不释放资源
                    m2(); //直接释放资源
                }
            }.start();
        });
    }
    public static void m1(){
        synchronized (Lock){
            try {
                System.out.println("the thread "+Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2(){
        synchronized (Lock){//wait 必须加锁
            try {
                System.out.println("the thread "+Thread.currentThread().getName());
                Lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
