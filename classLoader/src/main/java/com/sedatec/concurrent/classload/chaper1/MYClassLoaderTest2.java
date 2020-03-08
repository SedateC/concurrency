package com.sedatec.concurrent.classload.chaper1;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 *
 * 父类加载器优先父的加载器去加载
 * 父类加载器和子类加载器是包装关系，是一种包含关系
 * 父亲委托机提高安全性，防止恶意代码替换加载器可靠代码
 **/
public class MYClassLoaderTest2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader1");
        MyClassLoader myClassLoader2 = new MyClassLoader("MyClassLoader2",myClassLoader);
            Class<?> aClass = null;
            try {
                aClass = myClassLoader2.loadClass("com.sedatec.concurrent.classload.chaper1.MyObject");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(aClass);
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
    }

    @Test
    public void test(){
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader1");
        MyClassLoader myClassLoader2 = new MyClassLoader("MyClassLoader2");
        Class<?> aClass = null;
        try {
            aClass = myClassLoader2.loadClass("com.sedatec.concurrent.classload.chaper1.MyObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
    }

    @Test
    public void test2(){
        MyClassLoader myClassLoader = new MyClassLoader("MyClassLoader1");
        MyClassLoader myClassLoader2 = new MyClassLoader("MyClassLoader2");
        myClassLoader2.setDir("E:\\JAVA\\Loader2");
        Class<?> aClass = null;
        try {
            aClass = myClassLoader2.loadClass("com.sedatec.concurrent.classload.chaper1.MyObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
    }
}
