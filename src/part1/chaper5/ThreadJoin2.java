package part1.chaper5;

import java.util.stream.IntStream;

public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
           try{
               IntStream.range(1,1000).forEach(i->{
                   System.out.println(Thread.currentThread().getName()+i);
               });
           } catch (Exception e){
               e.printStackTrace();
           }finally {
               System.out.println("T1 All finish done ");
           }
        });
        t1.start();
        t1.join(1);//执行时候等待1毫秒 然后main执行 1毫秒后自己任然执行
        System.out.println("T1 finish done ");
        IntStream.range(1,1000).forEach(i->{
            System.out.println(Thread.currentThread().getName()+i);
        });
    }
}
