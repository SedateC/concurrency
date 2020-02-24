package part1.chaper10;

public class SyncroizedProblem {
    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(){
           @Override
           public void run() {
               try {
                   SyncroizedProblem.run();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
       t1.start();
       Thread.sleep(1000);
       Thread t2 = new Thread(){
           @Override
           public void run() {
               try {
                   SyncroizedProblem.run();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
       t2.start();
       /*
       * 这里如果 t1 执行 则永远不会结束 API 方法能打断 但是while 循环 打断无效
        * 解决方案自定义锁打断
       * */
    }
    private synchronized static void run() throws InterruptedException{
        while (true){

        }
    }
}
