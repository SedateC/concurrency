package com.sedatec.concurrent.classload.chaper8;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/15
 **/
public class UnSafeTest {

    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    private static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
