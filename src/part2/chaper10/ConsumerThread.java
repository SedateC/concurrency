package part2.chaper10;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerThread extends Thread {
    private final MessageQueue messageQueue;
    private final static Random r = new Random(System.currentTimeMillis());
    private final static AtomicInteger counter = new AtomicInteger(0);


    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super("Consumer "+ seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true){
            try{
              Message message =  messageQueue.take();
              System.out.println(Thread.currentThread().getName()+" take message "+message.getData());
              Thread.sleep(r.nextInt(1000));
            }catch (InterruptedException e){
                break;
            }
        }
    }
}
