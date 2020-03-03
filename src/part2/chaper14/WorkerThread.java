package part2.chaper14;

import java.util.Random;

public class WorkerThread  extends  Thread{
    private final Channel channel;
    private static final Random r = new Random(System.currentTimeMillis());
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            channel.take().execute();
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
