package part2.chaper6;

import java.util.LinkedList;

public class RequestQueue {
    /*
    * 自定义数组队列
    * 桥接 两个线程
    * */
    private final LinkedList <Request> queue = new LinkedList<>();

    public  Request getRequest(){
        synchronized (queue){
            while (queue.size() <= 0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();

        }
    }

    public void putRequest(Request request){
        synchronized (queue){
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
