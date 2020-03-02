package part2.chaper13;

import java.util.Random;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/2
 **/
public class CounterIncrement extends Thread {
    private volatile boolean terminated = false;

    private int counter = 0;

    private Random  r = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while ( !terminated){
                System.out.println( Thread.currentThread().getName() +" "+ counter++);
                Thread.sleep(r.nextInt(1000));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            clean();
        }
    }

    private void clean() {
        System.out.println("clean someThing about work");
    }

    public  void close(){
        terminated = true;
        this.interrupt();;
    }
}
