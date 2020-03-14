package com.sedatec.concurrent.classload.chaper6;

import java.util.concurrent.atomic.AtomicReference;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicReferenceTest {
   static AtomicReference<Simple> simpleAtomics = new AtomicReference<>(new Simple("1111",12));

    public static void main(String[] args) {
        System.out.println(simpleAtomics.get());
        Simple aaa =   new Simple("1111",23213);
        simpleAtomics.set(aaa);
        boolean flag = simpleAtomics.compareAndSet(aaa,new Simple("111111",14));
        System.out.println(flag);;
    }
    static class Simple {
        private String name ;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
