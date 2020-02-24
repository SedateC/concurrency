package part1.chaper12;

import java.util.Arrays;

public class ThreadGroupCreate {
    public static void main(String[] args) {
        //use the name
        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5_000);
                      /*  System. out.println(getThreadGroup());
                        System.out.println(getThreadGroup().getParent());*/
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
     //  System.out.println(t1.getThreadGroup());
        //use the parent and group name
        ThreadGroup tg2 = new ThreadGroup(tg1,"tg2");
        Thread t2 = new Thread(tg2,"t2"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5_000);
                        System.out.println(tg1.getName()); //可以访问其他ThreadGroup的读信息
                        Thread[] threads = new Thread[tg1.activeCount()];
                        tg1.enumerate(threads);
                        Arrays.asList(threads).forEach(System.out::println);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();
      /*
          System.out.println(Thread.currentThread().getName());
          System.out.println(Thread.currentThread().getThreadGroup().getName());

          main is a thread created by jdk
        */
    }
}
