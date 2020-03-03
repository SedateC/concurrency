package part2.chaper14;

import java.util.Random;

public class TransportThread extends  Thread {
    private final Channel channel;
    private static final Random r = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true;i++){
                Request request = new Request(getName(),i);
                this.channel.put(request);
                Thread.sleep(r.nextInt(1000));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
