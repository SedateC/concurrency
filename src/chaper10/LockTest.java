package chaper10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    static final  BooleanLock booleanLock = new BooleanLock();
    public static void main(String[] args) {

        Stream.of("T1","T2","T3","T4").forEach((name)->{
            new Thread(()->{
                try {
                    booleanLock.lock(10);
                    Optional.of(Thread.currentThread().getName() +"have lock monitor")
                            .ifPresent(System.out::println);
                    work();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (Lock.TimeOutException e){
                    Optional.of(Thread.currentThread().getName() +"Time out")
                            .ifPresent(System.out::println);
                }finally {
                    booleanLock.unlock();
                }
            },name).start();
        });
    }
    private static void  work() throws InterruptedException{
        Optional.of(Thread.currentThread().getName() +"is working").ifPresent(System.out::println);
       /*booleanLock.getBlockedThread().stream().forEach(thread -> {
           Optional.of(thread.getName() +"is waiting").ifPresent(System.out::println);
       });*/
        Thread.sleep(5_000);
    }
}
