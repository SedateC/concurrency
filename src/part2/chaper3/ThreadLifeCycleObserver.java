package part2.chaper3;

import java.util.List;

public class ThreadLifeCycleObserver implements LifeCycleListener{
    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids){
        if (ids == null || ids.isEmpty()) return;
        ids.stream().forEach(id-> new Thread( new ObservableRunnable(this){
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                    System.out.println("query for the id"+id);
                    Thread.sleep(1_000);
                    //int i = 1/0;
                    notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
                }catch (Exception e){
                    notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));
                }
            }
        }).start());
    }
    /*
       把自己传入runnable中
      自己的Listener 获得观察出来的方法结果
     *
     * */
    @Override
    public void onEvent(ObservableRunnable.RunnableEvent runnableEvent) {
        synchronized (LOCK){
            System.out.println(runnableEvent.getThread()+"-success-"+runnableEvent.getState());
            if (runnableEvent.getCause() != null){
                System.out.println(runnableEvent.getThread()+"-fail--"+runnableEvent.getState()+"");
                runnableEvent.getCause().printStackTrace();
            }
        }

    }
}
