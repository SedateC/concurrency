package com.sedatec.concurrent.classload.chaper7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 * 把TestMe 中的参数保持原子性
 * 和操作 atomicInteger 一样
 **/
public class AtomicIntegerFieldUpdateTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i");
        TestMe testMe = new TestMe();
        for (int i = 0; i< 2 ; i ++  ){
            new Thread(){
                @Override
                public void run() {
                    int y = 20;
                    int z = 0;
                    while (z < 20){
                        int v = updater.getAndIncrement(testMe);
                        System.out.println(Thread.currentThread().getName()+" v: "+v);
                        z++;
                    }
                }
            }.start();
        }
    }

}
