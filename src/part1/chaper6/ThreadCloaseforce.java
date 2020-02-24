package part1.chaper6;

public class ThreadCloaseforce {
    public static ThreadService threadService = new ThreadService();
    private  static class Worker implements Runnable{
        private volatile boolean flag  = true;
        @Override
        public void run() {
            //load a very heavy resource
             //connect 可以假设永久循环
            System.out.println("执行run 方法");
                try {
                    Thread.sleep(5000);  //假设5S 搞定
                    shouDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        public void shouDown(){
            this.flag = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        long startTime = System.currentTimeMillis();
        threadService.execute(worker);
        threadService.shutdown(10000);//假设10s时间结束
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
