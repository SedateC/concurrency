package part1.chaper4;

import java.util.Optional;

public class ThreadSimpleApi {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            Optional.of("hello").ifPresent(System.out::println);//创建Optional对象
            try {
              Thread.sleep(10_000);
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Optional.of(t1.getName()).ifPresent(System.out::println);
        Optional.of(t1.getId()).ifPresent(System.out::println);
        Optional.of(t1.getPriority()).ifPresent(System.out::println);
    }
}
