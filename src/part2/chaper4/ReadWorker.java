package part2.chaper4;

public class ReadWorker extends Thread {
    private final ShareData data ;

    public ReadWorker( ShareData data) {
       this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() + " reads "
                        +String.valueOf(readBuf));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
