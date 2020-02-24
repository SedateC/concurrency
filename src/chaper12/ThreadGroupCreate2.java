package chaper12;

import java.util.Arrays;

public class ThreadGroupCreate2 {
    public static void main(String[] args) {
        //use the name
        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t1.start();
        ThreadGroup tg2 = new ThreadGroup(tg1,"tg2");
        Thread t2 = new Thread(tg2,"t2"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t2.start();
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        t2.checkAccess(); //mian 线程是否有修改t2 的权限
        //tg1.destroy();
        Thread[] threads = new Thread[tg1.activeCount()];
        tg1.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);//tg1 组中有两个线程
        System.out.println("========================");
        tg1.enumerate(threads,true);//true 代表递归 ，默认true
        Arrays.asList(threads).forEach(System.out::println);//tg1 组中有两个线程
        System.out.println("========================");
        Thread[] mainThreads = new Thread[5];
        //Thread.currentThread().getThreadGroup().enumerate(mainThreads,true);
        Thread.currentThread().getThreadGroup().enumerate(mainThreads,false);
        Arrays.asList(mainThreads).forEach(System.out::println);//tg1 组中有两个线程
        System.out.println("========================");
        t1.interrupt(); //打断t1 所有线程
        Arrays.asList(mainThreads).forEach(System.out::println);//tg1 组中有两个线程
        //tg1 setDaemon 线程的时候 运行完会自动回收 否则会存在很长时间 必须用 tg1.destroy 手动回收；
 /*       tg1.setDaemon(true);
        tg1.isDestroyed();*/
    }
}
