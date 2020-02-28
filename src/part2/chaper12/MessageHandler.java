package part2.chaper12;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

    private final Executor executor = Executors.newFixedThreadPool(5);
    private static final Random r = new Random(System.currentTimeMillis());
    public void request (Message message){
         executor.execute(()->{
            String value  = message.getValue();
            try {
                Thread.sleep(r.nextInt(1000));
                System.out.println( value +" message will be handle by "+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    public void shutdown(){
        ((ExecutorService)executor).shutdown();;
    }
}
