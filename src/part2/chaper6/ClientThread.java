package part2.chaper6;

import java.util.Random;

public class ClientThread extends Thread {

    private final RequestQueue queue;

    private final Random random ;

    private final String sendValue;

    public ClientThread(final RequestQueue requestQueue,String sendValue) {
        this.queue = requestQueue;
        this.random = new Random(System.currentTimeMillis());
        this.sendValue = sendValue;
    }


    @Override
    public void run() {
        for (int i = 0; i<10; i++){
            System.out.println("Client - > Request "+ sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
