package part1.chaper5;

public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long startTimeStamp = System.currentTimeMillis();
        Thread t1  = new Thread(new CaptureRunable("M1",10000l));
        Thread t2  = new Thread(new CaptureRunable("M2",15000l));
        Thread t3  = new Thread(new CaptureRunable("M3",5000l));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long endTimeStamp = System.currentTimeMillis();
        System.out.println("all finish done startTime "+startTimeStamp+"endtime "+endTimeStamp);
    }
}

class CaptureRunable implements Runnable{
    private String machineName;
    private long speed;

    public CaptureRunable(String machineName, long speed) {
        this.machineName = machineName;
        this.speed = speed;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(speed);
            System.out.println(machineName+"speed finish done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getResult(){
        return machineName+" finish done.";
    }
}