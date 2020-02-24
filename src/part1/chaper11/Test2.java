package part1.chaper11;

import java.util.Arrays;
import java.util.Optional;

public class Test2 {
    /*
    * Thread.currentThread().getStackTrace()).stream() API
    * */
    public void test(){
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e->!e.isNativeMethod())
                .forEach(e-> Optional.of(e.getClassName()+":"+e.getMethodName()+":"+e.getLineNumber())
                        .ifPresent(System.out::println));
    }
}
