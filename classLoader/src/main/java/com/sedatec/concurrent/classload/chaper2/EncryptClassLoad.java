package com.sedatec.concurrent.classload.chaper2;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 **/
public class EncryptClassLoad extends ClassLoader {
    private final static String DEFAULT_DIR ="E:\\JAVA\\Loader\\classLoader3";
    private String dir = DEFAULT_DIR;

    public EncryptClassLoad() {
        super();
    }

    public EncryptClassLoad(String classLoaderName) {
        super();

    }

    public EncryptClassLoad(ClassLoader parent){
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".","/");
        File classFile = new File(dir,classPath+".class");
        if (!classFile.exists()){
            throw  new ClassNotFoundException("the class" + name+ " not found under directory "+ dir + "/" +classPath);
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0){
            new ClassNotFoundException("load file failed");
        }
        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(classFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int data;
            while ((data = fileInputStream.read())!= -1){
                baos.write(data ^ EncryptUtils.ENCRYPT_FACTOR );
            }
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
