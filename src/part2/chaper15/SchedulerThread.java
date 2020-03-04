package part2.chaper15;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/4
 **/
public class SchedulerThread extends Thread {
    private final ActivationQueue activationQueue ;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request){
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true){//定期从队列中拿任务执行
            activationQueue.take().execute();
        }
    }
}
