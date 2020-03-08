package com.sedatec.concurrent.classload.chaper1;

import java.io.*;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/8
 **/
public class MyClassLoader extends ClassLoader {
    private final static String DEFAULT_DIR ="E:\\JAVA\\Loader";
    private String dir = DEFAULT_DIR;
    private String classLoaderName;

    public MyClassLoader() {
        super();
    }

    public MyClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(String classLoaderName,ClassLoader parent){
        super(parent);
        this.classLoaderName = classLoaderName;
    }
    /*
    * XX,XXX,XXX.AAA
    *
    * */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".","/");
        File classFile = new File(dir,classPath+".class");
        if (!classFile.exists()){
            throw new ClassNotFoundException("the class not found");
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0){
            throw new ClassNotFoundException(" load class failed");
        }
        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {
        try {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = new FileInputStream(classFile);
            int len = 0;
            while ((len = fis.read(buffer))!= -1){
                baos.write(buffer,0,len);
            }
            baos.flush();
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }

    public void setClassLoaderName(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}

