package part1.chaper7;

public class SynchronizedThis {
    public static void main(String[] args) {
        ThiLock thiLock = new ThiLock();
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                thiLock.m1();
            }
        };
        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
                thiLock.m2();
            }
        };
        t1.start();
        t2.start();
    }
}
class ThiLock{
    public synchronized void m1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public synchronized void m2(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}