package com.sedatec.concurrent.classload.chaper3;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class CompareAndSetLock {
    private final AtomicInteger value = new AtomicInteger(0);
    Thread currentThread ;

    public void tryLock() throws GetLockException{
        boolean success =  value.compareAndSet(0,1);
        if (!success) {
         throw new GetLockException("get Lock failed");
        }
        this.currentThread = Thread.currentThread();
    }

    public void unLock(){
        if (currentThread == Thread.currentThread()){
            if (0 == value.get()){
                return;
            }
            value.compareAndSet(1,0);
        }
    }
}
