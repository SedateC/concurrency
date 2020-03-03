package part2.chaper14;

import java.util.Arrays;

public class Channel {
    private final static int MAX_REQUEST = 100;

    private final Request[] requests;

    private volatile int head;
    private volatile int tail;
    private volatile int count;

    private final WorkerThread[] workerPool;

    public Channel(int workers) {
        this.requests = new Request[MAX_REQUEST];
        this.workerPool = new WorkerThread[workers];
        init();
    }

    private void init(){
        for (int i = 0;i<workerPool.length;i++){
            workerPool[i] = new WorkerThread("Worker-"+i,this);
        }
    }
    /*
    * push switch to start
    * */
    public void startWorker(){
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request){
        while (count >= requests.length){
            try {
                wait();
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
        this.requests[tail] = request;
        this.tail = (tail+1) % requests.length;
        count++;
        this.notifyAll();
    }

    public synchronized Request take(){
        while (count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requests[head];
        this.head = (this.head + 1) % this.requests.length;
        count --;
        this.notifyAll();
        return request;
    }
}
