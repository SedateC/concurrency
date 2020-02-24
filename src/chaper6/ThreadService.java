package chaper6;

public class ThreadService {
    private Thread excuteThread;
    private boolean finished = false;
    public void execute(Runnable task){
        excuteThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                System.out.println("守护线程开启");//守护线程作用是finish状态监测 为ShowDown服务
                runner.start();
                try {
                    runner.join();
                    finished = true;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        excuteThread.start();
    }

    public void shutdown (long mills){
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if(System.currentTimeMillis() - currentTime >= mills){
                System.out.println("任务超时 执行打断");
                excuteThread.interrupt();
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("showDown 执行线程被打断");
                e.printStackTrace();
            }
        }
    }
}
