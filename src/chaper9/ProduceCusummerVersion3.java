package chaper9;

import java.util.stream.Stream;

public class ProduceCusummerVersion3 {
    private int i = 0;
    final private Object LOCK = new Object();
    private volatile Boolean isProduced = false;
    /*
       真实场景
    * 存在只有一个锁 多个消费者 notify 不知道是哪个消费者的锁 最后LOCK 等待notify 造成卡死
    * */
    public void produce(){
        synchronized (LOCK){
            while (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P--->"+i);
            LOCK.notifyAll();
            isProduced = true;
        }
    }


    public void consume(){
        synchronized (LOCK){
          while (isProduced){ // 必须要用while 要不第二个消费者会直接跳过isProduce监测 造成重复消费
              System.out.println("C-->"+i);
              LOCK.notifyAll();
              isProduced = false;
          }
          try {
              LOCK.wait();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        }
    }

    public static void main(String[] args) {
        ProduceCusummerVersion3 produceCusummerVersion =  new ProduceCusummerVersion3();
        Stream.of("p1","p2").forEach( n->{
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        produceCusummerVersion.produce();
                    }
                }
            }.start();
        });

        Stream.of("c1","c2").forEach( n->{
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        produceCusummerVersion.consume();
                    }
                }
            }.start();
        });

    }
}
