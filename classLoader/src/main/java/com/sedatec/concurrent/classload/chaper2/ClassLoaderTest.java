package com.sedatec.concurrent.classload.chaper2;

import com.sedatec.concurrent.classload.chaper1.MyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 **/
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
      //  MyClassLoader classLoader = new MyClassLoader();
        EncryptClassLoad classLoad = new EncryptClassLoad();
        classLoad.setDir("E:\\JAVA\\Loader\\classLoader3");
        Class<?> aClass =  classLoad.loadClass("com.sedatec.concurrent.classload.chaper1.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        Object obj = aClass.newInstance();
        Method method =  aClass.getMethod("hello",null);
        Object object =  method.invoke(obj,new Object[]{});
        System.out.println(object);
    }
}

