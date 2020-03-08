package com.sedatec.concurrent.classload.chaper2;

import org.junit.Test;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 **/
public class SimpleEncrypt {
    private static final String plain = "hello ClassLoader";

    private static final byte ENCRYPT_FACTOR = (byte) 0xff;


    /*
    * 加密
    * */
    @Test
    public void test(){
        byte[] bytes = plain.getBytes();
        byte[] encrypt = new byte[bytes.length];
        for (int i=0; i < bytes.length; i++){
            encrypt[i] = (byte)(bytes[i] ^ ENCRYPT_FACTOR);
        }
        System.out.println(new String(encrypt));
        /*
        * �����߼����������
        * */
    }


    /*
    * 解密
    * */
    @Test
    public void test2(){

        byte[] bytes = plain.getBytes();
        byte[] encrypt = new byte[bytes.length];
        for (int i=0; i < bytes.length; i++){
            encrypt[i] = (byte)(bytes[i] ^ ENCRYPT_FACTOR);
        }

        byte[] decrypt = new byte[encrypt.length];
        for (int i=0; i < encrypt.length; i++){
            decrypt[i] = (byte)(encrypt[i] ^ ENCRYPT_FACTOR);
        }
        System.out.println(new String(decrypt));
        /*
        * hello ClassLoader
        * */
    }
}
