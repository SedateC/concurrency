package part2.chaper15;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/4
 **/
public class ActiveObjectFactory {

    private ActiveObjectFactory(){

    }

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(activationQueue);
        ActiveObjectProxy activeObjectProxy = new ActiveObjectProxy(schedulerThread,servant);
        schedulerThread.start();
        return activeObjectProxy;
    }
}
