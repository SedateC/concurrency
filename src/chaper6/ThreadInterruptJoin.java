package chaper6;

public class ThreadInterruptJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){

                }
            }
        };
        t1.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                   // t1.interrupt();这里打断的是main 线程才可以
                    main.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        try {
            t1.join(); //这里打断的是main 线程才可以
        }catch (InterruptedException e){
            System.out.println("main 线程被打断");
            e.printStackTrace();
        }
    }
}
