package chaper3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CreateThread {
    public static void main(String[] args){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println( Thread.currentThread().getName());;
        System.out.println( Thread.currentThread().getThreadGroup().getName());
        t1.start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
    }
}
