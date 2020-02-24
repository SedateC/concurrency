package chaper6;

public class ThreadCloaseGraceFul {

    private static class Worker extends Thread{
        private volatile boolean start  = true;

        @Override
        public void run() {
            while (start){
                //
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
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        worker.shoudown();
    }
}
