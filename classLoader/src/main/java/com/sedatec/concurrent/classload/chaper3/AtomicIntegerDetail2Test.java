package com.sedatec.concurrent.classload.chaper3;


/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicIntegerDetail2Test {
    private  static CompareAndSetLock lock = new CompareAndSetLock();
    public static void main(String[] args) {
        for (int i = 0;i < 5; i++){

            new Thread(i+""){
                @Override
                public void run() {
                    //doSomeThing();
                    try {
                        doSomeThing2();
                    } catch (GetLockException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }


    public void testDoSomeThing(){

    }

    private static void  doSomeThing(){
        synchronized (AtomicIntegerDetail2Test.class){
            System.out.println(Thread.currentThread().getName()+"get the lock...");
            try {
                Thread.sleep(5_000);
                System.out.println("workOver");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void  doSomeThing2() throws GetLockException {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName()+"get the lock...");
            try {
                Thread.sleep(5_000);
                System.out.println("workOver");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unLock();
            }

    }
}
