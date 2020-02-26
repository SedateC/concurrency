package part2.chaper6;

import java.util.Random;

public class ServerThread extends Thread {

    private final RequestQueue queue;

    private final Random random ;

    private volatile Boolean flag = false;

    public ServerThread(final RequestQueue requestQueue) {
        this.queue = requestQueue;
        this.random = new Random(System.currentTimeMillis());

    }

    @Override
    public void run() {
        while (!flag){
            Request request = queue.getRequest();
            if (null == request ) {
                System.out.println("Received the empty request");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                return;
            }
        }
    }

    public void closed(){
        this.flag = true;
        this.interrupt();
    }
}
