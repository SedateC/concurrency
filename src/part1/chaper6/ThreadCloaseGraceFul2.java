package part1.chaper6;

public class ThreadCloaseGraceFul2 {

    private static class Worker extends Thread{
        private volatile boolean start  = true;

        @Override
        public void run() {
            while (isInterrupted()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break; //return
                }
            }
        }
        public void shoudown(){
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        worker.interrupt();
    }
}
