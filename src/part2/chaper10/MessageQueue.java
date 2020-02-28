package part2.chaper10;

import java.util.LinkedList;

public class MessageQueue {
    private final LinkedList<Message> queue;
    private final static int DEFAULT_MAX_LIMIT= 100;
    private final int Limit;

    public MessageQueue( int limit) {
        this.queue = new LinkedList<>();
        this.Limit = limit;
    }

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while (queue.size() > DEFAULT_MAX_LIMIT){
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getQueue() {
        synchronized (queue) {
            return queue.size();
        }
    }

    public  int getDefaultMaxLimit() { //final 不用枷锁
        return this.Limit;
    }
}
