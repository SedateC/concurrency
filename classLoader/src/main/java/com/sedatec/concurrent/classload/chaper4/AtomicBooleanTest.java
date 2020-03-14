package com.sedatec.concurrent.classload.chaper4;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/14
 **/
public class AtomicBooleanTest {
    @Test
    public void testCreate(){
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        assertFalse(atomicBoolean.get());
        atomicBoolean.lazySet(true); //不管怎么样最终会设置为True
        assertTrue(atomicBoolean.get());
        boolean flag = atomicBoolean.getAndSet(false); //先获取在赋值

    }
}
