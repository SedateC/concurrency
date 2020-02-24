package chaper6;

public class ThreadInterrupt {
    private static  final Object obj  = new Object();
    /*
    * sleep wait interrupt stop join 都可以被打断interrupt 标记
    * */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
           while (true){
                   synchronized (obj){
                       try {
                          // Thread.sleep(10);
                           obj.wait(10);
                       } catch (InterruptedException e) {
                           System.out.println("收到打断信号");
                           System.out.println(Thread.interrupted());//new 的run方法
                           e.printStackTrace();
                       }
                   }

           }
        });
        Thread t2 = new Thread(){//这是重写Thread 的run方法
            @Override
            public void run() {
                synchronized (obj){
                    try {
                         Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        System.out.println(isInterrupted());
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        System.out.println(t1.isInterrupted());
       // Thread.sleep(10);如果休眠太长 检测不到是打断的
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        t2.start();
        System.out.println(t2.isInterrupted());
        // Thread.sleep(10);如果休眠太长 检测不到是打断的
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }

}
