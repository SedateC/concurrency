package chaper13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleThreadPool {
    private final int size;

    private final int QUEUE_SIZE;

    private final static int DEFAULT_SIZE = 10;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private  final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private  volatile static int seq = 0; //队列s

    private static final ThreadGroup poolGroup = new ThreadGroup("poolGroup");

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();
    private boolean IS_DESTROY = false;
    private final DiscardPolicy discardPolicy;
    private final static DiscardPolicy default_discard_policy = new DiscardPolicy() {
        @Override
        public void discard() throws DiscardException {
                throw new DiscardException("Discard This Task");
        }
    };

    public SimpleThreadPool() {
        this(DEFAULT_SIZE,DEFAULT_TASK_QUEUE_SIZE,default_discard_policy);
    }

    public SimpleThreadPool(int size ,int DEFAULT_TASK_QUEUE_SIZE,DiscardPolicy discardPolicy) {
        this.size = size;
        this.QUEUE_SIZE = DEFAULT_TASK_QUEUE_SIZE;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0;i < size; i++){
            CreateWorkTask(); //创建默认个数的线程
        }
    }

    public  void submit(Runnable runnable){
        if (IS_DESTROY()){
            throw  new IllegalStateException("The thread pool already destroy and not allow submit it");
        }
        synchronized (TASK_QUEUE){
            if (TASK_QUEUE.size() > QUEUE_SIZE){
                discardPolicy.discard();;
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void CreateWorkTask(){
        WorkerTask task = new WorkerTask(poolGroup,THREAD_PREFIX+(seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private enum  TaskState{
        FREE,RUNNING,BOLOCKED,DEAD
    }
    public static class DiscardException extends RuntimeException{
        public DiscardException(String message) {
            super(message);
        }
    }

    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()){
                Thread.sleep(50);
        }
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0 ){
            for (WorkerTask task : THREAD_QUEUE){
                if (task.getTaskState() == TaskState.BOLOCKED){
                    task.interrupt();
                    task.closed();
                    initVal--;
                }else {
                    Thread.sleep(10);
                }
            }
        }
        System.out.println("the thread pool disposed");
        this.IS_DESTROY = true;
    }

    public int getSize() {
        return size;
    }

    public int getQUEUE_SIZE() {
        return QUEUE_SIZE;
    }

    public boolean IS_DESTROY() {
        return IS_DESTROY;
    }

    public interface  DiscardPolicy{
        void discard () throws  DiscardException;
    }

    private static class  WorkerTask extends Thread{
        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            outer:
            while (this.taskState != taskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BOLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                        //    e.printStackTrace();
                            break outer;
                        }
                    }
                    runnable= TASK_QUEUE.removeFirst();
                  /*
                    if (runnable != null){
                        taskState = TaskState.RUNNING;
                        runnable.run(); /很长时间 必须放锁外面 否则占用 TASK_QUEUE 锁很长时间
                        taskState = TaskState.FREE;
                    }*/
                }
                if (runnable != null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public TaskState getTaskState(){
            return taskState;
        }
        public void closed() {
            taskState = TaskState.DEAD;
        }
        public static void main(String[] args) throws InterruptedException {
            SimpleThreadPool simpleThreadPool =  new SimpleThreadPool();
            IntStream.rangeClosed(0,50).forEach(i->{
                simpleThreadPool.submit(()->{
                    System.out.println("The runnable "+i+"be serviced by"+ currentThread().getName());
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The runnable "+i+"be serviced by"+ currentThread().getName()+"finished");

                });
            });
            Thread.sleep(5_000);
            simpleThreadPool.shutdown();
            simpleThreadPool.submit(()->{
                System.out.println("The runnable "+"be serviced by"+ currentThread().getName()+"finished");
            });
        }

    }
}
