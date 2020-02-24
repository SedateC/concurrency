package part1.chaper7;

public class SychroziedStatic {
    //默认锁是 SychroziedStatic.class
    public synchronized static void m1(){
        System.out.println("m1 "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2(){
        System.out.println("m2 "+Thread.currentThread().getName());
        try {
            Thread.sleep(10_000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
