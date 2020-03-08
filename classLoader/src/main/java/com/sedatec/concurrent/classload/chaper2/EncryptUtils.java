package com.sedatec.concurrent.classload.chaper2;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 **/
public final class EncryptUtils {
    public static final byte ENCRYPT_FACTOR = (byte)0xff;

    public EncryptUtils() {
        //empty
    }

    public static void doEncrypt(String source,String target){
        try {
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(target);
            int data;
            while ((data = fis.read())!= -1){
                fos.write(data^ ENCRYPT_FACTOR);
            }
            fos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        EncryptUtils.doEncrypt("E:\\JAVA\\Loader\\aaa.txt","E:\\JAVA\\Loader\\bbb.txt");
    }
    @Test
    public void test2(){
        EncryptUtils.doEncrypt("E:\\JAVA\\Loader\\bbb.txt","E:\\JAVA\\Loader\\ccc.txt");
    }
    @Test
    public void test3(){
        EncryptUtils.doEncrypt("E:\\JAVA\\Loader\\classLoader3\\MyObject.class",
                "E:\\JAVA\\Loader\\classLoader3\\MyObject1.class");
    }

}
