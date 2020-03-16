package com.sedatec.concurrent.classload.chaper12;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/16
 * * A counting semaphore.  Conceptually, a semaphore maintains a set of
 * permits.  Each {@link #acquire} blocks if necessary until a permit is
 * available, and then takes it.  Each {@link #release} adds a permit,
 * potentially releasing a blocking acquirer.
 * However, no actual permit objects are used; the {@code Semaphore} just
 * keeps a count of the number available and acts accordingly.
 *
 **/
public class SemaphoreExample2 {
    private final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        for (int i = 0;i < 2; i++  ){

        }
    }

}
