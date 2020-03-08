package com.sedatec.concurrent.classload.chaper1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 **/
public class MYClassLoaderTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader");
        try {
         Class<?> aClass =   myClassLoader.loadClass("com.sedatec.concurrent.classload.chaper1.MyObject");
            System.out.println(aClass);
            System.out.println(aClass.getClassLoader());
            try {
                Object obj = aClass.newInstance();
                Method method =  aClass.getMethod("hello",null);
               Object object =  method.invoke(obj,new Object[]{});
               System.out.println(object);

               /*
               *
               * class com.sedatec.concurrent.classload.chaper1.MyObject
                MyClassLoader@74a14482
                my object static block
                hello world
               * */
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
