package chaper9;

import java.util.stream.Stream;

public class ProduceCusummerVersion {
    private int i = 0;
    final private Object LOCK = new Object();
    private volatile Boolean isProduced = false;
    /*
     单独消费场景
    * 存在只有一个锁 多个消费者 notify 不知道是哪个消费者的锁 最后LOCK 等待notify 造成卡死
    * */
    public void produce(){
        synchronized (LOCK){
            if (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                i++;
                System.out.println("P--->"+i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }


    public void consume(){
        synchronized (LOCK){
          if (isProduced){
              System.out.println("C-->"+i);
              LOCK.notify();
              isProduced = false;
          }else {
              try {
                  LOCK.wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        }
    }

    public static void main(String[] args) {
        ProduceCusummerVersion produceCusummerVersion =  new ProduceCusummerVersion();
        Stream.of("p1","p2").forEach( n->{
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        produceCusummerVersion.produce();
                    }
                }
            }.start();

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
